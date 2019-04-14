package Stream0411.Stream_Mid_Assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

//제출기한 4월26일
//참여 : 정주용
//출력 줄수가 너무많은 코드는 주석처리
class Person{

    private String name;
    private String socialNumber;

    public Person(String name, String socialNumber) {
        this.name = name;
        this.socialNumber = socialNumber;
    }

    public String getName() {
        return name.replaceAll(" ","");
    }

    public String getSocialNumber() {
        return socialNumber;
    }

    public String getFirstSocialNumber(){
        if(this.getSocialNumber().length()>5)
            return socialNumber.substring(0,6);
        else return "000000";
    }

    public String getCity(){
        return myProject.codeToCity(Integer.parseInt(this.socialNumber.substring(7,9)));
    }

    public String getYear(){
        return this.getSocialNumber().substring(0,2);
    }

    public String getGender(){

        if(this.getSocialNumber().substring(6,7).equals("1"))
            return "남성";
        else
            return "여성";
    }

    public String[] splitSocialNumber(){

        String[] strings = new String[2];
        if(this.getSocialNumber().length()>5) {
            strings[0] = this.getSocialNumber().substring(0, 6);
            strings[1] = this.getSocialNumber().substring(6, this.getSocialNumber().length());
            return strings;
        }
        else{
            strings[0] = "000000";
            strings[1] = "0000000";
            return strings;
        }
    }

    @Override
    public String toString(){
        return this.getName() + " 19" + this.getYear() + "년생 " +this.getCity() + " 에서 태어났으며 " + this.getGender() + " 이다";
    }
}

enum Gender {
    중성,남성,여성;
}

public class myProject {

    public static List<Person> TextReader(String fileName) throws IOException {
        String line;
        List<Person>personList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
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

    public static String codeToCity(int code){

        if(code<=8)return "서울";
        else if(code<=12)return "부산";
        else if(code<=15)return "인천";
        else if(code<=25)return "경기";
        else if(code<=34)return "강원";
        else if(code<=39)return "충북";
        else if(code==40)return "대전";
        else if(code<=47)return "충남";
        else if(code<=54)return "전북";
        else if(code<=56)return "광주";
        else if(code<=64)return "전남";
        else if(code<=69)return "대구";
        else if(code==76)return "대구";
        else if(code<=81)return "경북";
        else if(code==85)return "울산";
        else if(code<=93)return "경남";
        else if(code<=95)return "제주";
        else if(code==96)return "세종";
        else return "오류";
    }

    public static void main(String[] args) throws IOException {

        List<Person>personList =  TextReader("Identity.txt");

//        personList.stream().forEach(x->
//            System.out.println(x.getName()+ " : "+x.getSocialNumber())
//        );  //전체 데이터 출력


        // 단순 중복제거 , 불확실한정보 제거하기.
//        personList.stream()
//                .distinct()                                             //중복제거.
//                .filter(x->!x.getName().equals("unidentified")) // 이름에 불확실한 정보는 filtering 후 반대로 제거
//                .filter(x->!x.getSocialNumber().equals("unidentified")) // 주민번호에 불확실한 정보는 filtering 후 반대로 제거
//                .forEach(x->
//                        System.out.println(x.getName()+ " : "+x.getSocialNumber())
//                ); //출력

        String collect = personList.stream()    //주민번호 통째 string
                .filter(x -> !x.getName().equals("unidentified")) // 이름에 불확실한 정보는 filtering 후 반대로 제거
                .filter(x -> !x.getSocialNumber().equals("unidentified")) // 주민번호에 불확실한 정보는 filtering 후 반대로 제거
                .flatMap(x -> Arrays.stream(x.splitSocialNumber())) //주민번호 스플릿
                .collect(Collectors.joining("-"));//주민번호 사이에 '-' 넣음.
        //System.out.println(collect);

        //주민번호 앞자리가 같은 사람들
        Map<String, Long> socialNumberCountMap = personList.stream() //생년월일별 사람수 확인.
                .filter(x -> !x.getName().equals("unidentified")) // 이름에 불확실한 정보는 filtering 후 반대로 제거
                .filter(x -> !x.getSocialNumber().equals("unidentified")) // 주민번호에 불확실한 정보는 filtering 후 반대로 제거
                .sorted(Comparator.comparing(Person::getSocialNumber)) //
                .collect(Collectors.groupingBy(Person::getFirstSocialNumber, Collectors.counting()));//key 는 생년월일 value 는 갯수

 //       TreeSet<String>tss = new TreeSet<>(socialNumberCountMap.keySet()); //TreeSet 으로 형변환하여 sort
 //       for (String socialNumber: tss) {
 //           System.out.println(socialNumber + " : " + socialNumberCountMap.get(socialNumber) + " 명");
 //       }

        System.out.println("\n");

        System.out.println("[출생년도별 분포]");
        TreeMap<String, Long> birthYearCountMap =  new TreeMap<>(
                personList.stream()
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length()>5))  //이상한정보 걸러냄
                .collect(Collectors.groupingBy(x -> {
                    return x.getSocialNumber().substring(0, 2); //앞 두글자 뽑아냄(주민번호상 태어난년도)
                }, Collectors.counting())//value 는 카운트
                )
        );

