package com.basics.tests.patterns.page_object_model.tests;

import com.basics.tests.patterns.page_object_model.base.BaseTest;
import com.basics.tests.patterns.page_object_model.pages.DynamicLoadingPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends BaseTest {
    private DynamicLoadingPage dynamicLoadingPage;

    @BeforeMethod
    public void openDynamicLoadingPage() {
        dynamicLoadingPage = new DynamicLoadingPage(driver);
        dynamicLoadingPage.open();
    }

    @Test
    public void hiddenDynamicElementLoading() {
        dynamicLoadingPage.openHiddenDynamicElementPage();
        dynamicLoadingPage.clickOnStartButton();
        Assert.assertTrue(dynamicLoadingPage.isLoadingPresented());
        Assert.assertTrue(dynamicLoadingPage.isLoadingDisappeared());
        Assert.assertEquals(dynamicLoadingPage.getElementText(), DynamicLoadingPage.DYNAMIC_ELEMENT_TEXT);
    }

    @Test
    public void renderedAfterDynamicElementLoading() {
        dynamicLoadingPage.openRenderedAfterLoadingDynamicElementPage();
        dynamicLoadingPage.clickOnStartButton();
        Assert.assertTrue(dynamicLoadingPage.isLoadingPresented());
        Assert.assertTrue(dynamicLoadingPage.isLoadingDisappeared());
        Assert.assertEquals(dynamicLoadingPage.getElementText(), DynamicLoadingPage.DYNAMIC_ELEMENT_TEXT);
    }
}
