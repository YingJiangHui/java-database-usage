package ying.example.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import ying.example.mybatis.mapper.StudentPOMapper;
import ying.example.mybatis.pojo.StudentPO;
import ying.example.mybatis.pojo.StudentPOExample;
import ying.example.utils.SqlSessionFactoryUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DemoMyBatisGeneratorTest {
    @Test
    public void QBCExample() {
        try {
            SqlSession sqlSession = SqlSessionFactoryUtil.getSession();
            StudentPOMapper mapper = sqlSession.getMapper(StudentPOMapper.class);
            StudentPOExample studentPOExample = new StudentPOExample();
            StudentPOExample.Criteria criteria = studentPOExample.createCriteria();
            criteria.andAgeLessThan(20);
            studentPOExample.or().andClassNameEqualTo("B");
            studentPOExample.setOrderByClause("age desc");
            List<StudentPO> studentList = mapper.selectByExample(studentPOExample);
            studentList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void withRowBounds() {
        try {
            SqlSession sqlSession = SqlSessionFactoryUtil.getSession();
            StudentPOMapper mapper = sqlSession.getMapper(StudentPOMapper.class);
            StudentPOExample studentPOExample = new StudentPOExample();
            StudentPOExample.Criteria criteria = studentPOExample.createCriteria();
            criteria.andAgeLessThan(20);
            studentPOExample.or().andClassNameEqualTo("B");
            studentPOExample.setOrderByClause("age desc");
            RowBounds rowBounds = new RowBounds(1,5);
            List<StudentPO> studentList = mapper.selectByExampleWithRowbounds(studentPOExample, rowBounds);
            studentList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
