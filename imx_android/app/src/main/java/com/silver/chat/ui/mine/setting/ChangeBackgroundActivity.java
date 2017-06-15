package com.silver.chat.ui.mine.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.ChooseBackgroudUtils;
import com.silver.chat.util.PreferenceUtil;
import com.silver.chat.util.SkinSettingManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

import static com.silver.chat.util.Utils.context;

/**
 * Created by bowen on 2017/4/28.
 */

public class ChangeBackgroundActivity extends BaseActivity {
    @BindView(R.id.black_choose)
    ImageView blackChoose;
    @BindView(R.id.black_theme)
    LinearLayout blackTheme;
    @BindView(R.id.blue_choose)
    ImageView blueChoose;
    @BindView(R.id.blue_theme)
    LinearLayout blueTheme;
    @BindView(R.id.green_choose)
    ImageView greenChoose;
    @BindView(R.id.green_theme)
    LinearLayout greenTheme;
    @BindView(R.id.red_choose)
    ImageView redChoose;
    @BindView(R.id.red_theme)
    LinearLayout redTheme;
    @BindView(R.id.purple_choose)
    ImageView purpleChoose;
    @BindView(R.id.purple_theme)
    LinearLayout purpleTheme;
    @BindView(R.id.preservation_theme)
    ImageView preservationTheme;
    @BindView(R.id.ll_common_bg_view)
    LinearLayout iv_common_bg;
    private SkinSettingManager mSettingManager;

    @Override
    protected int getLayoutId() {
        return R.layout.acyivity_change_background;
    }

    @Override
    protected void initView() {
        super.initView();
        mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
    }

    @OnClick({R.id.black_theme, R.id.blue_theme, R.id.green_theme, R.id.red_theme, R.id.purple_theme,R.id.preservation_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.black_theme:
                iv_common_bg.setBackgroundResource(R.drawable.black_theme);
                blackChoose.setVisibility(View.VISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);

                break;
            case R.id.blue_theme:
                iv_common_bg.setBackgroundResource(R.drawable.blue_theme);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.VISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);

                break;

            case R.id.green_theme:
                iv_common_bg.setBackgroundResource(R.drawable.green_theme);
                greenChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);

                break;

            case R.id.red_theme:
                iv_common_bg.setBackgroundResource(R.drawable.red_theme);
                redChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);

                break;

            case R.id.purple_theme:
                iv_common_bg.setBackgroundResource(R.drawable.purple_theme);
                purpleChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);

                break;
            case R.id.preservation_theme:
                if (view.getId() == R.id.purple_theme){
                    mSettingManager.toggleSkins(0);
                }else if (view.getId() == R.id.black_theme){
                    mSettingManager.toggleSkins(1);
                }else if (view.getId() == R.id.blue_theme){
                    mSettingManager.toggleSkins(2);
                }else if (view.getId() == R.id.green_theme){
                    mSettingManager.toggleSkins(3);
                }else if (view.getId() == R.id.red_theme){
                    mSettingManager.toggleSkins(4);
                }
                finish();
                break;
        }
    }
}
