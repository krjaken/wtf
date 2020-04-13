package api;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class ApiTests {

    @Test(testName = "Первый тест")
    public void firstTest(){
        log.error("firstTest");
    }
}
