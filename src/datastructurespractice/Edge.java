/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

/**
 * Weighted one-way connection pointing to a node.
 * @author muhsin
 * @param <T> notates node type
 */
public class Edge<T> {
    private static final int DEFAULT_WEIGHT = 1;
    private int weight;
    private T targetNode;
    
    /**
     * Constructor. Weight is defaulted to one.
     * @param targetNode
     */
    public Edge(T targetNode){
        this(targetNode, DEFAULT_WEIGHT);
    }
    
    /**
     * Constructor.
     * @param targetNode
     * @param weight 
     */
    public Edge(T targetNode, int weight){
        this.targetNode = targetNode;
        this.weight = weight;
    }
    
    public T target(){
        return targetNode;
    }
    
    public int weight(){
        return weight;
    }
    
    @Override
    public String toString(){
        return "Target value: " + targetNode + ", weight: " + weight;
    }
}
