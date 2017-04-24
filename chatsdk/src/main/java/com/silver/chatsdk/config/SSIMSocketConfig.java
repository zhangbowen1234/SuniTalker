package com.silver.chatsdk.config;


/**
 * SOCKET config 对象
 */
public class SSIMSocketConfig extends SSIMNetworkConfig {
    //常量
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "8080";

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
