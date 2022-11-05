package com.innowise.userserialization.jsonserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innowise.userserialization.entity.UserDto;
import org.junit.jupiter.api.*;

import java.text.ParseException;

public class UserJsonParserTest {

    private static UserDto SAMPLE_USER;
    private static String SAMPLE_JSON;
    private UserJsonParser parser;

    @BeforeAll
    static void setupBeforeAll() throws ParseException {
        SAMPLE_USER = new UserDto(1, "Tom", "Smith", "tomsmith@mail.com", "12345", "1999-09-17");
        SAMPLE_JSON = "{\"id\":1,\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"email\":\"tomsmith@mail.com\",\"password\":\"12345\",\"birthday\":\"1999-09-17\"}";
    }

    @BeforeEach
    public void setupBeforeEach() {
        parser = new UserJsonParser();
    }

    @AfterEach
    public void tearDownAfterEach() {
        parser = null;
    }

    @Test
    public void testSerializeShouldReturnTheCorrectJsonString() throws JsonProcessingException {
        //given
        //when
        String actualJson = parser.serialize(SAMPLE_USER);
        //then
        Assertions.assertEquals(SAMPLE_JSON, actualJson);
    }

    @Test
    public void testDeserializeShouldReturnTheCorrectJsonString() throws JsonProcessingException {
        //given
        //when
        UserDto actualUser = parser.deserialize(SAMPLE_JSON);
        //then
        Assertions.assertEquals(SAMPLE_USER, actualUser);
    }
}
