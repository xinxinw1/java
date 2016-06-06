package tools;

import java.util.*;
import java.util.regex.Matcher;

import java.io.*;

import java.net.URL;

import java.lang.reflect.Array;

public class $ {
  ////// Type //////
  
  public static Class cls(Object a){
    return a.getClass();
  }
  
  public static String clsn(Object a){
    return cls(a).getSimpleName();
  }
  
  public static Class acls(Object a){
    return cls(a).getComponentType();
  }
  
  public static String tp(Object a){
    return (a == null)?"nul":ctp(cls(a));
  }
  
  public static String ctp(Class a){
    if (a.isArray() || a == Xarr.class || a == ArrayList.class)return "arr";
    if (in(a, int.class, Integer.class, double.class, Double.class))return "num";
    if (in(a, char.class, Character.class))return "chr";
    if (a == String.class)return "str";
    if (a == Regex.class)return "rgx";
    if (a == Cons.class)return "cns";
    if (a == Class.class)return "cls";
    if (a == Table.class)return "obj";
    return a.toString();
  }
  
  public static Class ubox(Class a){
    if (a == Integer.class)return int.class;
    if (a == Double.class)return double.class;
    if (a == Character.class)return char.class;
    if (a == Float.class)return float.class;
    if (a == Long.class)return long.class;
    if (a == Boolean.class)return boolean.class;
    if (a == Byte.class)return byte.class;
    if (a == Short.class)return short.class;
    return a;
  }
  
  ////// Predicates //////
  
  public static boolean arrp(Object a){return tp(a) == "arr";}
  public static boolean xrrp(Object a){return a instanceof Xarr;}
  public static boolean lrrp(Object a){return a instanceof ArrayList;}
  public static boolean strp(Object a){return a instanceof String;}
  public static boolean nump(Object a){return a instanceof Number;}
  public static boolean intp(Object a){return a instanceof Integer;}
  public static boolean doup(Object a){return a instanceof Double;}
  public static boolean chrp(Object a){return a instanceof Character;}
  public static boolean bolp(Object a){return a instanceof Boolean;}
  public static boolean rgxp(Object a){return a instanceof Regex;}
  public static boolean tabp(Object a){return a instanceof Table;}
  public static boolean lisp(Object a){return a instanceof Cons;}
  public static boolean atmp(Object a){return !lisp(a) || l(a).nilp();}
  public static boolean nilp(Object a){return lisp(a) && l(a).nilp();}
  public static boolean clsp(Object a){return a instanceof Class;}
  public static boolean nulp(Object a){return a == null;}
  
  public static boolean boxp(Object a){
    return a instanceof Boolean ||
           a instanceof Byte ||
           a instanceof Character ||
           a instanceof Float ||
           a instanceof Integer ||
           a instanceof Long ||
           a instanceof Short ||
           a instanceof Double;
  }
  
  ////// Comparison //////
  
  public static boolean is(Object a, Object b){
    return a == b || ((boxp(a) || strp(a) || lisp(a)) && a.equals(b));
  }
  
  public static boolean in(Object x, Object... a){
    for (int i = 0; i < a.length; i++){
      if (is(a[i], x))return true;
    }
    return false;
  }
  
  ////// Display //////
  
  public static String dsp(Object a){
    if (nulp(a))return "null";
    if (arrp(a)){
      String s = "[";
      if (len(a) > 0){
        s += dsp(ref(a, 0));
        for (int i = 1; i < len(a); i++){
          s += ", " + dsp(ref(a, i));
        }
      }
      s += "]";
      return s;
    }
    if (chrp(a))return "'" + a + "'";
    if (strp(a))return "\"" + a + "\"";
    if (lisp(a))return dlis(a);
    if (tabp(a))return dtab(a);
    return a.toString();
  }
  
  private static String dtab(Object a){
    String[] r = {};
    for (Object i : keys(a)){
      r = tai(r, str(i) + ": " + dsp(ref(a, i)));
    }
    return "{" + joi(r, ", ") + "}";
  }
  
  private static String dlis(Object a){
    if (nilp(a))return "()";
    if (is(car(a), "qt"))return "'" + dsp(cadr(a));
    if (is(car(a), "qq"))return "`" + dsp(cadr(a));
    if (is(car(a), "uq"))return "," + dsp(cadr(a));
    if (is(car(a), "uqs"))return ",@" + dsp(cadr(a));
    if (is(car(a), "neg"))return "~" + dsp(cadr(a));
    if (is(car(a), "not"))return "!" + dsp(cadr(a));
    return "(" + dlis2(a) + ")";
  }
  
  private static String dlis2(Object a){
    if (nilp(cdr(a)))return dsp(car(a));
    if (atmp(cdr(a)))return dsp(car(a)) + " . " + dsp(cdr(a));
    return li(car(a)) + " " + dlis2(cdr(a));
  }
  
  ////// Shorthands //////
  
  //// Array ////
  
  /*
  if I had
  
  public static int[] a(int... a){
    return a;
  }
  
  public static int[][] a(int[]... a){
    return a;
  }
  
  then a(a(1, 2, 3)) would return [1, 2, 3]
  because the inner arr is "apply"d to the first method
  */
  
  /*public static Object[] a(){
    return {};
  }*/
  
  public static int[] a(int... a){return a;}
  public static double[] a(double... a){return a;}
  public static char[] a(char... a){return a;}
  public static boolean[] a(boolean... a){return a;}
  public static String[] a(String... a){return a;}
  
