package com.uffizio.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUploadExample {
    public static void main(String[] args) throws InterruptedException {
        // Set the path to the WebDriver executable
        WebDriverManager.chromedriver().setup();
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();
        System.out.println("driver selected");
        try {
            // Open the target webpage
            driver.get("https://formstone.it/components/upload/demo/");
            Thread.sleep(5000);
            System.out.println("opens the webpage");
            // Locate the file input element (using the appropriate selector)
            WebElement uploadElement = driver.findElement(By.id("//div[@data-upload-options='{\"action\":\"https://formstone.it/demo/_extra/upload-target.php\"}']//div[@class='fs-upload-target'][normalize-space()='Drag and drop files or click to select']"));

            // Provide the path to the file to be uploaded
            uploadElement.sendKeys("C:\\Users\\admin\\Desktop\\Ranjit_Resume.pdf");

            // Optionally, submit the form or click a button to complete the upload
            WebElement submitButton = driver.findElement(By.id("submit-button"));
            submitButton.click();

            // Add further actions or verifications if needed
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
