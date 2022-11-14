package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.RoleType;
import com.ilCary.EPIC_ENERGY_SERVICES.models.User;
import com.ilCary.EPIC_ENERGY_SERVICES.services.RoleService;
import com.ilCary.EPIC_ENERGY_SERVICES.services.UserService;

@RestController
@RequestMapping("/api/users/") //TODO impostare la rotta
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @PostMapping
    public User saveUser(
            @RequestParam(value="name",required=false) String name,
            @RequestParam(value="lastname",required=false) String lastname,
            @RequestParam(value="username",required=true) String username,
            @RequestParam(value="email",required=false) String email,
            @RequestParam(value="password",required=true) String password
    ) {
        User user = User.builder()
        		.name(name)
        		.lastname(lastname)
        		.username(username)
        		.email(email)
        		.password(password)
        		.build();

        logger.info("Save User in UserController");
        return userService.save(user);
    }

    @GetMapping
    public List<User> getUserList() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "User deleted successfully";
    }
    
	@PutMapping("/{id}/add-role/{roleType}")
	public void addRole(@PathVariable("id") Long id, @PathVariable("roleType") RoleType roleType) {
		User u = userService.getById(id);
		u.addRole(roleService.getByRole(roleType));
		
		
		userService.save(u);
	}

    @PutMapping("{id}")
    public User updateUser(
            @PathVariable("id") Long id
//            @RequestParam("name") String name
            ) {

        User user = userService.getById(id);

        //TODO gestire il put

        userService.save(user);
        return user;
    }

}
