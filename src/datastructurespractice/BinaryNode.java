/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

/**
 *
 * @author muhsin
 */
public class BinaryNode<V> extends Node<V> {
    
     // Node with two chidlren
    private V value;
    private BinaryNode left;
    private BinaryNode right;
    
    public BinaryNode(V value, BinaryNode left, BinaryNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    public BinaryNode(V value, BinaryNode left){
        this.value = value;
        this.left = left;
        this.right = null;
    }
    
    public BinaryNode(V value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
    
    @Override
    public V value(){
        return value;
    }
    
    public BinaryNode<V> left(){
        return left;
    }
    
    public void setLeft(V value){
        this.left = new BinaryNode<>(value);
    }
    public void setLeft(BinaryNode<V> node){
        this.left = node;
    }
    
    public BinaryNode<V> right(){
        return right;
    }
    
    public void setRight(V value){
        this.right = new BinaryNode<>(value);
    }
    
    public void setRight(BinaryNode<V> node){
        this.right = node;
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
}
