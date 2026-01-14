package com.company.freshfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.freshfood.dto.OrderSearchResponse;
import com.company.freshfood.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	@Query(value = """
			    SELECT
				    MO.ORDER_ID,
				    MO.TOTAL_AMOUNT,
				    MO.ORDER_STATUS,
				    TO_CHAR(MO.CREATED_AT, 'YYYY/MM/DD HH24:MI') AS CREATED_AT,
				    MP.PAYMENT_METHOD,
				    MP.PAYMENT_STATUS,
				    MP.TRANSACTION_CODE,
				    STRING_AGG( MF.FOOD_NAME || '(Số lượng: ' || MOI.QUANTITY || ', Giá: ' || MOI.PRICE || 'đ)', '、' ORDER BY MF.FOOD_ID) AS ORDER_FOOD_NAME,
				    MA.RECEIVER_NAME,
				    MA.PHONE,
				    MA.ADDRESS_DETAIL,
				    COUNT(*) OVER() AS TOTAL_COUNT
				FROM
				    M_ORDER MO
				INNER JOIN
				    M_ORDER_ITEM MOI
				    ON MOI.ORDER_ID = MO.ORDER_ID
				    AND MOI.DELETED_FLAG = '0'
				INNER JOIN
				    M_FOOD MF
				    ON MF.FOOD_ID = MOI.FOOD_ID
				    AND MF.DELETED_FLAG = '0'
				INNER JOIN
				    M_ADDRESS MA
				    ON MA.ADDRESS_ID = MO.ADDRESS_ID
				    AND MA.DELETED_FLAG = '0'
				INNER JOIN
				    M_PAYMENT MP
				    ON MP.ORDER_ID = MO.ORDER_ID
				    AND MP.DELETED_FLAG = '0'
				WHERE
				    MO.DELETED_FLAG = '0'
				    AND (
				        :TRANSACTION_CODE IS NULL
				        OR :TRANSACTION_CODE = ''
				        OR MP.TRANSACTION_CODE ILIKE CONCAT('%', :TRANSACTION_CODE, '%')
				    )
				    AND (
				        :PAYMENT_STATUS IS NULL
				        OR MP.PAYMENT_STATUS = :PAYMENT_STATUS
				    )
				 GROUP BY
					 MO.ORDER_ID,
					 MO.TOTAL_AMOUNT,
					 MO.ORDER_STATUS,
					 MO.CREATED_AT,
					 MP.PAYMENT_METHOD,
					 MP.PAYMENT_STATUS,
					 MP.TRANSACTION_CODE,
					 MA.RECEIVER_NAME,
				     MA.PHONE,
				     MA.ADDRESS_DETAIL
				ORDER BY MO.CREATED_AT DESC, MO.ORDER_ID
				LIMIT :LIMIT OFFSET :OFFSET
			""", nativeQuery = true)
	List<OrderSearchResponse> getOrderedBySearchAll(@Param("TRANSACTION_CODE") String transactionCode,
			@Param("PAYMENT_STATUS") String paymentStatus, @Param("LIMIT") Integer limit,
			@Param("OFFSET") Integer offset);
}
