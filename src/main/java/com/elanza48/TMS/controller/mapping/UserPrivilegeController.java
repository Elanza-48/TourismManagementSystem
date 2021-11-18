package com.elanza48.TMS.controller.mapping;

import com.elanza48.TMS.model.ModelDtoMapper;
import com.elanza48.TMS.model.dto.UserPrivilegeDTO;
import com.elanza48.TMS.service.UserPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = {"application/hal+json"})
public class UserPrivilegeController {

    private UserPrivilegeService userPrivilegeService;
    private ModelDtoMapper modelDtoMapper;

    @Autowired
    public void setModelDtoMapper(ModelDtoMapper modelDtoMapper) {
        this.modelDtoMapper = modelDtoMapper;
    }
    @Autowired
    public void setUserPrivilegeService(UserPrivilegeService userPrivilegeService) {
        this.userPrivilegeService = userPrivilegeService;
    }

    @GetMapping("/privilege")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getAllPrivilege(){
        return ResponseEntity.ok(modelDtoMapper.userPrivilegeModelToDtoList(
                userPrivilegeService.getAllPrivilege()
        ));
    }

    @GetMapping("/privilege/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getPrivilegeByName(@PathVariable String name){
        return ResponseEntity.ok(modelDtoMapper.userPrivilegeModelToDto(
                userPrivilegeService.findPrivilege(name).get()
        ));
    }

    @PostMapping("/privilege")
    public ResponseEntity<?> createPrivilege(@RequestBody UserPrivilegeDTO userPrivilegeDTO){
        return ResponseEntity.accepted().body(modelDtoMapper.userPrivilegeModelToDto(
                userPrivilegeService.createPrivilege(
                        modelDtoMapper.userPrivilegeDtoToModel(userPrivilegeDTO))
        ));
    }
}
