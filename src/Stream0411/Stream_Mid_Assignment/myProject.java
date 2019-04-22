package Stream0411.Stream_Mid_Assignment;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//������� 4��26��
//���� : ���ֿ�
//��� �ټ��� �ʹ����� �ڵ�� �ּ�ó��


//�̸�,�ֹι�ȣ�� ���� 7������ �����͸� �ҷ���
//�������, ��µ���, ���� �� �����Ͽ�
//����⵵��, ��µ��ú�, ������ �׷����ϰ� �����Ͽ�
//��µ��ϰ�, CSV(����) ���Ϸε� ����� ���.

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
            return "����";
        else
            return "����";
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
        return this.getName() + " 19" + this.getYear() + "��� " +this.getCity() + " ���� �¾���� " + this.getGender() + " �̴�";
    }
    public String getSocialNumberBoxed(){
        return getFirstSocialNumber()+"-"+getSocialNumber().substring(6);
    }

}

enum Gender {
    �߼�,����,����;
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

    public static boolean createCSVFile(Map<String, Map<String, Map<Gender, List<Person>>>> map) throws IOException{

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("newFile.csv",false));

            for(Map<String,Map<Gender,List<Person>>>stringMapMap : map.values()){
                for(Map<Gender,List<Person>>genderListMap : stringMapMap.values()){
                    for(List<Person>list : genderListMap.values()){
                        for(Person person: list){
                            writer.write(person.getName()+",19"+person.getYear()+","+person.getCity()+","+person.getGender()+","+person.getSocialNumberBoxed());
                            writer.newLine();
                        }
                    }
                    writer.write("###################");
                    writer.newLine();
                }
                writer.write("###################");
                writer.newLine();
            }

            writer.flush();
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String codeToCity(int code){

        if(code<=8)return "����";
        else if(code<=12)return "�λ�";
        else if(code<=15)return "��õ";
        else if(code<=25)return "���";
        else if(code<=34)return "����";
        else if(code<=39)return "���";
        else if(code==40)return "����";
        else if(code<=47)return "�泲";
        else if(code<=54)return "����";
        else if(code<=56)return "����";
        else if(code<=64)return "����";
        else if(code<=69)return "�뱸";
        else if(code==76)return "�뱸";
        else if(code<=81)return "���";
        else if(code==85)return "���";
        else if(code<=93)return "�泲";
        else if(code<=95)return "����";
        else if(code==96)return "����";
        else return "����";
    }

