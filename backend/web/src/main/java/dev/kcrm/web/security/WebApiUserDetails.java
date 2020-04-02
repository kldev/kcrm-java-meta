package dev.kcrm.web.security;

import dev.kcrm.web.constant.UserRoles;
import dev.kcrm.web.data.documents.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebApiUserDetails implements UserDetails {

    private String id;
    private List<String> roles;
    private String username;
    private String password;

    public WebApiUserDetails() {
    }

    public WebApiUserDetails(String id, List<String> roles, String username, String password) {
        this.id = id;
        this.roles = roles;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<SimpleGrantedAuthority> roles = new ArrayList();

       for (String role : UserRoles.Roles) {
           if (getRoles().contains(role)) {
               roles.add(new SimpleGrantedAuthority(role));
           }
       }
        return roles;
    }

    @Override
    public String toString() {
        return "WebApiUserDetails{" +
                "id='" + id + '\'' +
                ", roles=" + roles +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static WebApiUserDetails convertFrom(User user) {
        return new WebApiUserDetails(user.getId(), user.getRoles(), user.getUsername(), user.getPassword());
    }
}
