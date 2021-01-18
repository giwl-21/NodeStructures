/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

import java.util.ArrayList;

/**
 * This is a node with any number of children through a list of edges.
 * @author muhsin
 * @param <V>
 */
public class WeightedMapNode<V> extends Node<V> {
    private V value;
    private ArrayList<Edge<WeightedMapNode<V>>> connections;
    
    public WeightedMapNode(V value){
        this.value = value;
        connections = new ArrayList<>();
    }
    
    public int childrenCount() {
        return connections.size();
    }
    
    @Override
    public V value(){
        return value;
    }
    
    public Edge<WeightedMapNode<V>> getConnection(int index){
        return connections.get(index);
    }
    
    public WeightedMapNode<V> getChild(int index){
        return connections.get(index).target();
    }
    
    /**
     * Adds a child connection to this node
     * @param edge: Connection to a weighted map node
     * @return Amount of children of this node
     */
    public int addChild(Edge<WeightedMapNode<V>> edge){
        connections.add(edge);
        return childrenCount();
    }
    
    /**
     * Adds a child connection to this node
     * @param node The connected node
     * @param weight The connection's weight
     * @return Amount of children of this node
     */
    public int addChild(WeightedMapNode<V> node, int weight){
        connections.add(new Edge<>(node, weight));
        return childrenCount();
    }
    /**
     * Adds a child connection to this node, defaults weight to 1
     * @param node The connected node
     * @return Amount of children of this node
     */
    public int addChild(WeightedMapNode<V> node){
        connections.add(new Edge<>(node));
        return childrenCount();
    }
    
    @Override
    public String toString(){
        return value.toString();
    }
}