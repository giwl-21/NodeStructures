/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

/**
 *
 * @author muhsin
 * @param <T>
 */
public class Stack<T> {
    private SingleNode<T> head;
    private int size;
    
    public Stack(){
       head = null;
       size = 0;
    }
    
    public void push(T value){
       head = new SingleNode<T>(value, head);
       size++;
    }
    
    public T peek(){
        if (head == null)
            return null;
        else
            return head.value();
    }
    
    public T pop(){
        T value = peek();
        if (head != null){
            head = head.next();
            size--;
        }
        return value;
    }
    
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public int search(T value){
        // returns the 1-based index of where the value's first occurance is at
        int index = 0;
        SingleNode currentNode = head;
        
        while(currentNode.value() != null){
            index++;
            if (currentNode.value().equals(value) || currentNode.value() == value){
                return index;
            }
            currentNode = currentNode.next();
        }
        return -1;
    }
    
    @Override
    public String toString(){
        String repr = "[";
        SingleNode node = head;
        for (int i = 0; i < size; i++){
            if (i != 0){
                repr += ", ";
            }
            repr += node.value().toString();
            node = node.next(); 
        }
        repr += "]";
        return repr;
    }
}
