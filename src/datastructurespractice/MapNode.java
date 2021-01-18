/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

import java.util.ArrayList;

/**
 *
 * @author muhsin
 */
public class MapNode<V> extends Node<V> {
    // Node with a variable children amount
    private V value;
    private ArrayList<MapNode<V>> children;
    
    public MapNode(V value){
        this.value = value;
        children = new ArrayList<>();
    }
    
    public V value(){
        return value;
    }
    
    public int add(V value){
        children.add(new MapNode<>(value));
        return childrenCount();
    }
    public int add(MapNode node){
        children.add(node);
        return childrenCount();
    }
    
    public MapNode<V> getChild(int index){
        return children.get(index);
    }
    
    public int childrenCount(){
        return children.size();
    }
    
    //TODO: Update child and remove child
}
