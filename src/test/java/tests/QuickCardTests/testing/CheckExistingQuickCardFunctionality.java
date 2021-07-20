package tests.QuickCardTests.testing;

import api.BookmakerApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import utils.Log;

public class CheckExistingQuickCardFunctionality extends BaseTest {

    @BeforeMethod(description = "1. Create Bookmaker, 2. Upload Bookmaker Logo, 3. Upload Bookmaker Quick Card image 4. Add Data in Bookmaker Profile section" +
            "5. Add Bonus for bookmaker, 6. Add data in 'Reviews' tab, 7. Add Bookmaker SEO, 7. Publish bookmaker")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers= {BookmakerApiCalls.bookmaker_Name_1,BookmakerApiCalls.bookmaker_Name_2};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);
        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_1);
        quickCardApiCalls.addQuickCardInfo("quick_card_name_1", 1, BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.changeBookmakerStatus(BookmakerApiCalls.bookmaker_Id_1,"published");

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.uploadBookmakerQuickCardImage(BookmakerApiCalls.bookmaker_Id_2);
        quickCardApiCalls.addQuickCardInfo("quick_card_name_2", 1, BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
    }

    @Test(description = "WAF-280 Check Existing quick card functionality")
    public void checkExistingQuickCardFunctionality() throws Exception {
        Log.warn(BookmakerApiCalls.bookmaker_Name_1);
        Assert.assertEquals(bookmakerApiCalls.changeBookmakerStatusWithIncorrectRating(BookmakerApiCalls.bookmaker_Id_2,"published"),
                "{\"errors\":[{\"slug\":\"bookmaker-quick-card-rank-invalid\",\"message\":\"Quick card with rank 1 exists. " +
                        "\\n        Check " + BookmakerApiCalls.bookmaker_Name_1 + " bookmaker\",\"details\":{\"path\":" +
                        "\"bookmaker-quick-card-rank\"}}],\"message\":\"Quick card with rank 1 exists. \\n        Check "
                + BookmakerApiCalls.bookmaker_Name_1 + " bookmaker\"}");
    }
    @AfterMethod(alwaysRun = true,description = "delete created bookmaker")
    public void deleteCreatedBookmakers()  {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
    }
}
