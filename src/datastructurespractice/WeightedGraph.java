/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A graph with nodes that are connected through one-way edges.
 * All values must be unique!!
 * @author muhsin
 * @param <V> value type
 */
public class WeightedGraph<V> extends Graph<WeightedMapNode<V>, V>{
    private final HashMap<V, WeightedMapNode<V>> registeredNodes;// Dict with values as both node and index
    private int size;
    private WeightedMapNode<V> root;
    
    /**
     * Constructor for the weighted graph Data structure
     * Need a root node since this is a one-way graph
     * @param rootNode
     */
    public WeightedGraph(WeightedMapNode<V> rootNode){
        size = 0;
        registeredNodes = new HashMap<>();
        root = rootNode;// Will be updated on first connection
        registerNode(root);
    }
    public WeightedGraph(V rootNodeValue){
        size = 0;
        registeredNodes = new HashMap<>();
        root = new WeightedMapNode<>(rootNodeValue);// Will be updated on first connection
        registerNode(root);
    }
    
    /**
     * Registers a new node into the system and connects
     * node1 to node2 with a specified edge weight.
     * @param graphNode
     * @param newNode
     * @param weight 
     * @return amount of nodes currently in graph
     */
    public int connectNode(WeightedMapNode<V> graphNode, WeightedMapNode<V> newNode, int weight){
        
        if (contains(graphNode)){
            if (contains(newNode.value()))
                graphNode.addChild(get(newNode.value()), weight);
            else {
                registerNode(newNode);
                graphNode.addChild(newNode, weight);
            }
        }
        return size;
    }
    /**
     * Registers the second node into the graph and makes a 
     * connection from node1 to node2 with a specified edge weight.
     * @param graphNodeValue
     * @param newNode
     * @param weight
     * @return 
     */
    public int connectNode(V graphNodeValue, WeightedMapNode<V> newNode, int weight){
        return connectNode(get(graphNodeValue), newNode, weight); 
    }
    /**
     * Registers a new node and makes it a child of a specified existing node.
     * @param graphNodeValue : Value of Node existing within the graph
     * @param newNodeValue : Value of new external node or another existing node
     * @param weight : weight of the edge
     * @return 
     */
    public int connectNode(V graphNodeValue, V newNodeValue, int weight){
        return connectNode(get(graphNodeValue), new WeightedMapNode<>(newNodeValue), weight);
    }
    /**
     * Each node will be assigned a number in the order they were registered.
     * This allows them to be quickly looked up by both object and key.
     * @param node 
     */
    private void registerNode(WeightedMapNode<V> node){
        if (!registeredNodes.containsValue(node)){
            registeredNodes.put(node.value(), node);
            size++;
        }
    }
    /**
     * Returns a list of all of the nodes.
     * @return 
     */
    @Override
    public ArrayList<WeightedMapNode<V>> getNodes(){
        ArrayList<WeightedMapNode<V>> nodes = new ArrayList<>();
        int i = 0;
        for (V key: registeredNodes.keySet()){
            nodes.add(registeredNodes.get(key));
            i++;
        }
        return nodes;
    }
    /**
     * Getter for a node based on its value
     * @param value
     * @return 
     */
    public WeightedMapNode<V> get(V value){
        return registeredNodes.get(value);
    }
    /**
     * Checks whether or not a node is present inside of this graph
     * @param node
     * @return 
     */
    public boolean contains(WeightedMapNode<V> node){
        return registeredNodes.containsValue(node);
    }
    /**
     * Checks whether or not a node's value is present in this graph.  
     * There should not be duplicates.
     * @param value
     * @return 
     */
    public boolean contains(V value){
        return registeredNodes.containsKey(value);
    }
    
    /**
     * Finds the shortest path from the root node to the end node
     * @param endNode
     * @return 
     */
    public ArrayList<V> pathToNode(WeightedMapNode<V> endNode){
        return pathToNode(root, endNode);
    }
    
    /**
     * Finds the path from one node to another.  Mostly involves
     * dealing with data from Dijkstra's algorithm function.
     * @param startNode
     * @param endNode
     * @return 
     */
    @Override
    public ArrayList<V> pathToNode(WeightedMapNode<V> startNode, WeightedMapNode<V> endNode){
        HashMap<V, DijkstraNode<V>> shortestPathNodes = dijkstra(startNode);
        Stack<WeightedMapNode<V>> pathStack = new Stack<>();
        DijkstraNode<V> currentNode = shortestPathNodes.get(endNode.value());
        while (pathStack.peek() != startNode){
            pathStack.push(currentNode.node);
            currentNode = shortestPathNodes.get(currentNode.previousNode.value());
        }
        
        ArrayList<V> path = new ArrayList<>();
        while (pathStack.peek() != null){
            path.add(pathStack.pop().value());
        }
        return path;
    }
    
    /**
     * Gets the length of shortest path from one node to the another.
     * A length of 2^31 means there is none.
     * @param startNode
     * @param endNode
     * @return 
     */
    public int shortestPathSize(WeightedMapNode<V> startNode, WeightedMapNode<V> endNode){
        // If the answer is 2^31, that means there is no path.
        HashMap<V, DijkstraNode<V>> shortestPathNodes = dijkstra(startNode);
        return shortestPathNodes.get(endNode.value()).distance;
    }
    
