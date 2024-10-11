package com.tryhard.maxnumber.services;

import java.io.IOException;

public interface MaxNumberService {

    String getMaxNumber(int order, String filepath) throws IOException;
}
