package tests.BackOfficeTests.testing;

import api.BackOfficeApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckCreateTagGroupFunctionality extends BaseTest {

    @Test(description = "Check Create tag group functionality")
    public void checkCreateTagGroupFunctionality() throws Exception {
        backOfficeApiCalls.createTagGroup();
        Assert.assertEquals(backOfficeApiCalls.getTagGroupById(), BackOfficeApiCalls.tagGroupId);
    }

    @AfterMethod(description = "Delete created Tag group", alwaysRun = true)
    public void deleteCreatedTagGroup() {
        backOfficeApiCalls.deleteTagGroupById(BackOfficeApiCalls.tagGroupId);
    }
}
