package sawi_scripts;

import static sawi_core.Constants.FAIL;
import static sawi_core.Constants.SUCCESS;
import sawi_core.Report;

/**
 *
 * @author VictorFerreiraPereir
 */
public class Regression {

    public static boolean testLogin() {
        try {
            System.out.println("Running test Login script!");
            System.out.println("Executed testLogin");
            Report.create_Log_File("Test Login");
            Report.log_End_Script(new Report("teste login"));
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
    }

    public static boolean testLogout() {
        try {
            System.out.println("Running test Logout script!");
            System.out.println("Executed testLogout");
            Report.create_Log_File("Test Login");
            Report.log_End_Script(new Report("teste login"));
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return FAIL;
        }
    }
}
