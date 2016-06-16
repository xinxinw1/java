import tools.$;

import java.util.*;
import java.io.*;

public class FileCopy {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
  
    FileInputStream in = null;
    FileOutputStream out = null;
    
    try {
      $.pr("Input file: ");
      in = new FileInputStream(s.nextLine());
      $.pr("Output file: ");
      out = new FileOutputStream(s.nextLine());
      
      int c;
      while ((c = in.read()) != -1){
        out.write(c);
      }
    } finally {
      if (in != null)in.close();
      if (out != null)out.close();
    }
  }
}
