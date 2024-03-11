package ying.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import ying.example.utils.SqlSessionFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static org.junit.Assert.*;

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
}