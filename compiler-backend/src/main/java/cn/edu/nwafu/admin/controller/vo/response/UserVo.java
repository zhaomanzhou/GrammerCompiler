package cn.edu.nwafu.admin.controller.vo.response;


import cn.edu.nwafu.admin.domain.User;
import com.idofast.common.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;


@Data
@ApiModel("用户VO对象")
public class UserVo
{

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名")
    private String nickname;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("头像地址")
    private String avatarUrl;

    @ApiModelProperty("角色; 管理员-0; 其他用户 > 0")
    private String role;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("使用的设备")
    private List<Integer> osDevice;

    private String ext;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    public static UserVo convertUserToVo(User user)
    {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRole(user.getRole().getMsg());
        //不是管理员，屏蔽掉一些数据
        if(user.getRole().getCode() > RoleEnum.ADMIN.getCode())
        {
            userVo.setAvatarUrl(null);
            userVo.setRemark(null);
            userVo.setExt(null);
            userVo.setOsDevice(null);
        }
        return userVo;
    }

}
