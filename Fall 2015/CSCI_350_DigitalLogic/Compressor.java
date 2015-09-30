package csci350;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
/**
 * 
 * @author Sebastian van Delden
 *
 * The purpose of this toy project is to get CSCI 350 students to play with
 * data on the binary level so that they get practice manipulating bits. 
 * 
 * The program will read in a text file and write out a compressed binary
 * version of the file. 
 * We will assume only the following characters are in the file:
 * 
 * A-Z                 26 characters
 * the space symbol    1 character
 * . , ' ! ?           5 characters
 *                     =============
 *  		           32 characters in total to be represented with 5 bits	
 *  
 * We should be able to compress our text files by about 5/8 ~= 60%
 *  
 * This will be a "lossy" compression meaning we will loose data. Any 
 * character in the input text file that is not in the above list simply gets
 * converted to a single blank space.		
 */
public class Compressor 
{
    /**
     * This hash/look-up table can be used to recovered the encoding 
     * (as a String) of a character
     */
    public static final Map<String, String> encoding = createMap();
	
    private static Map<String, String> createMap() {
    	
        Map<String, String> result = new HashMap<>();
        result.put(" ", "00000");
        result.put("A", "00001");
        result.put("B", "00010");
        result.put("C", "00011");
        result.put("D", "00100");
        result.put("E", "00101");
        result.put("F", "00110");
        result.put("G", "00111");
        result.put("H", "01000");
        result.put("I", "01001");
        result.put("J", "01010");
        result.put("K", "01011");
        result.put("L", "01100");
        result.put("M", "01101");
        result.put("N", "01110");
        result.put("O", "01111");
        result.put("P", "10000");
        result.put("Q", "10001");
        result.put("R", "10010");
        result.put("S", "10011");
        result.put("T", "10100"); 
        result.put("U", "10101");
        result.put("V", "10110");
        result.put("W", "10111");
        result.put("X", "11000");
        result.put("Y", "11001");
        result.put("Z", "11010");
        result.put(".", "11011");
        result.put(",", "11100");
        result.put("'", "11101");
        result.put("!", "11110");
        result.put("?", "11111");
        return Collections.unmodifiableMap(result);
    }
    
    /**
     * This hash/look-up table can be used to recovered the character from 
     * the encoding
     */
    public static final Map<String, String> decoding = createMap2();
	
    private static Map<String, String> createMap2() {
    	
        Map<String, String> result = new HashMap<>();
        result.put("00000", " ");
        result.put("00001", "A");
        result.put("00010", "B");
        result.put("00011", "C");
        result.put("00100", "D");
        result.put("00101", "E");
        result.put("00110", "F");
        result.put("00111", "G");
        result.put("01000", "H");
        result.put("01001", "I");
        result.put("01010", "J");
        result.put("01011", "K");
        result.put("01100", "L");
        result.put("01101", "M");
        result.put("01110", "N");
        result.put("01111", "O");
        result.put("10000", "P");
        result.put("10001", "Q");
        result.put("10010", "R");
        result.put("10011", "S");
        result.put("10100", "T");
        result.put("10101", "U");
        result.put("10110", "V");
        result.put("10111", "W");
        result.put("11000", "X");
        result.put("11001", "Y");
        result.put("11010", "Z");
        result.put("11011", ".");
        result.put("11100", ",");
        result.put("11101", "'");
        result.put("11110", "!");
        result.put("11111", "?");
        return Collections.unmodifiableMap(result);
    }
	
	
    public static void compress(String filename) throws Exception
    {
        // Read in all lines from the input text file that needs to
        //   be compressed.
        // Use Scanner.
        // Store it all as one big String

        String encodedStr = "";
        Scanner input = new Scanner(new File(filename));
        String content = "";
        while(input.hasNext()){
                content += input.nextLine();
        }
        input.close();
        // Create the file handle to the output file.
        FileOutputStream output = new FileOutputStream(
                new File(filename + ".compressed"));

        for(int i = 0; i < content.length(); i++)
        {
            String temp = String.valueOf(content.charAt(i)).toUpperCase();               

            if(encoding.containsKey(temp))
            {
                encodedStr += encoding.get(temp);
            }
            else
            {
                encodedStr += encoding.get(" ");
            }                
        }
        compressAndSave(encodedStr, output);
        
        // Close output file handle:
        output.close();
    }

    /**
     * The decompress method takes the compressed input file and converts it back to the 
     * original decompressed file.
     * 
     * @param filename is the name of the compressed file
     * @throws Exception
     */

    public static void decompress(String filename) throws Exception
    {
        // Get the contents of the compressed file and store as a String
        // internally which makes it easy to process. Convert String to array
        // of bytes for processing
        String newFilename = filename.replace("compressed", "decompresed");
        String byteString = "";
        String decodedString = "";
        byte[] fileData = Files.readAllBytes(Paths.get(filename));
        
        // Will be used to write out the decompressed file
        BufferedWriter output = new BufferedWriter(
                new PrintWriter(new FileWriter(newFilename)));
        
        ///////////////////////////////////////////////////////////////////////
        //
        // Algorithm to convert binary back to text
        //
        // HINT: You might need to do some bit-shifting and also use a bitwise 
        //          operator and a mask
        
        for(int i = 0; i < fileData.length; i++)
        {
            byteString += byteToString(fileData[i]);            
        }
        
        for(int i = 0; i < byteString.length(); i+=5)
        {
            try
            {
                decodedString += decoding.get(byteString.substring(i, (i + 5)));
            }
            catch(StringIndexOutOfBoundsException e)
            {
            }         
        }        
        output.write(decodedString);
        
        // Close output file handle
        output.close();
    }	

    private static void compressAndSave(
            String encodedString, FileOutputStream output) throws IOException
    {
        final int byteLen = 8;
        final int fractional = encodedString.length() % byteLen;

        if(fractional != 0)
        {
           for(int i = 0; i < (byteLen - fractional); i++)
           {
               encodedString += "0";
           }
        }
        

        for(int i = 0; i < encodedString.length(); i+=8)
        {
            byte temp = 0b00000000;
            String substring = encodedString.substring(i, (i + byteLen));
            
            for(int j = 0; j < substring.length(); j++)
            {
                if(substring.charAt(j) == '1')
                {
                    temp += 1;
                }
                if(j < (substring.length() - 1))
                {
                    temp<<=1;
                }                
            }
            output.write(temp);
        }      
    }
    
    private static String byteToString(byte num)
    {
        final int numBits = 8;
        String byteString = "";
        byte mask = 0b00000001;
        
        for(int i = 0; i < numBits; i++)
        {
            if((byte)(num & mask) == (byte)1)
            {
                byteString = "1" + byteString;
            }
            else
            {
                byteString = "0" + byteString;
            }
            num>>=1;
        }
        return byteString;
    }
}
