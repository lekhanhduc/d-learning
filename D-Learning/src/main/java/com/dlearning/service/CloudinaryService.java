package com.dlearning.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public Map<?,?> uploadFile(MultipartFile file, String folderName) throws IOException {
        return cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap(
                        "folder", folderName
                ));
    }

    public Map<String, Object> uploadVideoChunked(MultipartFile file, String folderName) throws IOException {
        File tempFile = convertMultipartFileToFile(file);
        Map<String, Object> uploadResult = cloudinary.uploader().uploadLarge(tempFile, ObjectUtils.asMap(
                "resource_type", "video",
                "folder", folderName,
                "chunk_size", 90000000
        ));
        tempFile.delete(); // Xóa file tạm sau khi tải lên
        return uploadResult;
    }
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("upload", file.getOriginalFilename());
        file.transferTo(tempFile);
        return tempFile;
    }
}
