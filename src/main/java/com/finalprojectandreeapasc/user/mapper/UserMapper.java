package com.finalprojectandreeapasc.user.mapper;

import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import com.finalprojectandreeapasc.user.dto.UserListDTO;
import com.finalprojectandreeapasc.user.dto.UserMinimalDTO;
import com.finalprojectandreeapasc.user.model.User;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "name", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserListDTO userListDtoFromUser(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO toDTO(User user);

    @Mappings({
            @Mapping(target = "username", source = "user.name"),
            @Mapping(target = "roles", ignore = true)
    })
    User fromDTO(UserMinimalDTO user);
}
