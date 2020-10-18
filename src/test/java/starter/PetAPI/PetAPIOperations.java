package starter.PetAPI;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

public class PetAPIOperations {

    Response apiResponse;
    String baseUrl="https://petstore.swagger.io/v2/pet/";
    @Step
    public void checkApplicationStatus()
    {
        int statusCode = SerenityRest.get("https://petstore.swagger.io").statusCode();
        Assert.assertEquals(200,statusCode);
    }

    @Step
    public void GETAvailablePets(String resource, String queryParam)
    {
        apiResponse = SerenityRest.given().queryParam("status",queryParam).get(baseUrl+resource);
    }

    @Step
    public void VerifyResponseCode(int ExpStatusCode)
    {
        Assert.assertEquals(ExpStatusCode,apiResponse.statusCode());
    }

    @Step
    public void ValidateGETResponse()
    {
        JsonArray petApiResponse;
        petApiResponse = apiResponse.as(JsonArray.class);
        for(int i=0;i<petApiResponse.size();i++)
        {
           JsonObject obj= petApiResponse.get(i).getAsJsonObject();
          Assert.assertEquals("available",obj.get("status").getAsString());
          Assert.assertNotNull(obj.get("id").getAsString());
        }
    }

    @Step
    public void RequestPost(String requestBody)
    {
        String Id;
        try{
            Serenity.setSessionVariable("PostRequestBody").to(requestBody);
            apiResponse = SerenityRest.given().header("Content-Type","application/json").body(requestBody).post(baseUrl);
            Serenity.setSessionVariable("PostResponseBody").to(apiResponse.getBody().asString());
            Id=apiResponse.getBody().path("id").toString();
            Serenity.setSessionVariable("PostResponseId").to(Id);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }

    }
    @Step
    public void comparePostRequestResponse()
    {
        Assert.assertEquals(Serenity.sessionVariableCalled("PostRequestBody").toString(),Serenity.sessionVariableCalled("PostResponseBody").toString());
    }
    @Step
    public void updateStatus(String statusValue)
    {
        String petId = Serenity.sessionVariableCalled("PostResponseId");
        apiResponse=SerenityRest.given().header("Content-Type","application/x-www-form-urlencoded").param("status",statusValue).post(baseUrl+petId);
    }

    @Step
    public void VerifyStatusAfterUpdate(String ExpectedStatus)
    {  String petId = Serenity.sessionVariableCalled("PostResponseId");
        String ActualStatus =SerenityRest.given().get(baseUrl+petId).getBody().path("status");
        Assert.assertEquals(ExpectedStatus,ActualStatus);

    }

    @Step
    public void DeletePet()
    {
        String petId = Serenity.sessionVariableCalled("PostResponseId");
        apiResponse = SerenityRest.delete(baseUrl+petId);
    }

    @Step
    public void verifyPetsDeleted()
    {
        String petId = Serenity.sessionVariableCalled("PostResponseId");
        apiResponse = SerenityRest.get(baseUrl+petId);
        Assert.assertEquals(404,apiResponse.statusCode());
    }

}
