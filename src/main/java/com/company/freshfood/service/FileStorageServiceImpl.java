package com.company.freshfood.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Value("${app.base-url}")
	private String baseUrl;

	public String saveFoodImage(Long foodId, MultipartFile file) {
		try {
			String originalName = file.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf("."));

			String fileName = UUID.randomUUID() + ext;

			Path foodDir = Paths.get(uploadDir, "foods", foodId.toString());
			Files.createDirectories(foodDir);

			Path target = foodDir.resolve(fileName);
			Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

			// URL sẽ dùng để client load ảnh
			return baseUrl + "/uploads/foods/" + foodId + "/" + fileName;

		} catch (IOException e) {
			throw new RuntimeException("Upload file failed", e);
		}
	}

}
