/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

import datastructurespractice.utils.*;

/**
 *
 * @author muhsin
 */
public class BinarySearchTree extends Tree<Integer> {
    private BinaryNode<Integer> head;
    private int size;
    public BinarySearchTree(){
        head = null;
        size = 0;
    }
    
    @Override
    public void add(Integer value){
        add(new BinaryNode<>(value));
    }
    public void add(int value){
        add(new Integer(value));
    }
    public void add(BinaryNode<Integer> node){
        if(head == null){
            head = node;
            size++;
        }
        else {
            add(node, head);
        }
    }
    public void add(BinaryNode<Integer> node, BinaryNode<Integer> currentNode){
        // Recursively adds to the left if lower, right if higher
        if(node.value() <= currentNode.value()){
            if(currentNode.left() == null){
                currentNode.setLeft(node);
                size++;
            }
            else {
                add(node, currentNode.left());
            }
        }
        else {
            if(currentNode.right() == null){
                currentNode.setRight(node);
                size++;
            }
            else {
                add(node, currentNode.right());
            }
        }
    }
    
    @Override
    public int getHeight(){
        return getHeight(head);
    }
    public int getHeight(BinaryNode node){
        if (node.left() == null && node.right() == null)
            return 0;
        else if (node.left() != null && node.right() == null)
            return 1+getHeight(node.left());
        else if (node.left() == null && node.right() != null)
            return 1+getHeight(node.right());
        else
            if (getHeight(node.right()) >= getHeight(node.left()))
                return 1+getHeight(node.right());
            else
                return 1+getHeight(node.left());
    }
    
    
    public int[] preOrderDFT(){
        // Returns an array of the tree's pre-order depth first traversal path
        return preOrderDFT(head);
    }
    private int[] preOrderDFT(BinaryNode<Integer> node){
        // Recursive function: Root, Left, Right
        // Updates arraylist, should only be size (size)
        int[] traversal = new int[]{node.value()};
        if (node.left() != null)
            traversal = ArrayUtils.join(traversal, preOrderDFT(node.left()));
        if (node.right() != null)
            traversal = ArrayUtils.join(traversal, preOrderDFT(node.right()));
        return traversal;
    }
    
    public int[] postOrderDFT(){
        // Returns an array of the tree's pre-order depth first traversal path
        return postOrderDFT(head);
    }
    private int[] postOrderDFT(BinaryNode<Integer> node){
        // Recursive function: Left, Right, Root
        // Updates arraylist, should only be size (size) 
        int[] root = new int[]{node.value()};
        if (node.left() == null && node.right() == null)
            return root;
        else if (node.right() == null)
            return ArrayUtils.join(postOrderDFT(node.left()), root);
        else if (node.left() == null)
            return ArrayUtils.join(postOrderDFT(node.right()), root);
        else
            return ArrayUtils.join(ArrayUtils.join(postOrderDFT(node.left()), postOrderDFT(node.right())), root);
    }
    
    public int[] inOrderDFT(){
        // Returns an array of the tree's pre-order depth first traversal path
        return inOrderDFT(head);
    }
    private int[] inOrderDFT(BinaryNode<Integer> node){
        // Recursive function: Left, Root, Right
        // Updates arraylist, should only be size (size) 
        int[] root = new int[]{node.value()};
        if (node.left() == null && node.right() == null)
            return root;
        else if (node.right() == null)// Left node exists
            return ArrayUtils.join(inOrderDFT(node.left()), root);
        else if (node.left() == null)// Right node exists
            return ArrayUtils.join(root, inOrderDFT(node.right()));
        else
            return ArrayUtils.join(ArrayUtils.join(inOrderDFT(node.left()), root), inOrderDFT(node.right()));
    }
    
    
    public int[] breadthFirstTraversal(){
        // 1-d array, ignoring nulls
        int[] traversal = new int[size];
        int index = 0;
        
        Queue<BinaryNode<Integer>> queue = new Queue<>();
        queue.enqueue(head);
        
        BinaryNode<Integer> currentNode;
        while(queue.peek() != null){
            currentNode = queue.remove();
            traversal[index] = currentNode.value();
            index++;
            if (currentNode.left() != null)
                queue.enqueue(currentNode.left());
            if (currentNode.right() != null)
                queue.enqueue(currentNode.right());
        }
        
        return traversal;
    }
    
}
