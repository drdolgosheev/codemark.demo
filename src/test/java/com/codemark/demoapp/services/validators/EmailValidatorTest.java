package com.codemark.demoapp.services.validators;

import com.codemark.demoapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmailValidatorTest {

    @Test
    void validate() {
        PasswordValidator validator = new PasswordValidator();

        String pass1 = "Sa^12345";
        String pass2 = "123345678";
        String pass3 = "saksajasjkajkskjsa";

        assertTrue(validator.validate(pass1));
        assertFalse(validator.validate(pass2));
        assertFalse(validator.validate(pass3));

    }
}