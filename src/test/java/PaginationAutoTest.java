import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.FileNotFoundException;

public class PaginationAutoTest {
    private static final int DEBTS_COUNT = 13;

    @BeforeClass
    public static void openBrowserAndLogin() {
        TestHelper.login("fast", "fast");
    }

    @Test
    public void paginationTest() throws FileNotFoundException {
        creatingDebts(DEBTS_COUNT);
        checkPage(1, 9, 12);
        Selenide.sleep(2000L);
        checkPage(3, 1, 4);
    }

    @AfterClass
    public static void leaveFromSiteAndDeleteDebts() {
        Selenide.$(By.name("logout")).click();
        Selenide.$("#username").setValue("vasya");
        Selenide.$("#password").setValue("vasya").pressEnter();
        for (int i = 0; i < DEBTS_COUNT; i++) {
            SelenideElement incomingDebtCardElem = Selenide.$(".incoming-debt-card");
            if (incomingDebtCardElem.find("md-card-content").find("p").getText().contains("testing description")) {
                incomingDebtCardElem.find(".delete-debt-button").click();
                Selenide.sleep(500L);
                Selenide.actions().sendKeys(Keys.ENTER).build().perform();
                Selenide.sleep(500L);

            }
        }
        Selenide.$(By.name("logout")).click();
    }

    private void creatingDebts(int count) {
        TestHelper.checkFriend("vasya");
        for (int i = 0; i < count; i++) {
            TestHelper.createDebt(
                    "Вася Пупкин",
                    "" + (i + 1),
                    "testing description #" + i,
                    "very-urgently",
                    false,
                    null);
        }
    }

    private void checkPage(int pageNum, int debtNumStart, int debtNumEnd) throws FileNotFoundException {
        ElementsCollection mdTabItemList = Selenide.$("#pages-container").findAll("md-tab-item");
        Boolean pageFounded = false;
        for (SelenideElement mdTabItemElem : mdTabItemList) {
            if (mdTabItemElem.find("span").getText().equals("" + pageNum)) {
                mdTabItemElem.click();
                pageFounded = true;
                break;
            }
        }
        Assert.assertTrue("Page #" + pageNum + " should be exist", pageFounded);

        Selenide.sleep(1000L);
        int debtIndex = 0;
        for (int i = debtNumEnd; i >= debtNumStart; i--) {
            TestHelper.checkDebt(
                    debtIndex++,
                    "Вася Пупкин",
                    "" + (i + 1),
                    "testing description #" + i,
                    "very-urgently",
                    false,
                    null);
        }
    }
}
