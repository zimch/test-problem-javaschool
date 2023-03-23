package com.digdes.school;

public class Main {
    public static void main(String[] args) throws Exception {
        JavaSchoolStarter javaSchoolStarter = new JavaSchoolStarter();

        javaSchoolStarter.execute("INSERT VALUES ‘lastName’ = ‘Федоров’ , ‘id’=3, ‘age’=40, ‘active’=true");
        javaSchoolStarter.execute("INSERT VALUES ‘lastName’ = ‘Петров’ , ‘id’=2, ‘age’=12, ‘active’=true, ’cost’=34.2");

        System.out.println(javaSchoolStarter.getList());
    }
}
