package ru.enenakhov.mai.lessons.lesson08;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ManipulationStreamAPI {

    // filter - возвращает stream, в котором есть только элементы, соответствующие условию фильтра
    // count - возвращает количество элементов в стриме
    // collect - преобразует stream в коллекцию или другую структуру данных
    // mapToInt - преобразовать объект в числовой стрим (стрим, содержащий числа)
    private static void testFilterAndCount() {
        System.out.println("Test filter and count start");

        // ************ Работа со строками
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // Вернуть количество вхождений объекта
        long count = collection.stream().filter("a1"::equals).count();
        System.out.println("count = " + count); // напечатает count = 2

        // Выбрать все элементы по шаблону
        List<String> select = collection.stream().filter((s) -> s.contains("1")).collect(Collectors.toList());
        System.out.println("select = " + select); // напечатает select = [a1, a1]

        // ************ Работа со сложными объектами

        // Зададим коллекцию людей
        Collection<People> people = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // Выбрать мужчин-военообязанных
        List<People> militaryService = people.stream().filter((p) -> p.getAge() >= 18 && p.getAge() < 27
                && p.getSex() == Sex.MAN).collect(Collectors.toList());
        System.out.println("militaryService = " + militaryService); // напечатает militaryService = [{name='Петя', age=23, sex=MAN}]

        // Найти средний возраст среди мужчин
        double manAverageAge = people.stream().filter((p) -> p.getSex() == Sex.MAN).
                mapToInt(People::getAge).average().getAsDouble();
        System.out.println("manAverageAge = " + manAverageAge); // напечатает manAverageAge = 36.0

        // Найти кол-во потенциально работоспосбных людей в выборке (т.е. от 18 лет и учитывая что женщины выходят в 55 лет, а мужчина в 60)
        long peopleWhoCanWork = people.stream().filter((p) -> p.getAge() >= 18).filter(
                (p) -> (p.getSex() == Sex.WOMAN && p.getAge() < 55) || (p.getSex() == Sex.MAN && p.getAge() < 60)).count();
        System.out.println("peopleWhoCanWork = " + peopleWhoCanWork); // напечатает peopleWhoCanWork = 2

    }

    // Метод Limit позволяет ограничить выборку определенным количеством первых элементов
    private static void testLimit() {
        System.out.println();
        System.out.println("Test limit start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // Вернуть первые два элемента
        List<String> limit = collection.stream().limit(2).collect(Collectors.toList());
        System.out.println("limit = " + limit); // напечатает limit = [a1, a2]

        // Вернуть два элемента начиная со второго
        List<String> fromTo = collection.stream().skip(1).limit(2).collect(Collectors.toList());
        System.out.println("fromTo = " + fromTo); // напечатает fromTo = [a2, a3]

        // вернуть последний элемент коллекции
        String last = collection.stream().skip(collection.size() - 1).findAny().orElse("1");
        System.out.println("last = " + last); // напечатает last = a1
    }

    // findFirst - возвращает первый Optional элемент из стрима
    // skip - пропускает N первых элементов (где N параметр метода)
    // collect преобразует stream в коллекцию или другую структуру данных
    private static void testFindFirstSkipCount() {
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        System.out.println("Test findFirst and skip start");
        // вернуть первый элемент коллекции
        String first = collection.stream().findFirst().orElse("1");
        System.out.println("first = " + first); // напечатает first = a1

        // вернуть последний элемент коллекции
        String last = collection.stream().skip(collection.size() - 1).findAny().orElse("1");
        System.out.println("last = " + last); // напечатает last = a1

        // найти элемент в коллекции
        String find = collection.stream().filter("a3"::equals).findFirst().get();
        System.out.println("find = " + find); // напечатает find = a3

        // вернуть третий элемент коллекции по порядку
        String third = collection.stream().skip(2).findFirst().get();
        System.out.println("third = " + third); // напечатает third = a3

        System.out.println();
        System.out.println("Test collect start");
        // выбрать все элементы по шаблону
        List<String> select = collection.stream().filter((s) -> s.contains("1")).collect(Collectors.toList());
        System.out.println("select = " + select); // напечатает select = [a1, a1]
    }

    // Метод anyMatch - возвращает true, если условие выполняется хотя бы для одного элемента
    // Метод noneMatch - возвращает true, если условие не выполняется ни для одного элемента
    // Метод allMatch - возвращает true, если условие выполняется для всех элементов
    private static void testMatch() {
        System.out.println();
        System.out.println("Test anyMatch, allMatch, noneMatch  start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // найти существуют ли хоть одно совпадение с шаблоном в коллекции
        boolean isAnyOneTrue = collection.stream().anyMatch("a1"::equals);
        System.out.println("anyOneTrue " + isAnyOneTrue); // напечатает true
        boolean isAnyOneFalse = collection.stream().anyMatch("a8"::equals);
        System.out.println("anyOneFlase " + isAnyOneFalse); // напечатает false

        // найти существуют ли все совпадения с шаблоном в коллекции
        boolean isAll = collection.stream().allMatch((s) -> s.contains("1"));
        System.out.println("isAll " + isAll); // напечатает false

        // сравнение на неравенство
        boolean isNotEquals = collection.stream().noneMatch("a7"::equals);
        System.out.println("isNotEquals " + isNotEquals); // напечатает true
    }

    // Метод Map изменяет выборку по определенному правилу, возвращает stream с новой выборкой
    private static void testMap() {
        System.out.println();
        System.out.println("Test map start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Изменение всех элементов коллекции
        List<String> transform = collection.stream().map((s) -> s + "_1").collect(Collectors.toList());
        System.out.println("transform = " + transform); // напечатает transform = [a1_1, a2_1, a3_1, a1_1]

        // убрать первый символ и вернуть числа
        List<Integer> number = collection.stream().map((s) -> Integer.parseInt(s.substring(1))).collect(Collectors.toList());
        System.out.println("number = " + number); // напечатает transform = [1, 2, 3, 1]

    }

    // Метод MapToInt - изменяет выборку по определенному правилу, возвращает stream с новой числовой выборкой
    private static void testMapToInt() {
        System.out.println();
        System.out.println("Test mapToInt start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // убрать первый символ и вернуть числа
        int[] number = collection.stream().mapToInt((s) -> Integer.parseInt(s.substring(1))).toArray();
        System.out.println("number = " + Arrays.toString(number)); // напечатает number = [1, 2, 3, 1]

    }

    // Метод FlatMap - похоже на Map - только вместо одного значения, он возвращает целый stream значений
    private static void testFlatMap() {
        System.out.println();
        System.out.println("Test flat map start");
        Collection<String> collection = Arrays.asList("1,2,0", "4,5");
        // получить все числовые значения, которые хранятся через запятую в collection
        String[] number = collection.stream().flatMap((p) -> Arrays.asList(p.split(",")).stream()).toArray(String[]::new);
        System.out.println("number = " + Arrays.toString(number)); // напечатает number = [1, 2, 0, 4, 5]
    }

    // Метод FlatMapToInt - похоже на MapToInt - только вместо одного значения, он возвращает целый stream значений
    private static void testFlatMapToInt() {
        System.out.println();
        System.out.println("Test flat map start");
        Collection<String> collection = Arrays.asList("1,2,0", "4,5");
        // получить сумму всех числовые значения, которые хранятся через запятую в collection
        int sum = collection.stream().flatMapToInt((p) -> Arrays.asList(p.split(",")).stream().mapToInt(Integer::parseInt)).sum();
        System.out.println("sum = " + sum); // напечатает sum = 12
    }

    // Метод Sorted позволяет сортировать значения либо в натуральном порядке, либо задавая Comparator
    private static void testSorted() {
        System.out.println();
        System.out.println("Test sorted start");

        // ************ Работа со строками
        Collection<String> collection = Arrays.asList("a1", "a4", "a3", "a2", "a1", "a4");

        // отсортировать значения по алфавиту
        List<String> sorted = collection.stream().sorted().collect(Collectors.toList());
        System.out.println("sorted = " + sorted); // напечатает sorted = [a1, a1, a2, a3, a4, a4]

        // отсортировать значения по алфавиту и убрать дубликаты
        List<String> sortedDistinct = collection.stream().sorted().distinct().collect(Collectors.toList());
        System.out.println("sortedDistinct = " + sortedDistinct); // напечатает sortedDistinct = [a1, a2, a3, a4]

        // отсортировать значения по алфавиту в обратном порядке
        List<String> sortedReverse = collection.stream().sorted((o1, o2) -> -o1.compareTo(o2)).collect(Collectors.toList());
        System.out.println("sortedReverse = " + sortedReverse); // напечатает sortedReverse = [a4, a4, a3, a2, a1, a1]

        // отсортировать значения по алфавиту в обратном порядке  и убрать дубликаты
        List<String> distinctReverse = collection.stream().sorted((o1, o2) -> -o1.compareTo(o2)).distinct().collect(Collectors.toList());
        System.out.println("distinctReverse = " + distinctReverse); // напечатает sortedReverse = [a4, a3, a2, a1]

        // ************ Работа с объектами
        // Зададим коллекцию людей
        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // Отсортировать по имени в обратном алфавитном порядке
        Collection<People> byName = peoples.stream().sorted((o1, o2) -> -o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        System.out.println("byName = " + byName); // byName = [{name='Петя', age=23, sex=MAN}, {name='Иван Иванович', age=69, sex=MAN}, {name='Елена', age=42, sex=WOMEN}, {name='Вася', age=16, sex=MAN}]

        // Отсортировать сначала по полу, а потом по возрасту
        Collection<People> bySexAndAge = peoples.stream().sorted((o1, o2) -> o1.getSex() != o2.getSex() ? o1.getSex().
                compareTo(o2.getSex()) : o1.getAge().compareTo(o2.getAge())).collect(Collectors.toList());
        System.out.println("bySexAndAge = " + bySexAndAge); // bySexAndAge = [{name='Вася', age=16, sex=MAN}, {name='Петя', age=23, sex=MAN}, {name='Иван Иванович', age=69, sex=MAN}, {name='Елена', age=42, sex=WOMEN}]
    }

    // Метод max вернет максимальный элемент, в качестве условия использует компаратор
    // Метод min вернет минимальный элемент, в качестве условия использует компаратор
    private static void testMinMax() {
        System.out.println();
        System.out.println("Test min and max start");
        // ************ Работа со строками
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");

        // найти максимальное значение
        String max = collection.stream().max(String::compareTo).get();
        System.out.println("max " + max); // напечатает a3

        // найти минимальное значение
        String min = collection.stream().min(String::compareTo).get();
        System.out.println("min " + min); // напечатает a1

        // ************ Работа со сложными объектами

        // Зададим коллекцию людей
        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // найти человека с максимальным возрастом
        People older = peoples.stream().max((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println("older " + older); // напечатает {name='Иван Иванович', age=69, sex=MAN}

        // найти человека с минимальным возрастом
        People younger = peoples.stream().min((p1, p2) -> p1.getAge().compareTo(p2.getAge())).get();
        System.out.println("younger " + younger); // напечатает {name='Вася', age=16, sex=MAN}
    }

    // Метод ForEach применяет указанный метод к каждому элементу стрима и заканчивает работу со стримом
    private static void testForEach() {
        System.out.println();
        System.out.println("For each start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Напечатать отладочную информацию по каждому элементу стрима
        System.out.print("forEach = ");
        collection.stream().map(String::toUpperCase).forEach((e) -> System.out.print(e + ",")); // напечатает forEach = A1,A2,A3,A1,
        System.out.println();

        Collection<StringBuilder> list = Arrays.asList(new StringBuilder("a1"), new StringBuilder("a2"), new StringBuilder("a3"));
        list.stream().forEachOrdered((p) -> p.append("_new"));
        System.out.println("forEachOrdered = " + list); // напечатает forEachOrdered = [a1_new, a2_new, a3_new]
    }

    // Метод Peek возвращает тот же стрим, но при этом применяет указанный метод к каждому элементу стрима
    private static void testPeek() {
        System.out.println();
        System.out.println("Test peek start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1");
        // Напечатать отладочную информацию по каждому элементу стрима
        System.out.print("peak1 = ");
        List<String> peek = collection.stream().map(String::toUpperCase).peek((e) -> System.out.print(e + ",")).
                collect(Collectors.toList());
        System.out.println(); // напечатает peak1 = A1,A2,A3,A1,
        System.out.println("peek2 = " + peek); // напечатает peek2 = [A1, A2, A3, A1]

        Collection<StringBuilder> list = Arrays.asList(new StringBuilder("a1"), new StringBuilder("a2"), new StringBuilder("a3"));
        List<StringBuilder> newList = list.stream().peek((p) -> p.append("_new")).collect(Collectors.toList());
        System.out.println("newList = " + newList); // напечатает newList = [a1_new, a2_new, a3_new]
    }

    // Метод reduce позволяет выполнять агрегатные функции на всей коллекцией (такие как сумма, нахождение минимального или максимального значение и т.п.)
    // Он возвращает одно Optional значение
    // map - преобразует один объект в другой (например, класс одного тип в другой)
    // mapToInt - преобразование объектов в числовой стрим (стрим, состоящий из значений int)
    private static void testReduce() {
        System.out.println();
        System.out.println("Test reduce start");

        // ************ Работа с числовыми объектами
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 2);

        // Вернуть сумму
        Integer sum = collection.stream().reduce((s1, s2) -> s1 + s2).orElse(0); // через stream Api
        Integer sumOld = 0; // по старому методу
        for (Integer i : collection) {
            sumOld += i;
        }
        System.out.println("sum = " + sum + " : " + sumOld); // напечатает sum = 12 : 12


        // Вернуть максимум
        Integer max1 = collection.stream().reduce((s1, s2) -> s1 > s2 ? s1 : s2).orElse(0); // через stream Api
        Integer max2 = collection.stream().reduce(Integer::max).orElse(0); // через stream Api используя Integer::max
        Integer maxOld = null; // по старому методу
        for (Integer i : collection) {
            maxOld = maxOld != null && maxOld > i ? maxOld : i;
        }
        maxOld = maxOld == null ? 0 : maxOld;
        System.out.println("max = " + max1 + " : " + max2 + " : " + maxOld); // напечатает max1 = 4 : 4 : 4

        // Вернуть минимум
        Integer min = collection.stream().reduce((s1, s2) -> s1 < s2 ? s1 : s2).orElse(0); // через stream Api
        Integer minOld = null; // по старому методу
        for (Integer i : collection) {
            minOld = minOld != null && minOld < i ? minOld : i;
        }
        minOld = minOld == null ? 0 : minOld;
        System.out.println("min = " + min + " : " + minOld); // напечатает min = 1 : 1

        // Вернуть последний элемент
        Integer last = collection.stream().reduce((s1, s2) -> s2).orElse(0); // через stream Api
        Integer lastOld = null; // по старому методу
        for (Integer i : collection) {
            lastOld = i;
        }
        lastOld = lastOld == null ? 0 : lastOld;
        System.out.println("last = " + last + " : " + lastOld); // напечатает last = 2 : 2

        // Вернуть сумму чисел, которые больше 2
        Integer sumMore2 = collection.stream().filter(o -> o > 2).reduce((s1, s2) -> s1 + s2).orElse(0);     // через stream Api
        Integer sumMore2Old = 0; // по старому методу
        for (Integer i : collection) {
            if (i > 2) {
                sumMore2Old += i;
            }
        }
        System.out.println("sumMore2 = " + sumMore2 + " : " + sumMore2Old); // напечатает sumMore2 = 7 : 7

        // Вернуть сумму нечетных чисел
        Integer sumOdd = collection.stream().filter(o -> o % 2 != 0).reduce((s1, s2) -> s1 + s2).orElse(0); // через stream Api
        Integer sumOddOld = 0; // по старому методу
        for (Integer i : collection) {
            if (i % 2 != 0) {
                sumOddOld += i;
            }
        }
        System.out.println("sumOdd = " + sumOdd + " : " + sumOddOld); // напечатает sumOdd = 4 : 4

        // ************ Работа со сложными объектами

        // Зададим коллекцию людей
        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMAN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // Найдем самого старшего мужчину
        int oldMan = peoples.stream().filter((p) -> p.getSex() == Sex.MAN).map(People::getAge).reduce((s1, s2) -> s1 > s2 ? s1 : s2).get();
        System.out.println("oldMan = " + oldMan); // напечатает 69

        // Найдем самого минимальный возраст человека у которго есть бука е в имени
        int younger = peoples.stream().filter((p) -> p.getName().contains("е")).mapToInt(People::getAge).reduce((s1, s2) -> s1 < s2 ? s1 : s2).orElse(0);
        System.out.println("younger = " + younger); // напечатает 23
    }

    // Метод collect преобразует stream в коллекцию или другую структуру данных
    // Полезные статические методы из Collectors:
    // toList, toCollection, toSet - представляют стрим в виде списка, коллекции или множества
    // toConcurrentMap, toMap - позволяют преобразовать стрим в map, используя указанные функции
    // averagingInt, averagingDouble, averagingLong - возвращают среднее значение
    // summingInt, summingDouble, summingLong - возвращает сумму
    // summarizingInt, summarizingDouble, summarizingLong - возвращают SummaryStatistics с разными агрегатными значениями
    // partitioningBy - разделяет коллекцию на две части по соотвествию условию и возвращает их как Map<Boolean, List>
    // groupingBy - разделить коллекцию по условию и вернуть Map<N, List<T>>, где T - тип последнего стрима, N - значение разделителя
    // mapping - дополнительные преобразования значений для сложных Collector'ов
    private static void testCollect() {
        System.out.println();
        System.out.println("Test distinct start");

        // ******** Работа с числами
        Collection<Integer> numbers = Arrays.asList(1, 2, 3, 4);

        // Получить сумму нечетных чисел
        long sumOdd = numbers.stream().collect(Collectors.summingInt(((p) -> p % 2 == 1 ? p : 0)));
        System.out.println("sumOdd = " + sumOdd); // напечатает sumEven = 4

        // Вычисть к каждого элемента 1 и получить среднее
        double average = numbers.stream().collect(Collectors.averagingInt((p) -> p - 1));
        System.out.println("average = " + average); // напечатает average = 1.5

        // Прибавить к числам 3 и получить статистику
        IntSummaryStatistics statistics = numbers.stream().collect(Collectors.summarizingInt((p) -> p + 3));
        System.out.println("statistics = " + statistics); // напечатает statistics = IntSummaryStatistics{count=4, sum=22, min=4, average=5.500000, max=7}

        // Получить сумму четных чисел через IntSummaryStatistics
        long sumEven = numbers.stream().collect(Collectors.summarizingInt((p) -> p % 2 == 0 ? p : 0)).getSum();
        System.out.println("sumEven = " + sumEven); // напечатает sumEven = 6

        // Разделить числа на четные и нечетные
        Map<Boolean, List<Integer>> parts = numbers.stream().collect(Collectors.partitioningBy((p) -> p % 2 == 0));
        System.out.println("parts = " + parts); // напечатает parts = {false=[1, 3], true=[2, 4]}

        // ******** Работа со строками
        Collection<String> strings = Arrays.asList("a1", "b2", "c3", "a1");

        // Получение списка из коллекции строк без дубликатов
        List<String> distinct = strings.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, b2, c3]

        // Получение массива уникальных значений из коллекции строк
        String[] array = strings.stream().distinct().map(String::toUpperCase).toArray(String[]::new);
        System.out.println("array = " + Arrays.asList(array)); // напечатает array = [A1, B2, C3]

        // Объединить все элементы в одну строку через разделитель : и обернуть тегами <b> ... </b>
        String join = strings.stream().collect(Collectors.joining(" : ", "<b> ", " </b>"));
        System.out.println("join = " + join); // напечатает <b> a1 : b2 : c3 : a1 </b>

        // Преобразовать в map, где первый символ ключ, второй символ значение
        Map<String, String> map = strings.stream().distinct().collect(Collectors.toMap((p) -> p.substring(0, 1), (p) -> p.substring(1, 2)));
        System.out.println("map = " + map); // напечатает map = {a=1, b=2, c=3}

        // Преобразовать в map, сгрупировав по первому символу строки
        Map<String, List<String>> groups = strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1)));
        System.out.println("groups = " + groups); // напечатает groups = {a=[a1, a1], b=[b2], c=[c3]}

        // Преобразовать в map, сгрупировав по первому символу строки и в качестве значения взять второй символ объединим через :
        Map<String, String> groupJoin = strings.stream().collect(Collectors.groupingBy((p) -> p.substring(0, 1), Collectors.mapping((p) -> p.substring(1, 2), Collectors.joining(":"))));
        System.out.println("groupJoin = " + groupJoin); // напечатает groupJoin = groupJoin = {a=1/1, b=2, c=3}

        // Напишем собственный Collector, который будет выполнять объединение строк с помощью StringBuilder
        Collector<String,StringBuilder, String> stringBuilderCollector =  Collector.of(
                StringBuilder::new, // метод инициализации аккумулятора
                (b ,s) -> b.append(s).append(" , "), // метод обработки каждого элемента
                (b1, b2) -> b1.append(b2).append(" , "), // метод соединения двух аккумуляторов при параллельном выполнении
                StringBuilder::toString // метод выполняющися в самом конце
        );
        String joinBuilder = strings.stream().collect(stringBuilderCollector);
        System.out.println("joinBuilder = " + joinBuilder); // напечатает joinBuilder = a1 , b2 , c3 , a1 ,

        // Аналог Collector'а выше стилем JDK7 и ниже
        StringBuilder b = new StringBuilder(); // метод инициализации аккумулятора
        for(String s: strings) {
            b.append(s).append(" , "); // метод обработки каждого элемента
        }
        String joinBuilderOld = b.toString(); // метод выполняющися в самом конце
        System.out.println("joinBuilderOld = " + joinBuilderOld); // напечатает joinBuilderOld = a1 , b2 , c3 , a1 ,
    }


    private enum Sex {
        MAN,
        WOMAN
    }

    private static class People {
        private final String name;
        private final Integer age;
        private final Sex sex;

        public People(String name, Integer age, Sex sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public Sex getSex() {
            return sex;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", sex=" + sex +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof People)) return false;
            People people = (People) o;
            return Objects.equals(name, people.name) &&
                    Objects.equals(age, people.age) &&
                    Objects.equals(sex, people.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age, sex);
        }
    }

    public static void main(String[] args) {
//        testFilterAndCount();
//        testLimit();
//        testFindFirstSkipCount();
//        testMatch();
//        testMap();
//        testMapToInt();
//        testFlatMap();
//        testFlatMapToInt();
//        testSorted();
//        testMinMax();
//        testForEach();
//        testPeek();
//        testReduce();
//        testCollect();
    }
}
