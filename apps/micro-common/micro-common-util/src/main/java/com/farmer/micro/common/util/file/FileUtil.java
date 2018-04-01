package com.farmer.micro.common.util.file;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public final class FileUtil {

    public static void writeToFile(String body,String path) {

        File file = new File(path);
        try {
            Files.write(body,file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}