package csci230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class CSCI 230-02
 * @author James Keels
 */
public class PalindromeChecker
{
    //userInput - global variable initally set to known invalid string
    private static String userInput = "%";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {      
        while (!isValidString())
        {
            System.out.println("----------------------------------");
            getUserPrompt();
        }
        
        if(isPalindrome())
        {
            System.out.printf("%s is a palindrome.", userInput);
        }
        else
        {
            System.out.printf("%s is not a palindrome.", userInput);
        }
    }
    
    /**
     * getUserPrompt gets user input and sets it equal to "usserInput"
     */
    private static void getUserPrompt()
    {
        try 
            {
                BufferedReader consoleInput = 
                        new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Please enter a string that only contains " +
                        "digits (0-9) and letters (a-z): ");
                userInput = consoleInput.readLine();
            } 
        catch (IOException e) 
            {
            }
    }
    
    /**
     * isValidString verifies that user input is a string of numbers, letters 
     * or a mixture of both
     * 
     * @return true/false to whether the string is valid
     */
    private static boolean isValidString()
    {
        boolean isValid = true;
        
        for(int i = 0; i < userInput.length(); i++)
        {
            if(!Character.isLetterOrDigit(userInput.charAt(i)))
            {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
    
    /**
     * isPalindrome checks string length, then checks if string is a palindrome 
     * only if length is greater than zero
     * 
     * @return true/false as to whether string is palindrome
     */
    private static boolean isPalindrome()
    {
        int len = userInput.length();
        int max;
        boolean result = true; 
        
        if(len == 0)
        {
            userInput = "An \"Empty String\"";
        }
        else
        {
            max = len % 2 == 0 ? (len / 2) : (int)Math.floor(len / 2);                

            for(int i = 0; i <= max; i++)
            {
                if((int)userInput.toLowerCase().charAt(i) != (int)userInput.toLowerCase().charAt(len - i - 1))
                {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}