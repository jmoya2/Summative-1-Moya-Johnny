package wordofthedayapi.controller;

import wordofthedayapi.models.Definition;
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
@WebMvcTest(WordOfTheDayController.class)
public class WordOfTheDayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Definition> definitionList;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnRandomWordAndDefinition() throws Exception {
        mockMvc.perform(get("/word")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllWordsAndDefinitionsInCollection() throws Exception {
        mockMvc.perform(get("/words")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNewWordAndDefinition() throws Exception {
        Definition inputDefinition = new Definition();

        inputDefinition.setId(11);
        inputDefinition.setWord("cache");
        inputDefinition.setDefinition("a collection of similar items stored in a secret place.1");

        String inputJson = mapper.writeValueAsString(inputDefinition);

        mockMvc.perform(post("/words").content(inputJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void shouldDeleteByWord() throws Exception {
        Definition inputDefinition = new Definition();

        inputDefinition.setId(11);
        inputDefinition.setWord("lido");
        inputDefinition.setDefinition("a fashionable beach resort.");

        mockMvc.perform(delete("/words/" + inputDefinition.getWord()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }
}
