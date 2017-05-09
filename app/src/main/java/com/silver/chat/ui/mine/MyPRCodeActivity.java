package com.silver.chat.ui.mine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;
import com.silver.chat.qrcode.core.BGAQRCodeUtil;
import com.silver.chat.qrcode.zxing.QRCodeEncoder;

/**
 * 作者：Fandy on 2016/12/8 14:54
 * 邮箱：fandy618@hotmail.com
 * <p>
 * 我的二维码
 */

public class MyPRCodeActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 1;
    /**
     * 二维码
     */
    private ImageView mIvCode;
    /**
     * 扫描
     */
    private ImageView mIvScan;
    /**
     * 返回
     */
    private ImageView mIvBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_pr_code;
    }

    @Override
    protected void initView() {
        super.initView();
        mIvCode = (ImageView) findViewById(R.id.iv_code);
        mIvScan = (ImageView) findViewById(R.id.iv_scan);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mIvScan.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        new CodeAsyncTask().execute();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                /*Intent intent = new Intent(this,ScanActivity.class);
                startActivity(intent);*/
                //startActivity(MyPRScanCodeActivity.class);
//                startActivity(MyPRScanCodeActivity.class);
                Intent intent = new Intent(this,ScanActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK &&requestCode == REQUEST_CODE) {
            data.getStringArrayExtra("result");
        }

    }

    class CodeAsyncTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            //二维码中间头像
//            Bitmap logoBitmap = BitmapFactory.decodeResource(MyPRCodeActivity.this.getResources(), R.mipmap.ic_launcher);     //, Color.WHITE, logoBitmap
            return QRCodeEncoder.syncEncodeQRCode("IM", BGAQRCodeUtil.dp2px(MyPRCodeActivity.this, 244), Color.WHITE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                mIvCode.setImageBitmap(bitmap);
            } else {
                Toast.makeText(MyPRCodeActivity.this, "生成二维码失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
