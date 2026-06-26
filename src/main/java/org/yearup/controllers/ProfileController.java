package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

// notes: needs a GET method getProfile(), and a PUT method create(). create() is already built in service class.
// needs an @autowired constructor to inject ProfileService (do we need to inject UserService too??) (Yes, I think)
@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController
{
    private ProfileService profileService;
    private UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService)
    {
        this.profileService = profileService;
        this.userService = userService;
    }



    // notes: get the USERS profile from the server. How do you get specifically the users profile?
    // Hello, it's me from the future, I looked at the ShoppingCart controller since it is doing a similar action.
    @GetMapping("{profile}")
    @PreAuthorize("hasRole")
    public ProfileService getById(@PathVariable String profile)
    {
        Profile p
        return
    }
}

