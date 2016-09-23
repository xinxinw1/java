package com.xinxin.tools;

/**
 * Created by secret2008 on 6/2/16.
 *
 * This class is for common tools functions that can be reused in many places
 */
public class $ {
    public static void pr(Object a){
        System.out.print(a);
    }

    public static void prn(Object a){
        System.out.println(a);
    }

    public static String makeStr(String name, float[] a){
        String out = name + ": ";
        for (int i = 0; i < a.length; i++){
            out += a[i] + " ";
        }
        return out;
    }

    public static interface CompareFn {
        boolean gt(float a, float b);
    }

    public static class CompareBigger implements CompareFn {
        public boolean gt(float a, float b){
            return a > b;
        }
    }

    public static class CompareSmaller implements CompareFn {
        public boolean gt(float a, float b){
            return a < b;
        }
    }

    public static String disp(float[] arr){
        String s = "[";
        if (arr.length > 0){
            s += arr[0];
            for (int i = 1; i < arr.length; i++){
                s += ", " + arr[i];
            }
        }
        s += "]";
        return s;
    }

    public static float[] copy(float[] arr){
        float[] newArr = new float[arr.length];
        for (int i = 0; i < arr.length; i++){
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static float[] append(float[] a, float[] b){
        float[] arr = new float[a.length+b.length];
        for (int i = 0; i < a.length; i++){
            arr[i] = a[i];
        }
        for (int i = 0; i < b.length; i++){
            arr[a.length+i] = b[i];
        }
        return arr;
    }
}
