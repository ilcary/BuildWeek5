package com.ilCary.EPIC_ENERGY_SERVICES.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilCary.EPIC_ENERGY_SERVICES.models.Invoice;
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
    
    
//---------------------------- Get ---------------------------------
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Page<User>> getUserList(Pageable p) {
    	
    	Page<User> res = userService.getAll(p);
        
    	 if (res.isEmpty()){
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } else{
             return new ResponseEntity<>(res, HttpStatus.OK);
         }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getById(id);
    }
    
//---------------------------- Post --------------------------------

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
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
        		.active(true)
        		.build();

        logger.info("Save User in UserController");
        return userService.save(user);
    }

//---------------------------- Put ---------------------------------
    
	@PutMapping("/{id}/add-role/{roleType}")
	@PreAuthorize("hasRole('ADMIN')")
	public void addRole(@PathVariable("id") Long id, @PathVariable("roleType") RoleType roleType) {
		User u = userService.getById(id);
		u.addRole(roleService.getByRole(roleType));
		
		
		userService.save(u);
		 logger.info("Role added");
	}

    @PutMapping("{id}")
    public User updateUser(
            @PathVariable("id") Long id,
            @RequestParam(value="name",required=false) String name,
            @RequestParam(value="lastname",required=false) String lastname,
            @RequestParam(value="username",required=false) String username,
            @RequestParam(value="email",required=false) String email,
            @RequestParam(value="password",required=false) String password
            ) {

        User user = userService.getById(id);

        if(name != null) user.setName(name);
        if(lastname != null) user.setLastname(lastname);
        if(username != null) user.setUsername(username);
        if(email != null) user.setEmail(email);
        if(password != null) user.setPassword(password);

        userService.save(user);
        return user;
    }

// -------------------------- Delete -------------------------------

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "User deleted successfully";
    }
    
    
 // ---------------------------- Tests --------------------------   
    
    @GetMapping("users-paginate-byname/{name}")
    public Page<User> getByNameAndPaginate(@PathVariable("name")String name, Pageable p){
        Page<User> res =  userService.getByNameAndPaginate(name, p);
        return res;

    }
    
    @GetMapping("test/test1")
    public String test1(){
        return "java";
    }
    
    @GetMapping("test/test2")
    public String test2(){
        return "java";
    }
    
}
