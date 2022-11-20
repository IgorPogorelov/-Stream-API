import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countPersonsAgeLessEighteen = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        List<String> surnamesSoldiers = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person -> Person.getFamily()).toList();

        List<Person> eduHigherPeoples = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getSex().equals(Sex.WOMAN) && person.getAge() <= 60 || person.getSex().equals(Sex.MAN) && person.getAge() <= 65)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily)).toList();

        System.out.println(countPersonsAgeLessEighteen);
        surnamesSoldiers.forEach(System.out::println);
        eduHigherPeoples.forEach(System.out::println);
    }
}
