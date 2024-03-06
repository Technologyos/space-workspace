package com.technologyos.role.controllers;

import com.technologyos.role.common.RoleRequestMapper;
import com.technologyos.role.common.RoleResponseMapper;
import com.technologyos.role.dtos.RoleRequest;
import com.technologyos.role.dtos.RoleResponse;
import com.technologyos.role.entities.Role;
import com.technologyos.role.exception.BusinessRuleException;
import com.technologyos.role.repositories.RoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleRequestMapper roleRequestMapper;

    @Autowired
    private RoleResponseMapper roleResponseMapper;

    @GetMapping()
    @Operation(summary = "Get all roles",
            description = "Get all roles with active status sorted by ASC creating time", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<List<RoleResponse>> findAll() {
        return Optional.ofNullable(roleRepository.findAll().isEmpty() ? null:roleRepository.findAll())
                .map(role -> roleResponseMapper.roleListToRoleResponseList(role))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    @Operation(summary = "Insert a role",
            description = "Insert a new role. Default status is active and default moment is now.", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<RoleResponse> post(@RequestBody RoleRequest roleRequest) {
        Role role = roleRequestMapper.roleRequestToRole(roleRequest);
        Role save = roleRepository.save(role);
        RoleResponse roleResponse = roleResponseMapper.roleToRoleResponse(save);
        return ResponseEntity.ok(roleResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a role by id",
            description = "Get a role by id with active status", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<RoleResponse> get(@PathVariable long id) {
        return  roleRepository.findById(id)
                .map(role -> roleResponseMapper.roleToRoleResponse(role))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a role by id",
            description = "update a role by id with active status", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<RoleResponse> put(@PathVariable long id, @RequestBody RoleRequest roleRequest) throws BusinessRuleException {
        Role role = null;
        Optional<Role> currentRole = roleRepository.findById(id);
        if(currentRole.isPresent()){
            currentRole.get().setRoleName(roleRequest.getRoleName());
            currentRole.get().setLongDescription(roleRequest.getLongDescription());
            currentRole.get().setStatus(roleRequest.getStatus());
            currentRole.get().setCreatingDate(roleRequest.getCreatingDate());
            role = roleRepository.save(currentRole.get());
        }else{
            throw new BusinessRuleException("1025",
                    "Error al actualizar el role", HttpStatus.PRECONDITION_FAILED);
        }
        RoleResponse roleResponse = roleResponseMapper.roleToRoleResponse(role);

        return ResponseEntity.ok(roleResponse);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a role",
            description = "Modify the status from Active to Inactive", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            roleRepository.delete(role.get());
        }else{
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/byName")
    @Operation(summary = "Get a role by roleName",
       description = "Get a role by roleName with active status", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
       content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
       @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<RoleResponse> getByRoleName(@RequestParam String roleName) {
        return  roleRepository.findByRoleName(roleName)
                .map(role -> roleResponseMapper.roleToRoleResponse(role))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/active")
    @Operation(summary = "Get all roles with active status",
       description = "Get all roles with active status sorted by ASC creating time", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
       content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
       @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<List<RoleResponse>> getActiveRoles() {
        return Optional.ofNullable(roleRepository.findAllWithSpecificStatus("A").isEmpty() ? null:roleRepository.findAllWithSpecificStatus("A"))
                .map(role -> roleResponseMapper.roleListToRoleResponseList(role))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/inactive")
    @Operation(summary = "Get all roles with inactive status",
       description = "Get all roles with inactive status sorted by ASC creating time", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
       content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
       @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<List<RoleResponse>> getInactiveRoles() {
        return Optional.ofNullable(roleRepository.findAllWithSpecificStatus("I").isEmpty() ? null:roleRepository.findAllWithSpecificStatus("I"))
                .map(role -> roleResponseMapper.roleListToRoleResponseList(role))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @PutMapping("/inactive/{id}")
    @Operation(summary = "update the status of role by id",
       description = "update the status of role by id with active status", tags = {"Role"})
    @ApiResponses(value={@ApiResponse(responseCode = "200", description = "Success",
       content = {@Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))}),
       @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<Integer> updateRoleToInactive(@PathVariable long id) {
        int status = roleRepository.updateRoleStatus(id);
        if(status != 1){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
