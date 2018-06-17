package sawi_core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import sawi_core.Constants.BrowserType;

/**
 *
 * @author VictorFerreiraPereir
 */
public class Datapool {

    private String tcNumber;
    private String scriptName;
    private boolean run = false;
    private BrowserType browser;
    private String environment;
    private static ArrayList<String> datapool;

    /**
     * Method <b>Datapool(String path)</b>
     * Overloaded constructor to load the external file.
     *
     * @param String path
     * @author victorf
	 *
     */
    Datapool(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            datapool = new ArrayList<>();
            while (br.ready()) {
                datapool.add(br.readLine());
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int getColumn(int line, String key) {
        String[] elements = datapool.get(line).split(";");
        int size = elements.length;

        for (int column = 0; column < size; column++) {
            if (elements[column].equals(key)) {
                return column;
            }
        }
        return 0;
    }

    public int getLine(int column, String key) {
        int size = datapool.size();
        for (int line = 0; line < size; line++) {
            if (datapool.get(line).split(";")[column].equals(key)) {
                return line;
            }
        }
        return 0;
    }

    /**
     * Method <b>getElementDatapool(int line, int column)</b>
     * Get one element from .csv file, receiving as parameter the line and
     * column of the element target. The .csv need to have semicolon as
     * separator.
     *
     * @param int line
     * @param int column
     * @author victorf
	 *
     */
    String getElementDatapool(int line, int column) {
        return datapool.get(line).split(";")[column];
    }

    /**
     * Method <b>getDatapoolSize()</b>
     * Get one element from .csv file, receiving as parameter the line and
     * column of the element target. The .csv need to have semicolon as
     * separator.
     *
     * @param int line
     * @param int column
     * @author victorf
	 *
     */
    public int getDatapoolSize() {
        return datapool.size();
    }

    /**
     * Method <b>getTcNumber()</b>
     * Returns <i>tcNumber</i> variable value.
     *
     * @param int column
     * @author victorf
	 *
     */
    public String getTcNumber() {
        return tcNumber;
    }

    /**
     * Method <b>getTcNumber()</b>
     * Returns <i>scriptName</i> variable value.
     *
     * @param int column
     * @author victorf
	 *
     */
    public String getScriptName() {
        return scriptName;
    }

    /**
     * Method <b>getRun()</b>
     * Returns <i>run</i> variable value.
     *
     * @param int column
     * @author victorf
	 *
     */
    public boolean getRun() {
        return run;
    }

    /**
     * Method <b>getBrowser()</b>
     * Returns <i>browser</i> variable value.
     *
     * @param int column
     * @author victorf
	 *
     */
    public BrowserType getBrowser() {
        return browser;
    }

    /**
     * Method <b>getEnvironment()</b>
     * Returns <i>environment</i> variable value.
     *
     * @param int column
     * @author victorf
	 *
     */
    public String getEnvironment() {
        return environment;
    }

}
