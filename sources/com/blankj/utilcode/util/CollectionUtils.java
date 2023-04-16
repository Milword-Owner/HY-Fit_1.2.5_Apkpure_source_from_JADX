package com.blankj.utilcode.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class CollectionUtils {

    public interface Closure<E> {
        void execute(int i, E e);
    }

    public interface Predicate<E> {
        boolean evaluate(E e);
    }

    public interface Transformer<E1, E2> {
        E2 transform(E1 e1);
    }

    private CollectionUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @SafeVarargs
    public static <E> List<E> newUnmodifiableList(E... eArr) {
        return Collections.unmodifiableList(newArrayList(eArr));
    }

    @SafeVarargs
    public static <E> List<E> newUnmodifiableListNotNull(E... eArr) {
        return Collections.unmodifiableList(newArrayListNotNull(eArr));
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E add : eArr) {
                arrayList.add(add);
            }
        }
        return arrayList;
    }

    @SafeVarargs
    public static <E> ArrayList<E> newArrayListNotNull(E... eArr) {
        ArrayList<E> arrayList = new ArrayList<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E e : eArr) {
                if (e != null) {
                    arrayList.add(e);
                }
            }
        }
        return arrayList;
    }

    @SafeVarargs
    public static <E> LinkedList<E> newLinkedList(E... eArr) {
        LinkedList<E> linkedList = new LinkedList<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E add : eArr) {
                linkedList.add(add);
            }
        }
        return linkedList;
    }

    @SafeVarargs
    public static <E> LinkedList<E> newLinkedListNotNull(E... eArr) {
        LinkedList<E> linkedList = new LinkedList<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E e : eArr) {
                if (e != null) {
                    linkedList.add(e);
                }
            }
        }
        return linkedList;
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSet = new HashSet<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E add : eArr) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    @SafeVarargs
    public static <E> HashSet<E> newHashSetNotNull(E... eArr) {
        HashSet<E> hashSet = new HashSet<>();
        if (!(eArr == null || eArr.length == 0)) {
            for (E e : eArr) {
                if (e != null) {
                    hashSet.add(e);
                }
            }
        }
        return hashSet;
    }

    @SafeVarargs
    public static <E> TreeSet<E> newTreeSet(Comparator<E> comparator, E... eArr) {
        TreeSet<E> treeSet = new TreeSet<>(comparator);
        if (!(eArr == null || eArr.length == 0)) {
            for (E add : eArr) {
                treeSet.add(add);
            }
        }
        return treeSet;
    }

    @SafeVarargs
    public static <E> TreeSet<E> newTreeSetNotNull(Comparator<E> comparator, E... eArr) {
        TreeSet<E> treeSet = new TreeSet<>(comparator);
        if (!(eArr == null || eArr.length == 0)) {
            for (E e : eArr) {
                if (e != null) {
                    treeSet.add(e);
                }
            }
        }
        return treeSet;
    }

    public static Collection newSynchronizedCollection(Collection collection) {
        return Collections.synchronizedCollection(collection);
    }

    public static Collection newUnmodifiableCollection(Collection collection) {
        return Collections.unmodifiableCollection(collection);
    }

    public static Collection union(Collection collection, Collection collection2) {
        if (collection == null && collection2 == null) {
            return new ArrayList();
        }
        if (collection == null) {
            return new ArrayList(collection2);
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object next : hashSet) {
            int max = Math.max(getFreq(next, cardinalityMap), getFreq(next, cardinalityMap2));
            for (int i = 0; i < max; i++) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static Collection intersection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object next : hashSet) {
            int min = Math.min(getFreq(next, cardinalityMap), getFreq(next, cardinalityMap2));
            for (int i = 0; i < min; i++) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private static int getFreq(Object obj, Map map) {
        Integer num = (Integer) map.get(obj);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static Collection disjunction(Collection collection, Collection collection2) {
        if (collection == null && collection2 == null) {
            return new ArrayList();
        }
        if (collection == null) {
            return new ArrayList(collection2);
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        HashSet hashSet = new HashSet(collection);
        hashSet.addAll(collection2);
        for (Object next : hashSet) {
            int max = Math.max(getFreq(next, cardinalityMap), getFreq(next, cardinalityMap2)) - Math.min(getFreq(next, cardinalityMap), getFreq(next, cardinalityMap2));
            for (int i = 0; i < max; i++) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static Collection subtract(Collection collection, Collection collection2) {
        if (collection == null) {
            return new ArrayList();
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList(collection);
        for (Object remove : collection2) {
            arrayList.remove(remove);
        }
        return arrayList;
    }

    public static boolean containsAny(Collection collection, Collection collection2) {
        if (!(collection == null || collection2 == null)) {
            if (collection.size() < collection2.size()) {
                for (Object contains : collection) {
                    if (collection2.contains(contains)) {
                        return true;
                    }
                }
            } else {
                for (Object contains2 : collection2) {
                    if (collection.contains(contains2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static Map<Object, Integer> getCardinalityMap(Collection collection) {
        HashMap hashMap = new HashMap();
        if (collection == null) {
            return hashMap;
        }
        for (Object next : collection) {
            Integer num = (Integer) hashMap.get(next);
            if (num == null) {
                hashMap.put(next, 1);
            } else {
                hashMap.put(next, Integer.valueOf(num.intValue() + 1));
            }
        }
        return hashMap;
    }

    public static boolean isSubCollection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null) {
            return false;
        }
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        for (Object next : collection) {
            if (getFreq(next, cardinalityMap) > getFreq(next, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isProperSubCollection(Collection collection, Collection collection2) {
        return collection != null && collection2 != null && collection.size() < collection2.size() && isSubCollection(collection, collection2);
    }

    public static boolean isEqualCollection(Collection collection, Collection collection2) {
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        Map<Object, Integer> cardinalityMap = getCardinalityMap(collection);
        Map<Object, Integer> cardinalityMap2 = getCardinalityMap(collection2);
        if (cardinalityMap.size() != cardinalityMap2.size()) {
            return false;
        }
        for (Object next : cardinalityMap.keySet()) {
            if (getFreq(next, cardinalityMap) != getFreq(next, cardinalityMap2)) {
                return false;
            }
        }
        return true;
    }

    public static <E> int cardinality(E e, Collection<E> collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        if (collection instanceof Set) {
            return collection.contains(e) ? 1 : 0;
        }
        if (e == null) {
            for (E e2 : collection) {
                if (e2 == null) {
                    i++;
                }
            }
        } else {
            for (E equals : collection) {
                if (e.equals(equals)) {
                    i++;
                }
            }
        }
        return i;
    }

    public static <E> E find(Collection<E> collection, Predicate<E> predicate) {
        if (!(collection == null || predicate == null)) {
            for (E next : collection) {
                if (predicate.evaluate(next)) {
                    return next;
                }
            }
        }
        return null;
    }

    public static <E> void forAllDo(Collection<E> collection, Closure<E> closure) {
        if (collection != null && closure != null) {
            int i = 0;
            for (E execute : collection) {
                closure.execute(i, execute);
                i++;
            }
        }
    }

    public static <E> void filter(Collection<E> collection, Predicate<E> predicate) {
        if (collection != null && predicate != null) {
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                if (!predicate.evaluate(it.next())) {
                    it.remove();
                }
            }
        }
    }

    public static <E> Collection<E> select(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (E next : collection) {
            if (predicate.evaluate(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> selectRejected(Collection<E> collection, Predicate<E> predicate) {
        if (collection == null || predicate == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (E next : collection) {
            if (!predicate.evaluate(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static <E1, E2> void transform(Collection<E1> collection, Transformer<E1, E2> transformer) {
        if (collection != null && transformer != null) {
            if (collection instanceof List) {
                ListIterator listIterator = ((List) collection).listIterator();
                while (listIterator.hasNext()) {
                    listIterator.set(transformer.transform(listIterator.next()));
                }
                return;
            }
            Collection<E2> collect = collect(collection, transformer);
            collection.clear();
            collection.addAll(collect);
        }
    }

    public static <E1, E2> Collection<E2> collect(Collection<E1> collection, Transformer<E1, E2> transformer) {
        ArrayList arrayList = new ArrayList();
        if (!(collection == null || transformer == null)) {
            for (E1 transform : collection) {
                arrayList.add(transformer.transform(transform));
            }
        }
        return arrayList;
    }

    public static <E> int countMatches(Collection<E> collection, Predicate<E> predicate) {
        int i = 0;
        if (!(collection == null || predicate == null)) {
            for (E evaluate : collection) {
                if (predicate.evaluate(evaluate)) {
                    i++;
                }
            }
        }
        return i;
    }

    public static <E> boolean exists(Collection<E> collection, Predicate<E> predicate) {
        if (!(collection == null || predicate == null)) {
            for (E evaluate : collection) {
                if (predicate.evaluate(evaluate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static <E> boolean addIgnoreNull(Collection<E> collection, E e) {
        return (collection == null || e == null || !collection.add(e)) ? false : true;
    }

    public static <E> void addAll(Collection<E> collection, Iterator<E> it) {
        if (collection != null && it != null) {
            while (it.hasNext()) {
                collection.add(it.next());
            }
        }
    }

    public static <E> void addAll(Collection<E> collection, Enumeration<E> enumeration) {
        if (collection != null && enumeration != null) {
            while (enumeration.hasMoreElements()) {
                collection.add(enumeration.nextElement());
            }
        }
    }

    public static <E> void addAll(Collection<E> collection, E[] eArr) {
        if (collection != null && eArr != null && eArr.length != 0) {
            collection.addAll(Arrays.asList(eArr));
        }
    }

    public static Object get(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
        } else if (obj instanceof Map) {
            return get(((Map) obj).entrySet().iterator(), i);
        } else {
            if (obj instanceof List) {
                return ((List) obj).get(i);
            }
            if (obj instanceof Object[]) {
                return ((Object[]) obj)[i];
            }
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                while (it.hasNext()) {
                    i--;
                    if (i == -1) {
                        return it.next();
                    }
                    it.next();
                }
                throw new IndexOutOfBoundsException("Entry does not exist: " + i);
            } else if (obj instanceof Collection) {
                return get(((Collection) obj).iterator(), i);
            } else {
                if (obj instanceof Enumeration) {
                    Enumeration enumeration = (Enumeration) obj;
                    while (enumeration.hasMoreElements()) {
                        i--;
                        if (i == -1) {
                            return enumeration.nextElement();
                        }
                        enumeration.nextElement();
                    }
                    throw new IndexOutOfBoundsException("Entry does not exist: " + i);
                }
                try {
                    return Array.get(obj, i);
                } catch (IllegalArgumentException unused) {
                    throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
                }
            }
        }
    }

    public static int size(Object obj) {
        int i = 0;
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Map) {
            return ((Map) obj).size();
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).size();
        }
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length;
        }
        if (obj instanceof Iterator) {
            Iterator it = (Iterator) obj;
            while (it.hasNext()) {
                i++;
                it.next();
            }
        } else if (obj instanceof Enumeration) {
            Enumeration enumeration = (Enumeration) obj;
            while (enumeration.hasMoreElements()) {
                i++;
                enumeration.nextElement();
            }
        } else {
            try {
                return Array.getLength(obj);
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
            }
        }
        return i;
    }

    public static boolean sizeIsEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        if (obj instanceof Object[]) {
            if (((Object[]) obj).length == 0) {
                return true;
            }
            return false;
        } else if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        } else {
            if (obj instanceof Enumeration) {
                return !((Enumeration) obj).hasMoreElements();
            }
            try {
                if (Array.getLength(obj) == 0) {
                    return true;
                }
                return false;
            } catch (IllegalArgumentException unused) {
                throw new IllegalArgumentException("Unsupported object type: " + obj.getClass().getName());
            }
        }
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static <E> Collection<E> retainAll(Collection<E> collection, Collection<E> collection2) {
        if (collection == null || collection2 == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (E next : collection) {
            if (collection2.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static <E> Collection<E> removeAll(Collection<E> collection, Collection<E> collection2) {
        if (collection == null) {
            return new ArrayList();
        }
        if (collection2 == null) {
            return new ArrayList(collection);
        }
        ArrayList arrayList = new ArrayList();
        for (E next : collection) {
            if (!collection2.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public static <T> void shuffle(List<T> list) {
        Collections.shuffle(list);
    }

    public static String toString(Collection collection) {
        return collection == null ? "null" : collection.toString();
    }
}
