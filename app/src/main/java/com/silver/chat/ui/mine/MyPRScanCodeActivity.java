package com.silver.chat.ui.mine;

import android.view.View;
import android.widget.ImageView;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;


/**
 * 扫一扫
 */

public class MyPRScanCodeActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 相册
     */
    private ImageView ivAlbum;
    /**
     * 手电
     */
    private ImageView ivFlashlight;
    /**
     * 返回
     */
    private ImageView ivBack;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_pr_code_scan;
    }

    @Override
    protected void initView() {
        super.initView();
        ivAlbum = (ImageView) findViewById(R.id.iv_album);
        ivFlashlight = (ImageView) findViewById(R.id.iv_flashlight);
        ivBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        super.initListener();
        ivAlbum.setOnClickListener(this);
        ivFlashlight.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_album:
                break;
            case R.id.iv_flashlight:
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
