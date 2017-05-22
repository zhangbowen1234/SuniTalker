package com.silver.chat.ui;

/**
 * Created by lenovo on 2017/5/18.
 */

public class SSIMConnectActivity {

    private static SSIMConnectActivity ssimConnectActivity;

    public static SSIMConnectActivity newInstance(){
        if (ssimConnectActivity == null){
            ssimConnectActivity = new SSIMConnectActivity();
        }
        return ssimConnectActivity;
    }


}
