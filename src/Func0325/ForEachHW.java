package Func0325;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ForEachHW {
    public static void main(String args[]) {

        List<Integer> list1 = Arrays.asList(4, 7, 2, 8, 9, 3, 6); // list1 is fixed.
        ArrayList<Integer> evens = new ArrayList<Integer>(); // list2 is resizable.

        Integer max = 0;
        for (Integer e : list1) {
            if (e > max)
                max = e;
            if (e % 2 == 0)
                evens.add(e);
        }
        System.out.println(max);
        System.out.println(evens);
        evens.clear();
        list1.forEach(e -> {
            if (e % 2 == 0) evens.add(e);
// if (e > max)
// max = e;
// No assignment. Why?
// 람다식에 들어가는 로컬변수는 final 특성을 가져야만한다.
// 로컬변수는 메소드 실행이 끝나면 메모리영역에서 사라지지만,
// 람다는 메소드 실행이 끝나도 메모리 영역에 남기때문이다.
// max 는 호출시 값이 변하기때문에 final 특성을 가지지 않아서 X
        });
        System.out.println(evens);
    }
}
