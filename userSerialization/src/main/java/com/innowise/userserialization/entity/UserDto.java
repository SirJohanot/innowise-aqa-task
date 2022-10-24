package com.innowise.userserialization.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String birthday;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserDto(@JsonProperty("id") Integer id, @JsonProperty("firstName") String firstName, @JsonProperty("lastName") String lastName, @JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("birthday") String birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public Integer getId() {
        return id;
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

    public String getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDto userDto = (UserDto) o;

        if (id != null ? !id.equals(userDto.id) : userDto.id != null) {
            return false;
        }
        if (firstName != null ? !firstName.equals(userDto.firstName) : userDto.firstName != null) {
            return false;
        }
        if (lastName != null ? !lastName.equals(userDto.lastName) : userDto.lastName != null) {
            return false;
        }
        if (email != null ? !email.equals(userDto.email) : userDto.email != null) {
            return false;
        }
        if (password != null ? !password.equals(userDto.password) : userDto.password != null) {
            return false;
        }
        return birthday != null ? birthday.equals(userDto.birthday) : userDto.birthday == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
