package Stream0411.Foo_03;


import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import java.util.Map;

class Student {
    String name;  boolean isMale;  int hak;  int ban;  int score;

    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name       = name;
        this.isMale     = isMale;
        this.hak        = hak;
        this.ban        = ban;
        this.score  = score;
    }

    String getName() { return name;}
    boolean isMale() { return isMale;}
    int getHak()     { return hak;}
    int getBan()     { return ban;}
    int getScore()   { return score;}

    public String toString() {
        return String.format("[%s, %s, %d�г� %d��, %3d��]", name,
                isMale ? "��":"��", hak, ban, score);
    }

    enum Level { HIGH, MID, LOW }
}

class GroupingBy {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("���ڹ�", true,  1, 1, 300),
                new Student("������", false, 1, 1, 250),
                new Student("���ڹ�", true,  1, 1, 200),
                new Student("������", false, 1, 2, 150),
                new Student("���ڹ�", true,  1, 2, 100),
                new Student("������", false, 1, 2,  50),
                new Student("Ȳ����", false, 1, 3, 100),
                new Student("������", false, 1, 3, 150),
                new Student("���ڹ�", true,  1, 3, 200),
                new Student("���ڹ�", true,  2, 1, 300),
                new Student("������", false, 2, 1, 250),
                new Student("���ڹ�", true,  2, 1, 200),
                new Student("������", false, 2, 2, 150),
                new Student("���ڹ�", true,  2, 2, 100),
                new Student("������", false, 2, 2,  50),
                new Student("Ȳ����", false, 2, 3, 100),
                new Student("������", false, 2, 3, 150),
                new Student("���ڹ�", true,  2, 3, 200)
        };

        System.out.printf("%n4. ���߱׷�ȭ(�г⺰, �ݺ�)%n");
        Map<Integer, Map<Integer, Map<Integer, List<Student>>>>stuByHakAndBan
                = Stream.of(stuArr)
                .collect(groupingBy(Student::getHak
                        ,groupingBy(Student::getBan
                            ,groupingBy(Student::getScore)
                        )
                ));

        for(Map<Integer,Map<Integer,List<Student>>>hak : stuByHakAndBan.values()){
            for(Map<Integer,List<Student>>ban:hak.values()){
                for(List<Student>score: ban.values()){
                    for(Student student : score)
                        System.out.println(student);
                }
                System.out.println();
            }
        }

    }  // main�� ��
}
