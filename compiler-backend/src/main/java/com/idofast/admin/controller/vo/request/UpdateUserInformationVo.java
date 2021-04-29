package com.idofast.admin.controller.vo.request;

import com.idofast.admin.domain.UserProxyInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.ZoneOffset;


@Data
@ApiModel("用户系统信息返回的vo对象；流量，到期时间等")
public class UpdateUserInformationVo
{

    @ApiModelProperty("用户的id")
    private Long id;

    @ApiModelProperty("用户等级")
    private Integer level;

    @ApiModelProperty("限速值，单位kb/s")
    private Integer speed;

    @ApiModelProperty("用户总流量")
    private Integer totalData;


    @ApiModelProperty("下一个结算日期；时间戳格式")
    private Long nextSettleDate;

    @ApiModelProperty("账号过期时间；时间戳格式")
    private Long expireDate;


    @ApiModelProperty("账号用的套餐Id")
    private Integer packageId;

    @ApiModelProperty("账号用的套餐名")
    private String packageName;

    @ApiModelProperty("账号是否有过付费记录")
    private Boolean recharged;


    @ApiModelProperty("账号命名空间")
    private Integer namespace;

    @ApiModelProperty("账号是否被封禁")
    private Boolean disable;

    public static UpdateUserInformationVo convertFrom(UserProxyInfo userProxyInfo){
        UpdateUserInformationVo vo = new UpdateUserInformationVo();
        BeanUtils.copyProperties(userProxyInfo, vo);
        vo.setNextSettleDate(userProxyInfo.getNextSettleDate().toEpochSecond(ZoneOffset.UTC));
        vo.setExpireDate(userProxyInfo.getExpireDate().toEpochSecond(ZoneOffset.UTC));
        return vo;
    }


}
