package com.uffizio.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;

public class CheckboxSelector {

    WebDriver driver;

    // Constructor to initialize the WebDriver
    public CheckboxSelector(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method to select checkboxes at three levels.
     * 
     * @param addressOfElement  - WebElement (parent element) to start searching from
     * @param xpathListOfFirstParameter - XPath for first level (company) checkboxes
     * @param firstParameter    - List of checkbox names for first level
     * @param xpathListOfSecondParameter - XPath for second level (group) checkboxes
     * @param secondParameter   - List of checkbox names for second level
     * @param xpathListOfThirdParameter - XPath for third level (object) checkboxes
     * @param thirdParameter    - List of checkbox names for third level
     * @return true if all checkboxes are found and selected, false otherwise
     */
    public boolean selectThreeLevelCheckboxes(WebElement addressOfElement, String xpathListOfFirstParameter, 
                                              String[] firstParameter, String xpathListOfSecondParameter, 
                                              String[] secondParameter, String xpathListOfThirdParameter, 
                                              String[] thirdParameter) {
        try {
            // Loop through each first level (company) checkbox and select it
            for (String first : firstParameter) {
                // Construct the XPath to locate the first level (company) checkbox
                String firstLevelXPath = String.format(xpathListOfFirstParameter, first);
                WebElement firstLevelCheckbox = addressOfElement.findElement(By.xpath(firstLevelXPath));

                // If the checkbox is not already selected, select it
                if (!firstLevelCheckbox.isSelected()) {
                    firstLevelCheckbox.click();
                }
            }

            // Loop through each second level (group) checkbox and select it
            for (String second : secondParameter) {
                // Construct the XPath to locate the second level (group) checkbox
                String secondLevelXPath = String.format(xpathListOfSecondParameter, second);
                WebElement secondLevelCheckbox = addressOfElement.findElement(By.xpath(secondLevelXPath));

                // If the checkbox is not already selected, select it
                if (!secondLevelCheckbox.isSelected()) {
                    secondLevelCheckbox.click();
                }
            }

            // Loop through each third level (object) checkbox and select it
            for (String third : thirdParameter) {
                // Construct the XPath to locate the third level (object) checkbox
                String thirdLevelXPath = String.format(xpathListOfThirdParameter, third);
                WebElement thirdLevelCheckbox = addressOfElement.findElement(By.xpath(thirdLevelXPath));

                // If the checkbox is not already selected, select it
                if (!thirdLevelCheckbox.isSelected()) {
                    thirdLevelCheckbox.click();
                }
            }

            return true; // If all checkboxes are found and selected, return true

        } catch (NoSuchElementException e) {
            // If any element is not found, catch the exception and return false
            return false;
        }
    }
}
