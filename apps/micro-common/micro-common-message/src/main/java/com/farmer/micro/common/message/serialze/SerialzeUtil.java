package com.farmer.micro.common.message.serialze;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SerialzeUtil {

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T serialze(String str,Class<T> tClass) throws IOException {

        return objectMapper.readValue(str,tClass);
    }
}
