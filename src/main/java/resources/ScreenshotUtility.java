package resources;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtility {

    public static String captureScreenshot(WebDriver driver, ExtentTest extentTest, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.<File>getScreenshotAs(OutputType.FILE);

            // Generate timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Define screenshot directory
            String screenshotDirectory = "test-output/screenshots/";
            Path screenshotPath = Paths.get(screenshotDirectory);

            // Create the directory if it doesn't exist
            Files.createDirectories(screenshotPath);

            // Construct the screenshot file name
            String screenshotFileName = screenshotName + "_" + timestamp + ".png";
            Path targetPath = Paths.get(screenshotDirectory, screenshotFileName);

            // Copy the file to the target location
            Files.copy(source.toPath(), targetPath);

            // Attach the screenshot to the ExtentTest
            extentTest.log(Status.INFO, "Screenshot: " + extentTest.addScreenCaptureFromPath(targetPath.toString()));

            // Return the path of the captured screenshot
            return targetPath.toString();
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return "";
        }
    }
}
