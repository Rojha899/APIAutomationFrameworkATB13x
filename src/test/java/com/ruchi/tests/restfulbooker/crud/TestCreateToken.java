package com.ruchi.tests.restfulbooker.crud;

import com.ruchi.base.BaseTest;
import com.ruchi.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Description("Verify that post request to the create token basically creates a 16-digit token. ")
    @Owner("Ruchi")
    @Test(groups = "reg",priority = 1)
    public void test_verifyTokenPOST(){

        // Automatically have the base URL set and the header set.
        // This URL as well as the header of application JSON is automatically
        // set when you start using extends from BaseTest.

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).when().body(payloadManager.setAuthPayload()).post();

        // Extraction ( JSON String response to Java Object)
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);


        // Validation of the request.
        assertActions.verifyStringKeyNotNull(token);

    }

    @Test(groups = "reg", priority = 1)
    @Owner("Ruchi")
    @Description("TC#2  - Create Invalid Token and Verify")
    public void testTokenPOST_Negative() {


        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body("{}").post();


        // Extraction ( JSON String response to Java Object)
        String invalid_reason = payloadManager.getInvalidResponse(response.asString());
        System.out.println(invalid_reason);


        // Validation of the request.
        assertActions.verifyStringKey(invalid_reason,"Bad credentials");

    }

}
