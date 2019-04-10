package Collectors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Collectors_ex {

    public Person[] csvReader(String fileName){
        String line;
        int length=0;
        int idx=0;

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while((line = br.readLine()) != null) {
                length++;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

        Person[]csv = new Person[length];

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[]text = line.split(",");
                csv[idx++] = new Person(text[0],Integer.parseInt(text[1]),text[2],text[3]);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return csv;
    }

    public static void main(String[] args) {
        Collectors_ex ex = new Collectors_ex();

        Person[] people = ex.csvReader("people.csv");

        Stream.of(people).forEach(x->{
            System.out.println(x.getName()+" "+x.getAge()+" "+x.getGender()+" "+x.getCity());
        });//��ü���;

        final List<Integer> collectToList = Stream.of(people)
                .map(Person::getAge)
                .sorted()
                .collect(Collectors.toList());// ���� �̾�(map)���� �����ϰ� ����Ʈ�� �ݷ�Ʈ
        collectToList.forEach(System.out::print);
        System.out.println();
        collectToList.forEach(x->{
            System.out.print(x+ ",");
        });

        System.out.println();

        final TreeSet<String> collectToTreeSet = Stream.of(people)
                .map(Person::getName)
                .collect(Collectors.toCollection(TreeSet::new));// TreeSet ���� �ݷ�Ʈ
        System.out.println("TreeSet First : " + collectToTreeSet.first()); //Name ���������Ŀ���
        System.out.println("TreeSet Last : " + collectToTreeSet.last());  //Name ���������Ŀ���

        System.out.println();

        final Integer collectToIntegerSum = Stream.of(people)
                .collect(Collectors.summingInt(Person::getAge));
        System.out.println("Sum Age : " + collectToIntegerSum); //Age ����

        System.out.println();

        // GroupingBy ##################

        Map<String, List<Person>> collectToMapKeyGender = Stream.of(people)
                .collect(Collectors.groupingBy(Person::getGender));// ������ �ݷ�Ʈ�� Map ����

        collectToMapKeyGender.get("����").forEach(x->{
            System.out.println(x.getName());//�������
        });

        // Comparator ##################

        final Optional<String> collectToMaxNameString = Stream.of(people)
                .map(Person::getName)
                .collect(Collectors.maxBy(Comparator.naturalOrder()));  //naturalOrder
        System.out.println("������ �ǵ��� �̸� : " + collectToMaxNameString.get());

        System.out.println();

        Stream.of(people)
            .sorted(Comparator.comparing(Person::getCity))  //??CompareTo ����ʹپ�
                .map(Person::getCity)
                .forEach(System.out::print);

        System.out.println();

        Stream<Person> sortedWithCityDic = Stream.of(people)
                .sorted(Comparator.comparing(Person::getCity));// City ���������ĵ� stream

        sortedWithCityDic.forEach(Person::printPerson);

    }
}
class Person implements Comparable<Person> {

    private String name;
    private int age;
    private String gender;
    private String city;

    public Person(String name, int age, String gender, String city) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.city = city;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getCity() {
        return city;
    }
    public void printPerson(){
        System.out.println(this.name + " " + this.age + " " + this.gender + " " + this.city);
    }

    @Override
    public int compareTo(Person object) {
        if(this.getCity().length()<object.getCity().length())
            return 1;
        else{
            return -1;
        }
    }
}
