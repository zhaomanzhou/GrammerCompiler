package com.idofast.admin.controller.vo.request;


import com.idofast.admin.domain.User;
import com.idofast.common.enums.DeletedEnum;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.enums.UserStatusEnum;
import com.idofast.common.util.MD5Util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("用户注册的vo对象")
public class RegisterUserVo
{
    @NotNull(message = "邮箱不能为空")
    @Email(message = "非法邮箱名")
    @ApiModelProperty("邮箱")
    private String email;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户名")
    private String ncikname;

    @NotNull(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String vcode;

    @ApiModelProperty("邀请吗")
    private String inviteCode;

    public User convertToUserDo()
    {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setOsDevice(0);
        user.setRole(RoleEnum.PLAIN);
        user.setStatus(UserStatusEnum.NORMAL);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        user.setDeleted(DeletedEnum.NORMAL);
        return user;
    }
}
