package ua.finalproject.model.entity;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleType role;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }

    public static Builder builder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(long id) {
            User.this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            User.this.firstName = firstName;

            return this;
        }

        public Builder setLastName(String lastName) {
            User.this.lastName = lastName;

            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;

            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;

            return this;
        }

        public Builder setRole(RoleType role) {
            User.this.role = role;

            return this;
        }

        public User build() {
            return User.this;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
