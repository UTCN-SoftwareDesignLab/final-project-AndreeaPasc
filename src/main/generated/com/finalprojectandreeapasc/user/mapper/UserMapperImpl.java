package com.finalprojectandreeapasc.user.mapper;

import com.finalprojectandreeapasc.user.dto.UserListDTO;
import com.finalprojectandreeapasc.user.dto.UserMinimalDTO;
import com.finalprojectandreeapasc.user.model.User;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-06-29T20:58:21+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserMinimalDTO userMinimalFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        UserMinimalDTO userMinimalDTO = new UserMinimalDTO();

        userMinimalDTO.setName( user.getUsername() );
        userMinimalDTO.setId( user.getId() );
        userMinimalDTO.setEmail( user.getEmail() );
        userMinimalDTO.setPassword( user.getPassword() );

        return userMinimalDTO;
    }

    @Override
    public UserListDTO userListDtoFromUser(User user) {
        if ( user == null ) {
            return null;
        }

        String email = null;

        email = user.getEmail();

        Set<String> roles = null;

        UserListDTO userListDTO = new UserListDTO( email, roles );

        userListDTO.setName( user.getUsername() );
        userListDTO.setId( user.getId() );
        userListDTO.setPassword( user.getPassword() );

        populateRoles( user, userListDTO );

        return userListDTO;
    }

    @Override
    public UserMinimalDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserMinimalDTO userMinimalDTO = new UserMinimalDTO();

        userMinimalDTO.setName( user.getUsername() );
        userMinimalDTO.setId( user.getId() );
        userMinimalDTO.setEmail( user.getEmail() );
        userMinimalDTO.setPassword( user.getPassword() );

        return userMinimalDTO;
    }

    @Override
    public User fromDTO(UserMinimalDTO user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setUsername( user.getName() );
        user1.setId( user.getId() );
        user1.setEmail( user.getEmail() );
        user1.setPassword( user.getPassword() );

        return user1;
    }
}
