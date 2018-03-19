package de.tappd.Tappd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getEveryBeerStyleIsCreatingWellDone() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/style").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("170 Beer-Styles imported")));
    }
    
    @Test
    public void getEveryBreweryIsCreatingWellDone() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/brewery").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("300 Breweries created")));
    }
    
    @Test
    public void getEveryBeerIsCreatingWellDone() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/rest/beer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("250 beers drunk")));
    }
}
