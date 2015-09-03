package com.torana.training.neo4jDAO.fileCopier;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by swathi on 7/6/2015..
 */
public class FileCopierBasedOnJava7Edition implements FileCopier {
    public Path copyFile(String sourceFile, String destFile) throws IOException {
        return Files.copy(Paths.get(sourceFile), Paths.get(destFile), StandardCopyOption.REPLACE_EXISTING);
    }
}
