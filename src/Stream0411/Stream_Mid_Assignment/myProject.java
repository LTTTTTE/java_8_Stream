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
        );  //전체 데이터 출력

        personList.stream()
                .distinct()                                             //중복제거.
                .filter(x->!x.getName().equals("unidentified")) // 이름에 불확실한 정보는 filtering 후 반대로 제거
                .filter(x->!x.getSocialNumber().equals("unidentified")) // 주민번호에 불확실한 정보는 filtering 후 반대로 제거
                .forEach(x->
                        System.out.println(x.getName()+ " : "+x.getSocialNumber())
                ); //출력


    }
}