  public static int[][] aa(int[]... a){return a;}
  public static double[][] aa(double[]... a){return a;}
  public static char[][] aa(char[]... a){return a;}
  public static boolean[][] aa(boolean[]... a){return a;}
  public static String[][] aa(String[]... a){return a;}
  
  @SafeVarargs
  public static <T> T[] ar(T... a){return a;}
  
  public static Xarr xr(Object... a){return new Xarr(a);}
  
  //// Table ////
  
  public static Table o(Object... a){return new Table(a);}
  
  //// Regex ////
  
  public static Regex x(String a){return new Regex(a);}
  public static Regex rgx(String a){return x(a);}
  
  //// List ////
  
  public static Cons li(Object... a){
    Cons r = nil();
    for (int i = len(a)-1; i >= 0; i--){
      r = cns(a[i], r);
    }
    return r;
  }
  
  //// Casting ////
  
  public static String s(Object a){return (String) a;}
  public static Number n(Object a){return (Number) a;}
  public static int i(Object a){return (int) a;}
  public static double d(Object a){return (double) a;}
  public static boolean b(Object a){return (boolean) a;}
  public static char c(Object a){return (char) a;}
  public static Table t(Object a){return (Table) a;}
  public static Cons l(Object a){return (Cons) a;}
  public static Object ob(Object a){return a;}
  public static Regex r(Object a){return (Regex) a;}
  public static int[] ir(Object a){return (int[]) a;}
  public static Object[] or(Object a){return (Object[]) a;}
  public static boolean[] br(Object a){return (boolean[]) a;}
  public static String[] sr(Object a){return (String[]) a;}
  
  ////// Output //////
  
  public static void ou(Object a){
    System.out.print(a);
  }
  
  public static void out(Object a){
    System.out.println(a);
  }
  
  public static void pr(Object a, Object... args){
    ou(stf(a, args));
  }
  
  public static void prn(Object a, Object... args){
    out(stf(a, args));
  }
  
  public static void prn(){out("");}
  
  ////// Input //////
  
  public static String gstr(){
    Scanner s = new Scanner(System.in);
    return s.nextLine();
  }
  
  public static String gstr(String a){
    ou(a);
    return gstr();
  }
  
  public static Number gnum(){return tnum(gstr());}
  public static Number gnum(String a){return tnum(gstr(a));}
  public static int gint(){return tint(gstr());}
  public static int gint(String a){return tint(gstr(a));}
  public static double gdou(){return tdou(gstr());}
  public static double gdou(String a){return tdou(gstr(a));}
  public static double gdoub(){return gdou();}
  public static double gdoub(String a){return gdou(a);}
  public static char gchr(){return tchr(gstr());}
  public static char gchr(String a){return tchr(gstr(a));}
  
  ////// Converters //////
  
  public static Number tnum(Object a){
    if (nump(a))return n(a);
    if (strp(a)){
      if (tdou(tint(a)) != tdou(a))return tdou(a);
      return tint(a);
    }
    if (chrp(a))return tint(a);
    throw err("tnum", "Can't coerce a = $1 to num", a);
  }
  
  public static int tint(Object a){
    return tint(a, 10);
  }
  
  public static int tint(Object a, int rdx){
    if (nump(a))return n(a).intValue();
    if (strp(a)){
      try {
        return Integer.parseInt(s(a), rdx);
      } catch (NumberFormatException e){
        return 0;
      }
    }
    if (chrp(a))return i(c(a));
    if (bolp(a))return b(a)?1:0;
    throw err("tint", "Can't coerce a = $1 to int", a);
  }
  
  public static double tdou(Object a){
    if (nump(a))return n(a).doubleValue();
    if (strp(a)){
      try {
        return Double.parseDouble(s(a));
      } catch (NumberFormatException e){
        return 0;
      }
    }
    if (chrp(a))return d(c(a));
    throw err("tdou", "Can't coerce a = $1 to doub", a);
  }
  
  public static String tstr(Object a){
    if (strp(a))return s(a);
    if (chrp(a))return String.valueOf(c(a));
    if (intp(a))return String.valueOf(i(a));
    if (doup(a))return String.valueOf(d(a));
    return str(a);
  }
  
  public static char tchr(Object a){
    if (chrp(a))return c(a);
    if (strp(a)){
      if (is(a, ""))return 0;
      return s(a).charAt(0);
    }
    if (nump(a))return c(tint(a));
    throw err("tchr", "Can't coerce a = $1 to chr", a);
  }
  
  ////// Sequence //////
  
  //// Items ////
  
  public static Object ref(Object a, Object... is){
    Object r = a;
    for (Object i : is)r = ref1(r, i);
    return r;
  }
  
  public static Object ref1(Object a, Object i){
    if (nulp(a))return null;
    if (arrp(a)){
      int j = i(i);
      if (j < 0 || j >= len(a))return null;
      if (lrrp(a))return ((ArrayList) a).get(j);
      return Array.get(a, j);
    }
    if (strp(a)){
      int j = i(i);
      if (j < 0 || j >= len(a))return "";
      return tstr(s(a).charAt(j));
    }
    if (tabp(a))return t(a).get(i);
    throw err("ref1", "Can't get item i = $1 in a = $2", i, a);
  }
  
  public static Object rel(Object a, int n){
    return ref(a, len(a)-1-n);
  }
  
