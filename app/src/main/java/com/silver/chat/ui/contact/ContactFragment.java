package com.silver.chat.ui.contact;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.silver.chat.R;
import com.silver.chat.adapter.SortGroupMemberAdapter;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.entity.GroupMemberBean;
import com.silver.chat.util.CharacterParser;
import com.silver.chat.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：Fandy on 2016/11/14 14:14
 * 邮箱：fandy618@hotmail.com
 */

/*
 *  联系人
 */

public class ContactFragment extends BasePagerFragment {

    private ListView mRecycleContent;


    private SortGroupMemberAdapter adapter;
    private List<GroupMemberBean> SourceDateList;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecycleContent = (ListView) view.findViewById(R.id.recyle_content);
//        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));


        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();

        SourceDateList = filledData(getResources().getStringArray(R.array.date));


    }


    /**
     * 为ListView填充数据
     *
     * @param
     * @return
     */
    private List<GroupMemberBean> filledData(String[] date) {
        List<GroupMemberBean> mSortList = new ArrayList<GroupMemberBean>();

        for (int i = 0; i < date.length; i++) {
            GroupMemberBean sortModel = new GroupMemberBean();
            sortModel.setName(date[i]);
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
            } else {
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }


        return mSortList;
    }


    @Override
    protected void initData() {
        super.initData();

        // 根据a-z进行排序源数据
        Collections.sort(SourceDateList, pinyinComparator);
        adapter = new SortGroupMemberAdapter(mActivity, SourceDateList);
        mRecycleContent.setAdapter(adapter);

    }


    @Override
    protected void getData() {

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_contact;
    }
}
