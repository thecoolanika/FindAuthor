
/**
 * A Token is one of the token types defined in Scanner, holding a String. 
 * 
 * @author Riya Gupta
 * @version 5-22-20
 */
public class Token
{
    private Scanner.TOKEN_TYPE t;
    private String s;
    
    /**
     * Creates a new Token object
     * @param t Scanner.TOKEN_TYPE type of Token
     * @param s String that Token represents
     */
    public Token(Scanner.TOKEN_TYPE t, String s)
	{
	    this.t = t;
	    this.s = s;
	}
	
	/**
	 * Returns the type of Token as defined in the Scanner class
	 * @return Scanner.TOKEN_TYPE type of Token
	 */
	public Scanner.TOKEN_TYPE getTokenType()
	{
	    return t;
	}
	
	/**
	 * Returns the String Token represents
	 * @return String in Token
	 */
	public String getString()
	{
	    return s;
	}
	
	/**
	 * Returns the String Token represents by calling getString()
	 * @return String in Token
	 */
	public String toString()
	{
	    return getString();
	}
	
	/**
	 * Checks if this Token is equal to the passed Token
	 * 		by the comparing the type and String
	 * @param t Token to check if this is equal to it
	 * @return true if equal;otherwise false
	 */
	public boolean equals(Token t)
	{
	    return getTokenType().equals(t.getTokenType()) &&
	    		getString().equals(t.getString());
	}

	/**
	 * Returns hashCode for a Token
	 * @return hash code for Token
	 */
	public int hashCode()
	{
	    return(s.hashCode());
	}
}
