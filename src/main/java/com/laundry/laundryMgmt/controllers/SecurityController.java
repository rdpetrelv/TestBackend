package com.laundry.laundryMgmt.controllers;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  Fatima GHAOUI
 * Controller class responsible for handling user-related security operations.
 * this controller provides endpoints for user authentication and retrieval.
 */
@CrossOrigin(origins = { "http://localhost:5173" }, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/users")
public class SecurityController {

    /**
     * Represents a user record containing the username.
     */
    public record User(String username) {
    }

    /**
     * Retrieves the username of the currently authenticated user.
     *
     * @param userDetails The UserDetails object containing information about the authenticated user.
     * @return A User record containing the username of the authenticated user.
     */
    @GetMapping(path = "/me")
    public User findUserName(@AuthenticationPrincipal UserDetails userDetails) {
        return new User(userDetails.getUsername());
    }

}