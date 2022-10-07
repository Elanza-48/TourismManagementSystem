package com.elanza48.TMS.controller.mapping;

import com.elanza48.TMS.model.entity.UserPrivilege;
import com.elanza48.TMS.model.mapper.DtoToModelMapper;
import com.elanza48.TMS.model.mapper.ModelDtoToMapper;
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
    private ModelDtoToMapper modelDtoToMapper;
    private DtoToModelMapper dtoToModelMapper;

    @Autowired
    public void setModelDtoToMapper(ModelDtoToMapper modelDtoToMapper) {
        this.modelDtoToMapper = modelDtoToMapper;
    }
    @Autowired
    public void setUserPrivilegeService(UserPrivilegeService userPrivilegeService) {
        this.userPrivilegeService = userPrivilegeService;
    }
    @Autowired
    public void setDtoToModelMapper(DtoToModelMapper dtoToModelMapper) {
        this.dtoToModelMapper = dtoToModelMapper;
    }

    @GetMapping("/privilege")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getAllPrivilege(){
        return ResponseEntity.ok(modelDtoToMapper.userPrivilegeModelToDtoList(
                userPrivilegeService.getAllPrivilege()
        ));
    }

    @GetMapping("/privilege/{name}")
    @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<?> getPrivilegeByName(@PathVariable String name){
        return ResponseEntity.ok(modelDtoToMapper.userPrivilegeModelToDto(
                userPrivilegeService.findPrivilege(name).get()
        ));
    }

    @PostMapping("/privilege")
    public ResponseEntity<?> createPrivilege(@RequestBody UserPrivilegeDTO userPrivilegeDTO){
        return ResponseEntity.accepted().body(modelDtoToMapper.userPrivilegeModelToDto(
                userPrivilegeService.createPrivilege(
                        dtoToModelMapper.userPrivilegeDtoToModel(userPrivilegeDTO, new UserPrivilege()))
        ));
    }
}
