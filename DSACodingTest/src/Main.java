/* package whatever; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
    private static int findShiftPoint(int[] arr, int l, int r) {
        if(l<=r) {
            int mid = l +(r-l)/2;
            if(arr[mid-1] == (2*(mid-1)) && arr[mid] > (2*mid)){
                return mid;
            }
            if(arr[mid] > 2*mid){
                return findShiftPoint(arr, l, mid-1);
            }else{
                return findShiftPoint(arr, mid+1, r);
            }
        }
        return l+1;
    }
    public static void main (String[] args) throws java.lang.Exception
    {
        int[] arr = {0, 2, 4, 6, 8, 15, 17, 19, 21, 23};
        int l = 0, r = arr.length-1, n = arr.length;
        int index = 0;
        if(arr[0] == 0){
            index = findShiftPoint(arr, l, r);
        }
        System.out.println(index);

        // your code goes here
    }
}