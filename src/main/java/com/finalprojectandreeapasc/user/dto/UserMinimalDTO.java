package com.finalprojectandreeapasc.user.dto;

import com.finalprojectandreeapasc.user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserMinimalDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
}
