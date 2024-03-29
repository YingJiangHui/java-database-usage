package ying.example.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import ying.example.mybatis.mapper.StudentPOMapper;
import ying.example.mybatis.pojo.StudentPO;
import ying.example.utils.SqlSessionFactoryUtil;

import java.io.IOException;

public class DemoMybatisCache {
    @Test
    public void mybatisFirstCacheInvalidationWhenClearCache() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper = session.getMapper(StudentPOMapper.class);
        StudentPO studentByFirstQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentByFirstQuery);
//        强制清除缓存
        session.clearCache();
        StudentPO studentBySecondQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentBySecondQuery);
    }

    @Test
    public void mybatisFirstCacheInvalidationWhenUpdate() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper = session.getMapper(StudentPOMapper.class);
        StudentPO studentByFirstQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentByFirstQuery);
        StudentPO updateStudentExample = new StudentPO();
        updateStudentExample.setAge(5);
        updateStudentExample.setId(1);
        studentPOMapper.updateByPrimaryKeySelective(updateStudentExample);
        StudentPO studentBySecondQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentBySecondQuery);
    }

    @Test
    public void mybatisFirstCacheInvalidationWhenInsert() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper = session.getMapper(StudentPOMapper.class);
        StudentPO studentByFirstQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentByFirstQuery);
        StudentPO updateStudentExample = new StudentPO();
        updateStudentExample.setAge(5);
        updateStudentExample.setName("Daf");
        updateStudentExample.setCountry("Japan");
        studentPOMapper.insertSelective(updateStudentExample);
        StudentPO studentBySecondQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentBySecondQuery);
    }

    @Test
    public void mybatisFirstCacheInvalidationWhenDelete() throws IOException {
        SqlSession session = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper = session.getMapper(StudentPOMapper.class);
        StudentPO studentByFirstQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentByFirstQuery);
        studentPOMapper.deleteByPrimaryKey(1);
        StudentPO studentBySecondQuery = studentPOMapper.selectByPrimaryKey(1);
        System.out.println(studentBySecondQuery);
    }

    @Test
    public void mybatisFirstCacheMiss() throws IOException {
        SqlSession session1 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper1 = session1.getMapper(StudentPOMapper.class);
        SqlSession session2 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper2 = session2.getMapper(StudentPOMapper.class);
        System.out.println("session 1");
        System.out.println(studentPOMapper1.selectByPrimaryKey(1));
        StudentPO updateStudentExample = new StudentPO();
        updateStudentExample.setAge(10);
        updateStudentExample.setId(1);
        System.out.println("update 了 id = 1 的字段");
        studentPOMapper2.updateByPrimaryKeySelective(updateStudentExample);
//        session2.commit(); // 不提交不会执行修改数据库
        System.out.println("session 2");
        System.out.println(studentPOMapper2.selectByPrimaryKey(1));
//        session1 未命中缓存
        System.out.println("session 1");
        System.out.println(studentPOMapper1.selectByPrimaryKey(1));
    }

    @Test
    public void mybatisSecondCache() throws IOException {
        SqlSession session1 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper1 = session1.getMapper(StudentPOMapper.class);
        System.out.println(studentPOMapper1.selectByPrimaryKey(1));
//        session关闭后一级缓存推入二级缓存
        session1.close();
        SqlSession session2 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper2 = session2.getMapper(StudentPOMapper.class);
        System.out.println(studentPOMapper2.selectByPrimaryKey(1));
    }
    @Test
    public void mybatisSecondCacheClear() throws IOException {
//        一级缓存除了增删改可以清空外还可以手动清空，二级缓存只能被增删改清空缓存
        SqlSession session1 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper1 = session1.getMapper(StudentPOMapper.class);
        System.out.println(studentPOMapper1.selectByPrimaryKey(1));
//        session关闭后一级缓存推入二级缓存
        session1.close();
        SqlSession session2 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper2 = session2.getMapper(StudentPOMapper.class);
        System.out.println("命中二级缓存");
        System.out.println(studentPOMapper2.selectByPrimaryKey(1));
        session2.close();

//        更新操作清空二级缓存
        SqlSession session3 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper3 = session3.getMapper(StudentPOMapper.class);
        StudentPO studentPO = new StudentPO();
        studentPO.setId(1);
        studentPO.setAge(1);
        studentPOMapper3.updateByPrimaryKeySelective(studentPO);
        System.out.println("提交更新操作");
        session3.commit();
        session3.close();

//        更新操作清空二级缓存
        SqlSession session4 = SqlSessionFactoryUtil.getSession();
        StudentPOMapper studentPOMapper4 = session4.getMapper(StudentPOMapper.class);
        System.out.println("未命中二级缓存被清空");
        System.out.println(studentPOMapper4.selectByPrimaryKey(1));
        session4.close();
    }
}