        for(String year : birthYearCountMap.keySet()){
            System.out.println("19"+year + "년생 수 : " + birthYearCountMap.get(year) + " 명");
        }

        System.out.println("\n");

        System.out.println("[성별 분포]");
        TreeMap<String, Long> genderCountMap = new TreeMap<>(personList.stream() //성별 분포
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length()==13))  //이상한정보 걸러냄
                .filter(x->{ //이상한정보 걸러냄
                    return x.getSocialNumber().substring(6,7).equals("1")||x.getSocialNumber().substring(6,7).equals("2");
                })
                .collect(Collectors.groupingBy(x -> {
                    return x.getSocialNumber().substring(6, 7); // 남성, 여성 알아내기
                }, Collectors.counting())
                )
        );

        for(String gender : genderCountMap.keySet()){
            System.out.println(Gender.values()[Integer.parseInt(gender)]  + " : " +genderCountMap.get(gender) + " 명");
        }

        System.out.println("\n");

        System.out.println("[도시별 분포]");
        TreeMap<String, Long> cityCountMap = new TreeMap<>(personList.stream()  //도시별 분포
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length() == 13))  //이상한정보 걸러냄
                .collect(Collectors.groupingBy(x -> {
                            return codeToCity(Integer.parseInt(x.getSocialNumber().substring(7, 9)));
                        }, Collectors.counting())
                )
        );

        for(String city : cityCountMap.keySet()){
            System.out.println(city  + " : " +cityCountMap.get(city) + " 명");
        }

        System.out.println("\n");

        // 년도 도시 성별  다중그룹
        Map<String, Map<String, Map<Gender, List<Person>>>> PersonMap = personList.stream()
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length() == 13))  //이상한정보 걸러냄
                .filter(x -> { //성별 이상한정보 걸러냄(20세기 출생자 , 외국인)
                    return x.getSocialNumber().substring(6, 7).equals("1") || x.getSocialNumber().substring(6, 7).equals("2");
                })
                .collect(Collectors.groupingBy(
                        x -> x.getSocialNumber().substring(0, 2)//년도 String
                        , Collectors.groupingBy(
                                x -> codeToCity(Integer.parseInt(x.getSocialNumber().substring(7, 9)))//도시 String
                                , Collectors.groupingBy(
                                        x -> Gender.values()[Integer.parseInt(x.getSocialNumber().substring(6, 7))]//성별 Enum String
                                )
                        )
                    )
                );

        for(Map<String,Map<Gender,List<Person>>>stringMapMap : PersonMap.values()){ //년도 오름차순
            for(Map<Gender,List<Person>>genderListMap : stringMapMap.values()){ //도시 오름차순
                for(List<Person>list : genderListMap.values()){ //남성먼저 다음 여성 출력
                    for(Person person: list){
                        System.out.println(person);
                    }
                }
            }

        }

    }
}