  @SuppressWarnings("unchecked")
  public static Object set(Object a, Object i, Object x){
    if (arrp(a)){
      int j = i(i);
      if (xrrp(a))return ((Xarr) a).set(j, x);
      if (lrrp(a)){
        if (j > len(a)){
          for (int k = j-len(a); k > 0; k++){
            ((ArrayList) a).add(null);
          }
        }
        ((ArrayList) a).add(j, x);
        return x;
      }
      Array.set(a, j, x);
      return x;
    }
    if (tabp(a))return t(a).set(i, x);
    throw err("set", "Can't set item i = $1 in a = $2 to x = $3", i, a, x);
  }
  
  public static Object fst(Object a){return ref(a, 0);}
  public static Object las(Object a){return rel(a, 0);}
  
  //// Apply ////
  
  public static int pos(Object x, Object a, int n){
    if (arrp(a)){
      for (int i = n; i < len(a); i++){
        if (is(ref(a, i), x))return i;
      }
      return -1;
    }
    if (strp(a)){
      if (strp(x))return s(a).indexOf(s(x), n);
      if (rgxp(x)){
        Matcher m = r(x).mtchr(s(a));
        if (!m.find())return -1;
        return m.start();
      }
      if (arrp(x)){
        int m = -1; int curr;
        for (int i = 0; i < len(x); i++){
          curr = pos(ref(x, i), a, n);
          if (curr != -1){
            if (m != -1)m = Math.min(m, curr);
            else m = curr;
          }
        }
        return m;
      }
    }
    // can't put tpos here because return isn't int
    throw err("pos", "Can't get pos of x = $1 in a = $2", x, a);
  }
  
  public static int pos(Object x, Object a){
    return pos(x, a, 0);
  }
  
  public static Object tpos(Object x, Object a){
    for (Object k : keys(a)){
      if (is(ref(a, k), x))return k;
    }
    return -1;
  }
  
  public static int pol(Object x, Object a, int n){
    if (arrp(a)){
      for (int i = n; i >= 0; i--){
        if (is(ref(a, i), x))return i;
      }
      return -1;
    }
    if (strp(a)){
      if (strp(x))return s(a).lastIndexOf(s(x), n);
      if (arrp(x)){
        int m = -1; int curr;
        for (int i = 0; i < len(x); i++){
          curr = pol(ref(x, i), a, n);
          if (curr != -1){
            if (m != -1)m = Math.max(m, curr);
            else m = curr;
          }
        }
        return m;
      }
    }
    throw err("pol", "Can't get last pos of x = $1 in a = $2 from n = $3", x, a, n);
  }
  
  public static int pol(Object x, Object a){
    return pol(x, a, len(a));
  }
  
  public static int[] pss(Object x, Object a){
    if (arrp(a)){
      int[] r = {};
      if (arrp(x)){
        for (int i = 0; i < len(a); i++){
          for (int k = 0; k < len(x); k++){
            if (is(ref(x, k), ref(a, i)))r = tai(r, i);
          }
        }
      } else {
        for (int i = 0; i < len(a); i++){
          if (is(x, ref(a, i)))r = tai(r, i);
        }
      }
      return r;
    }
    if (strp(a)){
      if (arrp(x)){
        int[] r = {}; Object curr; int v;
        for (int i = 0; i < len(a); i++){
          for (int k = 0; k < len(x); k++){
            curr = ref(x, k);
            for (v = 0; v < len(curr); v++){
              if (i+v == len(a))break;
              if (!is(ref(a, i+v), ref(curr, v)))break;
            }
            if (v == len(curr)){
              r = tai(r, i);
              break;
            }
          }
        }
        return r;
      }
      if (strp(x)){
        int[] r = {}; int v;
        for (int i = 0; i < len(a); i++){
          for (v = 0; v < len(x); v++){
            if (i+v == len(a))break;
            if (!is(ref(a, i+v), ref(x, v)))break;
          }
          if (v == len(x))r = tai(r, i);
        }
        return r;
      }
      if (rgxp(x)){
        Matcher m = r(x).mtchr(s(a));
        int[] r = {};
        while (m.find())r = tai(r, m.start());
        return r;
      }
      throw err("pss", "Can't get poses of x = $1 in str a = $2", x, a);
    }
    throw err("pss", "Can't get pss of x = $1 in a = $2", x, a);
  }
  
  public static int[] poses(Object x, Object a){return pss(x, a);}
  
  public static Object[] tpss(Object x, Object a){
    Object[] r = {};
    for (Object k : keys(a)){
      if (is(ref(a, k), x))r = tai(r, k);
    }
    return r;
  }
  
  public static boolean has(Object x, Object a){
    if (strp(a)){
      if (strp(x))return s(a).contains(s(x));
      if (rgxp(x)){
        Matcher m = r(x).mtchr(s(a));
        return m.find();
      }
      throw err("has1", "Can't find if str a = $1 has x = $2", a, x);
    }
    if (arrp(a)){
      for (int i = 0; i < len(a); i++){
        if (is(ref(a, i), x))return true;
      }
      return false;
    }
    if (tabp(a))return t(a).has(x);
    throw err("has", "Can't find if a = $1 has x = $2", a, x);
  }
  
  public static boolean all(Object x, Object a){
    if (arrp(a)){
      for (int i = 0; i < len(a); i++){
        if (!is(x, ref(a, i)))return false;
      }
      return true;
    }
    throw err("all", "Can't find if all in a = $1 are x = $2", a, x);
  }
  
  public static Object cut(Object x, Object a){
    if (arrp(a)){
      Object r = narr(a);
      for (int i = 0; i < len(a); i++){
        if (!is(ref(a, i), x))r = tai(r, ref(a, i));
      }
      return r;
    }
    if (strp(a))return rpl(x, "", a);
    if (tabp(a)){
      Table t = o();
      for (Object k : keys(a)){
        if (!is(ref(a, k), x)){
          set(t, k, ref(a, k));
        }
      }
      return t;
    }
    throw err("cut", "Can't cut x = $1 from a = $2", x, a);
  }
  
