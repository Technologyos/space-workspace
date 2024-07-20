package com.technologyos.role.common;

import com.technologyos.role.dtos.RoleRequest;
import com.technologyos.role.entities.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RoleRequestMapper {

    @Mappings({@Mapping(source = "roleName", target = "roleName")})
    Role roleRequestToRole(RoleRequest role);

    List<Role> roleRequestListToRoleList(List<RoleRequest> source);

    @InheritInverseConfiguration
    RoleRequest roleToRoleRequest(Role role);

    @InheritInverseConfiguration
    List<RoleRequest> roleListToRoleRequestList(List<Role> roles);
}
