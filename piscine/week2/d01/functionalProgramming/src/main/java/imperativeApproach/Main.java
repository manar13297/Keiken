package imperativeApproach;

import java.util.ArrayList;
import java.util.List;

// We define every single detail
public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Kernel", Gender.MALE),
            new Person("Sam", Gender.MALE)
        );

        List<Person> females = new ArrayList<>();
        for(Person person: people){
            if (Gender.FEMALE.equals(person.gender)){
                females.add(person);
            }
        }

        for(Person female: females){
            System.out.println(female);
        }
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
