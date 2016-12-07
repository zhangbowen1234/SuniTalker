package com.silver.chatsdk.config;

/**
 * Created by ArthurYis on 2016/12/6.
 */

public class SSIMNetworkConfig {
    private String host;
    private String port;


    SSIMNetworkConfig(String host, String port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String formatURL(){
        return String .format("%s:%s",this.host,this.port);
    }
}
