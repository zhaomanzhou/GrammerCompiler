package com.idofast.admin.repository;

import com.idofast.admin.domain.UserProxyInfo;
import com.idofast.admin.domain.dto.UserInfoLite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author zhaomanzhou
 * @version 1.0
 * @createTime 2021/2/20 10:19 下午
 */
@Repository
public interface UserProxyInfoRepository extends JpaRepository<UserProxyInfo, Long>
{
    /**
     * 查询分页列表
     *
     * @param id 查询条件
     * @return
     */

    @Query("select new com.idofast.admin.domain.dto.UserInfoLite (u , d) " +
            "from User u INNER JOIN  UserProxyInfo d " +
            "on u.id = d.id ")
    public Page<UserInfoLite> queryUserInfo( Pageable pageable);
}
