package me.codeboy.common.base.util;

import java.util.*;
import java.util.Map.Entry;

/**
 * 按照键值对中的值排序的TreeMap
 *
 * @param <K>
 *         键值对对应键
 * @param <V>
 *         键值对对应值
 * @author Yuedong Li
 */
public class CBMapSortUtil<K, V> {

    private final int GROW_UP = 1; //升序
    private final int GROW_DOWN = -1; //降序

    /**
     * 按照Map中key进行升序排列
     *
     * @param sourceMap
     *         要排序的map
     * @return 排序后的map
     */
    public Map<K, V> getMapSortByValueUp(Map<K, V> sourceMap) {
        return sortByValue(sourceMap, GROW_UP);
    }

    /**
     * 按照Map中key进行降序排列
     *
     * @param sourceMap
     *         要排序的map
     * @return 排序后的map
     */
    public Map<K, V> getMapSortByValueDown(Map<K, V> sourceMap) {
        return sortByValue(sourceMap, GROW_DOWN);
    }

    /**
     * 按照Map中value进行升序排列
     *
     * @param sourceMap
     *         要排序的map
     * @return 排序后的map
     */
    public Map<K, V> getMapSortByKeyUp(Map<K, V> sourceMap) {
        return sortByKey(sourceMap, GROW_UP);
    }

    /**
     * 按照Map中value进行降序排列
     *
     * @param sourceMap
     *         要排序的map
     * @return 排序后的map
     */
    public Map<K, V> getMapSortByKeyDown(Map<K, V> sourceMap) {
        return sortByKey(sourceMap, GROW_DOWN);
    }

    /**
     * 根据map中key进行排序
     *
     * @param sourceMap
     *         要排序的map
     * @param growthPattern
     *         排序规则，升序或者降序
     * @return 排序后的map
     */
    private Map<K, V> sortByKey(Map<K, V> sourceMap, final int growthPattern) {
        List<Entry<K, V>> list = new ArrayList<Entry<K, V>>(
                sourceMap.entrySet());
        Collections.sort(list, new Comparator<Entry<K, V>>() {

            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                K k1 = o1.getKey();
                K k2 = o2.getKey();
                return compareValue(k1, k2) * growthPattern;
            }
        });

        Map<K, V> desMap = new LinkedHashMap<K, V>();
        for (int i = 0; i < list.size(); i++) {
            Entry<K, V> item = list.get(i);
            desMap.put(item.getKey(), item.getValue());
        }
        return desMap;
    }

    /**
     * 根据map中value进行排序
     *
     * @param sourceMap
     *         要排序的map
     * @param growthPattern
     *         排序规则，升序或者降序
     * @return 排序后的map
     */
    private Map<K, V> sortByValue(Map<K, V> sourceMap, final int growthPattern) {
        List<Entry<K, V>> list = new ArrayList<Entry<K, V>>(
                sourceMap.entrySet());
        Collections.sort(list, new Comparator<Entry<K, V>>() {

            @Override
            public int compare(Entry<K, V> o1, Entry<K, V> o2) {
                V v1 = o1.getValue();
                V v2 = o2.getValue();
                return compareValue(v1, v2) * growthPattern;
            }
        });

        Map<K, V> desMap = new LinkedHashMap<K, V>();
        for (int i = 0; i < list.size(); i++) {
            Entry<K, V> item = list.get(i);
            desMap.put(item.getKey(), item.getValue());
        }
        return desMap;
    }

    /**
     * 对键值对中值进行比较,只针对6种基本类型，short int long float double String，其他的一律返回相等
     *
     * @param v1
     *         值1
     * @param v2
     *         值2
     * @return 比较结果
     */
    private int compareValue(Object v1, Object v2) {

        if (v1 instanceof Integer)
            return Integer.compare((Integer) v1, (Integer) v2);

        if (v1 instanceof Float)
            return Float.compare((Float) v1, (Float) v2);

        if (v1 instanceof Double)
            return Double.compare((Double) v1, (Double) v2);

        if (v1 instanceof Long)
            return Long.compare((Long) v1, (Long) v2);

        if (v1 instanceof Character)
            return Character.compare((Character) v1, (Character) v2);

        if (v1 instanceof Short)
            return Short.compare((Short) v1, (Short) v2);

        return 0;
    }
}