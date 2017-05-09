//package com.silver.chat.ui.contact;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.AlphaAnimation;
//import android.widget.LinearLayout;
//
//import com.silver.chat.R;
//import com.silver.chat.adapter.ContactListAdapter;
//import com.silver.chat.base.BasePagerFragment;
//import com.silver.chat.base.Common;
//import com.silver.chat.entity.ContactMemberBean;
//import com.silver.chat.network.SSIMUserManger;
//import com.silver.chat.network.callback.ResponseCallBack;
//import com.silver.chat.network.responsebean.BaseResponse;
//import com.silver.chat.network.responsebean.ContactListBean;
//import com.silver.chat.ui.mine.FriendInfoActivity;
//import com.silver.chat.util.CharacterParser;
//import com.silver.chat.util.PinyinComparator;
//import com.silver.chat.util.PreferenceUtil;
//import com.silver.chat.util.ToastUtils;
//import com.silver.chat.view.recycleview.BaseQuickAdapter;
//import com.silver.chat.view.recycleview.listenner.OnItemClickListener;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 作者：hibon on 2016/11/16 14:14
// */
//
///*
// *  联系人
// */
//
//public class ContactFragment2 extends BasePagerFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
//    /**
//     * 静止没有滚动
//     */
//    public static final int SCROLL_STATE_IDLE = 0;
//    /**
//     * 正在被外部拖拽,一般为用户正在用手指滚动
//     */
//    public static final int SCROLL_STATE_DRAGGING = 1;
//    /**
//     * 自动滚动
//     */
//    public static final int SCROLL_STATE_SETTLING = 2;
//    private static final String ISALLCONTACT = "isAllContact";
//
//    private LinearLayout mNewFriend, mGroupChat;
//    private RecyclerView mRecycleContent, mHorizontalRecycleContent;
//    private ContactListAdapter contactAdapter;
//    private LinearLayout mContactTopMuenu;
//    private SwipeRefreshLayout mRefreshLayout;
//
//    private List<ContactMemberBean> SourceDateList;
//    private ContactListAdapter contactListAdapter, oftenContactListAdapter;
//
//    /**
//     * 汉字转换成拼音的类
//     */
//    private CharacterParser characterParser;
//    /**
//     * 根据拼音来排列ListView里面的数据类
//     */
//    private PinyinComparator pinyinComparator;
//    /**
//     * 是否获取全部联系人
//     */
//    private boolean isAllContact;
//
//    public static ContactFragment2 newInstance(boolean isAllContact) {
//        Bundle args = new Bundle();
//        ContactFragment2 fragment = new ContactFragment2();
//        args.putBoolean(ISALLCONTACT, isAllContact);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    protected void initView(View view) {
//        super.initView(view);
//        mRecycleContent = (RecyclerView) view.findViewById(R.id.recyle_content);
////        mHorizontalRecycleContent = (RecyclerView) view.findViewById(R.id.horizontal_recyle_content);
////        mNewFriend = (LinearLayout) view.findViewById(R.id.new_friend_btn);
////        mGroupChat = (LinearLayout) view.findViewById(R.id.group_chat_btn);
////        mContactTopMuenu = (LinearLayout) view.findViewById(R.id.Horizontal_contact_layout);
////        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
//
//        //设置布局管理器
//        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));
//        //设置布局管理器及指定RecyclerView方向
////        mHorizontalRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL, false));
//        // 实例化汉字转拼音类
//        characterParser = CharacterParser.getInstance();
//        pinyinComparator = new PinyinComparator();
//        //初始化联系人数据
//        SourceDateList = filledData(getResources().getStringArray(R.array.date));
//
//        //控件显示的动画
//        mShowAnim = new AlphaAnimation(0.0f, 1.0f);
//        mShowAnim.setDuration(300);
//
//        //控件隐藏的动画
//        mHiddenAmin = new AlphaAnimation(1.0f, 0.0f);
//        mHiddenAmin.setDuration(300);
//    }
//
//
//    /**
//     * 为ListView填充数据
//     *
//     * @return
//     * @params
//     */
//    private List<ContactMemberBean> filledData(String[] date) {
//        List<ContactMemberBean> mSortList = new ArrayList<ContactMemberBean>();
//        for (int i = 0; i < date.length; i++) {
//            ContactMemberBean sortModel = new ContactMemberBean();
//            sortModel.setContactName(date[i]);
//            // 汉字转换成拼音
//            String pinyin = characterParser.getSelling(date[i]);
//            String sortString = pinyin.substring(0, 1).toUpperCase();
//            // 正则表达式，判断首字母是否是英文字母
//            if (sortString.matches("[A-Z]")) {
//                sortModel.setSortLetters(sortString.toUpperCase());
//            } else {
//                sortModel.setSortLetters("#");
//            }
//            mSortList.add(sortModel);
//        }
//        return mSortList;
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        // 根据a-z进行排序源数据
//        Collections.sort(SourceDateList, pinyinComparator);
//        //联系人列表的adapter
////        contactListAdapter = new ContactListAdapter(R.layout.item_contact_list, SourceDateList);
////        mRecycleContent.setAdapter(contactListAdapter);
//        //常用联系人列表的adapter
////        oftenContactListAdapter = new ContactListAdapter(R.layout.item_horizontal_contact_list, SourceDateList);
////        mHorizontalRecycleContent.setAdapter(oftenContactListAdapter);
//
////        if (isAllContact && contactListAdapter.getData().isEmpty()) {
//        //请求所有文件目录数据
//        getContactList();
//
////        } else if (!isAllContact &&contactListAdapter.getData().isEmpty()) {
////            //优先从数据库中读取数据
//////            QueryDbParent();
////
////        }
//    }
//
//    /**
//     * 联网获取联系人列表
//     */
//    public void getContactList() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SSIMUserManger.contactList(PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.TOKEN, ""), Common.version, PreferenceUtil.getInstance(mActivity).getString(PreferenceUtil.USERID, ""),
//                        "0", "10", new ResponseCallBack<BaseResponse<List<ContactListBean>>>() {
//
//
//                            @Override
//                            public void onSuccess(BaseResponse<List<ContactListBean>> listBaseResponse) {
//                                ToastUtils.showMessage(mActivity,listBaseResponse.getStatusMsg());
//                                Log.e("ContactList",listBaseResponse.data+"");
//                            }
//
//                            @Override
//                            public void onFailed(BaseResponse<List<ContactListBean>> listBaseResponse) {
//                                ToastUtils.showMessage(mActivity,listBaseResponse.getStatusMsg());
//                                Log.e("ContactList",listBaseResponse.data+"");
//                            }
//
//
//                            @Override
//                            public void onError() {
//                                ToastUtils.showMessage(mActivity, "获取失败");
//                            }
//                        });
//            }
//        }).start();
//
//
//    }
//
//
//    @Override
//    protected void initListener() {
//        super.initListener();
////        mNewFriend.setOnClickListener(this);
////        mGroupChat.setOnClickListener(this);
//        /**
//         * 联系人点击
//         */
//        mRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ContactMemberBean contactMemberBean = (ContactMemberBean) adapter.getItem(position);
//                Intent mIntent = new Intent(mActivity, FriendInfoActivity.class);
//                mIntent.putExtra("contactName", contactMemberBean.getContactName());
//                startActivity(mIntent);
//
//            }
//        });
//        /**
//         * 常用联系人点击
//         */
////        mHorizontalRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
////            @Override
////            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
////                ContactMemberBean contactMemberBean = (ContactMemberBean) adapter.getItem(position);
////                ToastUtil.toastMessage(mActivity, contactMemberBean.getContactName());
//
////            }
////        });
//
//        /**
//         * 滑动监听
//         */
////        mRecycleContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
////            @Override
////            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
////                super.onScrolled(recyclerView, dx, dy);
////
////            }
////
////            @Override
////            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
////                super.onScrollStateChanged(recyclerView, newState);
////                Log.i("onScrollStateChanged", "newState: " + newState);
////                if (newState == SCROLL_STATE_IDLE) {
//////                    mContactTopMuenu.setVisibility(View.GONE);
////                }
////            }
////        });
////        mRecycleContent.addOnScrollListener(new MyRecyclerViewScrollListener());
//        /**
//         * 刷新页面监听
//         */
////        mRefreshLayout.setOnRefreshListener(this);
//
//    }
//
//    private AlphaAnimation mShowAnim, mHiddenAmin;//控件的显示和隐藏动画
//
//    //滑动监听
//    private class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
//        @Override
//        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//            super.onScrollStateChanged(recyclerView, newState);
//            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//            int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();//获取最后一个完全显示的ItemPosition
//            // 当不滚动时
//            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                // 判断是否滚动到顶部
//                if (lastVisibleItem == 0) {
//                    mContactTopMuenu.startAnimation(mShowAnim);
//                    mContactTopMuenu.setVisibility(View.VISIBLE);
//                }
//            } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
//                if (mContactTopMuenu.getVisibility() == View.VISIBLE) {
//                    mContactTopMuenu.startAnimation(mHiddenAmin);
//                    mContactTopMuenu.setVisibility(View.GONE);//注意此处不要使用View.GONE
//                }
//            }
//        }
//    }
//
//
//    public void onClick(View view) {
//        switch (view.getId()) {
////            case R.id.new_friend_btn:
////                startActivity(NewFriendActivity.class);
////                break;
////            case R.id.group_chat_btn:
////                startActivity(GroupChatActivity.class);
////                break;
//        }
//    }
//
//    @Override
//    protected void getData() {
//    }
//
//    @Override
//    protected int getLayoutResourceId() {
//        return R.layout.fragment_contact;
//    }
//
//    @Override
//    public void onRefresh() {
//        Log.d("点击刷新了", "=============");
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        contactListAdapter.notifyDataSetChanged();
//        oftenContactListAdapter.notifyDataSetChanged();
//    }
//
//
//}
