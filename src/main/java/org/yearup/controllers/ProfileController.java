package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.service.ProfileService;
import org.yearup.service.UserService;

import java.security.Principal;

// notes: needs a GET method getProfile(), and a PUT method create(). create() is already built in service class.
// needs an @autowired constructor to inject ProfileService (do we need to inject UserService too??) (Yes, I think)
@RestController
@RequestMapping("profile")
@CrossOrigin
@PreAuthorize("isAuthenticated()")

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
    // Hello, it's me from the future, I looked at the ShoppingCart controller since it is doing a similar action. Use principal!
    @GetMapping
    public Profile getUserProfile(Principal principal)
    {
        String username = principal.getName();

        User user = userService.getByUserName(username);
        int userId = user.getId();

        return profileService.getByUserId(userId);
    }

    // notes: update the users profile information. use @RequestBody on the Profile class.
    // pass a Principal object (specifies username) and a Profile object (w/ data changes via @RequestBody)
    @PutMapping
    public Profile updateProfile(Principal principal, @RequestBody Profile profile)
    {
        String username = principal.getName();

        User user = userService.getByUserName(username);
        int userId = user.getId();

        return profileService.update(userId, profile);
    }

}

