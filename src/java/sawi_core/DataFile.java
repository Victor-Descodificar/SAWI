package sawi_core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author VictorFerreiraPereir
 */
public class DataFile {

    private static ArrayList<String> configFile;

    /**
     * Method <b>DataFile(String path)</b>
     * Overloaded constructor to load the external file.
     *
     * @param String path
     * @author victorf
     *
     */
    public DataFile(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            configFile = new ArrayList<>();
            while (br.ready()) {
                configFile.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method <b>DataFile()</b>
     * Default constructor
     *
     * @author victorf
     *
     */
    public DataFile() {
    }

    /**
     * Method <b>getElementConfigFile(int line, int column)</b>
     * Get one element from .csv file, receiving as parameter the line and
     * column of the element target. The .csv need to have semicolon as
     * separator.
     *
     * @param int line
     * @param int column
     * @author victorf
     *
     */
    public String getElementConfigFile(int line, int column) {
        return configFile.get(line).split(";")[column];
    }

    /**
     * Method <b>writeFile(String fileName, String data)<b>
     * Write text in a external file.
     *
     * @param String fileName
     * @param String data
     * @author victorf
     *
     */
    void writeFile(String fileName, String data) {
        try {
            FileWriter fileWritter = new FileWriter(fileName);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data + "\r\n");
            bufferWritter.flush();
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method <b>appendFile(String fileName, String data)<b>
     * Append text to an existent external file.
     *
     * @param String fileName
     * @param String data
     * @author victorf
     *
     */
    void appendFile(String fileName, String data) {
        try {
            FileWriter fileWritter = new FileWriter(fileName, true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.append(data + "\r\n");
            bufferWritter.flush();
            bufferWritter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
