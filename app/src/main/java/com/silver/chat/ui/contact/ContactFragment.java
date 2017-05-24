package com.silver.chat.ui.contact;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.silver.chat.database.callback.EasyRun;
import com.silver.chat.database.dao.BaseDao;
import com.silver.chat.database.helper.DBHelper;
import com.silver.chat.database.info.WhereInfo;
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
    private SwipeRefreshLayout mRefreshLayout;
    private RelativeLayout mToolbar;
    /**
     * 联系人集合
     */
    private List<ContactListBean> mContactList;
    /**
     * 排序后的联系人集合
     */
    private List<ContactListBean> mConList;
    private ContactListAdapter contactListAdapter;

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
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton fab;
    private BaseDao<ContactListBean> mDao;
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
        mContactList = new ArrayList<ContactListBean>();
        mConList = new ArrayList<ContactListBean>();
        linearLayoutManager = new LinearLayoutManager(mActivity);
        //设置布局管理器
        mRecycleContent.setLayoutManager(linearLayoutManager);
        String userId = PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID, "");
        mDao = DBHelper.get().dao(ContactListBean.class);
        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        //加载联系人列表
        mHandler.sendEmptyMessage(0);

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

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    // 根据a-z进行排序源数据
                    Collections.sort(mContactList, pinyinComparator);
                    //联系人列表的adapter
                    contactListAdapter = new ContactListAdapter(mActivity, mContactList);
                    mRecycleContent.setAdapter(contactListAdapter);
                    contactListAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void initData() {
        super.initData();
        /**
         * 联网获取联系人
         */
        //请求所有文件目录数据
        httpContactList();
        //优先从数据库中读取数据
//        QueryDbParent();
    }

    /**
     * 查询数据库所有联系人
     */
    private void QueryDbParent() {
        mDao.asyncTask(new EasyRun<List<ContactListBean>>() {
            @Override
            public List<ContactListBean> run() throws Exception {
                return getSortData();
            }

            @Override
            public void onMainThread(List<ContactListBean> data) throws Exception {
                if (data.isEmpty()) {
                    //其次从网络获取数据
                    httpContactList();
                } else {
                    mContactList = data;
                    mHandler.sendEmptyMessage(0);
                }
            }
        });

    }

    /**
     * 联网获取联系人
     */
    public void httpContactList() {
        String token = PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.TOKEN, "");
        String userId = PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID, "");
        SSIMFrendManger.contactList(getContext(),Common.version, userId, "0", "1000", token, new ResponseCallBack<BaseResponse<ArrayList<ContactListBean>>>() {
            @Override
            public void onSuccess(final BaseResponse<ArrayList<ContactListBean>> listBaseResponse) {
//                ToastUtils.showMessage(mActivity, listBaseResponse.getStatusMsg());
                ArrayList<ContactListBean> contactData = listBaseResponse.data;
                /**
                 * 填充其他数据
                 */
                for (int i = 0; i < contactData.size(); i++) {
                    ContactListBean sortModel = new ContactListBean();
                    sortModel.setNickName(contactData.get(i).getNickName());
                    sortModel.setUserId(PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID,""));
                    sortModel.setAvatar(contactData.get(i).getAvatar());
                    sortModel.setFriendId(contactData.get(i).getFriendId());
                    sortModel.setRemarkName(contactData.get(i).getRemarkName());
                    sortModel.setSex(contactData.get(i).getSex());
                    sortModel.setSignature(contactData.get(i).getSignature());
                    String pinyin = characterParser.getSelling(contactData.get(i).getNickName());
                    String sortString = pinyin.substring(0, 1).toUpperCase();
                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        sortModel.setSortLetters(sortString.toUpperCase());
                    } else {
                        sortModel.setSortLetters("#");
                    }
                    sortModel.setUserId(PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID, ""));
                    mConList.add(sortModel);
                }
                /**
                 *  数据库操作内容
                 */
                mDao.asyncTask(new EasyRun<List<ContactListBean>>() {
                    @Override
                    public List<ContactListBean> run() throws Exception {

                        List<ContactListBean> query = mDao.queryForAll();
                        //删除原始文件
                        mDao.delete(query);
                        //保存新数据
                        mDao.create(mConList);
                        Log.e("mDao.asTk_run", "===================");
                        return getSortData();
                    }

                    @Override
                    public void onMainThread(List<ContactListBean> data) throws Exception {
                        Log.e("mDao.asTk_onMainThread", "===================");
                        if (data.isEmpty()) {
                            ToastUtils.showMessage(mActivity, mActivity.getResources().getString(R.string.contactlist_null));
                        } else {
                            mContactList = data;
                            /**
                             * 通知显示联系人列表
                             */
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                });
            }

            @Override
            public void onFailed(BaseResponse<ArrayList<ContactListBean>> listBaseResponse) {
                ToastUtils.showMessage(mActivity, listBaseResponse.getStatusMsg());
                QueryDbParent();
            }

            @Override
            public void onError() {
                ToastUtils.showMessage(mActivity, "联网失败");
                QueryDbParent();
            }
        });
    }

    public List<ContactListBean> getSortData() {
        Log.e(" mDao.queryForAll():", mDao.queryForAll() + "");
        return mDao.query(WhereInfo.get().equal("userId",PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID,"")));
    }


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
                UIUtils.MoveToPosition(linearLayoutManager, 0);
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
        Log.d("ContactFragment_onPause", "=============");
        contactListAdapter.notifyDataSetChanged();
    }

    @Override
    protected void getData() {
    }
}
