package com.retail.manager.test.stepdefs;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

public class CommonStepDefs {
   

    @Before
    public void setUp() throws Exception {
        System.out.println("in setup");
    }

    @After
    public void tearDown() throws Exception {
    	System.out.println("in tearDown");
    }

    @Given("^the service has been deployed$")
    public void the_service_has_been_deployed() throws Throwable {
       System.out.println("in service deployed step");
    }
    
    @Given("^the root endpoint is queried$")
    public void the_root_endpoint_is_queried() throws Throwable {
    	System.out.println("the root endpoint is queried");
    }
    @Given("^the response should be successful$")
    public void the_response_should_be_successful() throws Throwable {
    	System.out.println("the response should be successful");
    	
    }
}
