package com.sample.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;


public final class Driver {

    private static WebDriver driver;
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("us81.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private constructor to restrict access of object creation
     */
    private Driver() {
    }

    /**
     * Method to load .properties file to Properties object
     */
    public static void readDataFromProperties(String fileName) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to return value for given key from .properties file
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Method to do the initial setup of WebDriver
     */
    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = WebDriverManager.chromedriver().create();
                    break;
                case "firefox":
                    driver = WebDriverManager.firefoxdriver().create();
                    break;
                case "safari":
                    driver = WebDriverManager.safaridriver().create();
                    break;
                case "edge":
                    driver = WebDriverManager.edgedriver().create();
                    break;
                case "opera":
                    driver = WebDriverManager.operadriver().create();
                    break;
                case "chromeheadless":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    /**
     * Method to return driver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Method to read data from Excel file and return Object[][]
     */
    public static Object[][] readXLSX() {
        Object[][] testData = null;
        try {
            File file = new File(getProperty("excelFilePath"));
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(getProperty("excelSheetName"));
            int totalRows = sheet.getLastRowNum() + 1;
            Row rowCells = sheet.getRow(0);
            int totalColumns = rowCells.getLastCellNum();
            DataFormatter format = new DataFormatter();
            testData = new Object[totalRows][totalColumns];
            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalColumns; j++) {
                    testData[i][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    /**
     * Method that accepts sheet name as an argument to read data from Excel file and return Object[][]
     */
    public static Object[][] readXLSX(String sheetName) {
        Object[][] testData = null;
        try {
            File file = new File(getProperty("excelFilePath"));
            FileInputStream fis = new FileInputStream(file);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            int totalRows = sheet.getLastRowNum() + 1;
            Row rowCells = sheet.getRow(0);
            int totalColumns = rowCells.getLastCellNum();
            DataFormatter format = new DataFormatter();
            testData = new Object[totalRows][totalColumns];
            for (int i = 0; i < totalRows; i++) {
                for (int j = 0; j < totalColumns; j++) {
                    testData[i][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    /**
     * Method to read data from CSV file and return ArrayList
     */
    public static List<String> readCSV(int dataColumn) {
        List<String> testData = new ArrayList<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(getProperty("csvFilePath")));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                testData.add(values[dataColumn]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    /**
     * Method to read data from CSV file and return HashMap
     */
    public static Map<String, String> readCSV(int keyColumn, int valueColumn) {
        Map<String, String> testData = new HashMap<>();
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(getProperty("csvFilePath")));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                testData.put(values[keyColumn], values[valueColumn]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    /**
     * Method to take full screenshot
     */
    public static void captureScreen() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
            LocalTime localTime = LocalTime.now();
            String minSec = dtf.format(localTime).replace(":", "");
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(getProperty("screenShotSavePath") + minSec + getProperty("screenShotExtension")));
            Driver.sleep(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to take screenshot of Entire page
     */
    public static void captureEntirePage() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
            LocalTime localTime = LocalTime.now();
            String minSec = dtf.format(localTime).replace(":", "");
            Screenshot ss = new AShot().takeScreenshot(driver);
            ImageIO.write(ss.getImage(), getProperty("screenShotExtension"), new File(getProperty("screenShotSavePath") + minSec));
            Driver.sleep(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to take screenshot of WebElement
     */
    public static void captureElement(WebElement element) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
            LocalTime localTime = LocalTime.now();
            String minSec = dtf.format(localTime).replace(":", "");
            Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver, element);
            ImageIO.write(ss.getImage(), getProperty("screenShotExtension"), new File(getProperty("screenShotSavePath") + minSec));
            Driver.sleep(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to highlight a WebElement and take screenshot
     */
    public static void captureHighlighted(WebElement element) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");
            LocalTime localTime = LocalTime.now();
            String minSec = dtf.format(localTime).replace(":", "");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            if (getProperty("needBackground").equalsIgnoreCase("yes")) {
                jse.executeScript("arguments[0].setAttribute('style', 'border:" + getProperty("highlightBorderSize") + "px solid " + getProperty("highlightBorderColor") + "; background:" + getProperty("backgroundColor") + "')", element);
            } else if (getProperty("needBackground").equalsIgnoreCase("no")) {
                jse.executeScript("arguments[0].style.border='" + getProperty("highlightBorderSize") + "px solid " + getProperty("highlightBorderColor") + "'", element);
            }
            sleep(1);
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(getProperty("screenShotSavePath") + minSec + getProperty("screenShotExtension")), true);
            Driver.sleep(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to verify the Title of the page
     */
    public static boolean verifyTitle(String expected) {
        return driver.getTitle().equals(expected);
    }

    /**
     * Method to select all options
     */
    public static void selectAll(Select dropDown) {
        for (WebElement each : dropDown.getOptions()) {
            each.click();
        }
    }

    /**
     * Method to adjust implicit wait time
     */
    public static void waitImplicit(int seconds) {
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    /**
     * Method to wait explicitly until the Title loads
     */
    public static void waitForTitle(String expectedTitle, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    /**
     * Method to wait explicitly until the WebElement is visible
     */
    public static void waitUntilVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Method to wait explicitly until the WebElement is invisible
     */
    public static void waitUntilInvisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Method to wait until the page is loaded
     */
    public static void waitPageLoad() {
        if (Driver.getDriver() instanceof ChromeDriver) {
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        } else if (Driver.getDriver() instanceof FirefoxDriver) {
            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        } else if (Driver.getDriver() instanceof EdgeDriver) {
            FirefoxOptions options = new FirefoxOptions();
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        }
    }

    /**
     * Method to adjust implicit wait time
     */
    public static void waitPageLoad(int seconds) {
        driver.manage().timeouts().pageLoadTimeout(seconds, TimeUnit.SECONDS);
    }

    /**
     * Method to adjust thread sleep time
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void blankClick() {
        Driver.getDriver().findElement(By.xpath("//html")).click();
    }

    /**
     * Method to kill session after test
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}