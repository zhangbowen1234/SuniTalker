package com.silver.chat.ui.mine.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.silver.chat.R;
import com.silver.chat.base.BaseActivity;

/**
 * Created by bowen on 2017/4/28.
 */

public class ChangeBackgroundActivity extends BaseActivity {
    Button black_theme,blue_theme,green_theme,red_theme,purple_theme;

    @Override
    protected void initView() {
        super.initView();
        black_theme = (Button) findViewById(R.id.black_theme);
        blue_theme = (Button) findViewById(R.id.blue_theme);
        green_theme = (Button) findViewById(R.id.green_theme);
        red_theme = (Button) findViewById(R.id.red_theme);
        purple_theme = (Button) findViewById(R.id.purple_theme);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.acyivity_change_background;
    }

    private void onClick(View view){
        switch (view.getId()){
            case R.id.black_theme:

                break;
            case R.id.blue_theme:

                break;

            case R.id.green_theme:

                break;

            case R.id.red_theme:

                break;

            case R.id.purple_theme:

                break;

        }

    }
}
