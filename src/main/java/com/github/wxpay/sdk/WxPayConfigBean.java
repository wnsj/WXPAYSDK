package com.github.wxpay.sdk;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @desc:微信配置类
 * @date: 2020-04-01 14:59
 * @author: dx
 * @version: 1.0
 */
public class WxPayConfigBean extends WXPayConfig {
    //公众账号ID
    private String appId;

    //商户号
    private String mchId;

    //商户支付密钥
    private String key;

    //证书路径
    private String certPath;

    private byte[] certData;

    //通知回调地址
    private String notifyUrl;

    private IWXPayDomain.DomainInfo domainInfo;

    public WxPayConfigBean(){
    }

    public void setCertPath(String certPath) throws Exception {
        this.certPath = certPath;
        if (certPath != null) {
            File file = new File(this.certPath);
            InputStream certStream = new FileInputStream(file);
            this.certData = new byte[(int) file.length()];
            certStream.read(this.certData);
            certStream.close();
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertPath() {
        return certPath;
    }

    public IWXPayDomain.DomainInfo getDomainInfo() {
        return domainInfo;
    }

    public byte[] getCertData() {
        return certData;
    }

    public void setCertData(byte[] certData) {
        this.certData = certData;
    }

    public void setDomainInfo(IWXPayDomain.DomainInfo domainInfo) {
        this.domainInfo = domainInfo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String getAppID() {
        return appId;
    }

    @Override
    public String getMchID() {
        return mchId;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public InputStream getCertStream() {
        try {
            File file = new File(this.certPath);
            return new FileInputStream(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {

            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                if (domainInfo == null) domainInfo = new DomainInfo("api.mch.weixin.qq.com", true);
                return domainInfo;
            }
        };
    }
}
