/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructurespractice.utils;

/**
 *
 * @author muhsin
 */
public class ArrayUtils {
    public static int[] join(int[] array1, int[] array2){
        int[] result = new int[array1.length + array2.length];
        int i = 0;
        while(i < result.length){
            if(i < array1.length)
                result[i] = array1[i];
            else
                result[i] = array2[i - array1.length];
            i++;
        }
        return result;
    }
}
