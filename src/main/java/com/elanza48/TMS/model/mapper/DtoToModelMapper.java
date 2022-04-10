package com.elanza48.TMS.model.mapper;

import com.elanza48.TMS.model.dto.*;
import com.elanza48.TMS.model.entity.*;
import com.elanza48.TMS.model.entity.Package;
import org.mapstruct.*;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DtoToModelMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "userAccount.id", ignore = true)
    UserAccount userAccountDtoToModel(UserAccountDTO userAccountDTO, @MappingTarget UserAccount userAccount);

    @Mapping(target = "userAccount.id", ignore = true)
    Hotel hotelDtoToModel(HotelDTO hotelDto, @MappingTarget Hotel userAccountHotel);

    @Mapping(target = "userAccount.id", ignore = true)
    Destination destinationDtoToModel(DestinationDTO destDto, @MappingTarget Destination destination);

    @Mapping(target = "userAccount.id", ignore = true)
    Package packageDtoToModel(PackageDTO packageDTO, @MappingTarget Package package1);

    @Mapping(target = "userAccount.id", ignore = true)
    Booking bookingDtoToModel(BookingDTO bookingDTO, @MappingTarget Booking booking);

    @Mapping(target = "userAccount.id", ignore = true)
    UserRole userRoleDtoToModel(UserRoleDTO userRoleDTO, @MappingTarget UserRole userRole);

    @Mapping(target = "userAccount.id", ignore = true)
    UserPrivilege userPrivilegeDtoToModel(UserPrivilegeDTO userPrivilegeDTO, @MappingTarget UserPrivilege userPrivilege);

}
