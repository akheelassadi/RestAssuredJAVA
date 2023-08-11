import Files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(PayLoad.AddPLace()).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
                .extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String place_ID = js.getString("place_id");

        System.out.println(place_ID);

        //update place and place ID
        String updatedPlace = "Udupi";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(PayLoad.UpdatePlace(place_ID, updatedPlace)).when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //get the information and verify if the new place is eqaul to updatedPlace
        String getNewResponse = given().queryParam("key", "qaclick123").queryParam("place_id", place_ID).header("Content-Type", "application/json")
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).body("address", equalTo("Udupi"))
                .extract().response().asString();

        JsonPath js1 = new JsonPath(getNewResponse);
        String newPlace = js1.getString("address");

        System.out.println(newPlace);

        Assert.assertEqual

    }
}
