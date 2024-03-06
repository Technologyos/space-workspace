package com.technologyos.role.common;

import com.technologyos.role.dtos.RoleResponse;
import com.technologyos.role.entities.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RoleResponseMapper {

    @Mappings({@Mapping(source = "id", target = "roleId")})
    RoleResponse roleToRoleResponse(Role role);

    List<RoleResponse> roleListToRoleResponseList(List<Role> source);

    @InheritInverseConfiguration
    Role roleResponseToRole(RoleResponse role);

    @InheritInverseConfiguration
    List<Role> roleResponseToRoleList(List<RoleResponse> roles);
}
