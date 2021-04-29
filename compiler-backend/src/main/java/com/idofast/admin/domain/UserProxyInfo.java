package com.idofast.admin.domain;

import com.idofast.common.enums.DeletedEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "deleted=0")
public class UserProxyInfo
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition="bigint")
    private Long id;

    @Column(updatable = false ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间'",insertable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '更新时间'",insertable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;

    /**
     * 用户等级; 默认0
     */
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
    private LocalDateTime nextSettleDate;

    /**
     * 过期时间
     */
    private LocalDateTime expireDate;

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
     * 账号命名空间
     */
    private Integer namespace;


    /**
     * 账号总付费了多少天
     */
    private Integer totalActiveDay;

    /**
     * 账号是否被删除
     */
    private DeletedEnum deleted;


}
