package libedit.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileEdit {

    public static File removeLineFromFile(File source, int lineNumber) {
        File tempFile = new File(source.getAbsolutePath() + "_temp#");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            int currentLineNum = 0;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLineNum == lineNumber) {
                    currentLineNum++;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
                currentLineNum++;
            }
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Source file not found");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
            return null;
        }
        return tempFile;
    }

    public static File removeLineFromFile(File source, String lineContent) {
        File tempFile = new File(source.getAbsolutePath() + "_temp#");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineContent)) {
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Source file not found");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.err.println("File write error");
            e.printStackTrace();
            return null;
        }
        return tempFile;
    }
}