    public static void main(String[] args) throws IOException {

        List<Person>personList =  TextReader("Identity.txt");

//        personList.stream().forEach(x->
//            System.out.println(x.getName()+ " : "+x.getSocialNumber())
//        );  //��ü ������ ��� 7����


        // �ܼ� �ߺ����� , ��Ȯ�������� �����ϱ�.
//        personList.stream()
//                .distinct()                                             //�ߺ�����.
//                .filter(x->!x.getName().equals("unidentified")) // �̸��� ��Ȯ���� ������ filtering �� �ݴ�� ����
//                .filter(x->!x.getSocialNumber().equals("unidentified")) // �ֹι�ȣ�� ��Ȯ���� ������ filtering �� �ݴ�� ����
//                .forEach(x->
//                        System.out.println(x.getName()+ " : "+x.getSocialNumber())
//                ); //��� ��7����

        String collect = personList.stream()    //�ֹι�ȣ ��° string
                .filter(x -> !x.getName().equals("unidentified")) // �̸��� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .filter(x -> !x.getSocialNumber().equals("unidentified")) // �ֹι�ȣ�� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .flatMap(x -> Arrays.stream(x.splitSocialNumber())) //�ֹι�ȣ ���ø�
                .collect(Collectors.joining("-"));//�ֹι�ȣ ���̿� '-' ����.
        //System.out.println(collect); String �� ũ�Ⱑ 7��*13

        //�ֹι�ȣ ���ڸ��� ���� �����(��������� ������� ��)
        Map<String, Long> socialNumberCountMap = personList.stream() //������Ϻ� ����� Ȯ��.
                .filter(x -> !x.getName().equals("unidentified")) // �̸��� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .filter(x -> !x.getSocialNumber().equals("unidentified")) // �ֹι�ȣ�� ��Ȯ���� ������ filtering �� �ݴ�� ����
                .sorted(Comparator.comparing(Person::getSocialNumber)) //
                .collect(Collectors.groupingBy(Person::getFirstSocialNumber, Collectors.counting()));//key �� ������� value �� ����

 //       TreeSet<String>tss = new TreeSet<>(socialNumberCountMap.keySet()); //TreeSet ���� ����ȯ�Ͽ� sort
 //       for (String socialNumber: tss) {
 //           System.out.println(socialNumber + " : " + socialNumberCountMap.get(socialNumber) + " ��");
 //       }
        System.out.println(socialNumberCountMap.values().stream().mapToInt(value -> value.intValue()).max().orElse(0));
 // ���� ���� �¾ ������� �� �¾ �����

        System.out.println("\n");

        System.out.println("[����⵵�� ����]");
        TreeMap<String, Long> birthYearCountMap =  new TreeMap<>(
                personList.stream()
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length()>5))  //�̻������� �ɷ���
                .collect(Collectors.groupingBy(x -> {
                    return x.getSocialNumber().substring(0, 2); //�� �α��� �̾Ƴ�(�ֹι�ȣ�� �¾�⵵)
                }, Collectors.counting())//value �� ī��Ʈ
                )
        );

        for(String year : birthYearCountMap.keySet()){
            System.out.println("19"+year + "��� �� : " + birthYearCountMap.get(year) + " ��");
        }

        System.out.println("\n");

        System.out.println("[���� ����]");
        TreeMap<String, Long> genderCountMap = new TreeMap<>(personList.stream() //���� ����
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length()==13))  //�̻������� �ɷ���
                .filter(x->{ //�̻������� �ɷ���
                    return x.getSocialNumber().substring(6,7).equals("1")||x.getSocialNumber().substring(6,7).equals("2");
                })
                .collect(Collectors.groupingBy(x -> {
                    return x.getSocialNumber().substring(6, 7); // ����, ���� �˾Ƴ���
                }, Collectors.counting())
                )
        );

        for(String gender : genderCountMap.keySet()){
            System.out.println(Gender.values()[Integer.parseInt(gender)]  + " : " +genderCountMap.get(gender) + " ��");
        }

        System.out.println("\n");

        System.out.println("[���ú� ����]");
        TreeMap<String, Long> cityCountMap = new TreeMap<>(personList.stream()  //���ú� ����
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length() == 13))  //�̻������� �ɷ���
                .collect(Collectors.groupingBy(x -> {//�����ڵ�̾Ƴ�
                            return codeToCity(Integer.parseInt(x.getSocialNumber().substring(7, 9)));
                        }, Collectors.counting())
                )
        );

        for(String city : cityCountMap.keySet()){
            System.out.println(city  + " : " +cityCountMap.get(city) + " ��");
        }

        System.out.println("\n");

        // �⵵ ���� ����  ���߱׷�
        Map<String, Map<String, Map<Gender, List<Person>>>> PersonMap = personList.stream()
                .filter(x -> !x.getName().equals("unidentified"))
                .filter(x -> !x.getSocialNumber().equals("unidentified"))
                .filter(x -> (x.getSocialNumber().length() == 13))  //�̻������� �ɷ���
                .filter(x -> { //���� �̻������� �ɷ���(20���� ����� , �ܱ���)
                    return x.getSocialNumber().substring(6, 7).equals("1") || x.getSocialNumber().substring(6, 7).equals("2");
                })
                .collect(Collectors.groupingBy(
                        x -> x.getSocialNumber().substring(0, 2)//�⵵ String
                        , Collectors.groupingBy(
                                x -> codeToCity(Integer.parseInt(x.getSocialNumber().substring(7, 9)))//���� String
                                , Collectors.groupingBy(
                                        x -> Gender.values()[Integer.parseInt(x.getSocialNumber().substring(6, 7))]//���� Enum String
                                )
                        )
                    )
                );

        for(Map<String,Map<Gender,List<Person>>>stringMapMap : PersonMap.values()){ //�⵵ ��������
            for(Map<Gender,List<Person>>genderListMap : stringMapMap.values()){ //���� ��������
                for(List<Person>list : genderListMap.values()){ //�������� ���� ���� ���
                    for(Person person: list){
                        System.out.println(person); //Override �� �޼ҵ�� ���
                    }
                }
            }

        }
        System.out.println(createCSVFile(PersonMap));

    }
}
