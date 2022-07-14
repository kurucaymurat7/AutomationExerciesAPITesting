package Get;

import BaseUrls.AutomationExericesAPI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest01 extends AutomationExericesAPI {
    /*
        API URL: https://automationexercise.com/api/productsList
        Request Method: GET
        Response Code: 200
        Response JSON: All products list
     */

    @Test
    public void get01_01() {
        //set URL
        spec.pathParams("first", "api", "second", "productsList");

        //send request and get response
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}/{second}");
        response.then().assertThat().statusCode(200);
        JsonPath jsonPath = response.jsonPath();

        for (int i = 0; i < jsonPath.getList("products").size(); i++) {
            System.out.println(jsonPath.getList("products").get(i));
        }
    }

    @Test
    public void get01_02() {
        //set URL
        spec.pathParams("first", "api", "second", "productsList");

        //send request and get response
        Response response = given().spec(spec).when().accept(ContentType.JSON).get("/{first}/{second}");
        response.then().assertThat().statusCode(200);

        //Once response, jsonPath olarak alınır.
        JsonPath jsonPath = response.jsonPath();

        //Elde edilen veri bir Map listesi oldugu icin List<Map> container' ataması yapılır.
        //Elde edilen verinin yapısına gore, her bir elemana erisim saglanir ve gerekli assertions yapılır.
        //Map icinde bir Map de oldugu icin Map<String, Object>'ten olusan bir List tanımlanır.

        List<Map<String, Object>> jsonPathList = jsonPath.getList("products");
        for (int i = 0; i < jsonPathList.size(); i++) {
            System.out.print(jsonPathList.get(i).get("id") + " , ");
            System.out.print(jsonPathList.get(i).get("name") + " , ");
            System.out.print(jsonPathList.get(i).get("price") + " , ");
            System.out.print(jsonPathList.get(i).get("brand") + " , ");
            //Buraya kadarki yapi, basit datalardan olustugu icin erisimi daha kolay
            //Bu adımdan sonra içiçe Map yapıları oldugu icin erisim bir adım daha alta inerek yapılır.
            System.out.print(((Map)((Map)jsonPathList.get(i).get("category")).get("usertype")).get("usertype") + " , ");
            System.out.print(((Map)jsonPathList.get(i).get("category")).get("category") + "  ");
            System.out.println();
        }
        //System.out.println("((Map)jsonPath.getList(\"products\").get(i)).get(\"id\") = " + ((Map) jsonPath.getList("products").get(1)).get("id"));
    }
}
