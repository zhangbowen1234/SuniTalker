package com.silver.chat.ui.contact;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;
import com.silver.chat.R;
import com.silver.chat.adapter.ContactListAdapter;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.base.Common;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.network.SSIMFrendManger;
import com.silver.chat.network.callback.ResponseCallBack;
import com.silver.chat.network.responsebean.BaseResponse;
import com.silver.chat.network.responsebean.ContactListBean;
import com.silver.chat.util.CharacterParser;
import com.silver.chat.util.PinyinComparator;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.UIUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：hibon on 2016/11/16 14:14
 * 联系人
 */

public class ContactFragment extends BasePagerFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    /**
     * 静止没有滚动
     */
    public static final int SCROLL_STATE_IDLE = 0;
    /**
     * 正在被外部拖拽,一般为用户正在用手指滚动
     */
    public static final int SCROLL_STATE_DRAGGING = 1;
    /**
     * 自动滚动
     */
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String ISALLCONTACT = "isAllContact";

    private LinearLayout mNewFriend, mGroupChat;
    private RecyclerView mRecycleContent, mHorizontalRecycleContent;
    private ContactListAdapter contactAdapter;
//    private LinearLayout mContactTopMuenu;
    private SwipeRefreshLayout mRefreshLayout;
    private RelativeLayout mToolbar;

    private List<ContactMemberBean> SourceDateList;
    private ContactListAdapter contactListAdapter, oftenContactListAdapter;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    /**
     * 是否获取全部联系人
     */
    private boolean isAllContact;
    private GestureDetector mGestureDetector;
    LinearLayoutManager linearLayoutManager;
    FloatingActionButton fab;

    public static ContactFragment newInstance(boolean isAllContact) {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        args.putBoolean(ISALLCONTACT, isAllContact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecycleContent = (RecyclerView) view.findViewById(R.id.recyle_content);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.hide();

        linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        mRecycleContent.setLayoutManager(linearLayoutManager);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        //初始化联系人数据
        SourceDateList = filledData(getResources().getStringArray(R.array.date));


        fab.attachToRecyclerView(mRecycleContent, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                fab.hide();
            }

            @Override
            public void onScrollUp() {
                fab.show();
            }
        }, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = mRecycleContent.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = linearManager.findFirstVisibleItemPosition();
                    if (firstVisibleItemPosition > 5) {
                        fab.show();
                    } else {
                        fab.hide();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

    }


    /**
     * 为ListView填充数据
     *
     * @return
     * @params
     */
    private List<ContactMemberBean> filledData(String[] date) {
        List<ContactMemberBean> mSortList = new ArrayList<ContactMemberBean>();
        for (int i = 0; i < date.length; i++) {
            ContactMemberBean sortModel = new ContactMemberBean();
            sortModel.setContactName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }

    @Override
    protected void initData() {
        super.initData();
        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        //联系人列表的adapter
        contactListAdapter = new ContactListAdapter(mActivity, SourceDateList);
        mRecycleContent.setAdapter(contactListAdapter);

//        if (isAllContact && contactListAdapter.getData().isEmpty()) {
        //请求所有文件目录数据
        getContactList();

//        } else if (!isAllContact &&contactListAdapter.getData().isEmpty()) {
//            //优先从数据库中读取数据
////            QueryDbParent();
//
//        }
    }

    /**
     * 联网获取联系人列表
     */
    public void getContactList() {
        String token = PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.TOKEN, "");
        String userId = PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID, "");
        Log.e("aaa",token+"==="+userId);
        SSIMFrendManger.contactList(Common.version, userId ,"0", "1000", token ,new ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>>() {

                            @Override
                            public void onSuccess(BaseResponse<ArrayList<ContactListBean>> listBaseResponse) {
                                ToastUtils.showMessage(mActivity, listBaseResponse.getStatusMsg());
                                Log.e("ContactList,onSuccess", listBaseResponse.data + "");
                            }

                            @Override
                            public void onFailed(BaseResponse<ArrayList<ContactListBean>> listBaseResponse) {
                                ToastUtils.showMessage(mActivity, listBaseResponse.getStatusMsg());
                                Log.e("ContactList_onFailed", listBaseResponse.toString());
                            }

                            @Override
                            public void onError() {
                                ToastUtils.showMessage(mActivity, "获取失败");
                            }
                        });

    }

    int firstposition;

    @Override
    protected void initListener() {
        super.initListener();
        /**
         * 刷新页面监听
         */
//        mRefreshLayout.setOnRefreshListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIUtils.MoveToPosition(linearLayoutManager,0);
                UIUtils.MoveToPosition(new LinearLayoutManager(mActivity), mRecycleContent, 0);
                fab.hide();

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void onRefresh() {
        Log.d("点击刷新了", "=============");
    }

    @Override
    public void onPause() {
        super.onPause();
//        contactListAdapter.notifyDataSetChanged();
//        oftenContactListAdapter.notifyDataSetChanged();
    }


    @Override
    protected void getData() {
    }
}
