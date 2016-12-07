package com.silver.chatsdk.config;

/**
 * HTTP/HTTPS config 对象
 */
public class SSIMHttpConfig extends SSIMNetworkConfig {
    //常量
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "8080";
    /**
     * 是否为https请求
     * true = 请求为https://
     * false = 请求为http://
     */
    private boolean isHttps;
    /**
     * 如果是https请求，是否需要双向验证
     * true=需要
     * false=不需要
     */
    private boolean mutualAuth;

    public boolean isHttps() {
        return isHttps;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public boolean isMutualAuth() {
        return mutualAuth;
    }

    public void setMutualAuth(boolean mutualAuth) {
        this.mutualAuth = mutualAuth;
    }

    /**
     * 使用默认参数 初始化对象
     */
    public SSIMHttpConfig() {
        super(HOST, PORT);
    }

    /**
     * 初始化
     *
     * @param host       IP地址／域名
     * @param port       端口号
     * @param isHttps    是否https请求
     * @param mutualAuth 是否双向验证
     */
    public SSIMHttpConfig(String host, String port, boolean isHttps, boolean mutualAuth) {
        super(host, port);
        this.isHttps = isHttps;
        this.mutualAuth = mutualAuth;
    }

    @Override
    public String formatURL() {
        String baseUrl = super.formatURL();
        return String.format("%s://%s", isHttps ? "https" : "http", baseUrl);
    }
}
