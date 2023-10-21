import java.io.*;

/**
 * Author describes any author with many of 
 * his/her different stats. 
 * 
 * @author Riya Gupta
 * 
 * @version 5-31-20
 */
public class Author
{
    private String directory;
    private FileInputStream in;
    private DataInputStream din;
    private String author;
    private Double averageWordLength;
    private Double averageWordsPerSentence;
    private Double typeTokenRatio;
    private Double hapaxLegomana;
    private Double complexity;
    //private double score;

    /**
     * Creates a new Authors
     * @param fileName String name of file 
     * 		with author stats
     */
    public Author(String fileName)
    {
        try
        {
            directory = "FindAuthorMaterial/SignatureFiles/";
            in = new FileInputStream(directory + fileName);
            din = new DataInputStream(in);
            author = din.readLine();
            averageWordLength = new Double(din.readLine());
            typeTokenRatio = new Double(din.readLine());
            hapaxLegomana = new Double(din.readLine());
            averageWordsPerSentence = new Double(din.readLine());
            complexity = new Double(din.readLine());
            // System.out.println(averageWordLength + " " + 
            //    typeTokenRatio + " " + 
            //    hapaxLegomana + " " + averageWordsPerSentence + " " +
            //    complexity);
            //score = 11*averageWordLength + 33*typeTokenRatio
            //+ 50*hapaxLegomana + 0.4*averageWordsPerSentence
            //+ 4*complexity;
        }
        catch (Exception ex) 
        {
            System.err.println("File not found");
        }
    }

    /**
     * Returns the author's name
     * @return String authors's name
     */
    public String getName()
    {
        return author;
    }

    /**
     * Returns the author's average word length
     * @return Double author's average word length
     */
    public Double aWL()
    {
        return averageWordLength;
    }

    /**
     * Returns the author's average sentence length
     * @return Double author's average sentence length
     */
    public Double aWPS()
    {
        return averageWordsPerSentence;
    }

    /**
     * Returns the author's type token ratio
     * @return Double author's average sentence length
     */
    public Double typeTokenRatio()
    {
        return typeTokenRatio;
    }

    /**
     * Returns the author's hapax legomana ratio 
     * @return Double author's hapax legomana ratio
     */
    public Double hL()
    {
        return hapaxLegomana;
    }
    
	/**
	 * Returns the author's sentence complexity
	 * @return Double author's sentence complexity
	 */
    public Double com()
    {
        return complexity;
    }

}
