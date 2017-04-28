package com.silver.chatsdk.config;


/**
 * SOCKET config 对象
 */
public class SSIMSocketConfig extends SSIMNetworkConfig {
    //常量
    private static final String HOST = "http://pan.sspaas.com/sspaas-cloud/";
    private static final String PORT = "";

    public SSIMSocketConfig(String host, String port) {
        super(host, port);
    }

    public SSIMSocketConfig() {
        super(HOST , PORT);
    }

    @Override
    public String formatURL() {
        return super.formatURL();
    }
}
