package sawi_core;

/**
 *
 * @author VictorFerreiraPereir
 */
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.GridType;
import com.relevantcodes.extentreports.LogStatus;

public class Report {

    private static ExtentReports extent;
    private static String logName;
    private static String startTime;
    private static String imageLocation;
    private static String totalTime;
    private static String path;
    private static boolean checkStatusLogFail = false; // to check if any log will be called
    private static boolean checkStatusLogError = false; // to check if any log crashed
    private String scriptName = "";
    private String message = "";
    private Exception e = null;

    /**
     * Method <b>Report(String scriptName)</b>
     * Overloaded constructor method.
     *
     * @param String scriptName
     * @author victorf
	 *
     */
    public Report(String scriptName) {
        this.scriptName = scriptName;
    }

    /**
     * Method <b>Report(String scriptName, String message)</b>
     * Overloaded constructor method.
     *
     * @param String scriptName
     * @param String message
     * @author victorf
	 *
     */
    public Report(String scriptName, String message) {
        this.scriptName = scriptName;
        this.message = message;
    }

    /**
     * Method <b>Report(String scriptName, String message)</b>
     * Overloaded constructor method.
     *
     * @param String scriptName
     * @param String message
     * @param Exception e
     * @author victorf
	 *
     */
    public Report(String scriptName, String message, Exception e) {
        this.scriptName = scriptName;
        this.message = message;
        this.e = e;
    }

    /**
     * Method <b>Report(String scriptName, Exception e)</b>
     * Overloaded constructor method.
     *
     * @param String scriptName
     * @param Exception e
     * @author victorf
	 *
     */
    public Report(String scriptName, Exception e) {
        this.scriptName = scriptName;
        this.e = e;
    }

    /**
     * Method <b>log_Skip(Report gr)</b>
     * Called when test is skipped. Create log with SKIP status.
     *
     * @param Report gr
     * @author victorf
	 *
     */
    public static void log_Skip(Report gr) {
        create_Report(Status.SKIP, gr.scriptName, gr.message, true);
        checkStatusLogFail = true;
    }

    /**
     * Method <b>log_Fail(Report gr)</b>
     * Create log with FAIL status.
     *
     * @param Report gr
     * @author victorf
	 *
     */
    public static void log_Fail(Report gr) {
        create_Report(Status.FAIL, gr.scriptName, gr.message, true);
        checkStatusLogFail = true;
    }

    /**
     * Method <b>log_Script_Error(Report gr)</b>
     * Create log with ERROR status.
     *
     * @param Report gr
     * @author victorf
	 *
     */
    public static void log_Script_Error(Report gr) {
        create_Report(Status.ERROR, gr.scriptName, get_Method_Name(gr.e), true);
        checkStatusLogError = true;
    }

    /**
     * Method <b>log_Element_Not_Found(Report gr)</b>
     * Create log with FAIL status.
     *
     * @param Report gr
     * @author victorf
	 *
     */
    public static void log_Element_Not_Found(Report gr) {
        create_Report(Status.FAIL, gr.scriptName, gr.e.getMessage().split("}")[0] + "}", true);
        checkStatusLogFail = true;
    }

    public static void log_Fatal(Report gr) {
        create_Report(Status.FATAL, gr.scriptName, get_Method_Name(gr.e), true);
        checkStatusLogError = true;
    }

