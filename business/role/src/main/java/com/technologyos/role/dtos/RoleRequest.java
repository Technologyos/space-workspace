package com.technologyos.role.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(name = "RoleRequest", description = "Model represent a Role on database")
@Data
public class RoleRequest {

    @Schema(name = "roleName", required = true, example = "dr", defaultValue = "",
            description = "Role name")
    @NotNull
    private String roleName;
    @Schema(name = "longDescription", required = true, example = "Director", defaultValue = "",
            description = "Role description")
    @NotNull
    private String longDescription;
    private String status;
    private String creatingDate;
}
