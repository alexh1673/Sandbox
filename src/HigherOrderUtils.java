import java.util.function.BiFunction;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HigherOrderUtils {

    static interface NamedBiFunction<T, T1, T2> extends BiFunction {
        String Name();
    }

    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String Name() {
            return "add";
        }

        @Override
        public Object apply(Object o, Object o2) {
            return (double)o + (double)o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String Name() {
            return "diff";
        }

        @Override
        public Object apply(Object o, Object o2) {
            return (double)o - (double)o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String Name() {
            return "mult";
        }

        @Override
        public Object apply(Object o, Object o2) {
            return (double)o * (double)o2;
        }
    };

    public static NamedBiFunction<Double, Double, Double> divide = new NamedBiFunction<Double, Double, Double>() {
        @Override
        public String Name() {
            return "div";
        }

        @Override
        public Object apply(Object o, Object o2) throws ArithmeticException{
            if((double)o2 == 0)
                throw new ArithmeticException("divide by zero");
            if((double)o / (double)o2 > 0)
            return Math.floor((double)o / (double)o2);
            else
                return Math.ceil((double)o / (double)o2);
        }
    };

    /**
     * Applies a given list of bifunctions -- functions that take two arguments of a certain type
     * and produce a single instance of that type -- to a list of arguments of that type. The
     * functions are applied in an iterative manner, and the result of each function is stored in
     * the list in an iterative manner as well, to be used by the next bifunction in the next
     * iteration. For example, given
     * List<Double> args = Arrays.asList(1d, 1d, 3d, 0d, 4d), and
     * List<NamedBiFunction<Double, Double, Double>> bfs = [add, multiply, add, divide],

     2

     CSE 216 : Programming Abstractions Homework III Submission Deadline: April 26, 2021
     * <code>zip(args, bfs)</code> will proceed iteratively as follows:
     * - index 0: the result of add(1,1) is stored in args[1] to yield args = [1,2,3,0,4]
     * - index 1: the result of multiply(2,3) is stored in args[2] to yield args = [1,2,6,0,4]
     * - index 2: the result of add(6,0) is stored in args[3] to yield args = [1,2,6,6,4]
     * - index 3: the result of divide(6,4) is stored in args[4] to yield args = [1,2,6,6,1]
     *
     * @param args: the arguments over which <code>bifunctions</code> will be applied.
     * @param bifunctions: the list of bifunctions that will be applied on <code>args</code>.
     * @param <T>: the type parameter of the arguments (e.g., Integer, Double)
     * @return the item in the last index of <code>args</code>, which has the final
     * result of all the bifunctions being applied in sequence.
     */
    public static <T> T zip(List<T> args, List<NamedBiFunction<T, T, T>> bifunctions) throws IllegalArgumentException,NullPointerException
    {
        if(args.size() == 0)
            throw new NullPointerException();

        if(bifunctions.size() < args.size())
                    IntStream.range(0,bifunctions.size()).
                    mapToObj(a -> args.set(a+1, (T)bifunctions.get(a).apply(args.get(a),args.get(a+1))))
                    .collect(Collectors.toList());
        else
            throw new IllegalArgumentException();
        return args.get(bifunctions.size());
    }


    public static class FunctionComposition<T,T1,T2>
    {
        BiFunction<Function,Function,Function> composition = new BiFunction<Function,Function,Function>() {
            @Override
            public Function apply(Function o, Function o2) {
                return o.andThen(o2);
            }
        };
    }


    public static void main(String[] args)
    {
        NamedBiFunction<Integer,String,String> a = new NamedBiFunction<Integer, String, String>() {
            @Override
            public String Name() {
                return null;
            }

            @Override
            public Object apply(Object o, Object o2) {
                return null;
            }
        };
    }

}
