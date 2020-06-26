package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.FileService;
import com.project.seatingplan.Service.InstituteService;
import com.project.seatingplan.dto.request.web.InstituteReq;
import com.project.seatingplan.dto.response.web.InstituteResp;
import com.project.seatingplan.model.Institute;
import com.project.seatingplan.repository.InstituteRepo;
import com.project.seatingplan.utils.RestResponse;
import com.project.seatingplan.utils.RestUriConstants;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/institutes")
public class InstituteController {
  private static final Logger logger = LoggerFactory.getLogger(InstituteController.class);

  private final InstituteService instituteService;
  private final FileService fileService;
  private final InstituteRepo instituteRepo;

  public InstituteController(InstituteService instituteService, FileService fileService, InstituteRepo instituteRepo) {
    this.instituteService = instituteService;
    this.fileService = fileService;
    this.instituteRepo = instituteRepo;
  }

  // save institutes
  @RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
  public RestResponse save(@RequestBody InstituteReq instituteReq) {
    try {
      Integer userId = 49;
      logger.info("save institute:{}, userId:{}", instituteReq, userId);
      instituteService.save(instituteReq, userId);
    } catch (Exception e) {
      logger.error("Failed to save institutes due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to save institutes due to:" + e.getMessage());
    }
    return RestResponse.build().withSuccess("Institutes saved  successfully.");
  }

  // update
  @RequestMapping(value = "/update", method = RequestMethod.POST, headers = ("content-type=multipart/*"), produces = "application/json", consumes = "application/json")
  public RestResponse update(@RequestParam(value = "instituteId", required = false) Integer instituteId,
                             @RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "contactOne", required = false) String contactOne,
                             @RequestParam(value = "contactTwo", required = false) String contactTwo,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "website", required = false) String website,
                             @RequestParam(value = "cityId", required = false) Integer cityId,
                             @RequestParam(value = "logo", required = false) MultipartFile file) {

    String logo = null;
    try {
      if (file != null) {
        logger.info("save with logo id:{} originalFileName:{}", instituteId, file.getOriginalFilename());
        if (file.getSize() > fileService.getImageMaxAllowedSize()) {

          throw new Exception(
            "File size is larger then max allowed size :" + fileService.getImageMaxAllowedSize());
        }
        byte[] data = file.getBytes();
        logo = fileService.saveImage(RestUriConstants.INSTITUTE, data);
      }
      InstituteReq instituteReq = new InstituteReq();

      instituteReq.setId(instituteId);
      instituteReq.setName(name);
      instituteReq.setAddress(address);
      instituteReq.setContactOne(contactOne);
      instituteReq.setContactTwo(contactTwo);
      instituteReq.setEmail(email);
      instituteReq.setCityId(cityId);
      instituteReq.setWebsite(website);
      instituteReq.setLogo(logo);

      logger.info("Institute:{}", instituteReq);
      String message = instituteService.update(instituteReq);
      logger.info(message);
      return RestResponse.build().withSuccess(message);
    } catch (Exception e) {
      if (logo != null) {
        FileUtils.deleteQuietly(new File(fileService.getAbsolutePath(logo)));
      }

      logger.error("Failed to update institute due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to update institute due to :" + e.getMessage());
    }
  }

  // Find By Id
  @RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findById(@PathVariable("id") Integer id) {
    try {
      logger.info("find institutes by Id");
      Institute institute = instituteService.findById(id);
      if (institute != null) {
        logger.info("institutes retrieved successfully");
        return RestResponse.build().withSuccess().withData(institute);
      } else {
        return RestResponse.build().withError("no data found");
      }
    } catch (Exception e) {
      logger.error("Failed to retrieve institute by Id due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to retrieve  institute due to:" + e.getMessage());
    }
  }

  // Find By Alias
  @RequestMapping(value = "/ByAlias/{alias}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findByAlias(@PathVariable("alias") String alias) {
    try {
      logger.info("find institutes by Alias");
      List<Institute> institute = instituteService.findByAlias(alias);
      if (institute != null) {
        logger.info("institutes retrieval by alias is successful");
        return RestResponse.build().withSuccess().withData(institute);
      } else {
        return RestResponse.build().withError("no data fetched");
      }
    } catch (Exception e) {
      logger.error("Failed to retrieve institute by Alias due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to retrieve institute due to:" + e.getMessage());
    }
  }


  // Find By UserId
  @RequestMapping(value = "/ByUser", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findByUserId() {
    try {
      Integer userId = 1;
      logger.info("find institutes by userId{}", userId);
      List<InstituteResp> instituteRespList = instituteService.findByUserId(userId);

      logger.info("Fetched institute list size:{} ", instituteRespList.size());
      return RestResponse.build().withSuccess("successfully institute retrieved").withData(instituteRespList);
    } catch (Exception e) {
      logger.error("Failed to retrieve institute by instituteId due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to retrieve institute due to:" + e.getMessage());
    }
  }

  /// Delete institutes ById
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("delete institute by id:{}", id);
      int deleted = instituteService.markDeleted(id);
      if (deleted > 0) {
        return RestResponse.build().withSuccess("institutes deleted successfully.");
      } else {
        return RestResponse.build().withSuccess("institutes not deleted successfully");
      }
    } catch (Exception e) {
      logger.error("Failed to delete institutes due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete institutes due to:" + e.getMessage());
    }
  }

  @RequestMapping(value = "/image/{info:.+}", method = RequestMethod.GET, produces = "image/jpeg")
  public byte[] getImage(@PathVariable("info") String info) {
    try {
      logger.info("get institute image with info:{}", info);
      if (info.endsWith(FileService.IMAGE_SUFFIX)) {
        return getImageWithName(info);
      } else {
        return getImageWithId(Integer.parseInt(info));
      }
    } catch (Exception e) {
      logger.error("Failed to get institute's Image due to:{}", e.toString(), e);
      throw new IllegalArgumentException("No image found due to:" + e, e);
    }
  }

  private byte[] getImageWithId(Integer id) throws Exception {
    logger.info("get institute image for id:{}", id);
    Institute entity = instituteRepo.getOne(id);
    byte[] data = fileService.getImageDataWithURI(entity.getLogo());
    logger.info("returning image with size:{} ", data.length);
    return data;
  }

  private byte[] getImageWithName(String name) throws Exception {
    logger.info("get institute image for name:{}", name);
    byte[] data = fileService.getImageDataWithName(RestUriConstants.INSTITUTE, name);
    logger.info("returning image with size:{} ", data.length);
    return data;
  }
}
