import java.util.*;
import java.io.*;

/**
 * FindAuthor, given a file, can make
 * 		a guess on who the author is
 * @author Riya Gupta
 * 
 * @version 5-31-20
 */
public class FindAuthor
{
    private Double averageWordLength;
    private Double averageWordsPerSentence;
    private Double typeTokenRatio;
    private Double hapaxLegomana;
    private Double complexity;
    //private double score;
    private Document d;

    /**
     * Creates a new FindAuthor object
     * @param fileName String file to find author of
     */
    public FindAuthor(String fileName) 
    {
        try
        {
            //FileReader fr = new FileReader(fileName);
            //BufferedReader br = new BufferedReader(fr);
            //Scanner s = new Scanner(br);
            // d = new Document(s);
            //System.out.println("Scanned file " + fileName);
            d = new Document(new Scanner(new BufferedReader(new FileReader(fileName))));
            //System.out.println("Scanned file " + fileName);
            averageWordLength = findAWL();
            averageWordsPerSentence = findAWPS();
            typeTokenRatio = findTTR();
            hapaxLegomana = findHL();
            complexity = findC();
            //score = 11*averageWordLength + 33*typeTokenRatio
            //+ 50*hapaxLegomana + 0.4*averageWordsPerSentence
            //+ 4*complexity;
            //System.out.println("Scanned file " + fileName + " score: " + score);

        }
        catch (Exception ex) 
        {
            System.err.println("File not found" + ex);
        }
    }

    /*
    public void printStats() 
    {
        System.out.println("Stats are " + averageWordLength + " " + 
            typeTokenRatio + " " +
            hapaxLegomana + " " + averageWordsPerSentence + " " + complexity);
        System.out.println("Words are " + d.getWords());
        System.out.println("Letters are " + d.getLetters());

    }
    */

    /*public double getScore()
    {
        //return score;
    }*/

    /**
     * Finds the average word length of a file
     * @return double average word length of a file
     */
    public double findAWL()
    {
        double w = d.getWords();
        double l = d.getLetters();
        //System.out.println("The words and letters are " + w + " " + l);
        return l/w;
    }

    /**
     * Finds the average word per sentence in a file
     * @return double average word per sentence in a file
     */
    public double findAWPS()
    {
        double w = d.getWords();
        double s = d.getSentences();
        //System.out.println("The words and sentences are " + w + " " + s);
        return w/s;
    }

    /**
     * Finds the token type ratio of a file
     * @return double token type ratio of a file
     */
    public double findTTR()
    {
        Map wordsWithCounters  = d.getAllWords();
        double diff = wordsWithCounters.keySet().size();
                //System.out.println("Diff " + diff + " words " + d.getWords());
        return diff/d.getWords();
    }

    /**
     * Finds the Hapax Lagamenon ratio of a file
     * @return double Hapax Lagamenon ratio of a file
     */
    public double findHL()
    {
        Map wordsWithCounters  = d.getAllWords();
        double ocurringOnce = 0;
        for(Object s: wordsWithCounters.keySet())
        {
            if(wordsWithCounters.get(s).equals(1))
                ocurringOnce++;
        }
        //System.out.println("Occur Once " + ocurringOnce + " words " + d.getWords());
        return ocurringOnce/d.getWords();
    }

    /**
     * Finds the complexity of a file
     * @return double complexity of file
     */
    public double findC()
    {
        double numP = (double) d.getPhrases();
        //System.out.println("The phrases and sentences are " + numP + " " + d.getSentences());
        return numP/d.getSentences();
    }

    /*
    public static void main(String[] args)
    {
    List<Author> authors = new ArrayList<Author> ();
    List<Double> scores = new ArrayList<Double>  ();
    File directoryPath = new File("FindAuthorMaterial/SignatureFiles/");
    File filesList[] = directoryPath.listFiles();
    for(File f: filesList)
    {
    Author temp = new Author(f.getName());
    authors.add(temp);
    scores.add(temp.getScore());
    }  
    int best = 0;
    for(int i = 1; i<authors.size(); i++)
    {
    if(Math.abs(scores.get(i) - score) < Math.abs(scores.get(best) - score))
    {
    best = i;
    }
    }
    System.out.println(authors.get(best).getName());
    }
     */
}