  public static int[] cut(int x, int[] a){return ir(cut(ob(x), ob(a)));}
  
  public static Object rpl(Object x, Object y, Object a){
    if (strp(a)){
      if (strp(x))return s(a).replace(s(x), s(y));
      if (rgxp(x)){
        Matcher m = r(x).mtchr(s(a));
        return m.replaceAll(s(y));
      }
      if (arrp(x)){
        String s = ""; int i = 0; int k;
        while (i < len(a)){
          for (k = 0; k < len(x); k++){
            if (pos(ref(x, k), a, i) == i){
              s += arrp(y)?ref(y, k):y;
              i += len(ref(x, k));
              break;
            }
          }
          if (k == len(x)){
            s += ref(a, i);
            i += 1;
          }
        }
        return s;
      }
    }
    if (arrp(a)){
      Object r = narr(a, len(a));
      for (int i = 0; i < len(a); i++){
        set(r, i, is(ref(a, i), x)?y:ref(a, i));
      }
      return r;
    }
    if (tabp(a)){
      Table t = o();
      for (Object k : keys(a)){
        if (is(ref(a, k), x)){
          set(t, k, y);
        } else {
          set(t, k, ref(a, k));
        }
      }
      return t;
    }
    throw err("rpl", "Can't rpl x = $1 with y = $2 in a = $3", x, y, a);
  }
  
