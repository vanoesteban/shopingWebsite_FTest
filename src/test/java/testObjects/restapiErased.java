package testObjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static java.lang.System.out;

public class restapiErased {

    @Test
        public void testResponsecode(){

            int code = RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").getStatusCode();

            out.println("Status code is " + code);

            Assert.assertEquals(code, 200);
        }

    @Test
    public void testResponsebody(){

        String data =  RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").asString();
        int time = (int) RestAssured.get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").getTime();

        out.println("Status code is " + data);
        out.println("Response time " + time);

        //Assert.assertEquals(data, 200);
    }

    @Test
    public void testHyderabadget(){
        RequestSpecification httpRequest;
        Response response;

        RestAssured.baseURI ="http://restapi.demoqa.com/utilities/weather/city/Hyderabad";
        httpRequest = RestAssured.given();
        response = httpRequest.get("/Hyderabad");
        JsonPath jsonPathValue = response.jsonPath();

        String contentType = response.header("Content-Type");
        //out.println("data: " + response.asString());
        //out.println("body: " + response.getBody().asString());
        /*out.println("headers: " + response.getHeaders());
        out.println("status: " + response.getStatusCode());
        out.println("time: " + response.getTime());*/
        //out.println("Content Type: " + contentType);

        out.println("City: "+ jsonPathValue.get("City").toString());
        Assert.assertEquals( jsonPathValue.get("City").toString(), "Hyderabad", "Correct response");

    }

    @Test
    public void testHyderabadpost(){
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request;
        request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        Response response;

        requestParams.put("FirstName", "Virender");
        requestParams.put("LastName", "Singh");

        requestParams.put("UserName", "simpleuser001");
        requestParams.put("Password", "password1");
        requestParams.put("Email",  "someuser@gmail.com");

        request.header("Content-Type", "application/json");
        request.body(requestParams.toJSONString());
        response = request.post("/register");
        out.println("Status response: " + response.getStatusCode());

    }
}
