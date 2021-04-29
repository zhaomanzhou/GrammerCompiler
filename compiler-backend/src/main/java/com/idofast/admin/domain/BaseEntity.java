package com.idofast.admin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public abstract class BaseEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition="bigint")
    protected Long id;

    @Column(updatable = false ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间'",insertable = false)
    @CreationTimestamp
    protected LocalDateTime createTime;



}
