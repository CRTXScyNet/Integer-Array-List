package org.example;

public class Main {
    public static void main(String[] args) {
//        IntegerListImpl list = new IntegerListImpl();
//        list.sortInsertion();
//        IntegerListImpl list1 = new IntegerListImpl();
//        list1.add(1);
//        list1.add(4);
//        list1.add(6);
//        list1.add(23);
//        list1.add(34);
//
//        list.add(23);
//        list.add(4);
//        list.add(1);
//        list.add(34);
//        list.add(6);
//        IntegerListImpl.print(list);
//        list.sortInsertion();
//        IntegerListImpl.print(list);
//        System.out.println(list1.equals(list));


        checkTime(0);
        System.out.println();
        checkTime(1);
        System.out.println();
        checkTime(2);
    }

    static void checkTime(int i){
        IntegerListImpl integerList = IntegerListImpl.generateRandomList(10000);

//        IntegerListImpl.print(integerList);
        long start = System.currentTimeMillis();
        if (i == 0){
            integerList.sortSelection();
        }else if (i == 1){
            integerList.sortBubble();
        }else {
            integerList.sortInsertion();
        }
                       //ваш_метод_сортировки(arr);
        System.out.println(System.currentTimeMillis() - start);
//        IntegerListImpl.print(integerList);
    }
}