package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;

public class SteapDeff implements En {
    @Given("I do something here")
    public void iDoSomethingHere() {
        System.out.println("iDoSomethingHere");
    }

    @When("I do something (.*) also")
    public void iDoSomethingHereAlso() {
        System.out.println("iDoSomethingHereAlso");
    }

    @Then("I valid that")
    public void iValidThat() {
        System.out.println("iValidThat");
    }

    @Then("kill all humans")
    public void killAllHumans() {
        System.out.println("kill them all");
    }
}
