package com.finalprojectandreeapasc.user.service;

import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import com.finalprojectandreeapasc.user.dto.UserListDTO;
import com.finalprojectandreeapasc.user.dto.UserMinimalDTO;
import com.finalprojectandreeapasc.user.mapper.UserMapper;
import com.finalprojectandreeapasc.user.model.ERole;
import com.finalprojectandreeapasc.user.model.Role;
import com.finalprojectandreeapasc.user.model.User;
import org.springframework.security.crypto.password.*;
import com.finalprojectandreeapasc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public List<UserMinimalDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserMinimalDTO create(UserMinimalDTO item) {
        User saveUser = userMapper.fromDTO(item);
        saveUser.setPassword(encoder.encode(item.getPassword()));
        UserMinimalDTO userDto = userMapper.toDTO(saveUser);
        return userMapper.toDTO(userRepository.save(
                userMapper.fromDTO(userDto)
        ));
    }

    public UserMinimalDTO update(Long id, UserMinimalDTO user) {
        User actUser = findById(id);
        actUser.setUsername(user.getName());
        actUser.setEmail(user.getEmail());
//        actUser.setRoles(user.getRoles().stream()
//                .map(role -> Role.builder().name(ERole.valueOf(role)).build())
//                .collect(Collectors.toSet()));
        return userMapper.toDTO(
                userRepository.save(actUser)
        );
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public UserMinimalDTO get(Long id) {
        return userMapper.toDTO(findById(id));
    }
}
