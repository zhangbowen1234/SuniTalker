package com.example.bowen.sunitalker.frags.main;



import com.example.bowen.sunitalker.R;
import com.example.common.comm.app.Fragment;
import com.example.common.comm.widget.GalleryView;

import butterknife.BindView;


public class ActiveFragment extends Fragment {
    @BindView(R.id.galleryView)
    GalleryView mGalley;

    public ActiveFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initData() {
        super.initData();
        mGalley.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }
}
