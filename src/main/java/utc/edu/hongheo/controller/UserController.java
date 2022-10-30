package utc.edu.hongheo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utc.edu.hongheo.model.User;
import utc.edu.hongheo.service.Impl.JwtService;
import utc.edu.hongheo.service.Impl.OldPasswordService;
import utc.edu.hongheo.service.Impl.RoleService;
import utc.edu.hongheo.service.Impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@PropertySource("classpath:application.properties")
@CrossOrigin("*")
public class UserController extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    // Xóa
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iterable<User> users = userService.findAll();
        new ResponseEntity<>(users,HttpStatus.OK);
        return ;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Iterable<User> users = userService.findAll();
        return;
    }
}