    /**
     * Dijkstra's minimal path algorithm executed for one source node to all other nodes. 
     * Uses "DijkstraNode" to convey general information, which other functions
     * will look through to output cleaner info.
     * @param startNode
     * @return A HashMap of node values to DijkstraNodes.  
     */
    private HashMap<V, DijkstraNode<V>> dijkstra(WeightedMapNode<V> startNode){
        // Returns the instead of length
        HashMap<V, DijkstraNode<V>> nodeMap = new HashMap<>();
        
        // unvisited contains all of the nodes, visited is empty
        HashMap<V, DijkstraNode<V>> unvisited = new HashMap<>();
        ArrayList<WeightedMapNode<V>> nodeList = getNodes();
        for (WeightedMapNode<V> node : nodeList) { 
            int initialDistance = Integer.MAX_VALUE;
            if(node == startNode)
                initialDistance = 0;
            unvisited.put(node.value(), new DijkstraNode<>(node, initialDistance));
        }
        HashMap<V, DijkstraNode<V>> visited = new HashMap<>();
        
        while (unvisited.size() > 0){
            // Loop that looks at the smallest of unvisited and looks at neighbors,
            // Then puts it back  
            DijkstraNode<V> minNode = Collections.min(unvisited.values(), new dijkstraComparator());
            if (minNode.distance == Integer.MAX_VALUE){
                break;
            }
            for (int i = 0; i < minNode.node.childrenCount(); i++){
                // If the total distance 
                int connectionDistance = minNode.node.getConnection(i).weight();
                V connectedNodeValue = minNode.node.getChild(i).value();
                if (connectionDistance + minNode.distance < unvisited.get(connectedNodeValue).distance){
                    unvisited.get(connectedNodeValue).distance = connectionDistance + minNode.distance;
                    unvisited.get(connectedNodeValue).previousNode = minNode.node;
                }
            }
            visited.put(minNode.node.value(), unvisited.remove(minNode.node.value()));
        }
        
        for (DijkstraNode<V> dNode : visited.values())
            nodeMap.put(dNode.node.value(), dNode);
        for (DijkstraNode<V> dNode : unvisited.values())
            nodeMap.put(dNode.node.value(), dNode);
        
        return nodeMap;
    }
    
    /**
     * Wrapper for the node so the distance and previous node can be stored.
     * Has fields "node" (final), "distance", "previousNode" 
     * 
     * @param <V> 
     */
    private static class DijkstraNode<V> {
        // Has a node
        public final WeightedMapNode<V> node;
        public int distance;
        public WeightedMapNode<V> previousNode;
        
        public DijkstraNode(WeightedMapNode<V> node, int distance){
            this.node = node;
            this.distance = distance;
            previousNode = node;
        }
    }
    
    /**
     * Private static class for getting minimum distance in Dijkstra algorithm
     */
    private static class dijkstraComparator implements Comparator<DijkstraNode>{
        @Override
        public int compare(DijkstraNode a, DijkstraNode b){
            if (a.distance > b.distance)
                return 1;
            else if (a.distance == b.distance)
                return 0;
            else
                return -1;
        }
    }
    
    /**
     * Depth first traversal (pre-order) through the graph, starting at the root.
     * @return a list of the values that were traversed
     */
    @Override
    public ArrayList<V> dft(){
        // Use list1.addAll(list2) for the lists
        HashSet<V> visited = new HashSet<>();
        return dft(root, visited);
    }
    
    /**
     * Need to create a visited array to prevent repeats
     * @param node
     * @return traversal list
     */
    private ArrayList<V> dft(WeightedMapNode<V> node, HashSet<V> visited){
        ArrayList<V> traversal = new ArrayList<>();
        traversal.add(node.value());
        visited.add(node.value());
        if (node.childrenCount() > 0){
            for (int i = 0; i < node.childrenCount(); i++){
                if (!visited.contains(node.getChild(i).value())){
                    // Inefficient search???
                    // Call-stack order issue with checking? Yep
                    traversal.addAll(dft(node.getChild(i), visited));
                }
                
            }
            return traversal;
        }
        else {
            return traversal;
        }
    }
    
    
    /**
     * Breadth first traversal through the graph, starting at the root.
     * Also uses a visited array.
     * @return a list of the values that were traversed
     */
    @Override
    public ArrayList<V> bft(){
        ArrayList<V> traversal = new ArrayList<>();
        Queue<WeightedMapNode<V>> childQueue = new Queue<>();
        childQueue.enqueue(root);
        WeightedMapNode<V> currentNode;
        HashSet<V> visited = new HashSet<>();
        
        while(childQueue.peek() != null){
            currentNode = childQueue.remove();
            if (!visited.contains(currentNode.value())){
                traversal.add(currentNode.value());
                visited.add(currentNode.value());
                for(int i = 0; i < currentNode.childrenCount(); i++){
                    childQueue.enqueue(currentNode.getChild(i));
                }
            }
        }
        return traversal;
    }
    
    @Override
    public String toString(){
        ArrayList<WeightedMapNode<V>> nodes = getNodes();
        String repr = "[";
        for (int i = 0; i < nodes.size(); i++){
            if (i != 0){
                repr += ", ";
            }
            repr += nodes.get(i).toString();
        }
        repr += "]";
        return repr;
    }
}
