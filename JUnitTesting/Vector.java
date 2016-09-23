public class Vector {
  public static double angleBetween(double ax, double ay, double bx, double by) {
    return Math.acos((ax*bx + ay*by)/Math.sqrt((ax*ax+ay*ay)*(bx*bx+by*by)));
  }
  
  public static float angleBetweenf(float ax, float ay, float bx, float by) {
    System.out.println(ax*bx + ay*by);
    System.out.println((ax*ax+ay*ay)*(bx*bx+by*by));
    System.out.println(Math.sqrt((ax*ax+ay*ay)*(bx*bx+by*by)));
    System.out.println((ax*bx + ay*by)/Math.sqrt((ax*ax+ay*ay)*(bx*bx+by*by)));
    return (float) Math.acos((ax*bx + ay*by)/Math.sqrt((ax*ax+ay*ay)*(bx*bx+by*by)));
  }
}
