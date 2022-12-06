package com.nju.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description
 * @date:2022/12/6 23:06
 * @author: qyl
 */
public class ReaderUtils {
    private static final BufferedReader READER = new BufferedReader (new InputStreamReader (System.in));
    public static final String OVER = "over";

    public static String getInputMsg() throws IOException {
        return READER.readLine ( );
    }
}
