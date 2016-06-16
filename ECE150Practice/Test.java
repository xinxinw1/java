import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args) throws NewException {
    $.prn(test2());
  }
  
  public static int test(){
    try {
      if (1 == 1){
        throw new NewException("test");
      } else if (1 == 2) {
        throw new NewException2("test");
      } else if (6 == 3){
        throw new Exception("test");
      }
      return 1;
    } catch (NewException e){
      $.prn(e);
      return 3;
    } catch (NewException2 e){
      $.prn(e);
      return 3;
    } catch (Exception e){
      $.prn(e);
      return 3;
    } finally {
      $.prn("here");
      //return 5;
    }
  }
  
  public static int test2() throws NewException {
    throw new NewException("hey");
  }
}

class NewException extends Exception {
  public NewException(String message){
    super(message);
  }
}

class NewException2 extends Exception {
  public NewException2(String message){
    super(message);
  }
}
