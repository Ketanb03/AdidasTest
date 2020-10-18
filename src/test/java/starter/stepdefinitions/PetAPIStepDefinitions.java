package starter.stepdefinitions;
//import io.cucumber.java.en.And;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.PetAPI.PetAPIOperations;

public class PetAPIStepDefinitions {
    @Steps
    PetAPIOperations ops;

    @Given("Pet store API is running")
    public void petStore_API_is_running() {
        ops.checkApplicationStatus();
    }

    @When("I request GET with resource as {string} and status as {string}")
    public void i_request_GET_with_and_status_as(String URL, String QueryParam) {
        ops.GETAvailablePets(URL, QueryParam);
    }

    @Then("I get status code as {string}")
    public void i_get_status_code_as(String statusCode) {
        ops.VerifyResponseCode(Integer.parseInt(statusCode));
    }

    @Then("I verify response contains only available pets")
    public void i_verify_response_contains_only_available_pets() {
        ops.ValidateGETResponse();
    }

    @When("I request POST with request body as {string}")
    public void iRequestPOSTWithResourceAsAndRequestBodyAs(String requestBody) {
        ops.RequestPost(requestBody);
    }

    @Then("I Assert on request and response content")
    public void iAssertOnRequestAndResponseContent() {
        ops.comparePostRequestResponse();
    }

    @And("I update pet status to {string}")
    public void iUpdatePetStatusTo(String status) {
        ops.updateStatus(status);
    }

    @And("I verify pet status is updated to {string}")
    public void iVerifyPetStatusIsUpdatedTo(String status) {
        ops.VerifyStatusAfterUpdate(status);
    }

    @And("I Delete this pet")
    public void iDeleteThisPet() {
        ops.DeletePet();

    }

    @And("I verify pet is deleted successfully")
    public void iVerifyPetIsDeletedSuccessfully() {
        ops.verifyPetsDeleted();
    }
}
