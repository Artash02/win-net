package tests.BackOfficeTests.testing;

import api.BackOfficeApiCalls;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckUpdateDeleteTagGroupFunctionality extends BaseTest {

    @BeforeMethod(description = "Create tag group")
    public void createTagGroup() throws Exception {
        backOfficeApiCalls.createTagGroup();
    }

    @Test(description = "Edit created tag group")
    public void editCreatedTagGroup() throws Exception {
        backOfficeApiCalls.updateTagGroup();
        Assert.assertEquals(backOfficeApiCalls.getUpdatedTagGroupValues(), new ArrayList<>(Arrays.asList(BackOfficeApiCalls.tagGroupName + "_edit",
                BackOfficeApiCalls.tagName + "_edit")));
    }

    @AfterMethod(description = "Delete created tag group", alwaysRun = true)
    public void deleteCreatedTagGroup() {
        backOfficeApiCalls.deleteTagGroupById(BackOfficeApiCalls.tagGroupId);
    }
}
