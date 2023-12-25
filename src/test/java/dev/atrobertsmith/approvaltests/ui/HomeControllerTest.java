package dev.atrobertsmith.approvaltests.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.atrobertsmith.approvaltests.model.Book;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HomeControllerTest {

    private WebDriver driver;
    private ObjectMapper mapper;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void uiLoadsAndShowsExpectedContent() throws Exception {
        //GIVEN that a book has been created
        var book = mapper.writeValueAsString(new Book("Cat's Cradle", "K V"));
        mockMvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //WHEN we load the page
        driver.get("http://localhost:" + port + "/");

        var title = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[1]"));

        //THEN we see the expected content
        Approvals.verify(title.getText());
    }

}