public class SomethingTest {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("@BeforeSuite");
    }

    @Test(priority = 7)
    public void test1() {
        System.out.println("Тест 1");
    }

    @Test(priority = 10)
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 2)
    public void test3() {
        System.out.println("Тест 3");
    }

    @Test(priority = 3)
    public void test4() {
        System.out.println("Тест 4");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("@AfterSuite");
    }
}