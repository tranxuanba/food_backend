package com.company.freshfood.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	String saveFoodImage(Long foodId, MultipartFile file);

}
