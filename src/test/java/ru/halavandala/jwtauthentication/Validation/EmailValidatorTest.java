package ru.halavandala.jwtauthentication.Validation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmailValidatorTest {
    @Mock
    private EmailValidator testEmailValidator;

    @BeforeEach
    void setUp() {
        testEmailValidator = new EmailValidator();
    }

    @Test
    void itShouldValidateEmail() {
        List<String> validList = new ArrayList<>();
        validList.add("anton@mail.ru");
        validList.add("oxxxymiron@google.com");
        validList.add("v4sy4pupk1n@bk.ru");
        validList.add("antonio_banderos@mail.ru");
        validList.add("podpolkovnikbusterenko123@mail.ru");
            for(String s : validList) {
              boolean  expected = testEmailValidator.isValid(s);
            assertEquals(expected, true);
        }
    }

    @Test
    void itShouldNotValidateEmail() {
        List<String> invalidList = new ArrayList<>();
        invalidList.add("#anton@mail.ru");
        invalidList.add("%oxxxymiron@google.com");
        invalidList.add("v4sy__4pupk1n @bk.ru");
        invalidList.add("antonio_banderos@.ru");
        invalidList.add("2podpolkovnikbusterenko123@mail.ru.com..");
        for (String s : invalidList) {
            boolean expected = testEmailValidator.isValid(s);
            assertEquals(expected, false);
        }
    }
}