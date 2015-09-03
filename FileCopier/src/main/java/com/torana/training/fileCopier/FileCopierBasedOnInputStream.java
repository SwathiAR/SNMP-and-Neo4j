package com.torana.training.neo4jDAO.fileCopier;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by swathi on 7/6/2015..
 */
public class FileCopierBasedOnInputStream implements FileCopier {
    public Path copyFile(String sourceFile, String destFile) throws IOException {
        InputStream inputStream = new FileInputStream(sourceFile);
        OutputStream outputStream = new FileOutputStream(destFile);
        int read;
        while((read = inputStream.read()) > 0) {
                outputStream.write(read);
            }
        return Paths.get(destFile);
    }
}