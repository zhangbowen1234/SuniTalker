package com.silver.chatsdk.config;


public class SSIMConfig {

    private SSIMHttpConfig httpConfig;
    private SSIMSocketConfig socketConfig;

    public SSIMHttpConfig getHttpConfig() {
        return httpConfig;
    }

    public void setHttpConfig(SSIMHttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

    public SSIMSocketConfig getSocketConfig() {
        return socketConfig;
    }

    public void setSocketConfig(SSIMSocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }

    public SSIMConfig(){
        this.httpConfig = new SSIMHttpConfig();
        this.socketConfig = new SSIMSocketConfig();
    }

    public SSIMConfig(SSIMHttpConfig httpConfig, SSIMSocketConfig socketConfig) {
        this.httpConfig = httpConfig;
        this.socketConfig = socketConfig;
    }

    //    private SSIMConfig(){}
//
//    private static class SingletonHolder{
//        private static final SSIMConfig INSTANCE = new SSIMConfig();
//    }
//    /**
//     *
//     */
//    public static SSIMConfig getInstance(){
//        SSIMConfig config = SingletonHolder.INSTANCE;
//        if (config.httpConfig == null && config.socketConfig ==null){
//            return null;
//        }
//        return config;
//    }
//
//    /**
//     *
//     */
//    public static  SSIMConfig getInstance(SSIMNetworkConfig httpConfig, SSIMNetworkConfig socketConfig){
//        SSIMConfig config = SingletonHolder.INSTANCE;
//        if (config.httpConfig == null && config.socketConfig ==null){
//            config.init(httpConfig,socketConfig);
//        }
//        return config;
//    }
//
//    private void init(SSIMNetworkConfig httpConfig, SSIMNetworkConfig socketConfig){
//        this.httpConfig = httpConfig;
//        this.socketConfig = socketConfig;
//    }
}
