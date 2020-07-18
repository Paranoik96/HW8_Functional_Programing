package com.cursor.functionals.executors;

import com.cursor.functionals.users.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserExecute {

    public static void start() {
        averageAgeOfUsers(initializeUsers(), "Львів");
        notFromExactCityOfUsers(initializeUsers(), "Киев");
        sortByAge(initializeUsers(),3);
        elderThan(initializeUsers());
        youngerThan(initializeUsers(), "Днепр");
    }

    static class MyScanner {
        private static final Scanner scan = new Scanner(System.in);

        private MyScanner() {
        }

        public static Scanner getInstance() {
            return scan;
        }
    }

    private static Map<Integer, User> initializeUsers() {
        Map<Integer, User> userMap = new TreeMap<>();
        userMap.put(1, new User("Вася", 16, "Днепр"));
        userMap.put(2, new User("Петя", 23, "Днепр"));
        userMap.put(3, new User("Елена", 42, "Луцк"));
        userMap.put(4, new User("Елена", 92, "Чернигов"));
        userMap.put(5, new User("Сергей", 5, "Киев"));
        userMap.put(6, new User("Мария", 32, "Киев"));
        userMap.put(7, new User("Иван Ивонавич", 69, "Львів"));

        return userMap;
    }

    private static void elderThan(Map<Integer, User> mainMap) {
        System.out.println("Enter the Number to show users by age from : ");
        while (!MyScanner.getInstance().hasNextInt()) {
            System.out.println("That's not a number!");
            MyScanner.getInstance().next();
            System.out.println("Enter the Number to show users by age from : ");
        }
        int lineNum = MyScanner.getInstance().nextInt();
        mainMap
                .entrySet()
                .stream()
                .filter(u -> u.getValue().getAge() > lineNum)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
    }

    private static void youngerThan(Map<Integer, User> mainMap, String cityName) {
        System.out.println("Enter the Number to Show Users by Age from " + cityName + " to : ");
        while (!MyScanner.getInstance().hasNextInt()) {
            System.out.println("That's not a number!");
            MyScanner.getInstance().next();
            System.out.println("Enter the Number to Show Users by Age from " + cityName + " to : ");
        }
        int lineNum = MyScanner.getInstance().nextInt();
        mainMap
                .entrySet()
                .stream()
                .filter(u -> u.getValue().getAge() < lineNum && u.getValue().getCity().equals(cityName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
    }

    private static void averageAgeOfUsers(Map<Integer, User> mainMap, String cityName) {
        System.out.println("average Age of " + cityName +" Users is : ");
        mainMap
                .values()
                .stream()
                .filter(user -> user.getCity().equals(cityName))
                .mapToDouble(User::getAge)
                .average()
                .ifPresent(System.out::println);
        System.out.println();
    }

    private static void notFromExactCityOfUsers(Map<Integer, User> mainMap, String cityName) {
        long amount = mainMap
                .entrySet()
                .stream()
                .filter(u -> !u.getValue().getCity().equals(cityName))
                .count();
        System.out.println("Users are not from " + cityName + " : " + amount + "\n");
    }

    private static void sortByAge(Map<Integer, User> mainMap, int limitNumber) {
        System.out.println("Sorted by age of first three users :");
        mainMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(user -> user.getValue().getAge()))
                .limit(limitNumber)
                .forEach(System.out::println);
        System.out.println();
    }
}
