package com.idofast.admin.domain.dto;

import com.idofast.admin.domain.User;
import com.idofast.admin.domain.UserProxyInfo;
import com.idofast.admin.util.LocalDateTimeUtil;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.enums.UserStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/20 7:20 下午
 */
@Data
public class UserInfoLite
{

    public UserInfoLite(User u, UserProxyInfo userProxyInfo)
    {
        BeanUtils.copyProperties(u, this);
        BeanUtils.copyProperties(userProxyInfo, this);
        createTime = LocalDateTimeUtil.toTimeStamp(u.getCreateTime());
        nextSettleDate = LocalDateTimeUtil.toTimeStamp(userProxyInfo.getNextSettleDate());
        expireDate = LocalDateTimeUtil.toTimeStamp(userProxyInfo.getExpireDate());

    }


    private Long id;

    private Long createTime;


    private String email;

    private String avatarUrl;

    private RoleEnum role;

    //用户状态，0正常，1，封禁
    private UserStatusEnum status;

    //备注
    private String remark;

    //使用的设备
    private Integer osDevice;

    private String ext;



    private Integer level;


    /**
     * 限速,单位kb
     */
    private Integer speed;

    /**
     *总流量,单位MB
     */
    private Integer totalData;

    /**
     * 已用流量
     */
    private Integer usedData;

    /**
     * 下一个结算日期
     */
    private Long nextSettleDate;

    /**
     * 过期时间
     */
    private Long expireDate;

    /**
     * 最大连接数
     */
    private Integer maxConnection;

    /**
     * 目前使用的套餐ID
     */
    private Integer packageId;

    /**
     * 目前使用的套餐名
     */
    private String packageName;

    /**
     * vip总时间
     */
    private Integer totalActiveDay;

    /**
     * 账号命名空间
     */
    private Integer namespace;




}
