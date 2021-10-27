package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.UserAccountDTO;
import com.elanza48.TMS.model.entity.UserAccount;

import org.mapstruct.Mapper;

@Mapper
public interface UserAccountMapper {
  UserAccountDTO userAccountModelToDto(UserAccount userAccount);

  List<UserAccountDTO> userAccountModelToDtos(List<UserAccount> users);

  UserAccount UserAccountDtoToModel(UserAccountDTO userAccountDTO);
}
