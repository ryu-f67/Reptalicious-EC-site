package com.example.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    private final S3Client s3Client;
    
    @Value("${aws.s3.bucket-name}")
    private String bucketName;
    
    @Value("${aws.s3.region}") 
    private String regionName;

    public S3Service(@Value("${aws.s3.access-key}") String accessKey,
                     @Value("${aws.s3.secret-key}") String secretKey,
                     @Value("${aws.s3.region}") String region) {
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }
    
    public String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        
        if (filename != null && !filename.isEmpty()) {
            int dotIndex = filename.lastIndexOf(".");
            
            if (dotIndex > 0) {
                return filename.substring(dotIndex + 1);
            }
        }
        return "";
    }

    public String uploadFile(Long productId, MultipartFile file, String fileKey) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("ファイルが選択されていません");
        }
        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            int originalWidth = originalImage.getWidth();
            int originalHeight = originalImage.getHeight();

            int newWidth = originalWidth;
            int newHeight = originalHeight;

            if (originalWidth > 500 || originalHeight > 500) {
                if (originalWidth >= originalHeight) {
                    newWidth = 500;
                    newHeight = (int) ((500.0 / originalWidth) * originalHeight);
                } else {
                    newHeight = 500;
                    newWidth = (int) ((500.0 / originalHeight) * originalWidth);
                }
            }
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(originalImage)
                    .size(newWidth, newHeight)
                    .outputFormat("png")
                    .toOutputStream(outputStream);

            // 商品IDを使って一意のファイル名を生成
            String extension = getFileExtension(file);
            String fileName = productId + "-" + fileKey + "." + extension;
    
            // S3 にアップロード
            byte[] resizedImageBytes = outputStream.toByteArray();
            try (InputStream resizedInputStream = new ByteArrayInputStream(resizedImageBytes)) {
                s3Client.putObject(
                        PutObjectRequest.builder()
                                .bucket(bucketName)
                                .key(fileName)
                                .contentType("image/png")
                                .build(),
                        RequestBody.fromInputStream(resizedInputStream, resizedImageBytes.length)
                );
            }
    
            // アップロードされたファイルの S3 URL を返す
            return "https://" + bucketName + ".s3." + regionName + ".amazonaws.com/" + fileName;
        }  catch (Exception e) {
            throw new RuntimeException("画像のリサイズまたはアップロードに失敗しました", e);
        }
    }
    
}
