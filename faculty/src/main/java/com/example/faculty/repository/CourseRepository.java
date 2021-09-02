package com.example.faculty.repository;

import com.example.faculty.entety.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findAll();

    Course findById(int id);

    @Query("select distinct(c.name) from Course c")
    List<String> findAllCourseNames();

    @Query("select distinct(d.semesterDuration) from Course d")
    List<Integer> findAllDurations();

    @Query("select distinct(c.capacity) from Course c")
    List<Integer> findAllCapacities();

    @Query("select t.name from Topic t")
    List<String> findAllTopics();

    @Query("select t.id from Teacher t")
    List<Integer> findAllTeacherNames();

    @Query("select t.id from Teacher t where lower(concat(t.lastName,' ',t.firstName,' ',t.secondName)) like lower(concat('%',:name,'%') )")
    List<Integer> findTeacherIdByName(@Param("name") String name);

    @Query("select c.name from Course c where lower(c.name) like lower(concat('%',:name,'%') )")
    List<String> findCourseNameByName(@Param("name") String name);

    @Query("select c from Course c where " +
            "c.name in (:courseName) and " +
            "c.semesterDuration in (:duration) and " +
            "c.capacity in (:capacity) and " +
            "c.topic.name in (:topic) and " +
            "c.teacher.id in (:teacherId)")
    List<Course> findAllByParams(@Param("courseName") List<String> courseName,
                                 @Param("duration") List<Integer> duration,
                                 @Param("capacity") List<Integer> capacity,
                                 @Param("topic") List<String> topic,
                                 @Param("teacherId") List<Integer> teacherId);

}