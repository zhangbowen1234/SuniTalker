package com.example.bowen.sunitalker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.common.Common;
import com.example.common.app.Activity;

import butterknife.BindView;

public class MainActivity extends Activity {

    @BindView(R.id.text)
    TextView mTestText;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mTestText.setText("Test  Hello.");

    }
}
