package ying.example.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import ying.example.utils.SqlSessionFactoryUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class DemoDifferenceArgsTest {
    @Test
    public void IfStatementTest() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student[] student = studentMapper.selectStudentByNameIfPresent("小明");
        Arrays.stream(student).forEach(System.out::println);
    }
    @Test
    public void IfStatementTestV2() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student[] student = studentMapper.selectStudentByNameIfPresentV2(null);
        Arrays.stream(student).forEach(System.out::println);
    }
    @Test
    public void TestSelectByNameAndAge() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student[] student = studentMapper.selectStudentByNameAndAge("小明",10);
        Arrays.stream(student).forEach(System.out::println);
    }

    @Test
    public void TestSelectByNameAndAgeV2() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student[] student = studentMapper.selectStudentByNameAndAgeV2("小明",10);
        Arrays.stream(student).forEach(System.out::println);
    }

    @Test
    public void SelectByObject() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setAge(10);
        Student[] students = studentMapper.selectByObject(student);
        Arrays.stream(students).forEach(System.out::println);
    }
    @Test
    public void SelectByObjectV2() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setAge(10);
        Student[] students = studentMapper.selectByObjectV2(student);
        Arrays.stream(students).forEach(System.out::println);
    }
    @Test
    public void SelectByMap() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",20);
        Student[] students = studentMapper.selectByMap(map);
        Arrays.stream(students).forEach(System.out::println);
    }

    @Test
    public void selectByMapResultMap() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",20);
        HashMap[] studentMaps = studentMapper.selectByMapResultMap(map);
        Arrays.stream(studentMaps).forEach(System.out::println);
    }

    @Test
    public void selectStudentByOne() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setClassName("B");
        Student[] students = studentMapper.selectStudentByOne(student);
        Arrays.stream(students).forEach(System.out::println);
    }
}