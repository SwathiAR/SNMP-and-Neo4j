package com.torana.training.neo4jDAO.fileCopier;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by swathi on 7/6/2015..
 */
public interface FileCopier {

     Path copyFile(String sourceFile, String destFile) throws IOException;
}
