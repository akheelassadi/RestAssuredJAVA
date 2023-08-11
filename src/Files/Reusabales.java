package Files;

import io.restassured.path.json.JsonPath;

public class Reusabales {

    public static JsonPath rawToJson(String response)
    {
        return new JsonPath(response);
    }
}
