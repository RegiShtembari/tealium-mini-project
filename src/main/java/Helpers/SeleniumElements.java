package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumElements {

    WebDriver driver;

    public SeleniumElements(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDropdownOption(String xpath, String searchField, String inputText, String clickPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        WebElement dropdown = driver.findElement(By.xpath(xpath));
        dropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchField)));
        WebElement searchInput = driver.findElement(By.xpath(searchField));

        searchInput.sendKeys(inputText);
        WebElement option = driver.findElement(By.xpath(clickPath));
        option.click();
    }

    public void fields(String fieldPath, String inputText) {

        WebElement textField = driver.findElement(By.xpath(fieldPath));
        textField.click();
        textField.clear();
        textField.sendKeys(inputText);

    }

    public void check(String checkboxPath) {
        WebElement check = driver.findElement(By.xpath(checkboxPath));
        check.click();
    }

    public boolean isCheckboxSelected(String xpath) {
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        return checkbox.isSelected();
    }

    public void datePicker(String datePickerPath, String year, String month, String day, String hour, String min) {
        WebElement datePicker = driver.findElement(By.xpath(datePickerPath));
        datePicker.click();
        driver.findElement(By.xpath(year)).click();
        driver.findElement(By.xpath(month)).click();
        driver.findElement(By.xpath(day)).click();
        driver.findElement(By.xpath(hour)).click();
        driver.findElement(By.xpath(min)).click();
    }

}
