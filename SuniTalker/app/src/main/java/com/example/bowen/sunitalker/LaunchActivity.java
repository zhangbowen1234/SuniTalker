package com.example.bowen.sunitalker;


import com.example.bowen.sunitalker.activities.MainActivity;
import com.example.bowen.sunitalker.frags.assist.PermissionsFragment;
import com.example.common.comm.app.Activity;

public class LaunchActivity extends Activity {

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            MainActivity.show(this);
            finish();
        }

    }
}
