package com.cursor.functionals.executors;

import com.cursor.functionals.users.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserExecute {

    public static void start() {
        averageAgeLvivUsers();
        notFromKyivUsers();
        sortByAge();
        elderThan();
        youngerThanFromDnipro();
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

    private static void elderThan() {
        System.out.println("Enter the Number to show users by age from : ");
        Map<Integer, User> oldUsers;
        oldUsers = initializeUsers();
        while (!MyScanner.getInstance().hasNextInt()) {
            System.out.println("That's not a number!");
            MyScanner.getInstance().next();
            System.out.println("Enter the Number to show users by age from : ");
        }
        int lineNum = MyScanner.getInstance().nextInt();
        oldUsers
                .entrySet()
                .stream()
                .filter(u -> u.getValue().getAge() > lineNum)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
    }

    private static void youngerThanFromDnipro() {
        System.out.println("Enter the Number to Show Users by Age from Dnipro to : ");
        Map<Integer, User> youngUsers;
        youngUsers = initializeUsers();
        while (!MyScanner.getInstance().hasNextInt()) {
            System.out.println("That's not a number!");
            MyScanner.getInstance().next();
            System.out.println("Enter the Number to Show Users by Age from Dnipro to : ");
        }
        int lineNum = MyScanner.getInstance().nextInt();
        youngUsers
                .entrySet()
                .stream()
                .filter(u -> u.getValue().getAge() < lineNum && u.getValue().getCity().equals("Днепр"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
    }

    private static void averageAgeLvivUsers() {
        System.out.println("average Age of Lviv Users is : ");
        Map<Integer, User> lvivUsers;
        lvivUsers = initializeUsers();
        lvivUsers
                .values()
                .stream()
                .filter(user -> user.getCity().equals("Львів"))
                .mapToDouble(User::getAge)
                .average()
                .ifPresent(System.out::println);
        System.out.println();
    }

    private static void notFromKyivUsers() {
        Map<Integer, User> users;
        users = initializeUsers();
        long amount = users
                .entrySet()
                .stream()
                .filter(u -> !u.getValue().getCity().equals("Киев"))
                .count();
        System.out.println("Users are not from Kyiv : " + amount);
        users.values()
                .stream()
                .filter(user -> !user.getCity().equals("Киев"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println();
    }

    private static void sortByAge() {
        System.out.println("Sorted by age of first three users :");
        Map<Integer, User> users;
        users = initializeUsers();
        users.entrySet()
                .stream()
                .sorted(Comparator.comparing(user -> user.getValue().getAge()))
                .limit(3)
                .forEach(System.out::println);
        System.out.println();
    }
}
