package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.freshfood.dto.UserInfoResponse;
import com.company.freshfood.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	@Query(value = """
			    SELECT
			        MU.EMAIL,
			        MU.FULL_NAME,
			        MU.PASSWORD_HASH,
			        MU.USER_ID,
			        MU.PHONE,
			        MU.STATUS,
			        MR.ROLE_NAME
			    FROM
			    	M_USER MU
			    INNER JOIN
			    	M_ROLE MR
			    	ON MR.ROLE_ID = MU.ROLE_ID
			    	AND MR.DELETED_FLAG = '0'
			    WHERE
			    	MU.DELETED_FLAG = '0'
			    	AND MU.EMAIL = :EMAIL
			""", nativeQuery = true)
	List<UserInfoResponse> getUserInfo(@Param("EMAIL") String email);

	boolean existsByEmail(String email, String deletedFlag);
}
