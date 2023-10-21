import java.util.*;
/**
 * A Setence object consists of a group of Sentence objects,
 * 
 * @author Riya Gupta
 * @version 5-23-20
 */
public class Sentence
{
    private List<Phrase> sentence; 
    //I chose a List because it is extremely 
    //efficient to traverse and can be expanded and shortened. 

    /**
     * Creates a new Sentence object
     */
    public Sentence()
    {
        sentence = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to Sentence
     * @param p Phrase to add
     */
    public void addPhrase(Phrase p)
    {
        sentence.add(p);
    }

    /**
     * Returns a copy of Sentence as a List
     * @return List<Phrase> copy of sentence
     */
    public List<Phrase> getSentenceCopy()
    {
        List<Phrase> toReturn = new ArrayList<Phrase>();
        for(Phrase p: sentence)
            toReturn.add(p);
        return toReturn;
    }

    @Override
    public String toString()
    {
        String s = "";
        for(Phrase p: sentence)
        {
            s+=p.toString();
            s+= " ";
        }
        return s;
    }

}
