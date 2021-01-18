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
public class Queue<T> {
    //First in First out
    private SingleNode<T> head;// Front of queue
    private SingleNode<T> tail;// End of queue
    private int size;
    
    public Queue(){
       head = null;
       tail = null;
       size = 0;
    }
    
    public void enqueue(T value){
       if (tail != null){
            tail.setNext(value);
            tail = tail.next();
       }
       else {
           tail = new SingleNode<>(value);
           head = tail;
       }
       size++;
    }
    
    public T peek(){
        if (head == null)
            return null;
        else
            return head.value();
    }
    
    public T remove(){
        T value = peek();
        if (value != null){
            head = head.next();
            size--;
            if (head == null){
                tail = null;
            }
        }
        return value;
    }
    
    public boolean isEmpty(){
        if (head == null) {
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
    
    public int size(){
        return size;
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
