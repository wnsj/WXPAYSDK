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

    private IWXPayDomain.DomainInfo domainInfo;

    public WxPayConfigBean() throws Exception {

    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setKey(String key) {
        this.key = key;
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

    public byte[] getCertData() {
        return certData;
    }

    public void setCertData(byte[] certData) {
        this.certData = certData;
    }

    public void setDomainInfo(IWXPayDomain.DomainInfo domainInfo) {
        this.domainInfo = domainInfo;
    }

    @Override
    String getAppID() {
        return appId;
    }

    @Override
    String getMchID() {
        return mchId;
    }

    @Override
    String getKey() {
        return key;
    }

    @Override
    InputStream getCertStream() {
        try {
            File file = new File(this.certPath);
            return new FileInputStream(file);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
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
