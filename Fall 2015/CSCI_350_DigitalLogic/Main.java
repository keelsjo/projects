package csci350;

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
 *                     32 characters in total to be represented with 5 bits	
 *  
 * We should be able to compress our text files by about 5/8 ~= 60%
 *  
 * This will be a "lossy" compression meaning we will loose data. Any 
 * character in the input text file that is not in the above list simply gets
 * converted to a single blank space.		
 */

public class Main {

    public static void main(String[] args) throws Exception
    {
        String relFilePath_orig = "src/csci350/input1.txt";
        String relFilePath_compressed = relFilePath_orig + ".compressed";
        
        // TODO Auto-generated method stub
        Compressor.compress(relFilePath_orig);
        Compressor.decompress(relFilePath_compressed);		
    }
}