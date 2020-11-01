package com.zy.report.modules.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: web_template
 * @description: 菜单实体类
 * @author: nile
 * @create: 2020-09-12 20:04
 **/
@Data
public class SysMenu implements Serializable {

    static final long serialVersionUID = 1L;

    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private Integer isFrame;

    private char menuType;

    private char visible;

    private char status;

    private Long roleId;

    private String icon;

    /**
     * 创建时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间，返回时转成正常日期格式 年-月-日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private String remark;
}
