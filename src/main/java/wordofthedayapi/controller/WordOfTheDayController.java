package wordofthedayapi.controller;

import wordofthedayapi.models.Definition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class WordOfTheDayController {

    private List<Definition> definitionList;
    private static int idCounter = 1;

    public WordOfTheDayController() {
        definitionList = new ArrayList<>();

        definitionList.add(new Definition(idCounter++, "atrocity", "an act of shocking cruelty"));
        definitionList.add(new Definition(idCounter++, "fanatical", "marked by excessive enthusiasm for a cause or idea"));
        definitionList.add(new Definition(idCounter++, "pensive", "deeply or seriously thoughtful"));
        definitionList.add(new Definition(idCounter++, "respite", "a pause from doing something"));
        definitionList.add(new Definition(idCounter++, "discordant", "not in agreement or harmony"));
        definitionList.add(new Definition(idCounter++, "eloquent", "expressing yourself readily, clearly, effectively"));
        definitionList.add(new Definition(idCounter++, "encompass", "include in scope"));
        definitionList.add(new Definition(idCounter++, "imperceptible", "impossible or difficult to sense"));
        definitionList.add(new Definition(idCounter++, "insuperable", "incapable of being surpassed or excelled"));
        definitionList.add(new Definition(idCounter++, "lethargy", "inactivity; showing an unusual lack of energy"));
    }

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getRandomWord() {
        Random random = new Random();
        int randomId = random.nextInt(definitionList.size());

        String word = definitionList.get(randomId).getWord();
        String definition = definitionList.get(randomId).getDefinition();

        return word + " - " + definition;
    }

    @RequestMapping(value = "/words", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Definition> getAllDefinitions() {

        return definitionList;
    }

    @RequestMapping(value = "/words", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Definition createDefinition(@RequestBody Definition definition) {

        definition.setId(idCounter++);
        definitionList.add(definition);

        return definition;
    }

    @RequestMapping(value = "/words/{word}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDefinitionByWord(@PathVariable String word) {
        int index = -1;

        for(int i = 0; i < definitionList.size(); i++) {
            if(definitionList.get(i).getWord().equals(word)) {
                index = i;
                break;
            }
        }

        if(index >=0) {
            definitionList.remove(index);
        }
    }
}
