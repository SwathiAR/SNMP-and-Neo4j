package com.torana.training.neo4jDAO.fileCopier;

import java.io.IOException;

/**
 * Created by swathi on 7/6/2015..
 */
public class Main {

    public static void main(String args[]) throws IOException {

        UserInputFileNameReader userInputFileNameReader = new UserInputFileNameReaderImpl();
        FileCopier fileCopier = new FileCopierBasedOnInputStream();
        String sourceFileName = userInputFileNameReader.getSourceFileName();
        String destFileName = userInputFileNameReader.getDestFileName();
        fileCopier.copyFile(sourceFileName, destFileName);

    }
}
