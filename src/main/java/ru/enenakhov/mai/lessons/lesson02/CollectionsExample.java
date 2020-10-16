package ru.enenakhov.mai.lessons.lesson02;

import org.apache.log4j.Logger;

import java.util.*;

public class CollectionsExample {

    private static final Logger logger = Logger.getLogger(CollectionsExample.class.getName());

    public void example01() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        printCollection(list);
    }

    public void example02() {
        printCollection(Arrays.asList(1, 2, 3, 4, 5));
    }

    public void example03() {
        printCollection(Collections.singletonList(1));
    }

    public void example04() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1.1");
        map.put("2", "2.2");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            logger.info("Entry: " + entry.getKey() + " => " + entry.getValue());
        }

        logger.info("Get value by key 1 : " + map.get("1"));

        map.remove("1");

        logger.info("Remove element by key 1");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            logger.info("Entry: " + entry.getKey() + " => " + entry.getValue());
        }

        logger.info("==============");
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList("4", "3", "5", "2", "6"));
        printCollection(treeSet);
        logger.info("==============");

        Set<Integer> set = new HashSet<>();
        set.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4));
        printCollection(set);
    }

    private void printCollection(Iterable collection) {
        for (Object value : collection) {
            logger.info(value);
        }
    }

    public static void main(String[] args) {
        CollectionsExample example = new CollectionsExample();
        example.example04();
    }
}
