package Stream0411.Stream_Mid_Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Person{

    private String name;
    private String socialNumber;

    public Person(String name, String socialNumber) {
        this.name = name;
        this.socialNumber = socialNumber;
    }

    public String getName() {
        return name;
    }

    public String getSocialNumber() {
        return socialNumber;
    }

}

public class myProject {

    public static List<Person> TextReader(String fileName) throws IOException {
        String line;
        List<Person>personList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("Identity.txt"));
        while ((line = br.readLine()) != null) {
            String[] person = line.split(";");
            if(person[0].equals(""))
                person[0]="unidentified";

            if(person.length<2) {
                Person newPerson = new Person(person[0], "unidentified");
                personList.add(newPerson);
            }
            else {
                Person newPerson = new Person(person[0], person[1]);
                personList.add(newPerson);
            }

        }
        return personList;
    }

    public static void main(String[] args) throws IOException {

        List<Person>personList =  TextReader("Identity");

        personList.stream().forEach(x->
            System.out.println(x.getName()+ " : "+x.getSocialNumber())
        );  //��ü ������ ���

        personList.stream()
                .distinct()                                             //�ߺ�����.
                .filter(x->!x.getName().equals("unidentified")) // �̸��� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .filter(x->!x.getSocialNumber().equals("unidentified")) // �ֹι�ȣ�� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .forEach(x->
                        System.out.println(x.getName()+ " : "+x.getSocialNumber())
                ); //���


    }
}