  public static Object rplt(Object p, Object a, Object x){
    if (arrp(a)){
      if (arrp(x)){
        Object r = narr(a, len(a));
        for (int i = 0; i < len(a); i++){
          set(r, i, b(ref(p, i))?ref(x, i):ref(a, i));
        }
        return r;
      }
      Object r = narr(a, len(a));
      for (int i = 0; i < len(a); i++){
        set(r, i, b(ref(p, i))?x:ref(a, i));
      }
      return r;
    }
    if (tabp(a)){
      if (tabp(x)){
        Table t = o();
        for (Object k : keys(a)){
          if (b(ref(p, k))){
            set(t, k, ref(x, k));
          } else {
            set(t, k, ref(a, k));
          }
        }
        return t;
      }
      Table t = o();
      for (Object k : keys(a)){
        if (b(ref(p, k))){
          set(t, k, x);
        } else {
          set(t, k, ref(a, k));
        }
      }
      return t;
    }
    throw err("rplt", "Can't rpl trues p = $1 with x = $2 in a = $3", p, x, a);
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T[] rplt(boolean[] p, T[] a, T[] x){
    return (T[]) rplt(ob(p), ob(a), ob(x));
  }
  
  public static int[] rplt(Object p, int[] a, int[] x){
    return (int[]) rplt(p, ob(a), ob(x));
  }
  
  public static int cnt(Object x, Object a){
    if (arrp(a)){
      int n = 0;
      for (int i = 0; i < len(a); i++){
        if (is(x, ref(a, i)))n++;
      }
      return n;
    }
    throw err("cnt", "Can't count num of x = $1 in a = $2", x, a);
  }
  
  //// Whole ////
  
  public static int len(Object a){
    if (arrp(a)){
      if (lrrp(a))return ((ArrayList) a).size();
      return Array.getLength(a);
    }
    if (strp(a))return s(a).length();
    if (tabp(a))return len(keys(a));
    throw err("len", "Can't get len of a = $1", a);
  }
  
  public static boolean emp(Object a){
    if (arrp(a) || strp(a) || tabp(a))return len(a) == 0;
    if (nulp(a))return true;
    throw err("emp", "Can't find if a = $1 is empty", a);
  }
  
  public static Object cpy(Object a, Class c, int len){
    if (arrp(a)){
      Object r = narr(c, len);
      int end = Math.min(len(a), len);
      for (int i = 0; i < end; i++){
        set(r, i, ref(a, i));
      }
      return r;
    }
    return a;
  }
  
  public static Object cpy(Object a, Class c){
    return cpy(a, c, len(a));
  }
  
  public static Object cpy(Object a, int len){
    if (arrp(a)){
      Object r = narr(a, len);
      int end = Math.min(len(a), len);
      for (int i = 0; i < end; i++){
        set(r, i, ref(a, i));
      }
      return r;
    }
    if (tabp(a)){
      Table t = o();
      for (Object k : keys(a)){
        set(t, k, ref(a, k));
      }
      return t;
    }
    return a;
  }
  
  public static Object cpy(Object a){
    return cpy(a, len(a));
  }
  
  public static Object rev(Object a){
    if (arrp(a)){
      Object r = narr(a, len(a));
      for (int i = 0; i < len(a); i++){
        set(r, i, ref(a, len(a)-1-i));
      }
      return r;
    }
    if (strp(a)){
      String s = "";
      for (int i = len(a)-1; i >= 0; i--){
        s += s(ref(a, i));
      }
      return s;
    }
    throw err("rev", "Can't rev a = $1", a);
  }
  
  //// Parts ////
  
  public static Object sli(Object a, int n, int m){
    if (n < 0)n = 0;
    if (n > len(a))n = len(a);
    if (m > len(a))m = len(a);
    if (m < n)m = n;
    if (strp(a))return s(a).substring(n, m);
    if (arrp(a)){
      Object r = narr(a, m-n);
      for (int i = 0; i < m-n; i++){
        set(r, i, ref(a, n+i));
      }
      return r;
    }
    throw err("sli", "Can't slice a = $1 from n = $2 to m = $3", a, n, m);
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T[] sli(T[] a, int n, int m){
    return (T[]) sli(ob(a), n, m);
  }
  
  public static Object sli(Object a, int n){
    return sli(a, n, len(a));
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T[] sli(T[] a, int n){
    return (T[]) sli(ob(a), n);
  }
  
  public static Object fstn(int n, Object a){
    return sli(a, 0, n);
  }
  
  public static Object lasn(int n, Object a){
    return sli(a, len(a)-n);
  }
  
  // nrst
  public static Object aftn(int n, Object a){
    return sli(a, n);
  }
  
  public static Object rst(Object a){
    return sli(a, 1);
  }
  
  // but last
  public static Object butn(int n, Object a){
    return sli(a, 0, len(a)-n);
  }
  
  public static Object but(Object a){
    return butn(1, a);
  }
  
  public static Object mid(Object a){
    return sli(a, 1, len(a)-1);
  }
  
  public static Object bef(Object x, Object a){
    return sli(a, 0, pos(x, a));
  }
  
  public static Object aft(Object x, Object a){
    return sli(a, pos(x, a)+1);
  }
  
  //// Group ////
  
  public static Object spl(Object a, Object x){
    if (strp(a)){
      if (strp(x)){
        Object r = new String[0];
        int last = 0; int v;
        int i = 0;
        while (i < len(a)){
          for (v = 0; v < len(x); v++){
            if (i+v == len(a))break;
            if (!is(ref(a, i+v), ref(x, v)))break;
          }
          if (v == len(x)){
            r = tai(r, sli(a, last, i));
            last = i+v;
            i += v;
          } else {
            i++;
          }
        }
        return tai(r, sli(a, last, len(a)));
      }
      if (arrp(x)){
        Object r = new String[0];
        int last = 0; Object curr; int k; int v;
        int i = 0;
        while (i < len(a)){
          for (k = 0; k < len(x); k++){
            curr = ref(x, k);
            for (v = 0; v < len(curr); v++){
              if (i+v == len(a))break;
              if (!is(ref(a, i+v), ref(curr, v)))break;
            }
            if (v == len(curr)){
              r = tai(r, sli(a, last, i));
              last = i+v;
              i += v;
              break;
            }
          }
          if (k == len(x))i++;
        }
        return tai(r, sli(a, last, len(a)));
      }
      if (rgxp(x)){
        Object r = new String[0];
        Matcher m = r(x).mtchr(s(a));
        int last = 0;
        while (m.find()){
          r = tai(r, sli(a, last, m.start()));
          last = m.end();
        }
        return tai(r, sli(a, last, len(a)));
      }
      throw err("spl", "Can't split str a = $1 by x = $2", a, x);
    }
    if (arrp(a)){
      Object r = narr(a);
      int last = 0;
      for (int i = 0; i < len(a); i++){
        if (is(ref(a, i), x)){
          r = tai(r, sli(a, last, i));
          last = i+1;
        }
      }
      return tai(r, sli(a, last, len(a)));
    }
    throw err("spl", "Can't split a = $1 by x = $2", a, x);
  }
  
  public static Object grp(Object a, int n){
    if (n > 0){
      Object r = narr(a);
      for (int i = 0; i < len(a); i += n){
        r = tai(r, sli(a, i, i+n));
      }
      return r;
    }
    throw err("grp", "Can't group a = $1 into groups of n = $2", a, n);
  }
  
  //// Join ////
  
  public static String joi(Object a, String x){
    String s = "";
    if (len(a) > 0){
      s += str(ref(a, 0));
      for (int i = 1; i < len(a); i++){
        s += x + str(ref(a, i));
      }
    }
    return s;
  }
  
  public static String joi(Object a){
    String s = "";
    for (int i = 0; i < len(a); i++){
      if (strp(ref(a, i)) || chrp(ref(a, i)))s += ref(a, i);
      else s += dsp(ref(a, i));
    }
    return s;
  }
  
  public static Object fla(Object a, Object x){
    Object r = narr(a);
    if (len(a) > 0){
      r = app2(r, ref(a, 0));
      for (int i = 1; i < len(a); i++){
        r = app(r, x, ref(a, i));
      }
    }
    return r;
  }
  
  public static Object fla(Object a){
    Object r = narr(a);
    for (int i = 1; i < len(a); i++){
      r = app2(r, ref(a, i));
    }
    return r;
  }
  
  public static Object app(Object a, Object... rst){
    Object b = a;
    for (Object i : rst)b = app2(b, i);
    return b;
  }
  
  public static int[] app(int[] a, Object... rst){return ir(app(ob(a), or(rst)));}
  
  public static Object app2(Object a, Object b){
    if (arrp(a)){
      if (arrp(b)){
        Object r;
        if (lrrp(a) && !xrrp(a))r = new ArrayList();
        if (xrrp(a) || xrrp(b) || !is(acls(a), acls(b)))r = new Xarr(a);
        else r = cpy(a, len(a)+len(b));
        for (int i = 0; i < len(b); i++){
          set(r, len(a)+i, ref(b, i));
        }
        return r;
      }
      return tai(a, b);
    }
    if (strp(a)){
      if (strp(b))return s(a) + s(b);
      return s(a) + str(b);
    }
    if (tabp(a)){
      Table t = o();
      for (Object k : keys(a))set(t, k, ref(a, k));
      for (Object k : keys(b))set(t, k, ref(b, k));
      return t;
    }
    throw err("app2", "Can't append a = $1 to b = $2", a, b);
  }
  
  public static Object bor(Object a, Object x, Object y){
    if (strp(a))return app(x, a, y);
    if (arrp(a))return tai(hea(a, x), y);
    throw err("bor", "Can't border a = $1 with x = $2 and y = $3", a, x, y);
  }
  
  public static Object nof(int n, Object a){
    if (strp(a)){
      String s = "";
      for (int i = n; i >= 1; i--)s += s(a);
      return s;
    }
    if (arrp(a)){
      Object r = narr(a);
      for (int i = n; i >= 1; i--)r = app2(r, a);
      return r;
    }
    Object r = narr(ubox(cls(a)));
    for (int i = n; i >= 1; i--)r = tai(r, a);
    return r;
  }
  
  public static int[] nof(int n, int[] a){return ir(nof(n, ob(a)));}
  public static boolean[] nof(int n, boolean[] a){return br(nof(n, ob(a)));}
  
  //// Array ////
  
  public static Object narr(Class tp, int len){
    return Array.newInstance(tp, len);
  }
  
  public static Object narr(Class tp){
    return narr(tp, 0);
  }
  
  public static Object narr(Object a, int len){
    if (arrp(a)){
      if (xrrp(a))return new Xarr();
      if (lrrp(a))return new ArrayList();
      return narr(acls(a), len);
    }
    throw err("narr", "Can't make narr with same type as a = $1", a);
  }
  
  public static Object narr(Object a){
    return narr(a, 0);
  }
  
  public static Object hea(Object a, Object x){
    Object r = cpy(a, len(a)+1);
    for (int i = len(r)-1; i >= 1; i--){
      set(r, i, ref(r, i-1));
    }
    set(r, 0, x);
    return r;
  }
  
  public static Object tai(Object a, Object x){
    Object r = cpy(a, len(a)+1);
    set(r, len(a), x);
    return r;
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T[] tai(T[] a, T x){return (T[]) tai(ob(a), ob(x));}
  public static int[] tai(int[] a, int x){return ir(tai(ob(a), ob(x)));}
  
  //// Table ////
  
  public static Table wit(Object a, Object... x){
    return t(app(a, o(x)));
  }
  
  //// Other ////
  
  public static boolean beg(Object a, Object... ag){
    for (int i = 0; i < len(ag); i++){
      if (beg1(a, ag[i]))return true;
    }
    return false;
  }
  
  public static boolean beg1(Object a, Object x){
    if (strp(a)){
      if (strp(x)){
        int lx = len(x);
        int la = len(a);
        if (lx > la)return false;
        for (int i = 0; i < lx; i++){
          if (!is(ref(a, i), ref(x, i)))return false;
        }
        return true;
      }
      return pos(x, a) == 0;
    }
    if (arrp(a))return is(fst(a), x);
    throw err("beg1", "Can't find if a = $1 begs with x = $2", a, x);
  }
  
  public static boolean end(Object a, Object... ag){
    for (int i = 0; i < len(ag); i++){
      if (end1(a, ag[i]))return true;
    }
    return false;
  }
  
  public static boolean end1(Object a, Object x){
    if (strp(a)){
      if (strp(x)){
        int lx = len(x);
        int la = len(a);
        if (lx > la)return false;
        for (int i = la-1, j = lx-1; j >= 0; i--, j--){
          if (!is(ref(a, i), ref(x, j)))return false;
        }
        return true;
      }
    }
    if (arrp(a))return is(las(a), x);
    throw err("end1", "Can't find if a = $1 ends with x = $2", a, x);
  }
  
  public static boolean bnd(Object a, Object x, Object y){
    return beg1(a, x) && end1(a, y);
  }
  
  ////// Imperative //////
  
  //// Each ////
  
  //// Whole ////
  
  public static Object clr(Object a){
    if (arrp(a)){
      if (lrrp(a)){
        ((ArrayList) a).clear();
        return a;
      }
    }
    if (lisp(a))return l(a).clr();
    if (tabp(a))return t(a).clr();
    throw err("clr", "Can't clr a = $1", a);
  }
  
  //// Array ////
  
  @SuppressWarnings("unchecked")
  public static Object psh(Object x, Object a){
    if (lrrp(a)){
      ((ArrayList) a).add(x);
      return a;
    }
    throw err("psh", "Can't push x = $1 onto a = $2", x, a);
  }
  
  public static Object pop(Object a){
    if (lrrp(a))return ((ArrayList) a).remove(len(a)-1);
    throw err("pop", "Can't pop from a = $1", a);
  }
  
  @SuppressWarnings("unchecked")
  public static Object ush(Object x, Object a){
    if (lrrp(a)){
      ((ArrayList) a).add(0, x);
      return a;
    }
    throw err("ush", "Can't unshift x = $1 onto a = $2", x, a);
  }
  
  public static Object shf(Object a){
    if (lrrp(a))return ((ArrayList) a).remove(0);
    throw err("shf", "Can't shift from a = $1", a);
  }
  
  //// Other ////
  
  public static Object att(Object x, Object a){
    if (xrrp(a) || lrrp(a)){
      if (arrp(x)){
        for (int i = 0; i < len(x); i++){
          psh(ref(x, i), a);
        }
        return a;
      }
      return psh(x, a);
    }
    if (tabp(a)){
      if (tabp(x)){
        for (Object k : keys(x)){
          set(a, k, ref(x, k));
        }
        return a;
      }
    }
    throw err("att", "Can't attach x = $1 to a = $2", x, a);
  }
  
  ////// Table //////
  
  public static Object[] keys(Object a){
    return t(a).keys();
  }
  
  public static Object[] vals(Object a){
    return t(a).vals();
  }
  
  /* would be great to have a highest common superclass fn
  (like Greatest Common Divisor)
  that does the same as acls(apl("tools.$.array", <arr of objects>))
  maybe http://stackoverflow.com/questions/9797212/finding-the-nearest-common-superclass-or-superinterface-of-a-collection-of-cla
  */
  
  ////// String //////
  
  public static String str(Object... a){
    return joi(a);
  }
  
  public static String low(Object a){
    return s(a).toLowerCase();
  }
  
  public static String upp(Object a){
    return s(a).toUpperCase();
  }
  
  public static String stf(Object a, Object... args){
    if (strp(a)){
      String[] x = new String[len(args)];
      String[] y = new String[len(args)];
      for (int i = 0; i < len(args); i++){
        x[i] = "$" + (i+1);
        y[i] = dsp(args[i]);
      }
      return s(rpl(x, y, a));
    }
    return dsp(a);
  }
  
  ////// Number //////
  
  public static int sum(int... a){
    int s = 0; int orig;
    for (int x : a){
      orig = s;
      s += x;
      if (x >= 0){
        if (s < orig)s = mxi();
      } else {
        if (s > orig)s = mni();
      }
    }
    return s;
  }
  
  public static int[] madd(int[] a, int[] b){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(a); i++){
      r[i] = a[i] + b[i];
    }
    return r;
  }
  
  public static int mxi(){return Integer.MAX_VALUE;}
  public static int mni(){return Integer.MIN_VALUE;}
  
  public static int maxint(){return mxi();}
  public static int minint(){return mni();}
  
  public static int pmax(int[] a){
    if (len(a) == 0)return -1;
    int m = 0;
    for (int i = 1; i < len(a); i++){
      if (a[i] > a[m])m = i;
    }
    return m;
  }
  
  public static int max(int... a){
    if (len(a) == 0)return 0;
    return a[pmax(a)];
  }
  
  public static int pmin(int[] a){
    if (len(a) == 0)return -1;
    int m = 0;
    for (int i = 1; i < len(a); i++){
      if (a[i] < a[m])m = i;
    }
    return m;
  }
  
  public static int min(int... a){
    if (len(a) == 0)return 0;
    return a[pmin(a)];
  }
  
  public static int abs(int a){
    return (a < 0)?-a:a;
  }
  
  public static int[] mabs(int[] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = abs(a[i]);
    }
    return r;
  }
  
  public static int sgn(int a){
    if (a > 0)return 1;
    if (a < 0)return -1;
    return 0;
  }
  
  public static int btw(int a, int min, int max){
    if (max < min)throw err("btw", "min = $1 can't be > max = $2", min, max);
    if (a < min)return min;
    if (a > max)return max;
    return a;
  }
  
  public static boolean btwp(int a, int min, int max){
    return a >= min && a <= max;
  }
  
  public static int[] mneg(int[] a){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = -a[i];
    }
    return r;
  }
  
  public static boolean posp(int a){
    return a > 0;
  }
  
  public static boolean negp(int a){
    return a < 0;
  }
  
  public static boolean[] mposp(int[] a){
    boolean[] r = new boolean[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = posp(a[i]);
    }
    return r;
  }
  
  public static int[] sums(int n, int[] a){
    int[] r = new int[len(a)+1];
    r[0] = n;
    for (int i = 0; i < len(a); i++){
      r[i+1] = r[i] + a[i];
    }
    return r;
  }
  
  public static int lef(int a, int b){
    return (a < b)?b-a:0;
  }
  
  public static int[] lef(int[] a, int[] b){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = lef(a[i], b[i]);
    }
    return r;
  }
  
