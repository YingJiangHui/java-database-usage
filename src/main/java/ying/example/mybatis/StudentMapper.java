package ying.example.mybatis;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    public Student selectStudent(int id);
    public Student[] selectStudentByNameIfPresent(String name);

    public Student[] selectStudentByNameIfPresentV2(String name);

    public Student[] selectStudentByNameAndAge(String name, int age);

    public Student[] selectStudentByNameAndAgeV2(@Param("name") String name,@Param("age") int age);

}
