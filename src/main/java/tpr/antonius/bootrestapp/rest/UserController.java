package tpr.antonius.bootrestapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tpr.antonius.bootrestapp.data.User;
import tpr.antonius.bootrestapp.data.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
        User user = new User();
        user.setName("Augustin");
        repository.save(user);
    }

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return repository.findAll().stream()
                .map(user -> new UserDto() {{
                    setId("" + user.getId());
                    setName(user.getName());
                }})
                .collect(Collectors.toList());
    }

}