  public static int[] lef(int[] a, int b){
    int[] r = new int[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = lef(a[i], b);
    }
    return r;
  }
  
  public static int[] lef(int a, int[] b){
    int[] r = new int[len(b)];
    for (int i = 0; i < len(r); i++){
      r[i] = lef(a, b[i]);
    }
    return r;
  }
  
  public static int[] nlef(int[] a, int[] b){
    return mneg(lef(b, a));
  }
  
  public static int[] nlef(int[] a, int b){
    return mneg(lef(b, a));
  }
  
  public static int[] nlef(int a, int[] b){
    return mneg(lef(b, a));
  }
  
  public static int nlef(int a, int b){
    return -lef(b, a);
  }
  
  public static int[] dequ(int n, int[] a, int[] mn, int[] mx){
    if (n == 0)return a;
    int[] left = (n > 0)?lef(a, mx):nlef(a, mn);
    boolean[] p = mposp(mabs(left));
    if (all(false, p))return a;
    int next = sgn(n)*min(cut(0, mabs(left)));
    if (abs(next) < abs(n)){
      int dx = next*cnt(true, p);
      if (abs(dx) < abs(n)){
        return dequ(n-dx, dequ(dx, a, p), mn, mx);
      }
    }
    return dequ(n, a, p);
  }
  
