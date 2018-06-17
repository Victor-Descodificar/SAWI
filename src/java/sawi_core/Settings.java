package sawi_core;

/**
 *
 * @author VictorFerreiraPereir
 */
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import static sawi_core.Constants.*;

public class Settings {

    private static WebDriver driver;
    private boolean[] run;
    private String[] url;
    private BrowserType[] bt;
    private String[] scriptName;
    private Datapool datapool;

    static WebDriver driver() {
        return driver;
    }

    private void setRun(ExecutionType et) {

    }

    private void setUrl(ExecutionType et) {

    }

    private void setDriverConfig() {
        // Set some driver configurations
        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(Constants.TIME_WAIT, TimeUnit.SECONDS);
        driver().manage().deleteAllCookies();
    }

    private void setBrowser(ExecutionType et) {

    }

    private void setScriptName(ExecutionType et) {

    }

    public void setEnvironment(ExecutionType et) {

    }

    // Execute just once
    void launchApplication(ExecutionType et) {
        switch (et) {
            case BACKGROUND:
                setEnvironment(et);
                break;
            case WEBINTERFACE:
                break;
        }
    }

    private void shutdown() {
        if (driver != null) {
            try {
                Thread.sleep(1500);
                driver().quit();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
