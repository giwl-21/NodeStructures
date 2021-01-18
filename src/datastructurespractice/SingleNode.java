/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

/**
 *
 * @author muhsin
 * @param <V>
 */
public class SingleNode<V> extends Node {
    // Either one child or two children
    private V value;
    private SingleNode next;
    
    public SingleNode(V value, SingleNode next){
        this.value = value;
        this.next = next;
    }
    
    public SingleNode(V value){
        this.value = value;
        this.next = null;
    }
    
    @Override
    public V value(){
        return value;
    }
    
    public SingleNode next(){
        return next;
    }
    
    public void setNext(SingleNode node){
        this.next = node;
    }
    public void setNext(V value){
        this.next = new SingleNode<>(value);
    }
}
