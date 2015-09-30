//package edu.cofc.cs.csci230;
package csci230;

import java.util.EmptyStackException;
import java.util.Random;

/**
 * A LIFO stack that has constant time complexity O(1) for
 * all three stack interface methods (i.e., push, pop, and 
 * peek).
 * 
 * This data structure was discussed in class along with the 
 * operations, please review your notes.
 * 
 * @author CSCI 230: Data Structures and Algorithms Fall 2015
 *
 * @param <AnyType>
 */
public class ConstantTimeStack<AnyType extends Comparable<? super AnyType>> implements Stack<Node<AnyType>>{
	
	/**
	 * private instance variables
	 */
	private SinglyLinkedList<AnyType> list = new SinglyLinkedList<>();

	/**
	 * Pushes an item onto the top of this stack in constant 
	 * time O(1)
	 * 
	 * @param t the item to be pushed onto this stack.
	 */
	public void push(Node<AnyType> t) {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your push solution must be a constant 
            * time O(1) operation
            * 
            */
            list.add(0, t);		
	} // end push() method

	/**
	 * Removes the object at the top of this stack and return the 
	 * item in constant time O(1)
	 * .
	 * @return The item at the top of this stack
	 * @throws EmptyStackException - if this stack is empty.
	 */
	public Node<AnyType> pop() throws EmptyStackException {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your pop solution must be a constant 
            * time O(1) operation
            * 
            */
            Node<AnyType> popped = null;
            
            if (list.isEmpty())
            {
                throw new EmptyStackException();
            }
            else
            {
                popped = list.remove(0);
            }
	
            return popped;	
	} // end pop() method

	/**
	 * Looks at the item at the top of this stack without removing it 
	 * from the stack in constant time O(1)
	 * 
	 * @return the item at the top of this stack
	 * @throws EmptyStackException  - if this stack is empty.
	 */
	public Node<AnyType> peek() throws EmptyStackException {
            /**
            * -------------------------------------------
            * TODO: You fully implement this method
            * 
            * Note: Your peek solution must be a constant 
            * time O(1) operation
            * 
            */
            Node<AnyType> peeked = null;
            
            if (list.isEmpty())
            {
                throw new EmptyStackException();
            }
            else
            {
                peeked = list.get(0);
            }
	
            return peeked;	
	} // end peek() method
	
	/**
	 * 
	 * @param args
	 */
	public static void main( String[] args ) {            
            /**
            * -------------------------------------------
            * TODO: You put your test cases here
            * 
            */
            char borderChar = '*';
            int iter = 72;
            Object[] writeHolder;
            String format = getFormat(iter);
            java.util.Stack stack_oracle = new java.util.Stack();
            ConstantTimeStack stack_testee = new ConstantTimeStack();
            
            printLine(borderChar, iter);
            printRow(format, "", "JavaStack", "CustomStack");            
            printLine(borderChar, iter);
            
            //TC 1 - Verify size of stacks
            writeHolder = verifySize(stack_oracle, stack_testee);
            printRow(format, "TC1-Initial Size", writeHolder[0], writeHolder[1]);
            
            //TC 2 - Pop element
            writeHolder = popNum(stack_oracle, stack_testee);
            printRow(format, "TC2-Pop from Empty Stack", writeHolder[0], writeHolder[1]);
            
            //TC 3 - Push 10 random numbers
            writeHolder = pushNum(stack_oracle, stack_testee, 10);
            printRow(format, "TC3-Push 10 Integers", writeHolder[0], writeHolder[1]);
            
            //TC 4 - Pop element
            writeHolder = popNum(stack_oracle, stack_testee);
            printRow(format, "TC4-Pop Element", writeHolder[0], writeHolder[1]);
            
            //TC 5 - Push 3 random numbers
            writeHolder = pushNum(stack_oracle, stack_testee, 3);
            printRow(format, "TC5-Push 3 Integers", writeHolder[0], writeHolder[1]);
            
            //TC 6 - Peek into stack
            writeHolder = peekIntoStack(stack_oracle, stack_testee);
            printRow(format, "TC6-Peek into stack", writeHolder[0], writeHolder[1]);
            
            //TC 7 - Verify size of stacks
            writeHolder = verifySize(stack_oracle, stack_testee);
            printRow(format, "TC7-Verify Size", writeHolder[0], writeHolder[1]);
            
            //TC 8 - Clear stacks
            writeHolder = clear(stack_oracle, stack_testee);
            printRow(format, "TC8-Clear Stacks", writeHolder[0], writeHolder[1]);
            
            //TC 9 - Peek into stack
            writeHolder = peekIntoStack(stack_oracle, stack_testee);
            printRow(format, "TC9-Peek into Empty Stack", writeHolder[0], writeHolder[1]);
            
            printLine(borderChar, iter);		
	} // end main method
        
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
         * @param javaStackInfo
         * @param customStackInfo 
         * Prints a row containing results of test case
         */
        private static void printRow(String format, String rowInfo, Object javaStackInfo, Object customStackInfo)
        {
            System.out.println(String.format(format, rowInfo, javaStackInfo.toString(), customStackInfo.toString()));
        }
        
