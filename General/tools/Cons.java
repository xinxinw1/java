/**
 * A cons cell is a pair of two items. The first item is called 
 * the car, and the second item is called the cdr. These cells 
 * combine together to form a linked list.
 * 
 * Let's denote a cons cell as (a . b) with a as the car and b
 * as the cdr. Let () be the empty cons cell, also known as "nil".
 * A linked list would be (a . (b . (c . ()))). Thus the car of
 * that list would be a, and the cdr would be (b . (c . ())).
 * 
 * Since writing out all the cons cells of a list takes a lot of
 * space, lists are usually written in a simpler form like (a b c).
 * If the cdr of the last link in the list is not nil, such as in
 * (a . (b . (c . d))), then the list is written as (a b c . d).
 * 
 * The names car and cdr are quite arbitrary as they originate as 
 * acronyms for hardware parts on 1950s computers. Nevertheless,
 * car and cdr have the benefit that compositions of the two can be
 * given similar names. For example, the car of the cdr of a list
 * can be called cadr.
 * 
 * Cons cells and linked lists are most prominently used in the
 * programming language called Lisp. For more about Lisp, see:
 * http://www.gigamonkeys.com/book/
 * and http://ycombinator.com/arc/tut.txt
 * 
 * This Cons class is an implementation of cons cells.
 * new Cons() represents nil
 * 
 * Usage:
 * Cons a = new Cons(1, 2);
 * $.prn(a) -> (1 . 2)
 * $.prn(a.car()) -> 1
 * $.prn(a.cdr()) -> 2
 * $.prn(a.scar(50)) -> (50 . 2)
 * $.prn(a.scdr(new Cons(1, new Cons()))) -> (50 1)
 * $.prn(a.clr()) -> ()
 * $.prn(a) -> ()
 * 
 * $.prn(a = $.cns(1, 2)) -> (1 . 2)
 * $.prn($.cdr(a, $.cns(3, 4))) -> (1 3 . 4)
 * $.prn($.cadr(a)) -> 3
 * $.prn($.nil()) -> ()
 * $.prn(a) -> (1 3 . 4)
 * $.prn($.clr(a)) -> ()
 * $.prn(a) -> ()
 * $.prn(a = $.li(1, 2, 3, 4, 5)) -> (1 2 3 4 5)
 * $.prn($.car($.cdr($.cdr(a)))) -> 3
 *
**/

package tools;

public class Cons {
  private Object a;
  private Object b;
  private boolean nil;
  
  public Cons(Object a, Object b){
    this.a = a;
    this.b = b;
  }
  
  public Cons(){
    a = null;
    b = null;
    nil = true;
  }
  
  public Object car(){
    if (nil)return new Cons();
    return a;
  }
  
  public Object cdr(){
    if (nil)return new Cons();
    return b;
  }
  
  public boolean nilp(){
    return nil;
  }
  
  // set car
  public Cons scar(Object a){
    this.a = a;
    if (nil){
      this.b = new Cons();
      nil = false;
    }
    return this;
  }
  
  public Cons scdr(Object b){
    this.b = b;
    if (nil){
      this.a = new Cons();
      nil = false;
    }
    return this;
  }
  
  public Cons clr(){
    a = null;
    b = null;
    nil = true;
    return this;
  }
  
  public String toString(){
    if (nil)return "()";
    if (b instanceof Cons && ((Cons) b).nilp())return "(" + a + ")";
    return "(" + a + " . " + b + ")";
  }
  
  public boolean equals(Object b){
    if (b == null)return false;
    if (b == this)return true;
    if (!(b instanceof Cons))return false;
    if (nil && ((Cons) b).nilp())return true;
    return false;
  }
}