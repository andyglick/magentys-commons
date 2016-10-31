package io.magentys.commons.adapt.string;

import java.util.List;
import java.util.Map;

public final class StringAdapters {
    public interface StringAdapter<T> {
        String toString(T t);

        T fromString(String s);
    }

    public static StringAdapter<String> string() {
        return new IdentityAdapter();
    }

    public static StringAdapter<List<String>> csv() {
        return new CsvAdapter();
    }

    public static StringAdapter<Integer> integer() {
        return new IntegerAdapter();
    }

    public static <X> ListAdapter<X> csvOf(final StringAdapter<X> ofType) {
        return new ListAdapter<X>(csv(), ofType);
    }

    public static <X> ListAdapter<X> listOf(final StringAdapter<List<String>> strings, final StringAdapter<X> ofType) {
        return new ListAdapter<X>(strings, ofType);
    }

    public static StringAdapter<Map<String, String>> map() {
        return new MapAdapter<String>(csv(), string());
    }

    public static <X> StringAdapter<Map<String, X>> mapOf(final StringAdapter<X> values) {
        return new MapAdapter<X>(csv(), values);
    }

    public static <T> StringAdapter<T> jsonOf(final Class<T> type) {
        return new JsonAdapter<T>(type);
    }

    public static <T> StringAdapter<List<T>> jsonListOf(final Class<T> type) {
        return JsonListAdapter.of(type);
    }
}
