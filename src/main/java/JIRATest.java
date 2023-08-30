import Files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JIRATest {

    @Test
    public static void AddComment() {
        RestAssured.baseURI = "http://localhost:8080";

        //create a session using sessionfileter object
        SessionFilter sessionFilter = new SessionFilter();

        String response = given().log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"username\": \"rujmaanassadi\",\n" +
                "    \"password\": \"Rujman2022!\"\n" +
                "}").filter(sessionFilter).when().post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().asString();

        //create an issue
        /*String IssueDetails = given().log().all().header("Content-Type","application/json").body(PayLoad.CreatIssue()).filter(sessionFilter)
                .when().post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().asString();*/

        //System.out.println(IssueDetails);

        //add comment to the issue
        String commentDetails = given().log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \"Hey I have updated the comment on the issue\"\n" +
                "}").filter(sessionFilter).when().post("/rest/api/2/issue/TEST-10/comment").then().assertThat().statusCode(201).extract().asString();
        System.out.println(commentDetails);

        //update the comment on the
        /*String updatedcommentDetails = given().log().all().header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \"Hey I have updated the comment on the issue\"\n" +
                "}").filter(sessionFilter).when().put("/rest/api/2/issue/TEST-10/comment/10100").then().assertThat().statusCode(200).extract().asString();
        System.out.println(updatedcommentDetails);*/

        //get issue details
/*        String IssueDetails = given().log().all().filter(sessionFilter).pathParam("key", "TEST-10")
                .queryParam("fields", "comment").when().get("/rest/api/2/issue/{key}").then().extract().response().asString();
        System.out.println(IssueDetails);*/

    }
}
