package Context;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;

public class TestContext {  //👉 This object is injected everywhere


    private WebDriver driver;
    private HomePage homePage;
    private ScenarioContext scenarioContext;

    public TestContext(){
        scenarioContext = new ScenarioContext();
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

    public void setScenarioContext(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

//    ❓ How does DI work in Cucumber?
//    Cucumber uses constructor-based DI via PicoContainer to inject shared objects per scenario.

//    ❓ Why TestContext is needed?
//    To maintain shared state (driver, pages, scenario data) without static variables.
//
//    ❓ How does this support parallel execution?
//    Each scenario gets its own TestContext instance.
//
//    ❓ Why not initialize driver in step definition?
//    Violates separation of concerns and causes duplication.

}
