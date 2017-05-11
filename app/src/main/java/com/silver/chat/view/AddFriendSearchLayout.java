package com.silver.chat.view;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.silver.chat.R;

/**
 *  Created by hibon on 2017/5/10.
 * 自定义搜索布局
 */

public class AddFriendSearchLayout extends LinearLayout {

    private Context mContext;
    private EditText mEtContent;
    private ImageView mIvSearch;
    private OnSearchClickListener mOnSearchClickListener;

    public AddFriendSearchLayout(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public AddFriendSearchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public AddFriendSearchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public void setOnSearchClickListener(OnSearchClickListener onSearchClickListener) {
        mOnSearchClickListener = onSearchClickListener;
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_search, null);
        mEtContent = (EditText) view.findViewById(R.id.et_search_content);
        mIvSearch = (ImageView) view.findViewById(R.id.iv_search);
        mEtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mOnSearchClickListener != null) {
                        mOnSearchClickListener.doSearch();
                    }
                }
                return false;
            }
        });
        LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        addView(view);
    }

    /**
     * EditText文字监听
     *
     * @param textWatcher
     */
    public void addTextChangedListener(TextWatcher textWatcher) {
        mEtContent.addTextChangedListener(textWatcher);
    }

    /**
     * 填充搜索内容
     *
     * @param content
     */
    public void setSearchContent(String content) {
        if (content != null) {
            mEtContent.setText(content);
        }
    }

    public String getContent() {
        return mEtContent.getText().toString();
    }

    /**
     * 搜索按钮点击
     *
     * @param onClickListener
     */
    public void setSearchOnClickListener(OnClickListener onClickListener) {
        mIvSearch.setOnClickListener(onClickListener);
    }

    public interface OnSearchClickListener {
        void doSearch();
    }
}
