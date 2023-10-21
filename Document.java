import java.util.*;
import java.io.*;

/**
 * A Document object is responsible for directing the scanning and parsing of a document. 
 * It will encapsulate all of the sentences found in the document.
 * 
 * @author Anika Pandey
 * @version 5-23-23
 */
public class Document
{
    private List<Sentence> document;
    //I chose a List because it is extremely 
    //efficient to traverse and can be expanded and shortened. 
    private Scanner s;
    private Token curr;
    private Phrase phrase;
    private int letters;
    private int words;
    private int sentences;
    private Map<String, Integer> allWords;
    private int phrases;

    /**
     * Creates a new Document
     * @param s Scaner that scans Document
     */
    public Document(Scanner s)
    {
        //System.out.println("In Document" + s);
        document = new ArrayList<Sentence>();
        this.s = s;
        curr = s.nextToken();
        letters = 0;
        words = 0;
        sentences = 0;
        allWords = new HashMap();

        //while (curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_FILE) 
        //{
        //    Sentence sentence = parseSentence();
        //    System.out.println("Found sentence " + sentence);
        //    document.add(sentence);
        //}
        //for (Sentence sentence: document)
        //{
        //    System.out.println(sentence);
        //}
        while (curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_FILE) 
        {
            Sentence sentence = parseSentence();
            document.add(sentence);
        }
        for (Sentence sentence: document)
        {
            //System.out.println(sentence);
        }

    }

    /**
     * Returns a Map of all the words
     * 		that map to its frequency
     * 		in Document
     * @return Map of all the words that map 
     * 		to its frequency
     */
    public Map getAllWords()
    {
        return allWords;
    }

    /**
     * Returns the number of letters in Document
     * @return int number of letters in Document
     */
    public int getLetters()
    {
        return letters;
    }

    /**
     * Returns the number of words in Document
     * @return int number of words in Document
     */
    public int getWords()
    {
        return words;
    }

    /**
     * Returns the number of sentences in Document
     * @return int number of sentences in Document
     */
    public int getSentences()
    {
        return sentences;
    }

    /**
     * Returns the number of phrases in Document
     * @return int number of phrases in Document
     */
    public int getPhrases()
    {
        return phrases;
    }

    /**
     * Returns the next token in the document
     * @return Token next token in the document
     */
    private Token getNextToken()
    {
        if(curr.getTokenType() == 
        	Scanner.TOKEN_TYPE.WORD || curr.getTokenType() == Scanner.TOKEN_TYPE.DIGIT)
        {
            words++;
            letters += curr.toString().length();
            String lowercase = curr.toString().toLowerCase();
            //System.out.println(lowercase);

            if(allWords.containsKey(lowercase))
            {
                //System.out.println(curr.toString().toLowerCase());
                allWords.put(lowercase, allWords.get(lowercase)+1);
            }
            else
                allWords.put(lowercase, 1);
        }

        curr = s.nextToken();
        return curr;
    }

    /**
     * Checks if the passed Token equals the current Token 
     * 		and calls getNextToken(), otherwise throws an exception
     * @param t Token to check if equal to current Token
     */
    public void eat(Token t)
    {
        if(t.equals(curr))
        {
            getNextToken();
        }
        else 
        {
            throw new IllegalArgumentException(
                "the next token does not match what was passed into eat()");
        }
    }

    /**
     * Parses a phrase
     * @return Phrase tokens parsed together
     */
    public Phrase parsePhrase()
    {
        Phrase phrase = new Phrase();
        boolean validPhrase = false;
        while (curr.getTokenType() == Scanner.TOKEN_TYPE.END_OF_PHRASE || 
        	curr.getTokenType() == Scanner.TOKEN_TYPE.UNKNOWN)
            eat(curr);
        while (curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_PHRASE &&
        	curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE &&
        	curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            if (curr.getTokenType() == Scanner.TOKEN_TYPE.WORD || 
            	curr.getTokenType() == Scanner.TOKEN_TYPE.DIGIT)
                validPhrase = true;
            phrase.addToken(curr);
            eat(curr);
        }
        if (curr.getTokenType() == Scanner.TOKEN_TYPE.END_OF_PHRASE)
        {
            phrase.addToken(curr);
            eat(curr);
        }
        if (validPhrase)
            phrases++;
        return phrase;
    }

    /**
     * Parses a sentence
     * @return Sentence phrases parsed together
     */
    public Sentence parseSentence()
    {
        Sentence sentence = new Sentence();
        while (curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_SENTENCE &&
        	curr.getTokenType() != Scanner.TOKEN_TYPE.END_OF_FILE)
        {
            Phrase phrase = parsePhrase();
            //System.out.println("Phrase:" + phrase);
            sentence.addPhrase(phrase);
        }
        if (curr.getTokenType() == Scanner.TOKEN_TYPE.END_OF_SENTENCE)
        {
            List<Token> punctuation = new ArrayList<Token>();
            punctuation.add(curr);
            Phrase end = new Phrase(punctuation);
            sentence.addPhrase(end);
            eat(curr);
        }
        sentences++;
        return sentence;
    }

    /**
     * Parses Document
     */
    public void parseDocument()
    {
        while(curr.getTokenType() != (Scanner.TOKEN_TYPE.WORD))
        {
            eat(curr);
        }
        while(curr.getTokenType() != (Scanner.TOKEN_TYPE.WORD))
        {
            Sentence s = parseSentence();
            document.add(s);
        }
    }

    
}
