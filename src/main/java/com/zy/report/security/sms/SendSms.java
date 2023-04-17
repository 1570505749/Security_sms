package com.zy.report.security.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zy.report.commons.enums.ResultEnum;
import com.zy.report.security.handles.UserDefinedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: report
 * @description: 短信发送类
 * @author: nile
 * @create: 2020-11-01 11:39
 **/
@Component
public class SendSms {

    private static final Logger logger = LoggerFactory.getLogger(SendSms.class);

    /**
     * 短信模板ID
     */
    private static final String TEMPLATE_CODE = "SMS_190266443";

    /**
     * 短信签名名称
     */
    private static final String SIGN_NAME = "clms";

    /**
     * RAM账号的AccessKeyID
     */
    private static final String ACCESS_KEY_ID = "*********************";

    /**
     * RAM账号AccessKeySecret
     */
    private static final String SECRET = ""*********************";";

    /**
     * 创建CommonRequest、设置短信发送参数。
     * @param mobile 手机号
     * @param code 验证码
     * @return CommonRequest实例
     */
    public static CommonRequest sendInit(String mobile, String code) {

        // 初始化ascClient需要的几个参数 -- 短信API产品名称（短信产品名固定，无需修改）
        final String product = "Dysmsapi";

        // 短信API产品域名（接口地址固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";

        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 创建API请求并设置参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //接收短信的手机号码。
        request.putQueryParameter("PhoneNumbers", mobile);
        //短信签名名称
        request.putQueryParameter("SignName", SIGN_NAME);
        //短信模板ID
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
        //短信模板变量对应的实际值，JSON格式
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");

        return request;
    }

    /**
     * 发送短信验证码
     * @param mobile 用户手机号
     * @param code 验证码
     */
    public static void sendSms(String mobile, String code){
        // 创建DefaultAcsClient实例并初始化  regionId ->地域ID
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",ACCESS_KEY_ID,SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        try {
            // 发起请求并处理应答或异常
            CommonResponse response = client.getCommonResponse(sendInit(mobile,code));
            if(!"OK".equals(JSON.parseObject(response.getData()).get("Code").toString())){
                logger.error("Send sms response: {}",response.getData());
                throw new UserDefinedException(ResultEnum.PARAMS_ERROR,JSON.parseObject(response.getData()).get("Message").toString());
            }
            logger.info("Send sms response: {}",response.getData());
        } catch (ClientException e) {
            logger.error("Send sms response: {}",e.getMessage());
            throw new UserDefinedException(ResultEnum.UNAVAILABLE);
        }
    }

}
