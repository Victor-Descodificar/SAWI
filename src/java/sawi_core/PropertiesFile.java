package sawi_core;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author VictorFerreiraPereir
 */
public class PropertiesFile {

    /**
     * Method <b>getValues(String key)</b> Read value from properties file (txt
     * file), according the key passed as parameter.
     *
     * @param String key
     * @author victorf
     */
    String getValues(String key) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(Constants.PATH + Constants.PROPERTIES_FILE));
            Object[] list = br.lines().toArray();
            for (Object s : list) {
                if (s.toString().split("=")[0].equals(key)) {
                    String r = s.toString().split("=")[1];
                    br.close();
                    return r;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "VALUE NOT FOUND!";
    }

}
