package cn.edu.nwafu.admin.domain;

import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Problem
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(columnDefinition="bigint")
    private Long id;

    @Column(updatable = false ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间'",insertable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    /**
     * 题目标题
     */
    private String title;
    /**
     * 题目内容；markdown格式
     */
    @Column(columnDefinition="TEXT")
    private String contentMarkdown;
    /**
     * 公告内容；html格式
     */
    @Column(columnDefinition="TEXT")
    private String contentHtml;
    /**
     * 公告状态：上线；下线；草稿
     */
    @Convert(converter = NoticeStatusEnum.Converter.class)
    private NoticeStatusEnum status;
    /**
     * 公告可见范围；全部；注册用户
     */
    @Convert(converter = NoticeVisibilityEnum.Converter.class)
    private NoticeVisibilityEnum visibility;
    /**
     * 公告是否置顶
     */
    private Boolean stick;
    /**
     * 公告序列值，默认为发布时间，排序用
     * order： mysql关键字
     */
    private Long orderValue;

    /**
     * 发布人id
     */
    private Long publisherId;

    /**
     * 测试样例数目， 默认0
     */
    @Column(columnDefinition = "int DEFAULT 0")
    private Integer caseNum;


    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '更新时间'",insertable = false)
    @UpdateTimestamp
    private LocalDateTime updateTime;


}
