import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens.  A token is defined as:
 *  1. A 'word' which is defined as a non-empty sequence of characters that 
 *     begins with an alpha character and then consists of alpha characters, 
 *     numbers, the single quote character "'", or the hyphen character "-". 
 *  2. An 'end-of-sentence' delimiter defined as any one of the characters 
 *     ".", "?", "!".
 *  3. An end-of-file token which is returned when the scanner is asked for a
 *     token and the input is at the end-of-file.
 *  4. A phrase separator which consists of one of the characters ",",":" or
 *     ";".
 *  5. A digit.
 *  6. Any other character not defined above.
 *  
 * @version 5-22-20
 * 
 * @author Mr. Page
 * @author Riya Gupta
 * 
 *
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;
    /**
     * Defines symbolic constants for each type of token
     */
    public static enum TOKEN_TYPE
    {
        WORD, END_OF_SENTENCE, END_OF_FILE, 
        END_OF_PHRASE, DIGIT, UNKNOWN
    };
    /**
     * Constructor for Scanner objects.  The Reader object should be one of
     *  1. A StringReader
     *  2. A BufferedReader wrapped around an InputStream
     *  3. A BufferedReader wrapped around a FileReader
     *  The instance field for the Reader is initialized to the input parameter,
     *  and the endOfFile indicator is set to false.  The currentChar field is
     *  initialized by the getNextChar method.
     * @param in is the reader object supplied by the program constructing
     *        this Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream.  It sets the endOfFile flag true if the end of file is reached on
     * the input stream.  Otherwise, it reads the next character from the stream
     * and converts it to a Java String object.
     * postcondition: The input stream is advanced one character if it is not at
     * end of file and the currentChar instance field is set to the String 
     * representation of the character read from the input stream.  The flag
     * endOfFile is set true if the input stream is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == '\r')
                inp = in.read();
            if(inp == -1) 
                endOfFile = true;
            else 
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Calls getNextChar() if the passed String
     * 		is the same as currentChar or throws
     * 		IllegalArgumentException
     * @param s String to check if equal to currentChar
     * @throws IllegalArgumentException
     */

    private void eat(String s)
    {	
        if(s.equals(currentChar))
        {
            getNextChar();
        }
        else
        {
            throw new IllegalArgumentException(
                "the next char does not match what was passed into eat()");
        }
    }

    /**
     * Checks if the passed String that holds a single character is a letter
     * @param s String to check if letter
     * @return true if s is a letter, otherwise false. 
     */
    private boolean isLetter(String s)
    {
        return (s.compareTo("a") >= 0 && s.compareTo("z") <= 0) || 
        (s.compareTo("A") >= 0 && s.compareTo("Z") <= 0);
    }

    /**
     * Checks if the passed String that holds a single character is a digit
     * @param s String to check if digit
     * @return true if s is a digit, otherwise false. 
     */
    private boolean isDigit(String s)
    {
        return (s.compareTo("0") >= 0 && s.compareTo("9") <= 0);
    }

    /**
     * Checks if the passed String that holds a single character is a special 
     * 		character (single quote or hypen)
     * @param s String to check if special character
     * @return true if s is a special character, otherwise false. 
     */
    private boolean isSpecialChar(String s)
    {
        return (s.equals("'") || s.equals("-"));
    }

    /***
     * Checks if the passed String that holds a single character is a 
     * 		phrase terminator
     * @param s String to check if phrase terminator
     * @return true if s is a phrase terminator, otherwise false. 
     */
    private boolean isPhraseTerminator(String s)
    {
        return(s.equals(",") || s.equals(":") || s.equals(";"));
    }

    /***
     * Checks if the passed String that holds a single character is a 
     * 		sentence terminator
     * @param s String to check if sentence terminator
     * @return true if s is a sentence terminator, otherwise false. 
     */
    private boolean isSentenceTerminator(String s)
    {
        return(s.equals(".") || s.equals("?") || s.equals("!"));
    }

    /***
     * Checks if the passed String that holds a single character is a white space
     * @param s String to check if white space
     * @return true if s is a white space, otherwise false. 
     */
    private boolean isWhiteSpace(String s)
    {
        return(s.equals(" ") ||s.equals("\n"));
    }

    /**
     * Checks if Scanner has reached the end of the file
     * @return true if it has not reached the end, otherwise false.
     */

    public boolean hasNextToken()
    {
        return(!endOfFile);
    }

    /**
     * Returns the next token in the file
     * @return Token next token in the file
     */
    public Token nextToken()
    {
        String inToken ="";
        TOKEN_TYPE token_type = TOKEN_TYPE.UNKNOWN;

        //int spaceCount = 0;
        while(!endOfFile && isWhiteSpace(currentChar)) 
        {
            eat(currentChar);
            //spaceCount++;
        }
        //if (spaceCount > 1)
        //return new Token(TOKEN_TYPE.END_OF_PHRASE, inToken);

        if (endOfFile)
        {
            token_type = TOKEN_TYPE.END_OF_FILE;
        }
        else if (token_type == TOKEN_TYPE.UNKNOWN) 
        {

            if (isLetter(currentChar))
            {
                token_type = TOKEN_TYPE.WORD;
            }
            else if (isDigit(currentChar))
            {
                token_type = TOKEN_TYPE.DIGIT;
            }
            else if (isPhraseTerminator(currentChar))
            {
                token_type = TOKEN_TYPE.END_OF_PHRASE;
            }
            else if (isSentenceTerminator(currentChar))
            {
                token_type = TOKEN_TYPE.END_OF_SENTENCE;
            }
            inToken = currentChar;
            eat(currentChar);
        }
        if (token_type == TOKEN_TYPE.WORD) 
        {
            while (!endOfFile && !isWhiteSpace(currentChar) && !isPhraseTerminator(currentChar) &&
            !isSentenceTerminator(currentChar))

            {
                inToken += currentChar;
                eat(currentChar);
            }
        }
        if (token_type == TOKEN_TYPE.DIGIT) 
        {
            while (!endOfFile && isDigit(currentChar) && !isWhiteSpace(currentChar) && 
            !isPhraseTerminator(currentChar) &&
            !isSentenceTerminator(currentChar))
            {
                inToken += currentChar;
                eat(currentChar);
            }
        }
        return new Token(token_type, inToken);
    }

}
