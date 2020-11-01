package com.zy.report.modules.user.controller;

import com.zy.report.commons.utils.Result;
import com.zy.report.modules.user.service.LoginService;
import com.zy.report.modules.user.service.SysUserService;
import com.zy.report.modules.user.vo.SysMenuVo;
import com.zy.report.modules.user.service.UserMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: web_template
 * @description: 前台菜单控制类
 * @author: nile
 * @create: 2020-09-30 18:08
 **/
@RestController
public class UserController {

    @Resource
    private UserMenuService userMenuService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private LoginService loginService;

    /**
     * 根据RoleId获取菜单list
     * @return 菜单list
     */
    @GetMapping("/getRoleMenu")
    public Result<List<SysMenuVo>> getRoleMenu(){
        List<SysMenuVo> sysMenuVos = userMenuService.getRoleMenu();
        return new Result<>(sysMenuVos);
    }

    /**
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result<Map<String,Object>> getUserInfo(){
        return new Result<>(sysUserService.getUserInfo());
    }

    @GetMapping("/visitor/code/sms")
    public  Result<Object> createSmsCode(@RequestParam String mobile){
        loginService.sendSmsCode(mobile);
        return new Result<>("短信发送成功！");
    }

}
