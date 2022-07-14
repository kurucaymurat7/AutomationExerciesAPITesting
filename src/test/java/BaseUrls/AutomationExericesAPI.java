package BaseUrls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AutomationExericesAPI {
    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://automationexercise.com").build();
    }
}
