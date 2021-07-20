package tests.FooterTests;

import api.BookmakerApiCalls;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import tests.base.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckFooterBookmakerReviews extends BaseTest {
    @BeforeMethod(description = "1. Create 6 bookmaker with different footer rank")
    public void beforeMethod() throws Exception {
        String[] namesOfBookmakers = {BookmakerApiCalls.bookmaker_Name_1, BookmakerApiCalls.bookmaker_Name_2,
                BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4,
                BookmakerApiCalls.bookmaker_Name_5, BookmakerApiCalls.bookmaker_Name_6};
        bookmakerApiCalls.createNBookmakers(namesOfBookmakers);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_1
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_1
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_1,"published", 1);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_2
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_2
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_2,"published", 2);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_3
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_3
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_3,"published", 3);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_4
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_4
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_4,"published", 4);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_5
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_5
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_5,"published", 9);

        bookmakerApiCalls.uploadBookmakerLogo(BookmakerApiCalls.bookmaker_Id_6);
        bookmakerApiCalls.addBookmakerData("profile",BookmakerApiCalls.bookmaker_Id_6
                ,bookmakerApiCalls.CREATE_BOOKMAKER_PROFILE_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_6
                , bookmakerApiCalls.CREATE_BOOKMAKER_BONUS_TAB);
        bookmakerApiCalls.addBookmakerData("reviews", BookmakerApiCalls.bookmaker_Id_6
                , bookmakerApiCalls.CREATE_BOOKMAKER_REVIEW_TAB);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_6,"published", 12);
    }

    @Test(description = "WN-182 : (OK) Footer Bookmaker-review (WAF-213)")
    public void FooterBookmakerReview() throws IOException, ParseException {
        mainPage.refreshPage();
        mainPage.waitToTitleContains(backOfficeApiCalls.getTitleByPageNameFromApi("Homepage"));
        softAssert.assertEquals(footerPage.getFooterReviewPartBookmakers(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_1,
                BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_3, BookmakerApiCalls.bookmaker_Name_4,
                BookmakerApiCalls.bookmaker_Name_5, BookmakerApiCalls.bookmaker_Name_6)),
                "The footer review part bookmakers sorting is wrong");

        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_3,"reviewCreation", 3);
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_5,"reviewCreation", 9);
        footerPage.clickOnReviewsSubMenuElement(BookmakerApiCalls.bookmaker_Name_1);
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), BookmakerApiCalls.bookmaker_Name_1 + " Review",
                "The bookmaker page not includes right overview title");
        softAssert.assertEquals(footerPage.getFooterReviewPartBookmakers(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_1,
                BookmakerApiCalls.bookmaker_Name_2, BookmakerApiCalls.bookmaker_Name_4, BookmakerApiCalls.bookmaker_Name_6)),
                "The footer review part bookmakers sorting is wrong");

        bookmakerApiCalls.changeBookmakerPublishStatus(BookmakerApiCalls.bookmaker_Id_2, "reviewCreation");
        bookmakerApiCalls.addBookmakerFooterRank(BookmakerApiCalls.bookmaker_Id_6,"published", 3);
        footerPage.clickOnReviewsSubMenuElement(BookmakerApiCalls.bookmaker_Name_4);
        softAssert.assertEquals(bookmakersPage.getOverviewTitleText(), BookmakerApiCalls.bookmaker_Name_4 + " Review",
                "The bookmaker page not includes right overview title");
        softAssert.assertEquals(footerPage.getFooterReviewPartBookmakers(), new ArrayList<>(Arrays.asList(BookmakerApiCalls.bookmaker_Name_1,
                BookmakerApiCalls.bookmaker_Name_6, BookmakerApiCalls.bookmaker_Name_4)),
                "The footer review part bookmakers sorting is wrong");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true, description = "Delete created bookmaker")
    public void deleteCreatedBookmaker() {
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_1);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_2);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_3);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_4);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_5);
        bookmakerApiCalls.deleteBookmakerById(BookmakerApiCalls.bookmaker_Id_6);
    }
}
