package com.lambdaschool.health.controller;

import com.lambdaschool.health.exception.ResourceNotFoundException;
import com.lambdaschool.health.model.ErrorDetail;
import com.lambdaschool.health.model.User;
import com.lambdaschool.health.model.UserInfo;
//import com.lambdaschool.health.service.UserInfoService;
import com.lambdaschool.health.service.UserInfoService;
import com.lambdaschool.health.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.awt.print.Book;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

@CrossOrigin //YOU CAN USE ORIGINS TO SPECIFY
@RestController
@RequestMapping(value = "/current")
public class UserInfoController
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/user", produces = {"application/json"})
    @CrossOrigin(origins = "http://https://samr-health.herokuapp.com/current")
    @ApiOperation(value = "Returns the current users info", response = UserInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Student Found", response = UserInfo.class),
            @ApiResponse(code = 404, message = "Student Not Found", response = ErrorDetail.class)
    })
    public ResponseEntity<?> listAllInfo()  {
        ArrayList<UserInfo> info = userInfoService.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();
        UserInfo user = userInfoService.findUserName(userName);
        if (authentication == null) {
            return new ResponseEntity<>("Couldnt Authenticate username", HttpStatus.FORBIDDEN);
        }
        if (user == null) {
            throw new ResourceNotFoundException("Could not find user");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }




    @PutMapping(value = "/user/{username}")
    @CrossOrigin(origins = "http://https://samr-health.herokuapp.com/current")
    @ApiOperation(value = "Updates the Users info", notes = "send body in a json with fname, lname, weight, or id", response = void.class)
    public ResponseEntity<?> updateUserInfo(@RequestBody UserInfo updateUserInfo, @PathVariable String username)
    {
        userInfoService.updateUserInfo(updateUserInfo, username);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
