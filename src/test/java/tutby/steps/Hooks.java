package tutby.steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import framework.BaseEntity;
import framework.Browser;

public class Hooks {
    @Before
    public void prepareTest() {
        BaseEntity.before();
    }

    @After
    public void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            Browser.captureScreenshot(scenario.getName());
        }
        BaseEntity.after();
    }
}