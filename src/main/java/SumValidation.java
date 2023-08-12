import Files.PayLoad;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public  void sumOfCourses() {
        JsonPath js= new JsonPath(PayLoad.CoursePrice());
        int noOfCourses = js.getInt("courses.size()");
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");

        System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
        int sum = 0;
        for (int i=0; i<noOfCourses;i++) {
            sum += (js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies"));
        }
        Assert.assertEquals(purchaseAmount, sum);
    }
}
