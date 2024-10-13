package com.tryhard.maxnumber.services;

import com.tryhard.maxnumber.model.MaxNumberResponse;

import java.io.IOException;

public interface MaxNumberService {

    MaxNumberResponse getMaxNumber(int index, String filepath) throws IOException;
}
