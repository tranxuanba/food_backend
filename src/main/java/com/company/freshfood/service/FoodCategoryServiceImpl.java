package com.company.freshfood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.freshfood.dto.FoodCategoryRequest;
import com.company.freshfood.dto.FoodCategoryResponse;
import com.company.freshfood.dto.SearchFoodCategoryResponse;
import com.company.freshfood.entity.FoodCategoryEntity;
import com.company.freshfood.mapper.FoodCategoryMapper;
import com.company.freshfood.repository.FoodCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

	private final FoodCategoryRepository foodCategoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<FoodCategoryResponse> getAllFoodCategory() {
		List<FoodCategoryEntity> entities = foodCategoryRepository.findByDeletedFlag("0");

		return FoodCategoryMapper.toFoodCategoryResponseList(entities);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SearchFoodCategoryResponse> getSearchAllFoodCategory(
			FoodCategoryRequest.FoodCategorySearchRequest request) {
		List<SearchFoodCategoryResponse> foodCategoryList = foodCategoryRepository
				.findByCategoryName(request.getCategoryName());

		return foodCategoryList;
	}

	@Override
	public void deleteFoodCategory(FoodCategoryRequest.FoodCategoryDeleteRequest request) {
		Optional<FoodCategoryEntity> entityList = foodCategoryRepository.findById(request.getCategoryId());
		if (entityList.isEmpty()) {
			throw new RuntimeException("Không tồn tại bản ghi tương ứng");
		}
		foodCategoryRepository.delete(entityList.get());
	}

	@Override
	public void addFoodCategory(FoodCategoryRequest.FoodCategoryAddRequest request) {
		FoodCategoryEntity entity = new FoodCategoryEntity();
		entity.setCategoryName(request.getCategoryName());
		if (request.getParentCategoryId() != null) {
			entity.setParentCategoryId(request.getParentCategoryId());
		}

		foodCategoryRepository.save(entity);
	}

	@Override
	public void updateFoodCategory(FoodCategoryRequest.FoodCategoryUpdateRequest request) {
		Optional<FoodCategoryEntity> entityList = foodCategoryRepository.findById(request.getCategoryId());
		if (entityList.isEmpty()) {
			throw new RuntimeException("Không tồn tại bản ghi tương ứng");
		}
		entityList.get().setCategoryName(request.getCategoryName());
		if (request.getParentCategoryId() != null) {
			entityList.get().setParentCategoryId(request.getParentCategoryId());
		}
		foodCategoryRepository.save(entityList.get());
	}
}
