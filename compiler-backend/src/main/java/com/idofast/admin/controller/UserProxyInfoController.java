package com.idofast.admin.controller;

import com.idofast.admin.annotation.AuthRole;
import com.idofast.admin.config.context.RequestContext;
import com.idofast.admin.controller.vo.response.UserInformationVo;
import com.idofast.admin.domain.User;
import com.idofast.admin.domain.UserProxyInfo;
import com.idofast.admin.domain.dto.UserInfoLite;
import com.idofast.admin.service.UserProxyInfoService;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.response.ServerResponse;
import com.idofast.common.response.error.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2020/12/31 12:32 上午
 */
@RestController
@RequestMapping("/users")
@Api(tags = "用户代理信息相关的API")
@CrossOrigin
@Slf4j
@Validated
public class UserProxyInfoController
{

    @Autowired
    private UserProxyInfoService userProxyInfoService;

    @GetMapping("/detail/{id}")
    @ApiOperation("根据id获取用户的系统信息")
    @AuthRole(RoleEnum.ADMIN)
    public ServerResponse<UserInformationVo> getUserInformation(@PathVariable Long id) throws BusinessException
    {
        UserProxyInfo information = userProxyInfoService.selectById(id, false);
        return ServerResponse.success(UserInformationVo.convertFrom(information));
    }

    @GetMapping("/detail/")
    @ApiOperation("获取自己的系统信息")
    public ServerResponse<UserInformationVo> getSelfInformation() throws BusinessException
    {
        User currentUser = RequestContext.getCurrentUser();
        UserProxyInfo information = userProxyInfoService.selectById(currentUser.getId(), true);
        return ServerResponse.success(UserInformationVo.convertFrom(information));
    }

    @GetMapping("/proxyinfo/list")
    @ApiOperation("获取用户列表")
    public ServerResponse<Object> getUserProxyInfoList()
    {
        Page<UserInfoLite> userList = userProxyInfoService.getUserList(null);
        return ServerResponse.success(userList);
    }





}
