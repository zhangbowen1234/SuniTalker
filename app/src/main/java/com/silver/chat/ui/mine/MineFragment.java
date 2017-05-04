package com.silver.chat.ui.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.silver.chat.R;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.view.RoundImageView;
import com.silver.chat.view.WhewView;

/**
 * 作者：hibon on 2016/11/24
 */

public class MineFragment extends BasePagerFragment implements View.OnClickListener{

    private TextView mPrCode,tv_file,tv_dynamic;
    private WhewView whewView;


    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_mine;
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        mPrCode = (TextView) view.findViewById(R.id.tv_qr_code);
        tv_file = (TextView) view.findViewById(R.id.tv_file);
        tv_dynamic = (TextView) view.findViewById(R.id.tv_dynamic);
        whewView = (WhewView) view.findViewById(R.id.wv);
        // 执行动画
//        whewView.start();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    protected void getData() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        mPrCode.setOnClickListener(this);
        tv_file.setOnClickListener(this);
        tv_dynamic.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_qr_code:
                startActivity(MyPRCodeActivity.class);
                break;
            case R.id.tv_file:

                break;
            case R.id.tv_dynamic:

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (whewView.isStarting()){
            //如果动画正在运行就停止，否则就继续执行
            whewView.stop();
        }
    }
}
