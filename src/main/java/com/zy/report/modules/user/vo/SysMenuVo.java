package com.zy.report.modules.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: web_template
 * @description: Vo
 * @author: nile
 * @create: 2020-09-30 19:15
 **/
@Data
public class SysMenuVo implements Serializable {

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由名称，建议唯一
     */
    private String name;

    /**
     * 该路由对应页面的 组件
     */
    private String component;

    private Mate mate;

    public SysMenuVo(){
        this.mate = new Mate();
    }


}

@Data
class Mate implements Serializable {

    private Boolean isLoading;

    private String title;

    private String icon;
}