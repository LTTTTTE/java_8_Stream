package optional.optionalTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //참조객체

        List<FooClass> list = new ArrayList<>();
        list.add(new FooClass("fooData01",1,new Specific("제목1","2020")));
        printFooSpecTitle(list);
        list.get(0).setSpecific(null); // NPE 억지로 생성
        printFooSpecTitle(list);

        //MAP

        TreeMap<Integer,Score> map = new TreeMap<>();
        map.put(1,new Score("1등!!"));
        map.put(2,new Score("2등~"));
        map.put(4,new Score("4등..."));

        IntStream.rangeClosed(1,5).forEach(x->{ // key 가없으면 NPE (3과 5)
            Score aNull = Optional.ofNullable(map.get(x))
                    .orElseGet(() -> {
                        return new Score("비어있는등수");
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
                        System.out.println("null 이므로 새로운놈 생성");
                        return "";
                    })
            );
        });
    }

}
