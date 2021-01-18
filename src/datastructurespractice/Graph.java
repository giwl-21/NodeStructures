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
 * @param <T> Node type
 * @param <V> value type
 */
public abstract class Graph<T, V> {
    // Each node on this graph has n number of children
    /**
     * 
     * @return Array-list of all the registered nodes
     */
    public abstract ArrayList<T> getNodes();
    public abstract ArrayList<V> pathToNode(T startNode, T endNode);
    public abstract ArrayList<V> dft();
    public abstract ArrayList<V> bft();
}
