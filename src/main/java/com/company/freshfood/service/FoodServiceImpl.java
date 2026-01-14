package com.company.freshfood.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.company.freshfood.dto.FoodCategoryResponse;
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

	private final FoodCategoryService foodCategoryService;

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
		List<Long> categoryIds;
		if (request.getCategoryIds() == null || request.getCategoryIds().isEmpty()) {
			categoryIds = foodCategoryService.getAllFoodCategory().stream().map(FoodCategoryResponse::getCategoryId)
					.toList();
		} else {
			categoryIds = request.getCategoryIds();
		}
		foodList = foodRepository.findFoodListByFoodName(request.getFoodName(), categoryIds, request.getLimit(),
				request.getOffset());
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

	@Override
	public void updateFood(FoodRequest.FoodUpdateRequest request) {
		Optional<FoodEntity> foods = foodRepository.findById(request.getFoodId());
		FoodEntity foodEntity = foods.get();
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
		List<FoodImageEntity> foodImageList = foodImageRepository.findByFoodId(foodId);
		FoodImageEntity foodImageEntity = foodImageList.get(0);
		foodImageEntity.setImageUrl(url);
		foodImageRepository.save(foodImageEntity);
	}

	@Override
	public void deleteFood(FoodRequest.FoodDeleteRequest request) {
		List<FoodImageEntity> foodImageList = foodImageRepository.findByFoodId(request.getFoodId());
		FoodImageEntity foodImageEntity = foodImageList.get(0);
		foodImageRepository.delete(foodImageEntity);
		Optional<FoodEntity> foods = foodRepository.findById(request.getFoodId());
		FoodEntity foodEntity = foods.get();
		foodRepository.delete(foodEntity);
	}
}
