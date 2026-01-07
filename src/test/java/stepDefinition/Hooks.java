package stepDefinition;

import Context.TestContext;
import TestComponents.DriverManagerSingletonClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class Hooks {

////    🔷 4️⃣ Hooks (Dependency Injection Happens Here)
//    private TestContext testContext;
//
//    public Hooks(TestContext testContext){
//        this.testContext=testContext;
//    }
////    Cucumber injects TestContext automatically (PicoContainer).
//
//    @Before
//    public void Setup(){
//        testContext.setDriver(DriverManagerSingletonClass.getDriver());
//    }
//
//
//    public void testHomePageTaskinHooks(){
//        testContext.getHomePage().clickCartButton();
//    }


    /*
    “Cucumber supports AND, OR, NOT expressions in conditional hooks.”
    “Multiple tags in hooks are handled using tag expressions, not arrays.”
    “Order attribute controls hook execution sequence.”
    “Conditional hooks improve performance and test isolation.”
    */

    @Before("@test3")
    public void testMethod1(){
        System.out.println("Before Method Executed using @before tag");
    }

    @Before(value = "@test3", order= 1)
    public void launchBrowser(){
        System.out.println("Broswer Launched - - - -");
    }

    @Before(value = "@test3", order= 2)
    public void login(){
        System.out.println("Logged in - - - -");
    }

    @After("(@test3 or @test1) and not @test2")
    public void testMethod2(){
        System.out.println("After Method Executed using @after tag");
    }

    @BeforeStep("not @test3")
    public void testMethod3(){
        System.out.println("BeforeStep method");
    }

    @AfterStep("@test3")
    public void testMethod4(){
        System.out.println("AfterStep method");
    }

}
