package quoteofthedayapi.controller;

import quoteofthedayapi.models.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class QuoteOfTheDayController {

    private List<Quote> quoteList;

    private static int idCounter = 1;

    public QuoteOfTheDayController() {
        quoteList = new ArrayList<>();

        quoteList.add(new Quote(idCounter++, "Things work out best for those who make the best of how things work out", "John Wooden"));
        quoteList.add(new Quote(idCounter++, "If you are not willing to risk the usual, then you will have to settle for the ordinary", "Jim Rohn"));
        quoteList.add(new Quote(idCounter++, "All our dreams can come true if we have the courage to pursue them.", "Walt Disney"));
        quoteList.add(new Quote(idCounter++, "Success is walking from failure to failure with no loss of enthusiasm.", "Winston Churchill"));
        quoteList.add(new Quote(idCounter++, "Opportunities don't happen, you create them.", "Chris Grosser"));
        quoteList.add(new Quote(idCounter++, "Don't be afraid to give up the good to go for the great.", "John D. Rockefeller"));
        quoteList.add(new Quote(idCounter++, "Only put off until tomorrow what you are willing to die having left undone.", "Pablo Picasso"));
        quoteList.add(new Quote(idCounter++, "Motivation is what gets you started. Havit is what keep you going.", "Jim Ryun"));
        quoteList.add(new Quote(idCounter++, "Don't let what you cannot do interfere with what you can do.", "John R. Wooden"));
        quoteList.add(new Quote(idCounter++, "You may have to fight a battle more than once to win it.", "Margaret Thatcher"));
    }

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String getRandomQuote() {
        Random random = new Random();
        int randomId = random.nextInt(quoteList.size());

        return quoteList.get(randomId).getQuote();
    }

    @RequestMapping(value = "/quotes", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Quote> getAllQuotes() {

        return quoteList;
    }

    @RequestMapping(value = "/quotes", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Quote createQuote(@RequestBody Quote quote) {

        quote.setId(idCounter++);
        quoteList.add(quote);

        return quote;
    }

    @RequestMapping(value = "/quotes", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateQuoteById(@RequestBody Quote quote) {
        int index = -1;

        for(int i = 0; i < quoteList.size(); i++) {
            if(quoteList.get(i).getId() == quote.getId()) {
                index = i;
                break;
            }
        }

        if(index >= 0) {
            quoteList.set(index, quote);
        }
    }

    @RequestMapping(value = "/quotes/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteQuoteById(@PathVariable int id) {
        int index = -1;

        for(int i = 0; i < quoteList.size(); i++) {
            if(quoteList.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        if(index >= 0) {
            quoteList.remove(index);
        }
    }

}
