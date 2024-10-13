package com.tryhard.maxnumber.controllers;

import com.tryhard.maxnumber.model.MaxNumberResponse;
import com.tryhard.maxnumber.services.MaxNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/number")
@RequiredArgsConstructor
public class NumberRestController {
    @Autowired
    private MaxNumberService maxNumberService;

    @GetMapping(value = "/{index}")
    public MaxNumberResponse getMaxNumber(@PathVariable int index, @RequestParam String filepath) throws IOException {
        return maxNumberService.getMaxNumber(index, filepath);
    }
}
