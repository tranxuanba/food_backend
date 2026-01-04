package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.freshfood.dto.CartMeResponse;
import com.company.freshfood.dto.CartMeUpdateResponse;
import com.company.freshfood.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

	@Query(value = """
			    SELECT
			        MCI.CART_ITEM_ID,
			        MF.FOOD_ID,
			        MF.FOOD_NAME,
			        MFI.IMAGE_URL,
			        MF.PRICE,
			        MCI.QUANTITY,
			        MCI.PRICE AS TOTAL_PRICE,
			        SUM(MCI.QUANTITY) OVER () AS TOTAL_COUNT
			    FROM
			    	M_CART MC
			    INNER JOIN
			    	M_CART_ITEM MCI
			    	ON MC.CART_ID = MCI.CART_ID
			    	AND MCI.DELETED_FLAG = '0'
			    INNER JOIN
			    	M_FOOD MF
			    	ON MCI.FOOD_ID = MF.FOOD_ID
			    	AND MF.DELETED_FLAG = '0'
			    INNER JOIN
			    	M_FOOD_IMAGE MFI
			    	ON MF.FOOD_ID = MFI.FOOD_ID
			    	AND MFI.DELETED_FLAG = '0'
			    WHERE
			    	MC.DELETED_FLAG = '0'
			   		AND MC.CREATE_USER_ID = :USER_ID
			""", nativeQuery = true)
	List<CartMeResponse> findCartListByUserId(@Param("USER_ID") String userId);

	@Query(value = """
			    SELECT
			        MCI.CART_ITEM_ID
			    FROM
			    	M_CART MC
			    INNER JOIN
			    	M_CART_ITEM MCI
			    	ON MC.CART_ID = MCI.CART_ID
			    	AND MCI.DELETED_FLAG = '0'
			    INNER JOIN
			    	M_FOOD MF
			    	ON MCI.FOOD_ID = MF.FOOD_ID
			    	AND MF.DELETED_FLAG = '0'
			    WHERE
			    	MC.DELETED_FLAG = '0'
			   		AND MC.CREATE_USER_ID = :USER_ID
			   		AND MF.FOOD_ID = :FOOD_ID
			""", nativeQuery = true)
	List<CartMeUpdateResponse> findCartMeIsExiest(@Param("USER_ID") String userId, @Param("FOOD_ID") Long foodId);

	List<CartEntity> findByCreateUserIdAndDeletedFlag(String createUserId, String deletedFlag);

}
