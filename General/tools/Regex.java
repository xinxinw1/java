/**
 * For a thorough tutorial on regex, see
 * http://www.regular-expressions.info/tutorial.html
 * 
 * For how to use regex in Java, see
 * http://www.regular-expressions.info/java.html
 * 
 * Usage:
 * $.prn($.has($.x("[cb]at"), "bat")) -> true
 * $.prn($.pss($.x("[cb]at"), "batcatbat")) -> [0, 3, 6]
 * $.prn($.rpl($.x("([0-9]+)([a-z]+)"), "($1*$2)", "3i+5x"))
 * -> "(3*i)+(5*x)"
 * 
**/

package tools;

import java.util.regex.Pattern; 
import java.util.regex.Matcher;

/*
Remember that $.rgx("\\\\") is one literal backslash in regex
and $.rgx("\\b") is a word boundary.
This is because \b is a string escape char
*/

public class Regex {
  private String s;
  private Pattern p;
  
  public Regex(String a){
    this.s = a;
    this.p = Pattern.compile(a);
  }
  
  public Matcher mtchr(String a){
    return p.matcher(a);
  }
  
  public String toString(){
    return "/" + s + "/";
  }
}