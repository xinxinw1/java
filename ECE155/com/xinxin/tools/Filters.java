package com.xinxin.tools;

/**
 * How to use:
 *   Filters.LowPass lowpass = new Filters.LowPass(0.5f);
 *   Filters.LowPass highpass = new Filters.HighPass(0.5f);
 *   float[][] items = {
 *       {1, 2, 3},
 *       {4, 5, 6},
 *       {1, 1, 2}
 *   }
 *   for (items : float[] item){
 *       $.prn(lowpass.newPoint(item));
 *       $.prn(highpass.newPoint(item));
 *   }
 *
 * Setting a = 1 for both makes the output unchanged
 */
public class Filters {
    static class LowPass {
        private double curr;
        private double a;
        private boolean init;
        
        public LowPass(double a){
            this.init = false;
            this.a = a;
        }

        public double next(double next){
            if (!init){
                curr = next;
                init = true;
            } else {
                curr = curr + a*(next-curr);
            }
            return curr;
        }
    }

    static class HighPass {
        private double currOut;
        private double prevIn;
        private double a;
        private boolean initCurr;
        
        public HighPass(double a){
            this.initCurr = false;
            this.a = a;
        }

        public double next(double next){
            if (!initCurr){
                currOut = next;
                initCurr = true;
            } else {
                currOut = a*(currOut + next - prevIn);
            }
            prevIn = next;
            return currOut;
        }
    }
}
/*
public class Filters {
    interface Filter {
        public float next(float next);
    }

    static class LowPass implements Filter {
        private float curr;
        private float a;
        private boolean init;

        public LowPass(float a){
            this.init = false;
            this.a = a;
        }

        public float next(float next){
            if (!init){
                curr = next;
                init = true;
            } else {
                curr = curr + a*(next-curr);
            }
            return curr;
        }
    }

    static class HighPass implements Filter {
        private float currOut;
        private float prevIn;
        private float a;
        private boolean initCurr;

        public HighPass(float a){
            this.initCurr = false;
            this.a = a;
        }

        public float next(float next){
            if (!initCurr){
                currOut = next;
                initCurr = true;
            } else {
                currOut = a*(currOut + next - prevIn);
            }
            prevIn = next;
            return currOut;
        }
    }

    interface FilterCreator {
        public Filter create();
    }

    static class FilterArray {
        private FilterCreator f;
        private Filter[] filters;
        private float[] curr;

        public FilterArray(FilterCreator f){
            this.f = f;
        }

        public float[] next(float[] next){
            if (filters == null){
                filters = new Filter[next.length];
                curr = new float[next.length];
                for (int i = 0; i < filters.length; i++){
                    filters[i] = f.create();
                }
            }
            for (int i = 0; i < filters.length; i++){
                curr[i] = filters[i].next(next[i]);
            }
            return curr;
        }
    }

    public static FilterArray makeLowPassFilterArray(final float a){
        return new FilterArray(new FilterCreator(){
            public Filter create(){
                return new LowPass(a);
            }
        });
    }

    public static FilterArray makeHighPassFilterArray(final float a){
        return new FilterArray(new FilterCreator(){
            public Filter create(){
                return new HighPass(a);
            }
        });
    }
}
*/
