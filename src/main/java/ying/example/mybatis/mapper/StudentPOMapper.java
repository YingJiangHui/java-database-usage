package ying.example.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import ying.example.mybatis.pojo.StudentPO;
import ying.example.mybatis.pojo.StudentPOExample;

public interface StudentPOMapper {
    long countByExample(StudentPOExample example);

    int deleteByExample(StudentPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StudentPO row);

    int insertSelective(StudentPO row);

    List<StudentPO> selectByExampleWithRowbounds(StudentPOExample example, RowBounds rowBounds);

    List<StudentPO> selectByExample(StudentPOExample example);

    StudentPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") StudentPO row, @Param("example") StudentPOExample example);

    int updateByExample(@Param("row") StudentPO row, @Param("example") StudentPOExample example);

    int updateByPrimaryKeySelective(StudentPO row);

    int updateByPrimaryKey(StudentPO row);
}