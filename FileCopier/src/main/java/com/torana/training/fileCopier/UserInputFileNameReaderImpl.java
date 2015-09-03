package com.torana.training.neo4jDAO.fileCopier;

import java.util.Scanner;

/**
 * Created by swathi on 7/6/2015..
 */
public class UserInputFileNameReaderImpl implements UserInputFileNameReader {
    public String getSourceFileName() {
        System.out.println("Enter the fully qualified name of the source file");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String getDestFileName() {
        System.out.println("Enter the fully qualified destination file path");
        Scanner scanner1 = new Scanner(System.in);
        return scanner1.nextLine();
    }
}