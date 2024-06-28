package org.example;

public class Main {
    public static void main(String[] args) {
//        IntegerListImpl.checkTime();
        IntegerListImpl list = new IntegerListImpl(1);
        list.add(4);
        list.add(1);
        list.add(345);
        list.add(5);
        list.add(54);
        list.add(45);
        list.add(75);
        list.add(456);
        Integer[] o = list.toArray();
//        IntegerListImpl.quickSort(o,0, o.length-1);
        for (Integer i :
                IntegerListImpl.quickSort(o, 0, o.length - 1)) {
            System.out.println(i);
        }

    }


}