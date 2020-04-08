package dev.kcrm.web.dto;

import dev.kcrm.web.security.WebApiUserDetails;

public class UserDto {

    private String id;
    private String username;
    private String roles;

    public UserDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }




    public static UserDto fromDetails(WebApiUserDetails details) {
        UserDto user = new UserDto();
        user.setId(details.getId());
        user.setRoles(String.join(",", details.getRoles()));
        user.setUsername(details.getUsername());

        return user;
    }
}
