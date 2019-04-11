package Stream0411.Foo_02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

class Student {
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

    enum Level { HIGH, MID, LOW }
}


public class StreamEx8excelArrayList {

    public static ArrayList<Student> createArrayStudent(String file) throws IOException {

        String line = "";
        ArrayList<Student>studentArrayList = new ArrayList<>();

        //구현부분
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
        ArrayList<Student> stuArr = createArrayStudent(cvsFile);


        System.out.printf("1. 단순그룹화(반별로 그룹화)%n");
        Map<Integer, List<Student>> stuByBan =
                stuArr.stream().collect(groupingBy(Student::getBan));

        for(List<Student> ban : stuByBan.values()) {
            for(Student s : ban)
                System.out.println(s);
        }

        System.out.printf("%n2. 단순그룹화(성적별로 그룹화)%n");
        Map<Student.Level, List<Student>> stuByLevel =
                stuArr.stream().collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student.Level key : keySet) {
            System.out.println("["+key+"]");

            for(Student s : stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }

        System.out.printf("%n3. 단순그룹화 + 통계(성적별 학생수)%n");
        Map<Student.Level, Long> stuCntByLevel = stuArr.stream()
                .collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else                         return Student.Level.LOW;
                }, counting()));

        for(Student.Level key : stuCntByLevel.keySet())
            System.out.printf("[%s] - %d명, ", key, stuCntByLevel.get(key));
        System.out.println();

        System.out.printf("%n4. 다중그룹화(학년별, 반별)%n");
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan =
                stuArr.stream()
                        .collect(groupingBy(Student::getHak,
                                groupingBy(Student::getBan)
                        ));

        for(Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
            for(List<Student> ban : hak.values()) {
                System.out.println();
                for(Student s : ban)
                    System.out.println(s);
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n");
        Map<Integer, Map<Integer, Student>> topStuByHakAndBan = stuArr.stream()
                .collect(groupingBy(Student::getHak,
                        groupingBy(Student::getBan,
                                collectingAndThen(
                                        maxBy(comparingInt(Student::getScore)),
                                        Optional::get
                                )
                        )
                ));

        for(Map<Integer, Student> ban : topStuByHakAndBan.values())
            for(Student s : ban.values())
                System.out.println(s);

        System.out.printf("%n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)%n");
        Map<String, Set<Student.Level>> stuByScoreGroup = stuArr.stream()
                .collect(groupingBy(s-> s.getHak() + "-" + s.getBan(),
                        mapping(s-> {
                            if(s.getScore() >= 200) return Student.Level.HIGH;
                            else if(s.getScore() >= 100) return Student.Level.MID;
                            else                    return Student.Level.LOW;
                        } , toSet())
                ));

        Set<String> keySet2 = stuByScoreGroup.keySet();

        for(String key : keySet2) {
            System.out.println("["+key+"]" + stuByScoreGroup.get(key));
        }
    }
}
