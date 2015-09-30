//package edu.cofc.cs.csci230;
package csci230;

import java.util.Random;

/**
 * ArrayList Data Structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2015
 *
 * @param <AnyType>
 */
public class ArrayList<AnyType extends Comparable<? super AnyType>> implements List<AnyType> {
     
    // instance variables
    private Object[] array;
    private int size = 0;
    private final static int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    
    /**
     * Constructs an empty list with an initial capacity
     * (for this assignment initial capacity is 10 - see 
     * constant instance variable)
     * 
     */
    public ArrayList() {
    	
    	array = new Object[ INITIAL_CAPACITY ];
    	
    } // end constructor
 
    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t
     */
    public void add( AnyType t) {
         
    	if ( size >= capacity )
        {
            grow();
        }
    	array[size]=t;
        size++;
         
    } // end add() method
 
    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, AnyType t) throws IndexOutOfBoundsException {
         
        if(index > size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            if ( size >= capacity )
            {
                grow();
            }

            for(int i = size; i > index; i--)
            {
                array[i] = array[i - 1];
            }
            array[index] = t;
            size++;   	
        }        
    } // end add() method
 
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index
     * @param t
     * @throws IndexOutOfBoundsException
     */
    public void set(int index, AnyType t) throws IndexOutOfBoundsException {
         
        if(index > (size - 1))
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            array[index] = t;
        }               
    } // end set() method
 
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    
    public AnyType remove( int index ) throws IndexOutOfBoundsException {

        if(index > (size - 1) || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            AnyType found = (AnyType)array[index];
            for(int i = index; i < (size - 1); i++)
            {
                array[i] = array[i + 1];
            }
            size--;

            if ((size * 2) + 1 <= capacity)
            {
                shrink();
            }        
            return found;    
        }           	
    }// end remove() method
 
    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index
     * @return
     * @throws IndexOutOfBoundsException
     */
    public AnyType get(int index) throws IndexOutOfBoundsException {

        if(index > (size - 1) || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        else
        {
            return (AnyType)array[index];    
        }
    } // end get() method
 
    /**
     * Returns the number of elements in this list. 
     * 
     * @return
     */
    public int size() {
         
        return size;
         
    } // end size() method
 
    /**
     * Returns true if this list contains no elements.
     * 
     * @return
     */
    public Boolean isEmpty() {
         
        return ( size == 0 );
         
    } // end isEmpty() method
     
     
    /**
     * Removes all of the elements from this list.
     * and the initial capacity is set back to 10
     * 
     */
    public void clear() {         
       this.array = new Object[ INITIAL_CAPACITY ];
       this.size = 0;
       this.capacity = INITIAL_CAPACITY;
    } // end clear method
    
    /**
     * Double the capacity of the array
     * 
     */
    private void grow() {
    	
    	Object[] newArray = new Object[size * 2];
        
        for(int i = 0; i < size; i++)
        {
            newArray[i] = array[i];
        } 
        this.array = newArray;
        this.capacity = newArray.length;
    } // end grow() method
    
    
    /**
     * Half the capacity of the array
     * 
     */
    private void shrink() {
    	Object[] newArray = new Object[(int)Math.ceil(size / 2)];
        
        for(int i = 0; i < newArray.length; i++)
        {
            newArray[i] = array[i];
        } 
        this.array = newArray;
        this.capacity = newArray.length;
    } // end shrink() method
     
     
    /**
     * 
     * @param args
     */
    public static void main( String[] args ) {
         
        char borderChar = '*';
        int iter = 72;
        Object[] writeHolder;
        String format = getFormat(iter);
        java.util.ArrayList list_oracle = new java.util.ArrayList();
        ArrayList list_testee = new ArrayList();

        printLine(borderChar, iter);
        printRow(format, "", "Java ArrayList", "Custom ArrayList");            
        printLine(borderChar, iter);

        //TC 1 - Verify size of lists
        writeHolder = verifySize(list_oracle, list_testee);
        printRow(format, "TC1-Initial Size", writeHolder[0], writeHolder[1]);

        //TC 2 - Add 10 random numbers
        writeHolder = addNum(list_oracle, list_testee, 10, -2);
        printRow(format, "TC2-Add 10 Integers", writeHolder[0], writeHolder[1]);

        //TC 3 - Add random number to at index 5
        writeHolder = addNum(list_oracle, list_testee, 1, 5);
        printRow(format, "TC3-Add at Index 5", writeHolder[0], writeHolder[1]);

        //TC 4 - Add random number to at index 0
        writeHolder = addNum(list_oracle, list_testee, 1, 0);
        printRow(format, "TC4-Add at Index 0", writeHolder[0], writeHolder[1]);

        //TC 5 - Add random number to at index -1
        writeHolder = addNum(list_oracle, list_testee, 1, -1);
        printRow(format, "TC5-Add at Index -1", writeHolder[0], writeHolder[1]);

        //TC 6 - Add random number to at index = (size + 1)
        writeHolder = addNum(list_oracle, list_testee, 1, list_oracle.size() + 1);
        printRow(format, "TC6-Add at Index " + (list_oracle.size() + 1), writeHolder[0], writeHolder[1]);

        //TC 7 - Verify size of list
        writeHolder = verifySize(list_oracle, list_testee);
        printRow(format, "TC7-Size After Adding", writeHolder[0], writeHolder[1]);

        //TC 8 - Remove number from index 5
        writeHolder = removeNum(list_oracle, list_testee, 5);
        printRow(format, "TC8-Remove at Index 5", writeHolder[0], writeHolder[1]);

        //TC 9 - Remove number from index 0
        writeHolder = removeNum(list_oracle, list_testee, 0);
        printRow(format, "TC9-Remove at Index 0", writeHolder[0], writeHolder[1]);

        //TC 10 - Remove number from index -1
        writeHolder = removeNum(list_oracle, list_testee, -1);
        printRow(format, "TC10-Remove at Index -1", writeHolder[0], writeHolder[1]);

        //TC 11 - Remove number from index = size
        writeHolder = removeNum(list_oracle, list_testee, list_oracle.size());
        printRow(format, "TC11-Remove at Index " + list_oracle.size(), writeHolder[0], writeHolder[1]);

        //TC 12 - Get node at index 3
        writeHolder = getValue(list_oracle, list_testee, 3);
        printRow(format, "TC12-Get Value at Index 3", writeHolder[0], writeHolder[1]);

        //TC 13 - Get node at index -1
        writeHolder = getValue(list_oracle, list_testee, -1);
        printRow(format, "TC13-Get Value at Index -1", writeHolder[0], writeHolder[1]);

        //TC 14 - Get node at index = size
        writeHolder = getValue(list_oracle, list_testee, list_oracle.size());
        printRow(format, "TC14-Get Value at Index " + list_oracle.size(), writeHolder[0], writeHolder[1]);

        //TC 15 - Verify size of list
        writeHolder = verifySize(list_oracle, list_testee);
        printRow(format, "TC15-Size After Remove", writeHolder[0], writeHolder[1]);

        //TC 16 - Clear List
        writeHolder = clearLists(list_oracle, list_testee);
        printRow(format, "TC16-After Clear", writeHolder[0], writeHolder[1]);

        //TC 17 - Verify size of list
        writeHolder = verifySize(list_oracle, list_testee);
        printRow(format, "TC17-Size After Clear", writeHolder[0], writeHolder[1]);

        printLine(borderChar, iter);
                
         
    } // end main() method
    
    /*Helper Methods for Display*/
        /**
         * 
         * @param count
         * @return string used to format display results of test cases
         */
        private static String getFormat(int count)
        {
            int columnSize = count / 3;
            
            return "*%-" + (columnSize + 3) + "s|%-" + (columnSize - 5) + "s|%-" + (columnSize - 2) + "s*";
        }
        
        /**
         * 
         * @param border
         * @param count 
         * Prints top and bottom border 
         */
        private static void printLine(char border, int count)
        {
            for(int i = 0; i < count; i++)
            {
                System.out.print(border);
            }
            System.out.println();
        }
        
        /**
         * 
         * @param format
         * @param rowInfo
         * @param arrayListInfo
         * @param singlyLinkedListInfo 
         * Prints a row containing results of test case
         */
        private static void printRow(String format, String rowInfo, Object javaArrayListInfo, Object customArrayListInfo)
        {
            System.out.println(String.format(format, rowInfo, javaArrayListInfo.toString(), customArrayListInfo.toString()));
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @return Size of each List object
         */
        private static Object[] verifySize(
                java.util.ArrayList javaList, ArrayList customList)
        {
            Object[] sizeArr = { javaList.size(), customList.size()};
            
            return sizeArr;
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @param count
         * @param index
         * @return Whether the two lists are alike after additions
         * Adds numbers to Lists
         */
        private static Object[] addNum(java.util.ArrayList javaList, ArrayList customList, int count, int index)
        {
            Random randNum = new Random();
            int addIndex = index != -2 ? index : javaList.size();
            Object[] results = {"false", "false"};
            
            for(int i = addIndex; i < (addIndex + count); i++)
            {
                int tempNum = randNum.nextInt();
                
                if(index == -2)
                {
                    javaList.add(tempNum);
                    customList.add(tempNum);
                }
                else
                {
                    try
                    {
                        javaList.add(addIndex, tempNum);
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        results[0] = "OoB Exception";
                    }
                    
                    try
                    {
                        customList.add(addIndex, tempNum);
                    }
                    catch(IndexOutOfBoundsException e)
                    {
                        results[1] = "OoB Exception";
                    }
                    
                    if(results[0].equals("OoB Exception") || results[1].equals("OoB Exception"))
                    {
                        break;
                    }
                }                
            }
            if(!results[0].equals("OoB Exception") && !results[1].equals("OoB Exception"))
            {
                if(compareArrays(javaList, customList))
                {
                    results[0] = "lists match";
                    results[1] = "lists match";
                }
                else
                {
                    results[0] = "list mismatch";
                    results[1] = "list mismatch";
                }
            }
            return results;
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @return true/false on whether two lists are alike in length and data
         */
        private static Boolean compareArrays(java.util.ArrayList javaList, ArrayList customList)
        {
            Boolean areAlike = false;
            
            if(javaList.size() == customList.size())
            {
                for (int i = 0; i < javaList.size(); i++)
                {
                    if((int)javaList.get(i) != (int)customList.get(i))
                    {
                        break;
                    }
                    if(i == (javaList.size() - 1))
                    {
                        areAlike = true;
                    }
                }
            }            
            return areAlike;
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @param index
         * @return info on objects removed from list
         */
        private static Object[] removeNum(java.util.ArrayList javaList, ArrayList customList, int index)
        {
            Object[] results = {"false", "false"};            
            Object removed_javaList = null;
            Object removed_customList = null;        
                
            try
            {
                removed_javaList = javaList.remove(index);
            }
            catch(IndexOutOfBoundsException e)
            {
                results[0] = "OoB Exception";
            }

            try
            {                
                removed_customList = customList.remove(index); 
            }
            catch(IndexOutOfBoundsException e)
            {
                results[1] = "OoB Exception";
            }
                    
            if(!results[0].equals("OoB Exception") && !results[1].equals("OoB Exception"))
            {
                if((int)removed_javaList != (int)removed_customList)
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareArrays(javaList, customList))
                {
                    results[0] = "lists match";
                    results[1] = "lists match";
                }
                else
                {
                    results[0] = "list mismatch";
                    results[1] = "list mismatch";
                }
            }
            return results;
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @param index
         * @return info on nodes retrieved from lists
         */
        private static Object[] getValue(java.util.ArrayList javaList, ArrayList customList, int index)
        {
            Object[] results = {"false", "false"};            
            Object retrieved_javaList = null;
            Object retrieved_customList = null;        
                
            try
            {
                retrieved_javaList = javaList.get(index);
            }
            catch(IndexOutOfBoundsException e)
            {
                results[0] = "OoB Exception";
            }

            try
            {                
                retrieved_customList = customList.get(index); 
            }
            catch(IndexOutOfBoundsException e)
            {
                results[1] = "OoB Exception";
            }
                    
            if(!results[0].equals("OoB Exception") && !results[1].equals("OoB Exception"))
            {
                if((int)retrieved_javaList != (int)retrieved_customList)
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareArrays(javaList, customList))
                {
                    results[0] = "lists match";
                    results[1] = "lists match";
                }
                else
                {
                    results[0] = "list mismatch";
                    results[1] = "list mismatch";
                }
            }
            return results;
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @return info on lists after clearing
         */
        private static Object[] clearLists(java.util.ArrayList javaList, ArrayList customList)
        {
            Object[] results = new Object[2];        
                
            javaList.clear();
            customList.clear();
            
            results[0] = javaList.isEmpty() ? "Empty" : "Not Empty";
            results[1] = customList.isEmpty() ? "Empty": "Not Empty";
            
            return results;
        }
  
} // end ArrayList class definition