  public static int[] dequ(int n, int[] a, boolean[] p){
    int num = cnt(true, p);
    int quo = n / num;
    int rm = n % num;
    int[] r = new int[len(a)];
    for (int i = 0; i < len(r); i++){
      r[i] = a[i];
      if (p[i]){
        r[i] += quo+sgn(rm);
        rm -= sgn(rm);
      }
    }
    return r;
  }
  
  public static int[] dequ(int n, int[] a){
    return dequ(n, a, nof(len(a), a(true)));
  }
  
  // proportional
  public static int[] dpro(int n, int[] a){
    return madd(pro(n, a), a);
  }
  
  public static int[] pro(int n, int[] a){
    if (len(a) == 0)return new int[0];
    int tot = sum(a);
    int[] quos = new int[len(a)];
    int[] rems = new int[len(a)];
    for (int i = 0; i < len(a); i++){
      quos[i] = n*a[i] / tot;
      rems[i] = n*a[i] % tot;
    }
    int ext = n - sum(quos);
    while (ext > 0){
      int m = pmax(rems);
      quos[m]++;
      rems[m] = 0;
      ext--;
    }
    return quos;
  }
  
  ////// List //////
  
  //// Basic ////
  
  public static Cons nil(){
    return new Cons();
  }
  
  public static Cons cns(Object a, Object b){
    return new Cons(a, b);
  }
  
