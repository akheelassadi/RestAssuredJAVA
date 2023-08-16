import Files.PayLoad;
import Files.Reusabales;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(PayLoad.AddBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().asString();

        JsonPath js = Reusabales.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    //getBook
    @Test
    public void GetBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().queryParam("ID", "isbn2aisle2").when().get("Library/GetBook.php").then().log()
                .all().assertThat().statusCode(200).extract().asString();
        System.out.println(response);
    }


    //deleteBook
    @Test
    public void DeleteBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        given().log().all().when().delete("Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200);
    }
    @DataProvider(name = "BooksData")
    public Object[][] GetData() {
        return new Object[][] {{"isbn1", "aisle1"},{"isbn2","aisle2"}};
    }
}
