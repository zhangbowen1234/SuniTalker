package com.example.bowen.sunitalker.frags.search;


import com.example.bowen.sunitalker.R;
import com.example.bowen.sunitalker.activities.SearchActivity;
import com.example.common.comm.app.Fragment;

/**
 * 搜索群的界面实现
 */
public class SearchGroupFragment extends Fragment
        implements SearchActivity.SearchFragment {


    public SearchGroupFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_search_user;
    }

    @Override
    public void search(String content) {

    }
}