  public static Object car(Object a){
    return l(a).car();
  }
  
  public static Object cdr(Object a){
    return l(a).cdr();
  }
  
  public static Object scar(Object a, Object x){
    return l(a).scar(x);
  }
  
  public static Object scdr(Object a, Object x){
    return l(a).scdr(x);
  }
  
  //// car and cdr extensions ////
  
  public static Object caar(Object a){return car(car(a));}
  public static Object cadr(Object a){return car(cdr(a));}
  public static Object cdar(Object a){return cdr(car(a));}
  public static Object cddr(Object a){return cdr(cdr(a));}
  
  ////// Regex //////
  
  ////// File //////
  
  public static File fil(String a){
    return fil(a, $.class);
  }
  
  public static File fil(String a, Class c){
    try {
      URL u = c.getResource(a);
      if (nulp(u))throw err("fil", "File a = $1 not found", a);
      return new File(u.toURI());
    } catch (Exception e){
      throw err("fil", "Can't open fil a = $1 due to e = $2", a, e);
    }
  }
  
  public static String get(String a){
    return get(fil(a));
  }
  
  public static String get(File a){
    BufferedReader b = null;
    try {
      b = new BufferedReader(new FileReader(a));
      String s = "";
      int c;
      while ((c = b.read()) != -1)s += (char) c;
      b.close();
      return s;
    } catch (IOException e1){
      try {
        if (!nulp(b))b.close();
        throw err("get", "Can't read a = $1 due to $2", a, e1);
      } catch (IOException e2){
        throw err("get", "Can't read a = $1 due to $2", a, e2);
      }
    }
  }
  
  public static String[] lns(String a){
    return lns(fil(a));
  }
  
  public static String[] lns(File a){
    BufferedReader b = null;
    try {
      b = new BufferedReader(new FileReader(a));
      String[] r = new String[0];
      String s;
      while (!nulp(s = b.readLine())){
        r = tai(r, s);
      }
      b.close();
      return r;
    } catch (IOException e1){
      try {
        if (!nulp(b))b.close();
        throw err("lns", "Can't read a = $1 due to $2", a, e1);
      } catch (IOException e2){
        throw err("lns", "Can't read a = $1 due to $2", a, e2);
      }
    }
  }
  
  public static Object wri(String a, Object x){
    return wri(fil(a), x);
  }
  
  public static Object wri(File a, Object x){
    BufferedWriter b = null;
    try {
      b = new BufferedWriter(new FileWriter(a));
      if (strp(x)){
        b.write(s(x));
        b.close();
        return x;
      }
      if (arrp(x)){
        String[] r = (String[]) x;
        if (len(r) > 0){
          b.write(r[0]);
          for (int i = 1; i < len(r); i++){
            b.newLine();
            b.write(r[i]);
          }
        }
        b.close();
        return x;
      }
      throw err("wri", "Can't write x = $1 to a = $2", x, a);
    } catch (IOException e1){
      try {
        if (!nulp(b))b.close();
        throw err("wri", "Can't write x = $1 to a = $2 due to $3", x, a, e1);
      } catch (IOException e2){
        throw err("wri", "Can't write x = $1 to a = $2 due to $3", x, a, e2);
      }
    }
  }
  
  ////// Time //////
  
  public static void pau(int mil){
    try {
      Thread.sleep(mil);
    } catch (InterruptedException e){}
  }
  
  public static Date dat(){
    return new Date();
  }
  
  ////// Random //////
  
  public static int rnd(int min, int max){
    Random r = new Random();
    return r.nextInt(max-min+1)+min;
  }
  
  public static int rand(int min, int max){return rnd(min, max);}
  
  ////// Error //////
  
  public static class RE extends RuntimeException {
    public RE(String a){
      super(a);
    }
  }
  
  public static RE err(String nm, String a, Object... args){
    return new RE("Error: " + nm + ": " + stf(a, args));
  }
  
  public static StackTraceElement[] sta(){
    return sli(new Throwable().getStackTrace(), 1);
  }
  
  public static void tra(){
    psta();
    exit();
  }
  
  public static void psta(int n){
    for (Object e : or(fstn(n, sli(sta(), 1)))){
      prn("-> $1", e);
    }
  }
  
  public static void psta(){
    for (Object e : sli(sta(), 1)){
      prn("-> $1", e);
    }
  }
  
  ////// Other //////
  
  public static Object cnul(Object a, Object x){
    return nulp(a)?x:a;
  }
  
  public static void exit(){
    System.exit(0);
  }
  
  
}