        /**
         * 
         * @param javaStack
         * @param customStack
         * @return Size of each List object
         */
        private static Object[] verifySize(
                java.util.Stack javaStack, ConstantTimeStack customStack)
        {
            Object[] sizeArr = { javaStack.size(), customStack.list.size()};
            
            return sizeArr;
        }
        
        /**
         * 
         * @param javaStack
         * @param customStack
         * @param count
         * @param index
         * @return Whether the two lists are alike after additions
         * Adds numbers to Lists
         */
        private static Object[] pushNum(java.util.Stack javaStack, ConstantTimeStack customStack, int count)
        {
            Random randNum = new Random();
            Object[] results = {"false", "false"};
            
            for(int i = 0; i < count; i++)
            {
                int tempNum = randNum.nextInt();
                
                javaStack.push(tempNum);
                customStack.push(new Node(tempNum));                                
            }
            
            if(compareStack(javaStack, customStack))
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
         * @param javaStack
         * @param customStack
         * @return true/false on whether two lists are alike in length and data
         */
        private static Boolean compareStack(java.util.Stack javaStack, ConstantTimeStack customStack)
        {
            Boolean areAlike = false;
            int size_customStack = customStack.list.size();
            int size_javaStack = javaStack.size();
            
            if(size_javaStack == size_customStack)
            {
                for (int i = 0; i < size_customStack; i++)
                {
                    if((int)customStack.list.get(i).getData() != (int)javaStack.get(size_javaStack - 1 - i))
                    {
                        break;
                    }
                    if(i == (size_customStack - 1))
                    {
                        areAlike = true;
                    }
                }
            }            
            return areAlike;
        }
        
        /**
         * 
         * @param javaStack
         * @param customStack
         * @param index
         * @return info on objects removed from list
         */
        private static Object[] popNum(java.util.Stack javaStack, ConstantTimeStack customStack)
        {
            Object[] results = {"false", "false"};            
            Object removed_javaStack = null;
            Node removed_customStack = null;        
                
            try
            {
                removed_javaStack = javaStack.pop();
            }
            catch(EmptyStackException e)
            {
                results[0] = "EmptyStackException";
            }

            try
            {                
                removed_customStack = customStack.pop(); 
            }
            catch(EmptyStackException e)
            {
                results[1] = "EmptyStackException";
            }
                    
            if(!results[0].equals("EmptyStackException") && !results[1].equals("EmptyStackException"))
            {
                if((int)removed_javaStack != (int)removed_customStack.getData())
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareStack(javaStack, customStack))
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
         * @param javaStack
         * @param customStack
         * @param index
         * @return info on nodes retrieved from lists
         */
        private static Object[] peekIntoStack(java.util.Stack javaStack, ConstantTimeStack customStack)
        {
            Object[] results = {"false", "false"};            
            Object retrieved_javaStack = null;
            Node retrieved_customStack = null;        
                
            try
            {
                retrieved_javaStack = javaStack.peek();
            }
            catch(EmptyStackException e)
            {
                results[0] = "EmptyStackException";
            }

            try
            {                
                retrieved_customStack = customStack.peek(); 
            }
            catch(EmptyStackException e)
            {
                results[1] = "EmptyStackException";
            }
                    
            if(!results[0].equals("EmptyStackException") && !results[1].equals("EmptyStackException"))
            {
                if((int)retrieved_javaStack != (int)retrieved_customStack.getData())
                {
                    results[0] = "data mismatch";
                    results[1] = "data mismatch";
                }
                else if(compareStack(javaStack, customStack))
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
        
        private static Object[] clear(java.util.Stack javaStack, ConstantTimeStack customStack)
        {
            javaStack.clear();
            customStack.list.clear();
            
            return new Object[] {javaStack.empty(), customStack.list.isEmpty()};
        }
} // end ConstantTimeStack class definition
