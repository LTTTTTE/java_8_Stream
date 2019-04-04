package Func0325;
import java.util.Arrays; // for Arrays.asList. List of fixed-size
import java.util.ArrayList; // for ArrayList Resizable List
import java.util.List;
    class T {
        public static void main(String[] args) {
            List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(4, 7, 2, 8, 9));
            List<Integer> list2 = Arrays.asList(4, 7, 2, 8, 9);
            list1.add(10);
// list2.add(10); // runtime error
            list1.forEach(System.out::println);
        }
    }