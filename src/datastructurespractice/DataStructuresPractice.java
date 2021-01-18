/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice;

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author muhsin
 */
public class DataStructuresPractice {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WeightedGraph<String> wg = new WeightedGraph<>("A");
        wg.connectNode("A", "B", 3);
        wg.connectNode("A", "C", 5);
        wg.connectNode("B", "C", 4);
        wg.connectNode("B", "D", 11);
        wg.connectNode("C", "D", 4);
        wg.connectNode("C", "E", 2);
        wg.connectNode("D", "F", 7);
        wg.connectNode("D", "G", 3);
        wg.connectNode("E", "F", 9);
        wg.connectNode("F", "H", 1);
        wg.connectNode("G", "F", 3);
        wg.connectNode("G", "H", 4);
        print(wg);
        print(wg.get("E").childrenCount());
        print(wg.pathToNode(wg.get("A"), wg.get("F")));
        print(wg.dft());
    }
    
    public static void bstTest(){
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(9);
        bst.add(4);
        bst.add(11);
        bst.add(2);
        bst.add(5);
        bst.add(10);
        bst.add(7);
        print("BFT: " + Arrays.toString(bst.breadthFirstTraversal()));
        print("Preorder DFT: " + Arrays.toString(bst.preOrderDFT()));
        print("Postorder DFT: " + Arrays.toString(bst.postOrderDFT()));
        print("Inorder DFT: " + Arrays.toString(bst.inOrderDFT()));
    }
    
    public static void print(Object object){
        System.out.println(object);
    }
    
}
