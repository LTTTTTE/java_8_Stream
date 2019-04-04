package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();

        list.add("HI");
        list.add("HELLO");
        list.add("GOOD");
        list.add("Bye");

        list.stream().forEach(str -> {
            System.out.println(str + ", length : " + str.length());
        });


        List<Student> list_01 = Arrays.asList(
                new Student("가나다", 10),
                new Student("라마바", 7),
                new Student("가가가", 1),
                new Student("하하하", 2)
        );

        list_01.stream()
                .mapToInt(Student::getAge)
                .sorted()
                .forEach(x -> {
                    System.out.print(x + " , ");
                });
        System.out.println();

        list_01.stream()
                .map(Student::getName)
                .sorted()
                .forEach(x -> {
                    System.out.print(x + " , ");
                });

        System.out.println();

        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("바보", 19));
        students.add(new Student("김땡떙", 78));
        students.add(new Student("최땡떙", 86));
        students.add(new Student("이떙땡", 22));
        students.add(new Student("정땡땡", 77));
        students.add(new Student("태땡떙", 66));
        students.add(new Student("안경", 82));
        students.add(new Student("스피커", 10));
        students.add(new Student("아이폰", 8));
        students.add(new Student("모니터", 36));

        students.stream()
                .map(Student::getName)
                .filter(str ->
                        str.contains("땡"))
                .sorted()
                .forEach(str ->
                        System.out.println(str));


        ArrayList<Student> arrayList = new ArrayList<>();
        List<Student> list_2 = Arrays.asList(
                new Student("ㅂㅂ", 1113),
        new Student("ㅁㅈㄴ", 122),
        new Student("ㅋㅋ", 18),
        new Student("ㅎㅎ", 1823)

        );

        arrayList.stream().forEach(str->{

        });

        list_2.stream().mapToInt(Student::getAge).sorted().forEach(System.out::println);


    }
}


class Student {

    public Student(String name, int age) {
        setName(name);
        setAge(age);
    }

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}