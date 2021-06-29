package com.finalprojectandreeapasc;
import com.finalprojectandreeapasc.recipes.model.dto.RecipeDto;
import com.finalprojectandreeapasc.recipes.repository.RecipeRepository;
import com.finalprojectandreeapasc.recipes.service.RecipeService;
import com.finalprojectandreeapasc.security.dto.SignupRequest;
import com.finalprojectandreeapasc.user.model.ERole;
import com.finalprojectandreeapasc.user.model.Role;
import com.finalprojectandreeapasc.user.repository.RoleRepository;
import com.finalprojectandreeapasc.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.finalprojectandreeapasc.security.*;
import java.util.Set;
import com.finalprojectandreeapasc.recipes.model.*;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final RecipeRepository itemRepository;

    private final RecipeService recipeService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            itemRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("user1@email.com")
                    .username("user1")
                    .password("WooHoo1!")
                    .roles(Set.of("CUSTOMER"))
                    .build());

            authService.register(SignupRequest.builder()
                    .email("manager1@email.com")
                    .username("manager1")
                    .password("Manager1!")
                    .roles(Set.of("MANAGER"))
                    .build());

            recipeService.create(RecipeDto.builder()
                    .title("Pancakes")
                    .ingredients("1 cup all-purpose flour.\n" +
                            "2 tablespoons white sugar.\n" +
                            "2 teaspoons baking powder.\n" +
                            "1 teaspoon salt.\n" +
                            "1 egg, beaten.\n" +
                            "1 cup milk.\n" +
                            "2 tablespoons vegetable oil.\n")
                    .quantity(100)
                    .build());
        }
    }
}