package cn.edu.nwafu.admin.domain;

import cn.edu.nwafu.admin.domain.enumconvert.UserStatusConvert;
import com.idofast.common.enums.DeletedEnum;
import com.idofast.common.enums.RoleEnum;
import com.idofast.common.enums.UserStatusEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class User implements Serializable
{


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition="bigint")
    private Long id;

    @Column(updatable = false ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间'",insertable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    private String nickname;

    @Column(columnDefinition = "",unique = true, nullable = false)
    private String email;

    private String avatarUrl;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Convert(converter = RoleEnum.Converter.class)
    private RoleEnum role;


    //用户状态，0正常，1，封禁
    @Column(nullable = false, columnDefinition = "tinyint default 1")
    @Convert(converter = UserStatusConvert.class)
    private UserStatusEnum status;

    //备注
    private String remark;



    private String ext;


    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '更新时间'",insertable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;




    @Convert(converter = DeletedEnum.Converter.class)
    private DeletedEnum deleted;

}
