package com.company.freshfood.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.FoodDetailResponse;
import com.company.freshfood.dto.FoodRequest;
import com.company.freshfood.dto.FoodSearchResponse;
import com.company.freshfood.entity.FoodEntity;
import com.company.freshfood.entity.FoodImageEntity;
import com.company.freshfood.exception.ResourceNotFoundException;
import com.company.freshfood.repository.FoodImageRepository;
import com.company.freshfood.repository.FoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

	private final FoodRepository foodRepository;

	private final FoodImageRepository foodImageRepository;

	private final FileStorageService fileStorageService;

	@Override
	public void insertFood(FoodRequest.FoodInsertRequest request) {
		FoodEntity foodEntity = new FoodEntity();
		foodEntity.setCategoryId(request.getCategoryId());
		foodEntity.setFoodName(request.getFoodName());
		foodEntity.setDescription(request.getDescription());
		foodEntity.setPrice(request.getPrice());
		if (request.getDiscountPrice() != null) {
			foodEntity.setDiscountPrice(request.getDiscountPrice());
		}
		foodEntity.setQuantity(request.getQuantity());
		foodEntity.setStatus(request.getStatus());
		foodEntity = foodRepository.save(foodEntity);
		Long foodId = foodEntity.getFoodId();
		String url = fileStorageService.saveFoodImage(foodId, request.getFoodImage());
		FoodImageEntity foodImageEntity = new FoodImageEntity();
		foodImageEntity.setFoodId(foodId);
		foodImageEntity.setImageUrl(url);
		foodImageRepository.save(foodImageEntity);
	}

	@Override
	public List<FoodSearchResponse> getFoodList(FoodRequest.FoodSearchRequest request) {
		List<FoodSearchResponse> foodList = new ArrayList<>();
		foodList = foodRepository.findFoodListByFoodName(request.getFoodName(), request.getCategoryId());
		return foodList;
	}

	@Override
	public FoodDetailResponse getFoodDetailInfo(FoodRequest.FoodDetaiRequest request) {
		List<FoodDetailResponse> foodDetais = new ArrayList<>();
		foodDetais = foodRepository.getFoodDetailInfo(request.getFoodId());
		if (CollectionUtils.isEmpty(foodDetais)) {
			throw new ResourceNotFoundException("Không có sản phẩm tương ứng");
		}
		return foodDetais.get(0);
	}
}
