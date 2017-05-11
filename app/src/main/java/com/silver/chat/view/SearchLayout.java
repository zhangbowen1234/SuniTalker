package com.silver.chat.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.silver.chat.R;

/**
 * 作者：Fandy on 2016/11/23 14:17
 * 邮箱：fandy618@hotmail.com
 * <p>
 * tabbar搜索
 */

public class SearchLayout extends LinearLayout implements View.OnClickListener, TextWatcher {

    private Context mContext;
    private ImageView mIvBack;
    private ImageView mIvClear;
    private EditText mEtSearch;

    private OnSearchClickListener mOnSearchClickListener;

    public SearchLayout(Context context) {
        super(context);
        init();
        mContext = context;
    }

    public SearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public SearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void setHint(int resId) {
        String hint = mContext.getResources().getString(resId);
        mEtSearch.setHint(hint);
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_edittext, null);
        mIvBack = (ImageView) view.findViewById(R.id.iv_back);
        mIvClear = (ImageView) view.findViewById(R.id.iv_clear);
        mEtSearch = (EditText) view.findViewById(R.id.et_search);
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        addView(view);
        mIvBack.setOnClickListener(this);
        mIvClear.setOnClickListener(this);

        updateEt();
        mEtSearch.addTextChangedListener(this);
    }

    /**
     * 更新edittext文本
     */
    private void updateEt() {
        if (mEtSearch.getText().length() != 0) {
            mIvClear.setVisibility(VISIBLE);
        } else {
            mIvClear.setVisibility(GONE);
        }
    }

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        mOnSearchClickListener = onSearchClickListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                if (mOnSearchClickListener != null)
                    mOnSearchClickListener.onBack();
                break;
            case R.id.iv_clear:
                if (mEtSearch.getText().length() != 0) {
                    mEtSearch.setText("");
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        updateEt();
    }

    public interface OnSearchClickListener {
        void onBack();
    }
}