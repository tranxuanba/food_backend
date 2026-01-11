package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.freshfood.dto.SearchFoodCategoryResponse;
import com.company.freshfood.entity.FoodCategoryEntity;

public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Long> {

	List<FoodCategoryEntity> findByDeletedFlag(String deletedFlag);

	@Query(value = """
			    SELECT
				    MFC.CATEGORY_ID,
				    MFC.CATEGORY_NAME,
				    MFC.PARENT_CATEGORY_ID
				FROM
				    M_FOOD_CATEGORY MFC
				WHERE
				    MFC.DELETED_FLAG = '0'
				    AND (
				        :CATEGORY_NAME IS NULL
				        OR :CATEGORY_NAME = ''
				        OR MFC.CATEGORY_NAME ILIKE CONCAT('%', :CATEGORY_NAME, '%')
				    )
				ORDER BY MFC.CATEGORY_ID
			""", nativeQuery = true)
	List<SearchFoodCategoryResponse> findByCategoryName(@Param("CATEGORY_NAME") String categoryName);

}
