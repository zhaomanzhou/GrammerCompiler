package com.idofast.admin.repository;


import com.idofast.admin.domain.Notice;
import com.idofast.common.enums.NoticeStatusEnum;
import com.idofast.common.enums.NoticeTypeEnum;
import com.idofast.common.enums.NoticeVisibilityEnum;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>, JpaSpecificationExecutor<Notice>
{


    @Modifying
    @Transactional
    @Query("update Notice n set n.status = :status where n.id = :id")
    void updateStatusById(@Param("id") Long id, @Param("status") NoticeStatusEnum status);

    default Specification<Notice> exampleSpecification(Example<Notice> example){
        return new Specification<Notice>()
        {
            @Override
            public Predicate toPredicate(Root<Notice> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
            {
                if(example == null){
                    return null;
                }
                return QueryByExamplePredicateBuilder.getPredicate(root, criteriaBuilder, example);
            }
        };
    }
    default Specification<Notice> visibilityLessSpecification(NoticeVisibilityEnum noticeVisibilityEnum){
        return (Specification<Notice>) (root, query, criteriaBuilder) -> {
            if(noticeVisibilityEnum == null){
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("visibility"),noticeVisibilityEnum.getCode());
        };
    }
}

//
//class NoticeSpecs
//{
//    public Specification<Notice> getNoticeList(Integer noticeType, Integer noticeVisibility, Integer noticeStatus){
//        return (root, queryOrder, builder) -> {
//            final List<Predicate> predicates = new ArrayList<>();
//            if(noticeType != null)
//            {
//                predicates.add(builder.equal(root.get("noticeType"), noticeType));
//            }
//            if(noticeVisibility != null)
//            {
//                predicates.add(builder.lessThan(root.get("noticeVisibility"), noticeVisibility));
//            }
//            if(noticeStatus != null)
//            {
//                predicates.add(builder.equal(root.get("noticeStatus"), noticeStatus));
//            }
//
//            predicates.add(QueryByExamplePredicateBuilder.getPredicate(root, builder, example));
//
//
//            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//    }
//}
