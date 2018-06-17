package sawi_core;

import sawi_scripts.Regression;

/**
 *
 * @author VictorFerreiraPereir
 */
public class LoadScripts {

    boolean loadScripts(String param) {

        switch (param) { // Get the tc number
            case "TC001":
                return Regression.testLogin();
            case "TC002":
                return Regression.testLogout();
            default:
                return false;
        }
    }

}
