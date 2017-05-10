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
    @BindView(R.id.ll_change_background)
    LinearLayout llChangeBackground;

    @Override
    protected int getLayoutId() {
        return R.layout.acyivity_change_background;
    }


    @OnClick({R.id.black_theme, R.id.blue_theme, R.id.green_theme, R.id.red_theme, R.id.purple_theme,R.id.preservation_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.black_theme:
                BaseActivity.getBackgroundView().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.black_theme));
                blackChoose.setVisibility(View.VISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);
                break;
            case R.id.blue_theme:
                BaseActivity.getBackgroundView().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.blue_theme));
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.VISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);

                break;

            case R.id.green_theme:
                BaseActivity.getBackgroundView().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.green_theme));
                greenChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);
                break;

            case R.id.red_theme:
                BaseActivity.getBackgroundView().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.red_theme));
                redChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                purpleChoose.setVisibility(View.INVISIBLE);
                break;

            case R.id.purple_theme:
                BaseActivity.getBackgroundView().setBackgroundDrawable(this.getResources().getDrawable(R.drawable.purple_theme));
                purpleChoose.setVisibility(View.VISIBLE);
                blackChoose.setVisibility(View.INVISIBLE);
                blueChoose.setVisibility(View.INVISIBLE);
                greenChoose.setVisibility(View.INVISIBLE);
                redChoose.setVisibility(View.INVISIBLE);
                break;
            case R.id.preservation_theme:
                if (view.getId() == R.id.purple_theme){
                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.ChooseBackground, 0 + "");
                }else if (view.getId() == R.id.black_theme){
                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.ChooseBackground, 1 + "");
                }else if (view.getId() == R.id.blue_theme){
                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.ChooseBackground, 2 + "");
                }else if (view.getId() == R.id.green_theme){
                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.ChooseBackground, 3 + "");
                }else if (view.getId() == R.id.red_theme){
                    PreferenceUtil.getInstance(mContext).setString(PreferenceUtil.ChooseBackground, 4 + "");
                }
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String ChooseBackgroud = PreferenceUtil.getInstance(this).getString(PreferenceUtil.ChooseBackground, "");
        PreferenceUtil.getInstance(context).getString("ChooseBackgroud", ChooseBackgroud);
        ChooseBackgroudUtils.choosebackgroud(mContext, ChooseBackgroud);
    }
}
