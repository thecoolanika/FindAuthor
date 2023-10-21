import java.util.*;
/**
 * A Phrase object consists of a group of Token objects,
 * 		each containing a single word.
 * 
 * @author Anika Pandey
 * @version 5-23-23
 */
public class Phrase
{
    private List<Token> phrase; 
    //I chose a List because it is extremely 
    //efficient to traverse and can be expanded and shortened. 

    /**
     * Creates a new Phrase object
     */
    public Phrase()
    {
        phrase = new ArrayList<Token>();
    }
    
    /**
     * Creates a new Phrase object
     * @param p List<Token> to set phrase to
     */
    public Phrase(List<Token> p)
    {
        phrase = p;
    }
    
    
    /**
     * Adds a token to Phrase
     * @param t Token to add
     */
    public void addToken(Token t)
    {
        phrase.add(t);
    }

    /**
     * Returns a copy of Phrase as a List
     * @return List<Token> copy of phrase
     */
    public List<Token> getPhraseCopy()
    {
        List<Token> toReturn = new ArrayList<Token>();
        for(Token t: phrase)
            toReturn.add(t);
        return toReturn;
    }

    @Override
    public String toString()
    {
        String s = "";
        boolean noSpace = true;
        for(Token t: phrase)
        {
            if (noSpace || t.getTokenType() == Scanner.TOKEN_TYPE.END_OF_PHRASE ||
            	t.getTokenType() == Scanner.TOKEN_TYPE.UNKNOWN)
                s+=t.toString();
            else 
            {
                s+=" " + t.toString();
            }
            if (t.getTokenType() == Scanner.TOKEN_TYPE.UNKNOWN || 
            	t.getTokenType() == Scanner.TOKEN_TYPE.END_OF_PHRASE)
            	noSpace = true;
            else
            	noSpace = false;
        }
        return s;
    }

}
