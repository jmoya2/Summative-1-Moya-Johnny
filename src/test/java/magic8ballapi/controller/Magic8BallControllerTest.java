package magic8ballapi.controller;

import magic8ballapi.models.Answer;
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
@WebMvcTest(Magic8BallController.class)
public class Magic8BallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    private List<Answer> answerList;

    @Before
    public void setUp() {

    }

    @Test
    public void shouldReturnRandomAnswer() throws Exception {
        Answer inputAnswer = new Answer();

        inputAnswer.setQuestion("Will I win the lottery?");

        String inputJson = mapper.writeValueAsString(inputAnswer);

        mockMvc.perform(post("/magic").content(inputJson).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnAllAnswersInCollection() throws Exception {
        mockMvc.perform(get("/magic")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Answer inputAnswer = new Answer();

        inputAnswer.setId(6);

        mockMvc.perform(delete("/magic/" + inputAnswer.getId()).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }
}
