package quoteofthedayapi.controller;

import quoteofthedayapi.models.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuoteOfTheDayController.class)
public class QuoteOfTheDayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Quote> quoteList;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnRandomQuote() throws Exception {
        mockMvc.perform(get("/quote")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllQuotesInCollection() throws Exception {
        mockMvc.perform(get("/quotes")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNewQuote() throws Exception {
        Quote inputQuote = new Quote();

        inputQuote.setId(11);
        inputQuote.setQuote("Cogito ergo sum");
        inputQuote.setAuthor("Rene Descartes");

        String inputJson = mapper.writeValueAsString(inputQuote);

        mockMvc.perform(post("/quotes").content(inputJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Quote inputQuote = new Quote();

        inputQuote.setId(11);
        inputQuote.setQuote("Be yourself; everyone else is already taken.");
        inputQuote.setAuthor("Oscar Wilde");

        mockMvc.perform(delete("/quotes/" + inputQuote.getId()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }
}
