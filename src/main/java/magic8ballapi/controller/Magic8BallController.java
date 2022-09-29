package magic8ballapi.controller;

import magic8ballapi.models.Answer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class Magic8BallController {

    private List<Answer> answerList;
    private static int idCounter = 1;

    public Magic8BallController() {
        answerList = new ArrayList<>();

        answerList.add(new Answer(idCounter++, "", "It is certain."));
        answerList.add(new Answer(idCounter++, "", "It is decidedly so."));
        answerList.add(new Answer(idCounter++, "", "Reply hazy, try again."));
        answerList.add(new Answer(idCounter++, "", "Ask again later."));
        answerList.add(new Answer(idCounter++, "", "Don't count on it."));
        answerList.add(new Answer(idCounter++, "", "My reply is no."));
    }

    @RequestMapping(value = "/magic", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Answer> getAllAnswers() {

        return answerList;
    }

    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public String askQuestion(@RequestBody Answer question) {

        Random random = new Random();
        int randomId = random.nextInt(answerList.size());

        question.setId(idCounter++);
        question.setQuestion(question.getQuestion());
        question.setAnswer(answerList.get(randomId).getAnswer());

        answerList.add(question);

        return question.getAnswer();
    }

    @RequestMapping(value = "/magic/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAnswerById(@PathVariable int id) {
        int index = -1;

        for(int i = 0; i < answerList.size(); i++) {
            if(answerList.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if(index >= 0) {
            answerList.remove(index);
        }
    }
}
