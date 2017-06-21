package com.silver.chat;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silver.chat.adapter.MainPagerAdapter;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.chat.SearchChatRecordActivity;
import com.silver.chat.ui.contact.AddFriendActivity;
import com.silver.chat.ui.contact.SearchContactActivity;
import com.silver.chat.ui.contact.group.CreatGroupActivity;
import com.silver.chat.ui.contact.group.FindGroupActivity;
import com.silver.chat.ui.mine.MyPRCodeActivity;
import com.silver.chat.ui.mine.SettingActivity;
import com.silver.chat.util.ScreenManager;
import com.silver.chat.util.ToastUtils;
import com.silver.chat.view.BadgedTabCustomView;
import com.silver.chat.view.TabLayoutPlus;
import com.silver.chat.view.dialog.TvLoginOutDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_toolbar_search)
    ImageView ivToolbarSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayoutPlus tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.tv_scan)
    TextView tvScan;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_start_chat)
    TextView tvStartChat;
    @BindView(R.id.tv_unread)
    TextView tvUnread;
    @BindView(R.id.tv_read)
    TextView tvRead;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lly_more)
    RelativeLayout llyMore;
    @BindView(R.id.tv_scan_chat)
    TextView tvScanChat;
    @BindView(R.id.tv_search_chat)
    TextView tvSearchChat;
    @BindView(R.id.start_group_chat)
    TextView tvStartGroupChat;
    @BindView(R.id.tv_add_friend)
    TextView tvAddFriend;
    @BindView(R.id.tv_search_group)
    TextView tvSearchGroup;
    @BindView(R.id.tv_more_chat)
    TextView tvMoreChat;
    @BindView(R.id.iv_back_chat)
    ImageView ivBackChat;
    @BindView(R.id.lly_chat_more)
    RelativeLayout llyChatMore;


    private MainPagerAdapter mPagerAdapter;
    private Animation mButtonInAnimation;
    private Animation mButtonOutAnimation;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        mButtonInAnimation = AnimationUtils.loadAnimation(this, R.anim.button_in);
        mButtonOutAnimation = AnimationUtils.loadAnimation(this, R.anim.button_out);
        setSupportActionBar(toolbar);
        toolbar.setTitle("chat");

    }

    @Override
    protected void initData() {
        super.initData();
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewpager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            BadgedTabCustomView customView = tabLayout.getTabCustomViewAt(i);
            if (customView != null) {
                customView.setTabCount(true, i);
            }
        }
        tabLayout.getTabCustomViewAt(1).setTabCount(true, 0);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mButtonOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llyMore.setVisibility(View.GONE);
                llyChatMore.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //更多弹窗点击
    @OnClick({R.id.iv_toolbar_search, R.id.iv_more, R.id.tv_scan, R.id.tv_search, R.id.tv_start_chat, R.id.tv_unread, R.id.tv_read, R.id.tv_clear, R.id.iv_back, R.id.lly_more, R.id.tv_scan_chat, R.id.tv_search_chat, R.id.start_group_chat, R.id.tv_add_friend, R.id.tv_search_group, R.id.tv_more_chat, R.id.iv_back_chat, R.id.lly_chat_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_toolbar_search:
                if (tabLayout.getSelectedTabPosition() == 0) {
                    startActivity(SearchChatRecordActivity.class);
                }
                if (tabLayout.getSelectedTabPosition() == 1) {
                    startActivity(SearchContactActivity.class);
                }
                break;
            case R.id.iv_more:
                if (tabLayout.getSelectedTabPosition() == 2) {
                    startActivity(SettingActivity.class);
                } else {
                    openPanelView();
                }
                break;
            case R.id.iv_back:
                closePanelView();
                break;
            case R.id.iv_back_chat:
                closePanelView();
                break;
            case R.id.tv_scan:
                Intent goRegIntent = new Intent(this, MyPRCodeActivity.class);
                ScreenManager.getScreenManager().StartPage(this, goRegIntent, true);
                break;
            case R.id.tv_search:
                startActivity(SearchChatRecordActivity.class);
                closePanelView();
                break;
            case R.id.tv_start_chat:
                ToastUtils.showMessage(mContext, "正在修改中...");
                break;
            case R.id.tv_unread:
                ToastUtils.showMessage(mContext, "正在修改中...");
                break;
            case R.id.tv_read:
                ToastUtils.showMessage(mContext, "正在修改中...");
                break;
            case R.id.tv_clear:
                deleteSession();
                closePanelView();
                break;
            case R.id.tv_scan_chat:
                Intent intent = new Intent(this, MyPRCodeActivity.class);
                ScreenManager.getScreenManager().StartPage(this, intent, true);
                break;
            case R.id.tv_search_chat:
                startActivity(SearchContactActivity.class);
                closePanelView();
                break;
            case R.id.tv_add_friend:
                closePanelView();
                startActivity(AddFriendActivity.class);
                break;
            case R.id.tv_search_group:
                closePanelView();
                startActivity(FindGroupActivity.class);
                break;
            case R.id.start_group_chat:
                closePanelView();
                startActivity(CreatGroupActivity.class);
                break;
            case R.id.tv_more_chat:
                ToastUtils.showMessage(mContext, "正在修改中...");
                break;
        }
    }

    //弹出更多
    private void openPanelView() {
        if (tabLayout.getSelectedTabPosition() == 0) {
            llyMore.setVisibility(View.VISIBLE);
            tvScan.startAnimation(mButtonInAnimation);
            tvSearch.startAnimation(mButtonInAnimation);
            tvStartChat.startAnimation(mButtonInAnimation);
            tvRead.startAnimation(mButtonInAnimation);
            tvUnread.startAnimation(mButtonInAnimation);
            tvClear.startAnimation(mButtonInAnimation);
        } else if (tabLayout.getSelectedTabPosition() == 1) {
            llyChatMore.setVisibility(View.VISIBLE);
            tvScanChat.startAnimation(mButtonInAnimation);
            tvSearchChat.startAnimation(mButtonInAnimation);
            tvStartGroupChat.startAnimation(mButtonInAnimation);
            tvAddFriend.startAnimation(mButtonInAnimation);
            tvSearchGroup.startAnimation(mButtonInAnimation);
            tvMoreChat.startAnimation(mButtonInAnimation);
        }
    }
    //收起更多
    private void closePanelView() {
        if (tabLayout.getSelectedTabPosition() == 0) {
            tvScan.startAnimation(mButtonOutAnimation);
            tvSearch.startAnimation(mButtonOutAnimation);
            tvStartChat.startAnimation(mButtonOutAnimation);
            tvRead.startAnimation(mButtonOutAnimation);
            tvUnread.startAnimation(mButtonOutAnimation);
            tvClear.startAnimation(mButtonOutAnimation);
        } else if (tabLayout.getSelectedTabPosition() == 1) {
            tvScanChat.startAnimation(mButtonOutAnimation);
            tvSearchChat.startAnimation(mButtonOutAnimation);
            tvStartGroupChat.startAnimation(mButtonOutAnimation);
            tvAddFriend.startAnimation(mButtonOutAnimation);
            tvSearchGroup.startAnimation(mButtonOutAnimation);
            tvMoreChat.startAnimation(mButtonOutAnimation);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }
    /**
     * 清空会话列表
     */
    private void deleteSession(){
        new TvLoginOutDialog(this).builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setTitle("是否清空会话列表")
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if (sessionList.size() != 0){
//                            for (int i = 0; i < sessionList.size(); i++) {
//                                AppContext.getInstance().instance.delSessionById(sourceId);
//                            }
//                        }
                    }
                }).show();

    }
}
