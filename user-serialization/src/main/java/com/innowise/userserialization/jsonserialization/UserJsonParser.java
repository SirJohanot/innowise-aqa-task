package com.innowise.userserialization.jsonserialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.userserialization.entity.UserDto;

public class UserJsonParser {

    public UserJsonParser() {
    }

    public String serialize(UserDto userDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userDto);
    }

    public UserDto deserialize(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, UserDto.class);
    }
}
