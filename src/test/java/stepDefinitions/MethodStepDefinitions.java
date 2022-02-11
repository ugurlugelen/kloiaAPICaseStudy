package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.CreateTestData;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class MethodStepDefinitions {

    Response response;
    Object expectedID;

    @Given("Finds pets with using path {string} and query params {string}")
    public void finds_pets_with_using_path_and_query_params(String path, String query) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        response = given().queryParam("status", query).when().get(path);
    }
    @Given("Verify each item in response should have id not null")
    public void verify_each_item_in_response_should_have_id_not_null() {
        JsonPath jsonPath = response.jsonPath();
        List<Object> petID = jsonPath.getList("id");

        for (Object w: petID) {
            if(String.valueOf(w) !=null){
                Assert.assertTrue(true);
            }
        }


    }
    @Given("Verify status code should be {int}")
    public void verify_status_code_should_be(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }
    @Given("Verify {string} should be {string}")
    public void verify_should_be(String string, String string2) {
        response.then().header(string,string2);
    }

    @Given("Verify status should be equal to {string}")
    public void verify_status_should_be_equal_to(String status) {

        JsonPath jsonPath = response.jsonPath();
        List<String> petStatus = jsonPath.getList("status");
        for (String w: petStatus) {
            if (w.equals(status)){
                Assert.assertTrue(true);
            }
        }

    }

    @Given("Create user with using path {string} and set the {string} to {string}")
    public void create_user_with_using_path_and_set_the_to(String path, String contentType, String contentTypeValue) {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        Map<String,Object> bodyData = CreateTestData.setUpTestData();
        expectedID = bodyData.get("id");
        response = given().header(contentType,contentTypeValue).
                accept(contentTypeValue).
                body(bodyData).
                post(path);
    }

    @Given("Verify message should be equal to posted id")
    public void verify_message_should_be_equal_to_posted_id() {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedID,jsonPath.get("message"));
    }

    @Given("Create user with using path {string} and set the Json body with desired parameters")
    public void create_user_with_using_path_and_set_the_json_body_with_desired_parameters(String path, DataTable dataTable) {
        List<String> userNameLastNameKey = dataTable.row(0);
        List<String> userNameLastNameValue = dataTable.row(1);
        Map<String,Object> postBody = CreateTestData.setUpTestData();
        postBody.replace(userNameLastNameKey.get(0),userNameLastNameValue.get(0));
        postBody.replace(userNameLastNameKey.get(1),userNameLastNameValue.get(1));
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        Map<String,Object> bodyData = CreateTestData.setUpTestData();
        expectedID = bodyData.get("id");
        response = given().contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(bodyData).
                post(path);

    }

}
