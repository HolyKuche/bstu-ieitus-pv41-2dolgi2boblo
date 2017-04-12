import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class TestHelper {
    public static void login(String username, String password) {
        Configuration.browser = "chrome";
        String pathToChromeDriver = Paths.get("/usr/bin/chromedriver").toAbsolutePath().toString();
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        Selenide.open("http://localhost:8080/login");
        Selenide.$("#username").setValue(username);
        Selenide.$("#password").setValue(password).pressEnter();
    }

    public static void createDebt(String whomName, String money, String description, String importance, Boolean flag, File image) {
        Selenide.$("#create-debt-button").click();
        Selenide.$(By.name("whom")).click();
        SelenideElement dzoraPorohFriendListElem = null;
        for (SelenideElement friendListElem : Selenide.$$(".friend-list-item")) {
            if (friendListElem.getText().equals(whomName)) {
                dzoraPorohFriendListElem = friendListElem;
                break;
            }
        }
        dzoraPorohFriendListElem.click();
        Selenide.sleep(500L);
        Selenide.$(By.name("money")).setValue(money);
        Selenide.$(By.name("description")).setValue(description);
        Selenide.$("#" + importance + "-importance").click();
        if (flag) {
            Selenide.$(By.name("flag")).click();
        }
        if (image != null) {
            Selenide.$(By.name("file")).uploadFile(image);
        }
        Selenide.$("#submit-creating-button").click();
        Selenide.$("#creating-debt-modal").shouldBe(Condition.not(Condition.exist));
    }

    public static void checkFriend(String username) {
        Selenide.$("#search-friend-field").setValue(username);
        Assert.assertTrue("User with username a \"" + username + "\" should be in friend list",
                Selenide.$(".friend-card").has(Condition.exist));
    }

    public static void checkDebt(int index, String whomName, String money, String description, String importance, Boolean flag, File image) throws FileNotFoundException {
        SelenideElement debtCardElem = Selenide.$$(".debt-card").get(index);
        Assert.assertEquals("Friend name should be \"" + whomName + "\"",
                debtCardElem.find(".whom-card-field").getText(), whomName);
        Assert.assertEquals("Money field should be " + money + " \u20BD",
                debtCardElem.find(".money-card-field").getText(), money + " \u20BD");
        Assert.assertEquals("Description field should be \"" + description +"\"",
                debtCardElem.find(".description-card-field").getText(), description);
        Assert.assertTrue("Importance field should be \"" + importance + "\"",
                debtCardElem.find("." + importance + "-importance-card-field").has(Condition.exist));
        Assert.assertTrue("Flag field should" + (flag ? " " : " not ") + "be contained",
                debtCardElem.find(".flag-card-field").has(flag ? Condition.exist : Condition.not(Condition.exist)));
        if (image != null) {
            Assert.assertEquals("Image should be a copy of uploaded image",
                    debtCardElem.find(".image-card-field").download(), image);
        }
    }
}
