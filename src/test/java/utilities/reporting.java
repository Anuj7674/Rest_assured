package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reporting extends TestListenerAdapter {

    public ExtentReports extent;

    public static ExtentSparkReporter htmlReporter;
    public static  ExtentTest logger;

    public void onStart(ITestContext testContext)  {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timestamp
        String repName = "Test-Report -"+ timeStamp + ".html";

    htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/" + repName);
      /*  try {
       htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/report-config.xml");
    } catch (IOException e) {
        throw new RuntimeException(e);
    }*/
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("user", "anuj");

        htmlReporter.config().setDocumentTitle("Rest Assured");
        htmlReporter.config().setReportName("Functional Test Report");
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void onTestSuccess(ITestResult tr){

        logger = extent.createTest((tr.getName()));
        logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

    }
    public void onTestFailure(ITestResult tr){

        logger=extent.createTest(tr.getName());
        logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";

      /*  File f= new File(screenshotPath);
        if(f.exists()){
            logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));

        }*/
    }
    public void onTestSkipped(ITestContext tr)
    {

        logger = extent.createTest(tr.getName());
        logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));

    }
    public void onFinish(ITestContext testContext)
    {

        extent.flush();
    }
}
