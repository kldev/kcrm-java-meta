package dev.kcrm.web.data.documents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.kcrm.web.security.PasswordHashUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "crm.user")
public class User {

    @Id
    private String id;

    @NotNull
    @Max(100)
    private String username;

    @JsonIgnore
    @NotNull
    @Max(100)
    private String password;

    @NotNull
    @Max(100)
    private String fullName;

    @NotNull
    @Max(100)
    private String email;

    private List<String> roles = new ArrayList<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    public static final class UserBuilder {
        private String id;
        private String username;
        private String password;
        private String fullName;
        private String email;
        private List<String> roles = new ArrayList<>();

        private UserBuilder() {
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = PasswordHashUtil.Hash(password);
            return this;
        }

        public UserBuilder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public UserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserBuilder withRole(String role) {

            if (this.roles.contains(role) ==  false) {
                this.roles.add(role);
            }


            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setRoles(roles);
            return user;
        }
    }
}
