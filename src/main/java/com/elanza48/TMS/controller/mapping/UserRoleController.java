package com.elanza48.TMS.controller.mapping;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.dto.UserRoleDTO;
import com.elanza48.TMS.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/user", produces = {"application/hal+json"})
public class UserRoleController {

    private UserRoleService userRoleService;
    private ModelDtoMapper modelDtoMapper;

    @Autowired
    public void setModelDtoMapper(ModelDtoMapper modelDtoMapper) {
        this.modelDtoMapper = modelDtoMapper;
    }
    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("/role")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(modelDtoMapper.userRoleModelToDtoList(
                userRoleService.getAllRole()
        ));
    }

    @GetMapping("/role/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getRoleByName(@PathVariable String name){
        return ResponseEntity.ok(modelDtoMapper.userRoleModelToDto(
                userRoleService.findRole(name).get()
        ));
    }

    @PostMapping("/role")
    public ResponseEntity<?> createRole(@RequestBody UserRoleDTO userRoleDTO){
        return ResponseEntity.accepted().body(modelDtoMapper.userRoleModelToDto(
                userRoleService.createRole(modelDtoMapper.userRoleDtoToModel(userRoleDTO))
        ));
    }
}
