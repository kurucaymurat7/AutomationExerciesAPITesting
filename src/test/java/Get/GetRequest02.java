package Get;

import BaseUrls.AutomationExericesAPI;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest02 extends AutomationExericesAPI {
    /*
        API URL: https://automationexercise.com/api/brandsList
        Request Method: GET
        Response Code: 200
        Response JSON: All brands list
     */

    @Test
    public void get02_01() {
        //set the url
        spec.pathParams("first", "api", "second", "brandsList");

        //send request get response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> jsonPathList = jsonPath.getList("brands");
        List<String> allBrandsList = new ArrayList<>();
        for (int i = 0; i < jsonPathList.size(); i++) {
            allBrandsList.add(jsonPathList.get(i).get("brand").toString());
        }
        System.out.println(allBrandsList);
    }
}
