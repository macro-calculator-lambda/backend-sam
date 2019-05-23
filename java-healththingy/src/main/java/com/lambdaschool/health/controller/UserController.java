package com.lambdaschool.health.controller;

import com.lambdaschool.health.model.User;
import com.lambdaschool.health.model.UserInfo;
import com.lambdaschool.health.service.UserInfoService;
import com.lambdaschool.health.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserInfoService infoService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    @GetMapping(value = "/getusername", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }




    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user", consumes = {"application/json"}, produces = {"application/json"})
    @ApiOperation(value = "Creates A New user This is probably what front end needs most", notes = "Send with json with username and password", response = void.class)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);
        UserInfo newinfo = new UserInfo();
        newinfo.setUsername(newuser.getUsername());
        infoService.savename(newinfo);
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User updateUser, @PathVariable long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}