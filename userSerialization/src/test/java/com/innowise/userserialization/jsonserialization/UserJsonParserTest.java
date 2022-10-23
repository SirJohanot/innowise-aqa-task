package com.innowise.userserialization.jsonserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.innowise.userserialization.entity.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserJsonParserTest {

    private static final UserDto SAMPLE_USER = new UserDto(1, "Tom", "Smith", "tomsmith@mail.com", "12345", "01-01-1999");
    private static final String SAMPLE_JSON = "{\"id\":1,\"firstName\":\"Tom\",\"lastName\":\"Smith\",\"email\":\"tomsmith@mail.com\",\"password\":\"12345\",\"birthday\":\"01-01-1999\"}";
    private UserJsonParser parser;

    @BeforeEach
    public void setup() {
        parser = new UserJsonParser();
    }

    @AfterEach
    public void tearDown() {
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
