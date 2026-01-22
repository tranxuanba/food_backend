package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.freshfood.dto.FoodDetailResponse;
import com.company.freshfood.dto.FoodSearchResponse;
import com.company.freshfood.entity.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
	@Query(value = """
			    SELECT
				    MF.FOOD_ID,
				    MF.FOOD_NAME,
				    MFC.CATEGORY_ID,
				    MFC.CATEGORY_NAME,
				    MF.DESCRIPTION,
				    MF.QUANTITY,
				    MF.STATUS,
				    MF.PRICE,
				    MF.DISCOUNT_PRICE,
				    MFI.IMAGE_URL,
				    COUNT(*) OVER() AS TOTAL_COUNT
				FROM
				    M_FOOD MF
				INNER JOIN
				    M_FOOD_CATEGORY MFC
				    ON MFC.CATEGORY_ID = MF.CATEGORY_ID
				    AND MFC.DELETED_FLAG = '0'
				INNER JOIN
				    M_FOOD_IMAGE MFI
				    ON MFI.FOOD_ID = MF.FOOD_ID
				    AND MFI.DELETED_FLAG = '0'
				WHERE
				    MF.DELETED_FLAG = '0'
				    AND (
				        :FOOD_NAME IS NULL
				        OR :FOOD_NAME = ''
				        OR MF.FOOD_NAME ILIKE CONCAT('%', :FOOD_NAME, '%')
				    )
				    AND (
				        :CATEGORY_IDS IS NULL
				        OR MF.CATEGORY_ID IN (:CATEGORY_IDS)
				    )
				ORDER BY MF.FOOD_ID
				LIMIT :LIMIT OFFSET :OFFSET
			""", nativeQuery = true)
	List<FoodSearchResponse> findFoodListByFoodName(@Param("FOOD_NAME") String foodName,
			@Param("CATEGORY_IDS") List<Long> categoryIds, @Param("LIMIT") Integer limit, @Param("OFFSET") Integer offset);

	@Query(value = """
			    SELECT
			        MF.FOOD_ID,
			        MF.FOOD_NAME,
			        MF.STATUS,
			        MF.PRICE,
			        MF.DISCOUNT_PRICE,
			        MF.DESCRIPTION,
			        MFC.CATEGORY_NAME,
			        MF.QUANTITY,
			        MFI.IMAGE_URL
			    FROM
			    	M_FOOD MF
			    INNER JOIN
			    	M_FOOD_CATEGORY MFC
			    	ON MFC.CATEGORY_ID = MF.CATEGORY_ID
			    	AND MFC.DELETED_FLAG = '0'
			    INNER JOIN
			    	M_FOOD_IMAGE MFI
			    	ON MFI.FOOD_ID = MF.FOOD_ID
			    	AND MFI.DELETED_FLAG = '0'
			    WHERE
			    	MF.DELETED_FLAG = '0'
			    	AND MF.FOOD_ID = :FOOD_ID
			""", nativeQuery = true)
	List<FoodDetailResponse> getFoodDetailInfo(@Param("FOOD_ID") Long foodId);
}
