package com.sport.reactivesportproducer;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SportController {

    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping(value = "/sports", produces = "application/json")
    @ResponseBody
    public String getSports () throws IOException {
        Logger logger  = LoggerFactory.getLogger("request");
        logger.debug("getting a request {} ", System.currentTimeMillis());
        return sportService.getSports();
    }
}
