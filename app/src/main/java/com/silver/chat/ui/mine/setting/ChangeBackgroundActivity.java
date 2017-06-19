package com.silver.chat.ui.mine.setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.ui.mine.SettingActivity;
import com.silver.chat.util.SkinSettingManager;

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
    @BindView(R.id.ll_common_bg_view)
    LinearLayout iv_common_bg;
    private SkinSettingManager mSettingManager;
    private int isChoose = 0;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.acyivity_change_background;
    }

    @Override
    protected void initView() {
        super.initView();
        mSettingManager = new SkinSettingManager(this);
        mSettingManager.initSkins();
        final int skinType = mSettingManager.getSkinType();
        isChoose = skinType;
        if (skinType == 0){
            isChoosePurple();
        }else if (skinType == 1){
            isChooseBlack();
        }else if (skinType == 2){
            isChooseBlue();
        }else if (skinType == 3){
            isChooseGreen();
        }else if (skinType == 4){
            isChooseRed();
        }
    }

    @OnClick({R.id.black_theme, R.id.blue_theme, R.id.green_theme, R.id.red_theme, R.id.purple_theme,R.id.preservation_theme})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.purple_theme:
                isChoosePurple();
                isChoose = 0;
                break;
            case R.id.black_theme:
                isChooseBlack();
                isChoose = 1;
                break;
            case R.id.blue_theme:
                isChooseBlue();
                isChoose = 2;
                break;
            case R.id.green_theme:
                isChooseGreen();
                isChoose = 3;
                break;
            case R.id.red_theme:
                isChooseRed();
                isChoose = 4;
                break;
            case R.id.preservation_theme:
                if (isChoose == 0){
                    mSettingManager.toggleSkins(0);
                }else if (isChoose == 1){
                    mSettingManager.toggleSkins(1);
                }else if (isChoose == 2){
                    mSettingManager.toggleSkins(2);
                }else if (isChoose == 3){
                    mSettingManager.toggleSkins(3);
                }else if (isChoose == 4){
                    mSettingManager.toggleSkins(4);
                }
                startActivity(SettingActivity.class);
                finish();
                break;
        }
    }
    //选择背景
    private void isChoosePurple(){
        iv_common_bg.setBackgroundResource(R.drawable.purple_theme);
        purpleChoose.setVisibility(View.VISIBLE);
        blackChoose.setVisibility(View.INVISIBLE);
        blueChoose.setVisibility(View.INVISIBLE);
        greenChoose.setVisibility(View.INVISIBLE);
        redChoose.setVisibility(View.INVISIBLE);
    }
    private void isChooseBlack(){
        iv_common_bg.setBackgroundResource(R.drawable.black_theme);
        blackChoose.setVisibility(View.VISIBLE);
        blueChoose.setVisibility(View.INVISIBLE);
        greenChoose.setVisibility(View.INVISIBLE);
        redChoose.setVisibility(View.INVISIBLE);
        purpleChoose.setVisibility(View.INVISIBLE);
    }
    private void isChooseBlue(){
        iv_common_bg.setBackgroundResource(R.drawable.blue_theme);
        blackChoose.setVisibility(View.INVISIBLE);
        blueChoose.setVisibility(View.VISIBLE);
        greenChoose.setVisibility(View.INVISIBLE);
        redChoose.setVisibility(View.INVISIBLE);
        purpleChoose.setVisibility(View.INVISIBLE);
    }
    private void isChooseGreen(){
        iv_common_bg.setBackgroundResource(R.drawable.green_theme);
        greenChoose.setVisibility(View.VISIBLE);
        blackChoose.setVisibility(View.INVISIBLE);
        blueChoose.setVisibility(View.INVISIBLE);
        redChoose.setVisibility(View.INVISIBLE);
        purpleChoose.setVisibility(View.INVISIBLE);
    }
    private void isChooseRed(){
        iv_common_bg.setBackgroundResource(R.drawable.red_theme);
        redChoose.setVisibility(View.VISIBLE);
        blackChoose.setVisibility(View.INVISIBLE);
        blueChoose.setVisibility(View.INVISIBLE);
        greenChoose.setVisibility(View.INVISIBLE);
        purpleChoose.setVisibility(View.INVISIBLE);
    }
}
