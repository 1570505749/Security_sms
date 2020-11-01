package com.zy.report.commons.enums;

import lombok.Getter;

/**
 * @FileName: ResultEnum
 * @Author: nile
 * @Date: 2020/6/17 20:43
 * Description: 返回结果枚举
 */
@Getter
public enum  ResultEnum {
    /**
     * 返回枚举类型，每一个枚举类型代表一个返回状态
     * 1** 信息，服务器收到请求，需要请求这继续执行操作
     * 2** 成功，操作被成功执行并接受处理
     * 3** 重定向，需要进一步的操作已完成请求
     * 4** 客户端错误，请求包汉语发错误或无法完成请求
     * 5** 服务器错误，服务在处理请求过程中发生了错误
     */
    GET_SUCCESS(200, "服务器成功返回请求的数据。"),
    POST_SUCCESS(201,"新建或修改数据成功。"),
    QUEUE_SUCCESS(202,"一个请求已经进入后台排队（异步任务）。"),
    DELETE_SUCCESS(204,"删除数据成功。"),
    ERROR(400, "发出的请求有错误，服务器没有进行新建或修改数据的操作"),
    NOT_AUTHORITY(401,"用户没有权限（令牌、用户名、密码错误）。"),
    PARAMS_ERROR(402,"参数错误！"),
    ACCESS_FORBIDDEN(403,"用户得到授权，但是访问是被禁止的。"),
    NOT_FOUND(404,"发出的请求针对的是不存在的记录，服务器没有进行操作"),
    METHOD_NOT_ALLOWED(405, "该请求方法不被允许。"),
    NOT_LOGIN(406,"请求的操作需认证。"),
    AUTHENTICATION_ERROR(407,"认证错误。"),
    NOT_RESOURCE(410, "请求的资源被永久删除，且不会再得到的。"),
    VALIDATION_ERROR (412,"当创建一个对象时，发生一个验证错误。"),
    INTERNAL_SERVER_ERROR(500,"服务器发生错误，请检查服务器。"),
    UNAUTHORIZED(501,"Token 已过期。"),
    GATEWAY_ERROR(502,"网关错误。"),
    UNAVAILABLE(503,"服务不可用，服务器暂时过载或维护。"),
    UNKNOWN_MISTAKE(503,"未知错误。"),

    ;

    /**
     * code 响应码
     * msg 响应信息
     */
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
