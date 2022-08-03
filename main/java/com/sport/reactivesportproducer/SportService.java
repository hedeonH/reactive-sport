package com.sport.reactivesportproducer;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SportService {

    public String getSports() throws IOException {
        String data1 = "C:\\mentoring2022\\reactive-sport-producer\\src\\main\\resources\\package.json";
        return new String(Files.readAllBytes(Paths.get(data1)));
    }
}
