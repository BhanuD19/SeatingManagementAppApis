package com.project.seatingplan.Service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

@Service
public class FileService {
  private static final Logger logger = LoggerFactory.getLogger(FileService.class);

  public static final String IMAGE_SUFFIX = ".image";
  public static final String IMAGE_URI_TEPLATE = "{0}/image/{1}.image";
  public static final String IMAGE_Entity_NAME_TEPLATE = "{0}/image/{1}";

  @Value("${catalina.home}/seating-plan-app-images")
  private String imageRepositoryPath;

  @Value("1048576")
  private Long imageMaxAllowedSize;

  @PostConstruct
  public void init() {
    if(imageRepositoryPath == null) {
      logger.error("Fileservice: image repository path is not initialised.imagerepositorypath={}", imageRepositoryPath);
      throw new IllegalStateException("FileService:Image repository path is not initialised");
    }

    File file = new File(imageRepositoryPath);
    if(!file.exists() || file.isFile()) {
      logger.info("Creating image repository path={}", imageRepositoryPath);
      boolean mkdirs = file.mkdirs();
      logger.info("Imagerepositorypath= {}", imageRepositoryPath, mkdirs);
    }
  }

  public File getImageFile(String entityName, Object id) {
    String path = FilenameUtils.concat(imageRepositoryPath, MessageFormat.format(IMAGE_URI_TEPLATE, entityName, id));
    logger.debug("Getting file path:{}", path);
    return new File(path);
  }

  public byte[] getImageData(String entityName, Object id) throws IOException {
    File file = getImageFile(entityName, id);
    return FileUtils.readFileToByteArray(file);
  }

  public String saveImage(String entityName, Object id, byte[] data) throws IOException {
    File file = getImageFile(entityName, id);
    logger.info("Save image:{}", file);
    FileUtils.writeByteArrayToFile(file, data);
    return file.getAbsolutePath();
  }

  public byte[] getImageDataWithURI(String imageURI) throws IOException {
    String path = FilenameUtils.concat(imageRepositoryPath, imageURI);
    File file = new File(path);
    logger.debug("Getting file path:{}", file);
    return FileUtils.readFileToByteArray(file);
  }

  public byte[] getImageDataWithName(String entityName, String imageName) throws IOException {
    String imageURI = MessageFormat.format(IMAGE_URI_TEPLATE, entityName, imageName);
    return getImageDataWithURI(imageURI);
  }

  public String saveImage(String entityName, byte[] data) throws IOException {
    String imageURI = MessageFormat.format(IMAGE_URI_TEPLATE, entityName, UUID.randomUUID().toString());
    String path = FilenameUtils.concat(imageRepositoryPath, imageURI);
    File file = new File(path);
    logger.info("save image:{}", file);
    FileUtils.writeByteArrayToFile(file, data);
    return imageURI;
  }

  public String getAbsolutePath(String fileURI) {return FilenameUtils.concat(imageRepositoryPath, fileURI); }

  public String getImageRepositoryPath() { return imageRepositoryPath; }

  public void setImageRepositoryPath(String imageRepositoryPath) {
    this.imageRepositoryPath = imageRepositoryPath;
  }

  public Long getImageMaxAllowedSize() {
    return imageMaxAllowedSize;
  }

  public void setImageMaxAllowedSize(Long imageMaxAllowedSize) {
    this.imageMaxAllowedSize = imageMaxAllowedSize;
  }
}
