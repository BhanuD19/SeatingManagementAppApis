package com.project.seatingplan.Controller;

import com.project.seatingplan.Service.RoleService;
import com.project.seatingplan.model.Role;
import com.project.seatingplan.utils.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
  private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @RequestMapping(value = "/ById/{id}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findById(@PathVariable("id") Integer id) {
    try {
      logger.info("find all roles");
      Role role = roleService.findById(id);
      if (role != null) {
        logger.info("found all roles by id  successfully");
        return RestResponse.build().withSuccess().withData(role);
      } else {
        return RestResponse.build().withError("no data found");
      }
    } catch (Exception e) {
      logger.error("Failed to fetch role due to:{}", e.toString(), e);
      return RestResponse.build().withError("No role created due to:" + e.getMessage());
    }
  }

  @RequestMapping(value = "/ByName/{name}", method = RequestMethod.GET, produces = "application/json")
  public RestResponse findByName(@PathVariable("name") String name) {
    try {
      logger.info("find roles by name:{}", name);
      Role role = roleService.findByName(name);
      if (role != null) {
        logger.info("find roles by name successfully");
        return RestResponse.build().withSuccess().withData(role);
      } else {
        return RestResponse.build().withError("no data found");
      }
    } catch (Exception e) {
      logger.error("Failed to retrieve roles due to:{}", e.toString(), e);
      return RestResponse.build().withError("No role retrieve due to:" + e.getMessage());
    }
  }

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public RestResponse findAll() {
    try {
      logger.info("find all roles");
      List<Role> role = roleService.findAll();
      if (role != null) {
        logger.info("find all roles successfully");
        return RestResponse.build().withSuccess().withData(role);
      } else {
        return RestResponse.build().withError("no data found");
      }
    } catch (Exception e) {
      logger.error("Failed to find all roles due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to find all role due to:" + e.getMessage());
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
  public RestResponse delete(@PathVariable("id") Integer id) {
    try {
      logger.info("delete role by id:{}", id);
      int deleted = roleService.delete(id);
      if (deleted > 0) {
        return RestResponse.build().withSuccess("role deleted successfully.");
      } else {
        return RestResponse.build().withError("role not deleted");
      }

    } catch (Exception e) {
      logger.error("Failed to delete role due to:{}", e.toString(), e);
      return RestResponse.build().withError("Failed to delete role due to:" + e.getMessage());
    }
  }
}
