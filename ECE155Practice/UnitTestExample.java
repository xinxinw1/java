public class UnitTestExample {
  public static double methodUnderTest(double param1, double param2){
    if(param1 > 3.5){
      return param1 / param2;
    }
    else if(param1 > -4.6 && param1 < 3.5){
      return Math. log (param2);
    }
    else if(param1 <= -4.6){
      return Math. tan (param2);
    }
    else{
      return Double. NaN ;
    }
  }
}
