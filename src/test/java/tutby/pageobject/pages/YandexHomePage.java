package tutby.pageobject.pages;

import framework.BasePage;
import framework.Browser;
import framework.PropertyReader;
import org.openqa.selenium.By;

public class YandexHomePage extends BasePage {
    public YandexHomePage(){
        super(By.xpath("//div[contains(@class,'home-logo')]//a[contains(@href,'https://yandex.by/')]"),"Yandex Search Page");
    }
    public void navigateToTutByLoginPage(){
        Browser.navigate(PropertyReader.getProperty("url"));
    }
}
