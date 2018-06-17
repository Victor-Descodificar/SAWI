/**
 *
 * @author VictorFerreiraPereir
 */
package sawi_core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;

public class Constants {

    /**
     * <b>Constants class</b>
     * Will keep all the static final variables in the project.
     *
     * @author victorf
     *
     *
     */
    // This is a enum type, to be used in browser's choice
    enum BrowserType {
        FIREFOX, CHROME, IE, HTMLUNIT
    }

    /* Determine the execution type */
    static enum ExecutionType {
        BACKGROUND, WEBINTERFACE
    }

    static final short TIME_WAIT = 30;


    /* Final log message from all scripts */
    static final String END_OF_SCRIPT = "End of script";

    /* Path to sources */
    final static String PATH = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath().replaceAll("%20", " ")).toString().concat("\\");

    /* Get the path of configuration files */
    static final String PROPERTIES_FILE = "config.properties";

    /* Gecko driver path */
    static final String FIREFOX_DRIVER = PATH.concat("geckodriver.exe");
    static final String CHROME_DRIVER = PATH.concat("chromedriver.exe");
    static final String IE_DRIVER = PATH.concat("IEDriverServer.exe");

    /* Keywords from datapool column title */
    static final String SCRIPT_TITLE = "testcasetitle";
    static final String SCRIPT_NAME = "name";
    static final String SCRIPT_EXECUTION = "run";
    static final String SCRIPT_BROWSER = "browser";
    static final String SCRIPT_ENV = "environment";

    // Scripts status
    public static final boolean SUCCESS = true;
    public static final boolean FAIL = false;

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);

        } catch (InterruptedException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sleepMiliseconds(int miliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(miliseconds);

        } catch (InterruptedException ex) {
            Logger.getLogger(Constants.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
