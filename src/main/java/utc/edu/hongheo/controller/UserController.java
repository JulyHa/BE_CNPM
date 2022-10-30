package utc.edu.hongheo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utc.edu.hongheo.model.User;
import utc.edu.hongheo.service.Impl.JwtService;
import utc.edu.hongheo.service.Impl.OldPasswordService;
import utc.edu.hongheo.service.Impl.RoleService;
import utc.edu.hongheo.service.Impl.UserService;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class UserController{
//    @Autowired
//    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private OldPasswordService oldPasswordService;

    @GetMapping("users")
    public ResponseEntity<Iterable<User>> showAllUser(){
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/admin/users")
    public ResponseEntity<Iterable<User>> showAllUserByAdmin() {
        Iterable<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
