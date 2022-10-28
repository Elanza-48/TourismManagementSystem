package com.elanza48.TMS.controller.mapping;

import com.elanza48.TMS.model.entity.UserRole;
import com.elanza48.TMS.model.mapper.DtoToModelMapper;
import com.elanza48.TMS.model.mapper.ModelDtoToMapper;
import com.elanza48.TMS.model.dto.UserRoleDTO;
import com.elanza48.TMS.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRoleController {

    private UserRoleService userRoleService;
    private ModelDtoToMapper modelDtoToMapper;
    private DtoToModelMapper dtoToModelMapper;

    @Autowired
    public void setModelDtoToMapper(ModelDtoToMapper modelDtoToMapper) {
        this.modelDtoToMapper = modelDtoToMapper;
    }
    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }
    @Autowired
    public void setDtoToModelMapper(DtoToModelMapper dtoToModelMapper) {
        this.dtoToModelMapper = dtoToModelMapper;
    }

    @GetMapping("/role")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getAllRole(){
        return ResponseEntity.ok(modelDtoToMapper.userRoleModelToDtoList(
                userRoleService.getAllRole()
        ));
    }

    @GetMapping("/role/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getRoleByName(@PathVariable String name){
        return ResponseEntity.ok(modelDtoToMapper.userRoleModelToDto(
                userRoleService.findRole(name).get()
        ));
    }

    @PostMapping("/role")
    public ResponseEntity<?> createRole(@RequestBody UserRoleDTO userRoleDTO){
        return ResponseEntity.accepted().body(modelDtoToMapper.userRoleModelToDto(
                userRoleService.createRole(dtoToModelMapper.userRoleDtoToModel(userRoleDTO, new UserRole()))
        ));
    }
}
