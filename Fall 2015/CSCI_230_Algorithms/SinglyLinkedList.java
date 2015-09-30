//package edu.cofc.cs.csci230;
package csci230;

import java.util.ArrayList;
import java.util.Random;


/**
 * Singly LinkedList Data Structure
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2015
 *
 * @param <AnyType>
 */
public class SinglyLinkedList<AnyType extends Comparable<? super AnyType>> implements List<Node<AnyType>> {
	
	// instance variables
	private Node<AnyType> headNode = null;
	private int size = 0;

	/**
	 * Appends the specified element to the end of this list.
	 * 
	 * @param t
	 */
	public void add(Node<AnyType> t) {
		
		if ( isEmpty() ) 
                {
                    headNode = t;
                } 
                else
                {
                    get( size-1 ).setNextNode( t );
                }		
		size++;
		
	} // end add() method

	/**
	 * Inserts the specified element at the specified position in this list.
	 * 
	 * @param index
	 * @param t
	 * @throws IndexOutOfBoundsException
	 */
	public void add(int index, Node<AnyType> t) throws IndexOutOfBoundsException 
        {
            if(index > size || index < 0)
            {
                throw new IndexOutOfBoundsException();
            }
            else if(index == size)
            {
                add(t);
            }
            else if (index == 0)
            {
                t.setNextNode(headNode);
                headNode = t;
                size++;
            }
            else
            {
                Node<AnyType> tempPrev = get(index-1);
                t.setNextNode(tempPrev.getNextNode());
                tempPrev.setNextNode(t);  
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
	public void set(int index, Node<AnyType> t) throws IndexOutOfBoundsException 
        {		
            if(index >= size || index < 0)
            {
                throw new IndexOutOfBoundsException();
            }
            else if (index == 0)
            {
                headNode.setData(t.getData());
            }
            else
            {
                get(index).setData(t.getData());
            }         		
	} // end set() method

	/**
	 * Removes the element at the specified position in this list.
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Node<AnyType> remove( int index ) throws IndexOutOfBoundsException 
        {
            Node<AnyType> removed = null;
            
            if(index >= size || index < 0 || size == 0)
            {
                throw new IndexOutOfBoundsException();
            }
            else if (index == 0)
            {
                removed = headNode;
                
                headNode = headNode.getNextNode();
                removed.setNextNode(null);
                size--;
            }
            else
            {
                Node<AnyType> tempPrev = get(index-1);
                removed = tempPrev.getNextNode();
                tempPrev.setNextNode(removed.getNextNode());
                removed.setNextNode(null);
                size--;
            }
            return removed;
	} // end remove() method

	/**
	 * Returns the element at the specified position in this list.
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Node<AnyType> get(int index) throws IndexOutOfBoundsException 
        {
            Node<AnyType> gotten = headNode;
            
            if(index >= size || index < 0 || size == 0)
            {
                throw new IndexOutOfBoundsException();
            }
            else
            {
                for (int i = 0; i < index; i++)
                {
                    gotten = gotten.getNextNode();
                }
            }
            return gotten;
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
		
		return size == 0;
		
	} // end isEmpty() method
	
	
	/**
	 * Removes all of the elements from this list.
	 * 
	 */
	public void clear() {
		headNode = null;
                size = 0;
	} // end clear method
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) 
        {
            char borderChar = '*';
            int iter = 72;
            Object[] writeHolder;
            String format = getFormat(iter);
            ArrayList list_oracle = new ArrayList();
            SinglyLinkedList list_testee = new SinglyLinkedList();
            
            printLine(borderChar, iter);
            printRow(format, "", "ArrayList", "SinglyLinkedList");            
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
            writeHolder = getNode(list_oracle, list_testee, 3);
            printRow(format, "TC12-Get Node at Index 3", writeHolder[0], writeHolder[1]);
            
            //TC 13 - Get node at index -1
            writeHolder = getNode(list_oracle, list_testee, -1);
            printRow(format, "TC13-Get Node at Index -1", writeHolder[0], writeHolder[1]);
            
            //TC 14 - Get node at index = size
            writeHolder = getNode(list_oracle, list_testee, list_oracle.size());
            printRow(format, "TC14-Get Node at Index " + list_oracle.size(), writeHolder[0], writeHolder[1]);
            
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
        private static void printRow(String format, String rowInfo, Object arrayListInfo, Object singlyLinkedListInfo)
        {
            System.out.println(String.format(format, rowInfo, arrayListInfo.toString(), singlyLinkedListInfo.toString()));
        }
        
        /**
         * 
         * @param javaList
         * @param customList
         * @return Size of each List object
         */
        private static Object[] verifySize(
                ArrayList javaList, SinglyLinkedList customList)
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
        private static Object[] addNum(ArrayList javaList, SinglyLinkedList customList, int count, int index)
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
                    customList.add(new Node(tempNum));
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
                        customList.add(addIndex, new Node(tempNum));
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
        private static Boolean compareArrays(ArrayList javaList, SinglyLinkedList customList)
        {
            Boolean areAlike = false;
            
            if(javaList.size() == customList.size())
            {
                for (int i = 0; i < javaList.size(); i++)
                {
                    if((int)javaList.get(i) != (int)customList.get(i).getData())
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
        private static Object[] removeNum(ArrayList javaList, SinglyLinkedList customList, int index)
        {
            Object[] results = {"false", "false"};            
            Object removed_javaList = null;
            Node removed_customList = null;        
                
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
                if((int)removed_javaList != (int)removed_customList.getData())
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
        private static Object[] getNode(ArrayList javaList, SinglyLinkedList customList, int index)
        {
            Object[] results = {"false", "false"};            
            Object retrieved_javaList = null;
            Node retrieved_customList = null;        
                
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
                if((int)retrieved_javaList != (int)retrieved_customList.getData())
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
        private static Object[] clearLists(ArrayList javaList, SinglyLinkedList customList)
        {
            Object[] results = new Object[2];        
                
            javaList.clear();
            customList.clear();
            
            results[0] = javaList.isEmpty() ? "Empty" : "Not Empty";
            results[1] = customList.isEmpty() ? "Empty": "Not Empty";
            
            return results;
        }
} // end SinglyLinkedList class definition