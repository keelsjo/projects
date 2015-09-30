package csci230;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @class CSCI 230-02
 * @author James Keels
 */
public class BinaryCounter
{    
    public static void main(String[] args) 
    {
        int userInput;
        int result;
        final int initAccum = 0;
        
        userInput = userPrompt();
        result = countOnes(userInput, initAccum);
        System.out.printf("There are %d ones in the binary "
                + "representation of %d\r\n", result, userInput);
    }
    
    /**
     * countOnes converts base 10 number into base 2 then counts the number 
     * of ones in the binary number recursively
     * 
     * @param num number to check
     * @param accum running tally of 1's
     * @return integer number of 1's in binary representation of decimal number
     */   
    private static int countOnes(int num, int accum)
    {
        if (num != 0)
        {
            accum += num % 2;
            return countOnes(num / 2, accum);
        }
        return accum;
    }
    
    /**
     * userPrompt gets value from user as string, casts it to type int, and
     * verifies that it is positive. 
     * 
     * @return integer value from user in base 10
     */    
    private static int userPrompt() 
    {
        Integer decValue = null;

        while(decValue == null)
        {
            try 
            {
                BufferedReader consoleInput = 
                        new BufferedReader(new InputStreamReader(System.in));

                System.out.print("Enter a Positive Integer Value: ");
                decValue = Integer.parseInt(consoleInput.readLine());
                if(isNegative(decValue))
                {
                    decValue = null;
                    throw new IOException();
                }
            } 
            catch (IOException | NumberFormatException e) 
            {
                System.out.println("Invalid Value - Please try again\r\n");               
            }
        }        
        return decValue;
    }
    
    /**
     * isNegative checks if base 10 number is negative
     * 
     * @param num number to check
     * @return true/false on whether number is negative (num < 0)
     */    
    private static boolean isNegative(int num)
    {
        return num < 0;
    }
}