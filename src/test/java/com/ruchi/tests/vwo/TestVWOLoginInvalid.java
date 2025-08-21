package com.ruchi.tests.vwo;

import com.ruchi.base.BaseTest;
import com.ruchi.endpoints.APIConstants;
import com.ruchi.modules.vwo.VWOPayloadManager;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestVWOLoginInvalid extends BaseTest {

    @Test
    public void test_VWO_Login_Negative() {
        // Setup will first and making the request - Part - 1
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(vwoPayloadManager.setLoginDataInvalid()).log().all()
                .post();

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 401);

        String msg = vwoPayloadManager.getLoginDataInvalid(response.asString());
        assertActions.verifyStringKey(msg, "Invalid User");

    }
}