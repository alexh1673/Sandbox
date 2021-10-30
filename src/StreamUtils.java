import java.util.Collection;
import java.util.stream.Collectors;
import java.util.*;

public class StreamUtils {

    /**
     * @param strings: the input collection of <code>String</code>s.
     * @return a collection of those <code>String</code>s in the input collection

    that start with a capital letter.

     */
    public static Collection<String> capitalized(Collection<String> strings)
    {
        return strings.stream().filter(s -> s.charAt(0) > 64 && s.charAt(0) < 91).collect(Collectors.toList());
    }
    //3 methods called, including stream


    /**
     * Find and return the longest <code>String</code> in a given collection of <code>String</code>s.
     *
     * @param strings: the given collection of <code>String</code>s.
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
    If <code>true</code>, then the element encountered earlier in
     * the iteration is returned, otherwise the later element is returned.
     * @return the longest <code>String</code> in the given collection,
     * where ties are broken based on <code>from_start</code>.
     */
    public static String longest(Collection<String> strings, boolean from_start) throws NullPointerException
    {
        return strings.stream()
                .reduce("",(a, b)
                        ->  a == null ? b : b == null ? a :
                                a.length() > b.length()
                        ? a : b.length() == a.length()
                        ? from_start ? a : b : b
                        );
    }

    /**
     * Find and return the least element from a collection of given elements that are comparable.
     *
     * @param items: the given collection of elements
     * @param from_start: a <code>boolean</code> flag that decides how ties are broken.
     * If <code>true</code>, the element encountered earlier in the
     * iteration is returned, otherwise the later element is returned.
     * @param <T>: the type parameter of the collection (i.e., the items are all of type T).
     * @return the least element in <code>items</code>, where ties are
     * broken based on <code>from_start</code>.
     */
    public static <T extends Comparable<T>> T least(Collection<T> items, boolean from_start) throws NullPointerException{
        if(items.isEmpty())
            throw new NullPointerException("empty list");
        return items.stream().reduce(items.stream().findFirst().get(),(a, b)
                -> a == null ? b : b == null ? a :
                    a.compareTo(b) == 1
                ? b : a.compareTo(b) == 0
                ? from_start ? b : a : a);
    }

    /**
     * Flattens a map to a stream of <code>String</code>s, where each element in the list
     * is formatted as "key -> value" (i.e., each key-value pair is converted to a string
     * with this format).
     *
     * @param aMap the specified input map.
     * @param <K> the type parameter of keys in <code>aMap</code>.
     * @param <V> the type parameter of values in <code>aMap</code>.
     * @return the flattened list representation of <code>aMap</code>.
     */
    public static <K, V> List<String> flatten(Map<K, V> aMap)
    {
        return aMap.entrySet().stream().map(a -> a.getKey()+" -> "+a.getValue()).collect(Collectors.toList());
    }

    private static class Phone implements Comparable<Phone> {
        int    price;
        String brand;

        Phone(int price, String brand) {
            this.price = price;
            this.brand = brand;
        }

        static Phone of(int price, String brand) { return new Phone(price, brand); }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Phone)) return false;
            Phone phone = (Phone) o;
            return price == phone.price &&
                    brand.equals(phone.brand);
        }

        @Override public int hashCode() {
            return Objects.hash(price, brand);
        }

        @Override public int compareTo(Phone that) {
            return this.price - that.price;
        }

        @Override public String toString() { return String.format("Phone{price: %d; brand: %s}", price, brand); }
    }

    public static void main(String[] args)
    {

    }
}
