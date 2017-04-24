package com.silver.chat.ui.contact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.silver.chat.R;
import com.silver.chat.adapter.ContactListAdapter;
import com.silver.chat.base.BasePagerFragment;
import com.silver.chat.entity.ContactMemberBean;
import com.silver.chat.entity.User;
import com.silver.chat.util.CharacterParser;
import com.silver.chat.util.PinyinComparator;
import com.silver.chat.util.ToastUtil;
import com.silver.chat.view.recycleview.BaseQuickAdapter;
import com.silver.chat.view.recycleview.listenner.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 作者：zhenghp on 2016/11/16 14:14
 */

/*
 *  联系人
 */

public class ContactFragment extends BasePagerFragment  {

    private RecyclerView mRecycleContent, mHorizontalRecycleContent;
    private ContactListAdapter contactAdapter;

    private List<ContactMemberBean> SourceDateList;

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
        mRecycleContent = (RecyclerView) view.findViewById(R.id.recyle_content);
        mHorizontalRecycleContent = (RecyclerView) view.findViewById(R.id.horizontal_recyle_content);


        //设置布局管理器
        mRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity));
        //设置布局管理器及指定RecyclerView方向
        mHorizontalRecycleContent.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));


        // 实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        //初始化联系人数据
        SourceDateList = filledData(getResources().getStringArray(R.array.date));


    }


    /**
     * 为ListView填充数据
     *
     * @return
     * @params
     */
    private List<ContactMemberBean> filledData(String[] date) {
        List<ContactMemberBean> mSortList = new ArrayList<ContactMemberBean>();
        for (int i = 0; i < date.length; i++) {
            ContactMemberBean sortModel = new ContactMemberBean();
            sortModel.setContactName(date[i]);
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

        //联系人列表的adapter
        mRecycleContent.setAdapter(new ContactListAdapter(R.layout.item_contact_list,SourceDateList));
        //常用联系人列表的adapter
        mHorizontalRecycleContent.setAdapter(new ContactListAdapter(R.layout.item_horizontal_contact_list,SourceDateList));

    }

    User user;
    @Override
    protected void initListener() {
        super.initListener();

        mRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("ContactFragment.class", "position=" + position);

                ContactMemberBean contactMemberBean = (ContactMemberBean) adapter.getItem(position);




                Intent mIntent = new Intent(mActivity,ContactChatActivity.class);
                mIntent.putExtra("contactName",contactMemberBean.getContactName());
                startActivity(mIntent);


            }
        });


        mHorizontalRecycleContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d("ContactFragment.class", "position=" + position);
                ContactMemberBean contactMemberBean = (ContactMemberBean) adapter.getItem(position);
                ToastUtil.toastMessage(mActivity, contactMemberBean.getContactName());

            }
        });

    }



    @Override
    protected void getData() {
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_contact;
    }

}
