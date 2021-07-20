package tests.BackOfficeTests.testing;


import api.BackOfficeApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

public class CheckTagGroupDuplication extends BaseTest {

    @BeforeMethod(description = "Create Tag group")
    public void createTagGroup() throws Exception {
        backOfficeApiCalls.createTagGroup();
    }

    @Test(description = "Check no duplication for tag group functionality")
    public void checkNoDuplicationForTagGroupFunctionality() throws Exception {
        Assert.assertEquals(backOfficeApiCalls.getStatusCodeWhenAddSameTagGroup(), 409);
    }

    @AfterMethod(description = "Delete created Tag group", alwaysRun = true)
    public void deleteCreatedTagGroup() {
        backOfficeApiCalls.deleteTagGroupById(BackOfficeApiCalls.tagGroupId);
    }
}
