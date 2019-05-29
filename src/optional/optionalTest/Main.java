package optional.optionalTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //������ü

        List<FooClass> list = new ArrayList<>();
        list.add(new FooClass("fooData01",1,new Specific("����1","2020")));
        printFooSpecTitle(list);
        list.get(0).setSpecific(null); // NPE ������ ����
        printFooSpecTitle(list);

        //MAP

        TreeMap<Integer,Score> map = new TreeMap<>();
        map.put(1,new Score("1��!!"));
        map.put(2,new Score("2��~"));
        map.put(4,new Score("4��..."));

        IntStream.rangeClosed(1,5).forEach(x->{ // key �������� NPE (3�� 5)
            Score aNull = Optional.ofNullable(map.get(x))
                    .orElseGet(() -> {
                        return new Score("����ִµ��");
                    });
            System.out.println(aNull.getScore());
        });
    }

    public static void printFooSpecTitle(List<FooClass> list){

        list.forEach(x->{
            System.out.println(Optional.ofNullable(x)
                    .map(FooClass::getSpecific)
                    .map(Specific::getTitle)
                    .orElseGet(() -> {
                        System.out.println("null �̹Ƿ� ���ο�� ����");
                        return "";
                    })
            );
        });
    }

}
