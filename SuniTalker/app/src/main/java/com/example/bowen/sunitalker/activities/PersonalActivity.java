package com.example.bowen.sunitalker.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bowen.sunitalker.R;
import com.example.common.comm.app.ToolbarActivity;
import com.example.common.comm.widget.PortraitView;

import net.qiujuer.genius.res.Resource;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends ToolbarActivity {
    private static final String BOUND_KEY_ID = "BOUND_KEY_ID";
    private String userId;

    @BindView(R.id.im_header)
    ImageView mHeader;
    @BindView(R.id.im_portrait)
    PortraitView mPortrait;
    @BindView(R.id.txt_name)
    TextView mName;
    @BindView(R.id.txt_desc)
    TextView mDesc;
    @BindView(R.id.txt_follows)
    TextView mFollows;
    @BindView(R.id.txt_following)
    TextView mFollowing;
    @BindView(R.id.btn_say_hello)
    Button mSayHello;

    // 关注
    private MenuItem mFollowItem;
    private boolean mIsFollower = false;

    public static void show(Context context, String userId) {
        Intent intent = new Intent(context, PersonalActivity.class);
        intent.putExtra(BOUND_KEY_ID, userId);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        userId = bundle.getString(BOUND_KEY_ID);
        return !TextUtils.isEmpty(userId);
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.personal, menu);
        mFollowItem = menu.findItem(R.id.action_follow);
        changeFollowItemStatus();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_follow) {
            // 进行关注操作
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_say_hello)
    void onSayHelloClick() {
        // TODO
        //MessageActivity.show(this, null);
    }

    /**
     * 更改关注菜单的状态
     */
    private void changeFollowItemStatus(){
        if (mFollowItem == null)
            return;

        // 根据状态设置颜色
        Drawable drawable = mIsFollower?getResources()
                .getDrawable(R.drawable.ic_favorite):
                getResources().getDrawable(R.drawable.ic_favorite_border);
        drawable = DrawableCompat.wrap(drawable); // 进行一次封装
        DrawableCompat.setTint(drawable, Resource.Color.WHITE); // 上色
        mFollowItem.setIcon(drawable);

    }
}
