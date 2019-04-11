package Stream0411.Foo_04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

class Student implements Comparable {
    private String name;
    private boolean isMale;
    private int hak;
    private int ban;
    private int score;

    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name       = name;
        this.isMale     = isMale;
        this.hak        = hak;
        this.ban        = ban;
        this.score  = score;
    }

    public String getName() { return name;}
    public boolean isMale() { return isMale;}
    public int getHak()     { return hak;}
    public int getBan()     { return ban;}
    public int getScore()   { return score;}

    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]", name,
                isMale ? "남":"여", hak, ban, score);
    }

    @Override
    public int compareTo(Object o) {

        if(this.getScore()<((Student)o).getScore())
            return -1;
        else
            return 1;
    }

    enum Level { HIGH, MID, LOW }
}

public class Sort {

    public static ArrayList<Student> createArrayStudent(String file) throws IOException {

        String line = "";
        ArrayList<Student>studentArrayList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("stud.csv"));
        while ((line = br.readLine()) != null) {

            String[] student = line.split(",");
            Student newStudent = new Student(
                    student[0]
                    ,Boolean.parseBoolean(student[1])
                    ,Integer.parseInt(student[2])
                    ,Integer.parseInt(student[3])
                    ,Integer.parseInt(student[4])
            );
            studentArrayList.add(newStudent);
        }
        return studentArrayList;
    }

    public static void main(String[] args) throws IOException {

        String cvsFile = "stud.csv";
        List<Student> stud1 = createArrayStudent(cvsFile);
        List<Student> stud2 = createArrayStudent(cvsFile);

        System.out.println("\n[CompareTo Sort]\n");
        stud1.stream()
                .sorted(Comparable::compareTo)  //Comparable 상속후 compareTo 메소드 재정의후 사용
                .forEach(x-> System.out.println(x.toString()));

        System.out.println("\n[Comparing Sort]\n");
        stud2.stream()
                .sorted(Comparator.comparing(Student::getScore)) //comparing 스코어(Integer) 기준 정렬
                .forEach(x-> System.out.println(x.toString()));

    }
}
