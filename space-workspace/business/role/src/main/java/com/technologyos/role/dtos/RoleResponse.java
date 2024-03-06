package com.technologyos.role.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "RoleResponse", description = "Model represent a Role on database")
@Data
public class RoleResponse {

    @Schema(name = "roleId", required = true, example = "2", defaultValue = "1",
            description = "Unique Id of role on database")
    private long roleId;
    @Schema(name = "roleName", required = true, example = "dr", defaultValue = "",
            description = "Role name")
    private String roleName;
    @Schema(name = "longDescription", required = true, example = "Director", defaultValue = "",
            description = "Role description")
    private String longDescription;
    private String status;
    private String creatingDate;
}
