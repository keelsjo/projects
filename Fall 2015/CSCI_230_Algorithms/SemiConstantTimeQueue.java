//package edu.cofc.cs.csci230;
package csci230;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * A semi-constant time FIFO queue. The time complexity for 
 * the interface methods are:
 * ----------------------------------
 * 1) add: O(1) - not considering capacity resize operations
 * 2) remove: O(n) - not considering capacity resize operations
 * 3) peek: O(1)
 * 
 * Please note: the above time complexities do not factor in
 * capacity resize (grow and shrink) operations. Even though
 * capacity resize operations will occur, for this assignment 
 * you may assume the are negligible.
 * 
 * This data structure was discussed in class along with the 
 * operations, please review your notes.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2015
 *
 * @param <AnyType>
 */
public class SemiConstantTimeQueue<AnyType extends Comparable<? super AnyType>> implements Queue<AnyType> {
	
	/**
	 * private instance variables
	 */
	private ArrayList<AnyType> list = new ArrayList<>();


	/**
	 * Inserts the specified element at the end of the queue in 
	 * constant time O(1)
	 * 
	 * @param t element to add
	 * @throws NullPointerException- if the specified element is null 
	 *                               (queue does not permit null elements)
	 */
	public void add(AnyType t) throws NullPointerException {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your add solution must be a constant 
            * time O(1) operation (not considering capacity 
            * resize operations)
            * 
            */
            if (t == null)
            {
                throw new NullPointerException();
            }
            else
            {
                list.add(t);
            }
	} // end add() method

	/**
	 * Retrieves and removes the head of the queue in
	 * linear time O(n).
	 * 
	 * Hint: shift operations will make this a linear time
	 * operation.
	 * 
	 * @return the head of the queue
	 * @throws NoSuchElementException - if this queue is empty
	 */
	public AnyType remove() throws NoSuchElementException {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your push solution must be a linear 
            * time O(n) operation. See hint above.
            * 
            *
            */
            AnyType removed = null;
            if(list.isEmpty())
            {
                throw new NoSuchElementException();
            }
            else
            {
                removed = list.remove(0);
            }
		
            return removed;			
	} // end remove() method

	/**
	 * Retrieves, but does not remove, the head of the queue, 
	 * or returns null if this queue is empty.
	 * 
	 * @return the head of this queue, or null if this queue is empty
	 */
	public AnyType peek() {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your add solution must be a constant 
            * time O(1) operation 
            * 
            */
            AnyType peeked = null;
            if(!list.isEmpty())
            {
                peeked = list.get(0);
            }
		
            return peeked;
		
	} // end peek() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
            /**
            * -------------------------------------------
            * TODO: You put your test cases here
            * 
            */
            char borderChar = '*';
            int iter = 72;
            Object[] writeHolder;
            String format = getFormat(iter);
            java.util.ArrayDeque queue_oracle = new java.util.ArrayDeque();
            SemiConstantTimeQueue queue_testee = new SemiConstantTimeQueue();
            
            printLine(borderChar, iter);
            printRow(format, "", "JavaQueue", "CustomQueue");            
            printLine(borderChar, iter);
            
            //TC 1 - Verify size of queues
            writeHolder = verifySize(queue_oracle, queue_testee);
            printRow(format, "TC1-Initial Size", writeHolder[0], writeHolder[1]);
            
            //TC 2 - Remove element
            writeHolder = removeNum(queue_oracle, queue_testee);
            printRow(format, "TC2-Remove from Empty Stack", writeHolder[0], writeHolder[1]);
            
            //TC 3 - Add 10 random numbers
            writeHolder = addNum(queue_oracle, queue_testee, 10);
            printRow(format, "TC3-Add 10 Integers", writeHolder[0], writeHolder[1]);
            
            //TC 4 - Remove elements
            writeHolder = removeNum(queue_oracle, queue_testee);
            printRow(format, "TC4-Remove Element", writeHolder[0], writeHolder[1]);
            
            //TC 5 - Add 3 random numbers
            writeHolder = addNum(queue_oracle, queue_testee, 3);
            printRow(format, "TC5-Add 3 Integers", writeHolder[0], writeHolder[1]);
            
            //TC 6 - Peek into queue
            writeHolder = peekIntoQueue(queue_oracle, queue_testee);
            printRow(format, "TC6-Peek into queue", writeHolder[0], writeHolder[1]);
            
            //TC 7 - Verify size of queues
            writeHolder = verifySize(queue_oracle, queue_testee);
            printRow(format, "TC7-Verify Size", writeHolder[0], writeHolder[1]);
            
