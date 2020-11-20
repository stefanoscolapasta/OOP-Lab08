package it.unibo.oop.lab.codeanalysis;

import java.util.List;
import java.util.Random;

public class useStream {
    public static void main(String[] args) {
        final List<Integer> li = List.of(10,20,30,40,50,60,70,80,90);
        Random randomno = new Random();
        System.out.println(randomno.nextInt(3));
        
    }
}
