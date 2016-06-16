import tools.$;

import java.util.*;

public class Test {
  public static void main(String[] args){
    try {
      if (1 == 2){
        throw new NewException("test");
      } else if (1 == 2) {
        throw new NewException2("test");
      } else {
        throw new Exception("test");
      }
    } catch (NewException e){
      $.prn(e);
    } catch (NewException2 e){
      $.prn(e);
    } catch (Exception e){
      $.prn(e);
    }
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
