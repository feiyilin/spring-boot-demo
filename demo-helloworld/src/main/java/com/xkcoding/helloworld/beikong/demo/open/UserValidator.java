package com.xkcoding.helloworld.beikong.demo.open;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UserValidator implements DataValidator<User> {
    @Override
    public ValidationResult validate(User user) {
        ValidationResult result = new ValidationResult();
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            result.setValid(false);
            result.setErrorMessage("Username cannot be empty");
        } else if (user.getEmail() == null || user.getEmail().isEmpty()) {
            result.setValid(false);
            result.setErrorMessage("Email cannot be empty");
        } else {
            result.setValid(true);
        }
        return result;
    }

}