            //TC 8 - Clear queues
            writeHolder = clear(queue_oracle, queue_testee);
            printRow(format, "TC8-Clear Queues", writeHolder[0], writeHolder[1]);
            
            //TC 9 - Peek into queue
            writeHolder = peekIntoQueue(queue_oracle, queue_testee);
            printRow(format, "TC9-Peek into Empty Queue", writeHolder[0], writeHolder[1]);
            
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
        private static void printRow(String format, String rowInfo, Object javaQueueInfo, Object customQueueInfo)
        {
            System.out.println(String.format(format, rowInfo, javaQueueInfo.toString(), customQueueInfo.toString()));
        }
        
        /**
         * 
         * @param javaQueue
         * @param customQueue
         * @return Size of each List object
         */
        private static Object[] verifySize(
                java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue)
        {
            Object[] sizeArr = { javaQueue.size(), customQueue.list.size()};
            
            return sizeArr;
        }
        
        /**
         * 
         * @param javaQueue
         * @param customQueue
         * @param count
         * @param index
         * @return Whether the two lists are alike after additions
         * Adds numbers to Lists
         */
        private static Object[] addNum(java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue, int count)
        {
            Random randNum = new Random();
            Object[] results = {"false", "false"};
            
            for(int i = 0; i < count; i++)
            {
                int tempNum = randNum.nextInt();
                
                javaQueue.add(tempNum);
                customQueue.add(tempNum);                                
            }
            
            if(compareStack(javaQueue, customQueue))
            {
                results[0] = "lists match";
                results[1] = "lists match";
            }
            else
            {
                results[0] = "list mismatch";
                results[1] = "list mismatch";
            }
            return results;
        }
        
        /**
         * 
         * @param javaQueue
         * @param customQueue
         * @return true/false on whether two lists are alike in length and data
         */
        private static Boolean compareStack(java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue)
        {
            Boolean areAlike = false;
            int size_customQueue = customQueue.list.size();
            Object[] queueArray = javaQueue.toArray(new Object[0]);
            int size_javaQueue = queueArray.length;
            
            if(size_javaQueue == size_customQueue)
            {
                for (int i = 0; i < size_customQueue; i++)
                {
                    if((int)customQueue.list.get(i) != (int)queueArray[i])
                    {
                        break;
                    }
                    if(i == (size_customQueue - 1))
                    {
                        areAlike = true;
                    }
                }
            }            
            return areAlike;
        }
        
        /**
         * 
         * @param javaQueue
         * @param customQueue
         * @param index
         * @return info on objects removed from list
         */
        private static Object[] removeNum(java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue)
        {
            Object[] results = {"false", "false"};            
            Object removed_javaQueue = null;
            Object removed_customQueue = null;        
                
            try
            {
                removed_javaQueue = javaQueue.remove();
            }
            catch(NoSuchElementException e)
            {
                results[0] = "NoSuchElementEx.";
            }

            try
            {                
                removed_customQueue = customQueue.remove(); 
            }
            catch(NoSuchElementException e)
            {
                results[1] = "NoSuchElementEx.";
            }
                    
            if(!results[0].equals("NoSuchElementEx.") && !results[1].equals("NoSuchElementEx."))
            {
                if((int)removed_javaQueue != (int)removed_customQueue)
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareStack(javaQueue, customQueue))
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
         * @param javaQueue
         * @param customQueue
         * @param index
         * @return info on nodes retrieved from lists
         */
        private static Object[] peekIntoQueue(java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue)
        {
            Object[] results = {"false", "false"};           
            Object retrieved_javaQueue = javaQueue.peek();
            Object retrieved_customQueue = customQueue.peek(); 
            
            if (retrieved_javaQueue == null)
            {
                results[0] = "Empty Queue";
            }

            if (retrieved_customQueue == null)
            {
                results[1] = "Empty Queue";
            }
                    
            if(!results[0].equals("Empty Queue") && !results[1].equals("Empty Queue"))
            {
                if((int)retrieved_javaQueue != (int)retrieved_customQueue)
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareStack(javaQueue, customQueue))
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
        
        private static Object[] clear(java.util.ArrayDeque javaQueue, SemiConstantTimeQueue customQueue)
        {
            javaQueue.clear();
            customQueue.list.clear();
            
            return new Object[] {javaQueue.isEmpty(), customQueue.list.isEmpty()};
        }

} // end ConstantTimeQueue class definition
