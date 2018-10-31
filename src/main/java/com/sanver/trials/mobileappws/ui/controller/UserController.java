package com.sanver.trials.mobileappws.ui.controller;

import com.sanver.trials.mobileappws.service.UserService;
import com.sanver.trials.mobileappws.shared.dto.UserDto;
import com.sanver.trials.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.sanver.trials.mobileappws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getUser() {
        return "Get user is called";
    }

    @PostMapping
    public UserRest postUser(@RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);
        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);
        return returnValue;
    }

    @PutMapping
    public String putUser() {
        return "Put user is called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete user is called";
    }
}
