import com.codeborne.selenide.Selenide;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;

public class CreatingDebtAutoTest {
    @BeforeClass
    public static void openBrowserAndLogin() {
        TestHelper.login("fast", "fast");
    }

    @Test
    public void creatingDebtTest() throws FileNotFoundException {
        File image = new File("/home/kuche/Изображения/Пикчи/cat.jpg");
        Selenide.sleep(500L);
        TestHelper.checkFriend("poroh");
        TestHelper.createDebt(
                "Жора Порох",
                "5",
                "Give me my money!",
                "urgently",
                true,
                image
        );

        Selenide.sleep(500L);
        TestHelper.checkDebt(
                0,
                "Жора Порох",
                "5",
                "Give me my money!",
                "urgently",
                true,
                image
        );
    }

    @AfterClass
    public static void leaveFromSiteAndDeleteDebt() {
        Selenide.$(By.name("logout")).click();
        Selenide.$("#username").setValue("poroh");
        Selenide.$("#password").setValue("poroh").pressEnter();
        Selenide.$(".incoming-debt-card").find(".delete-debt-button").click();
        Selenide.sleep(500L);
        Selenide.actions().sendKeys(Keys.ENTER).build().perform();
        Selenide.$(By.name("logout")).click();
    }
}
