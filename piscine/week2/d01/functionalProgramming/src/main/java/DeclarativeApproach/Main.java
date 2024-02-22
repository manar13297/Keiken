package DeclarativeApproach;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Kernel", Gender.MALE),
            new Person("Sam", Gender.MALE)
        );

        // Declarative approach ---> Streams with Collections
        List<Person> females =  people.stream()
                .filter(person -> Gender.FEMALE.equals(person.gender))
                .toList()
                ;
        females.forEach(System.out::println);
    }

    private static class Person{
        private final String name;
        private final Gender gender;

        Person(String name , Gender gender){
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender{
        MALE, FEMALE
    }
}
