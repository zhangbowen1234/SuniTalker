package com.silver.chat.ui.mine.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

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
//                PreferenceUtil.getInstance(mContext).setLog(true);
                finish();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
