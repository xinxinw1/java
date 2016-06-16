import tools.$;

import java.util.*;
import java.io.*;

public class FileCopy {
  public static void main(String[] args) throws IOException {
    Scanner s = new Scanner(System.in);
  
    BufferedReader in = null;
    PrintWriter out = null;
    
    try {
      $.pr("Input file: ");
      in = new BufferedReader(new FileReader(s.nextLine()));
      $.pr("Output file: ");
      out = new PrintWriter(new FileWriter(s.nextLine()));
      
      String l;
      while ((l = in.readLine()) != null){
        out.println(l);
      }
    } finally {
      if (in != null)in.close();
      if (out != null)out.close();
    }
  }
}
