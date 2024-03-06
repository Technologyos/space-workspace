package com.technologyos.role.entities;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Role")
@Table(
   name = "role",
   uniqueConstraints = {
      @UniqueConstraint(name = "role_name_unique", columnNames = "role_name")
   }
)
@Data
public class Role {
    @Id
    @SequenceGenerator(
       name = "role_sequence",
       sequenceName = "role_sequence",
       allocationSize = 1
    )
    @GeneratedValue(strategy = SEQUENCE, generator = "role_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;

    @Column(name = "long_description", nullable = false, length = 200)
    private String longDescription;

    @Column(name = "role_status", nullable = false)
    private String status;

    @Column(name = "creating_date", nullable = false)
    private String creatingDate;
}