    public static void log_End_Script(Report gr) {
        try {
            // If log failed and log error were not called, means the test
            // passed successfully
            if (checkStatusLogFail == false) {
                if (checkStatusLogError == false) {
                    create_Report(Status.PASS, gr.scriptName, Constants.END_OF_SCRIPT + calculateTotalTimeSpent(),
                            false);
                }
            }
            // If any of the logs fail or log error were called, the final
            // result will be a test failed
            if (checkStatusLogFail || checkStatusLogError) {
                create_Report(Status.FAIL, gr.scriptName, Constants.END_OF_SCRIPT + calculateTotalTimeSpent(), false);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Calculate the total time for the script
    private static String calculateTotalTimeSpent() throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date st = format.parse(startTime);
        Date et = new Date();
        long diff = et.getTime() - st.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        // For .xls log
        totalTime = diffMinutes + " min " + diffSeconds + " sec";
        return "  Total execution time: " + totalTime;
    }

    /**
     * Generates the HMTL page. This method is required to start the log
     * creation.
     *
     * @throws Exception
     */
    public static void create_Log_File(String scriptName) throws Exception {

        // Get the start time execution
        DateFormat st = new SimpleDateFormat("HH:mm:ss");
        startTime = st.format(new Date());
        DateFormat dateFormatDir = new SimpleDateFormat("MM-dd-yyyy");
        path = new PropertiesFile().getValues("LOG_PATH") + "/" + dateFormatDir.format(new Date());

        String reportLocationHtml = path + "/log_html";
        // Preventing FileNotFoundException. Creating the directories
        File f = new File(reportLocationHtml);
        if (!f.exists()) {
            f = new File(reportLocationHtml);
            f.mkdirs();
        }

        String reportLocationText = path + "/log_text";
        File g = new File(reportLocationText);
        if (!g.exists()) {
            g = new File(reportLocationText);
            g.mkdirs();
        }

        imageLocation = reportLocationHtml + "/images/";
        File h = new File(imageLocation);
        if (!h.exists()) {
            h = new File(imageLocation);
            h.mkdirs();
        }

        // For extent reports log
        DateFormat dateFormat = new SimpleDateFormat("HH mm ss - MM-dd-yyyy");

        // Create the file of extent reports
        extent = ExtentReports.get(Report.class);
        extent.init(reportLocationHtml + "/Log " + scriptName + " - " + dateFormat.format(new Date()) + ".html", true,
                DisplayOrder.BY_OLDEST_TO_LATEST, GridType.STANDARD);
        extent.config().documentTitle("Log for OOBT system");
        extent.config().reportHeadline("Log report page for OOBT");

        // For text file log
        logName = reportLocationText + "/" + scriptName + " - " + dateFormat.format(new Date()) + ".log";
        new DataFile().writeFile(logName, ""); // Write the new log file
        new DataFile().appendFile(logName, "Script name: " + scriptName);
        DateFormat date2 = new SimpleDateFormat("MM-dd-yyyy");
        new DataFile().appendFile(logName, "Start time and date: " + date2.format(new Date()) + " " + startTime);

    }

    private enum Status {
        ERROR, FAIL, FATAL, INFO, PASS, SKIP, WARNING;
    }

    private static String get_Method_Name(Exception e) {

        StackTraceElement[] stack = e.getStackTrace();
        String stackTrace = "";
        int size = stack.length;
        String errorCause = "";

        if (e.getCause() != null) {
            errorCause = e.getCause().toString();
        } else {
            errorCause = "Unknown";
        }

        // Get the first 5 occurrences
        for (int i = 0; i < 5; i++) {
            if (i < size) {
                stackTrace = stackTrace + "\r\n" + stack[i].getMethodName();
            }
        }
        return "Stack trace: " + stackTrace + "\r\n Error cause: " + errorCause;
    }

    private static void create_Text_Log(Status status, String scriptName, String description) throws IOException {

        DateFormat occurrence = new SimpleDateFormat("HH:mm:ss");

        new DataFile().appendFile(logName, "Status " + status + " - Occurrence time: " + occurrence.format(new Date()));
        if (!description.trim().isEmpty() || !description.equals(" ")) {
            new DataFile().appendFile(logName, "Description: " + description + "\r\n");
        }
    }

    /**
     * Status can be: <br>
     * ERROR, FAIL, FATAL, INFO, PASS, SKIP or WARNING.
     *
     * @throws IOException
     */
    private static void create_Report(Status status, String methodName, String description, boolean screenShot) {

        // Create the log file
        try {
            if (description == null) {
                create_Text_Log(status, methodName, "");
            } else {
                create_Text_Log(status, methodName, description);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        extent.startTest(methodName, "Test for method " + "<i>" + methodName + "</i>" + description);
        if (!(Settings.driver() == null) && screenShot == true) {
            extent.attachScreenshot(createScreenshot(Settings.driver(), methodName));
        }

        // Create the log for HTML log and TEXT log
        if (status == Status.ERROR) {
            extent.log(LogStatus.ERROR, "ERROR");
        } else if (status == Status.FAIL) {
            extent.log(LogStatus.FAIL, "FAIL");
        } else if (status == Status.FATAL) {
            extent.log(LogStatus.FATAL, "FATAL");
        } else if (status == Status.INFO) {
            extent.log(LogStatus.INFO, "INFO");
        } else if (status == Status.PASS) {
            extent.log(LogStatus.PASS, "PASS");
        } else if (status == Status.SKIP) {
            extent.log(LogStatus.SKIP, "SKIP");
        } else if (status == Status.WARNING) {
            extent.log(LogStatus.WARNING, "WARNING");
        } else {
            extent.log(LogStatus.SKIP, "UNKNOWN STATUS");
        }
    }

    private static String createScreenshot(WebDriver driver, String methodName) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd - HH mm ss");
        Date date = new Date();
        try {
            // Generate screenshot as a file object
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // copy file object to designated location
//            FileUtils.copyFile(scrFile, new File(imageLocation + methodName + " " + dateFormat.format(date) + ".png"));

        } catch (UnhandledAlertException e) {
            System.out.println("Error while generating screenshot:\n" + e.toString());
            return "";
        }
        return imageLocation + methodName + " " + dateFormat.format(date) + ".png";
    }

}
