package ying.example.mybatis;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

public class Student {
    int id;
    String name;
    int age;
    String className;


    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", className='" + className + '\'' +
                '}';
    }
}
