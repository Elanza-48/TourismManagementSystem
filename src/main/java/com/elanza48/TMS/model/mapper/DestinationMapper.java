package com.elanza48.TMS.model.mapper;

import java.util.List;

import com.elanza48.TMS.model.dto.DestinationDTO;
import com.elanza48.TMS.model.entity.Destination;

import org.mapstruct.Mapper;

@Mapper
public interface DestinationMapper {

  DestinationDTO destinationModelToDto(Destination dest);
  List<DestinationDTO> destinationModelToDtoList(List<Destination> dests);
  Destination destinationDtoToModel(DestinationDTO destDto);
  
}
