package ying.example.mybatis;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.HashMap;

public interface StudentMapper {
    public Student selectStudent(int id);

    public Student[] selectStudentByNameIfPresent(String name);

    public Student[] selectStudentByNameIfPresentV2(String name);

    public Student[] selectStudentByNameAndAge(String name, int age);

    public Student[] selectStudentByNameAndAgeV2(@Param("name") String name, @Param("age") int age);

    public Student[] selectByObject(Student student);

//    @Results(
//            {@Result(column = "name", property = "name"),
//                    @Result(column = "age", property = "age"),
//                    @Result(column = "class_name", property = "className")}
//    )
    public Student[] selectByObjectV2(@Param("student") Student student);

    public Student[] selectByMap(@Param("map") HashMap<String, Object> map);

}
