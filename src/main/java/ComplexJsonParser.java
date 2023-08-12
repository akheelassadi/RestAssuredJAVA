import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.util.Objects;

public class ComplexJsonParser {

    //problem statement
    //1. Print No of courses returned by API
    //2.Print Purchase Amount
    //3. Print Title of the first course
    //4. Print All course titles and their respective Prices
    //5. Print no of copies sold by RPA Course
    //6. Verify if Sum of all Course prices matches with Purchase Amount

    public static void  main(String[] args) {

        JsonPath js= new JsonPath(PayLoad.CoursePrice());
        System.out.println("1. Print No of courses returned by API");
        int noOfCourses = js.getInt("courses.size()");
        System.out.println(noOfCourses);

        System.out.println("2.Print Purchase Amount");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        System.out.println("3. Print Title of the first course");
        String titleFirstCourse = js.getString("courses[0].title");
        System.out.println(titleFirstCourse);

        System.out.println("4. Print All course titles and their respective Prices");
        for (int i=0; i<noOfCourses; i++) {
            String titlePrice = js.getString("courses["+i+"].title");
            int price= js.getInt("courses["+i+"].price");
            System.out.println(titlePrice + " " + price);
        }

        System.out.println("5. Print no of copies sold by RPA Course");
        for(int i=0; i<noOfCourses; i++) {
            if (js.getString("courses[" + i + "].title").equalsIgnoreCase("RPA")) {
                System.out.println(js.getInt("courses["+i+"].copies"));
                break;
            }
        }

    }
}
