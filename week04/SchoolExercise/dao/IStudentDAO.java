package SchoolExercise.dao;

import SchoolExercise.Semester;
import SchoolExercise.Student;
import SchoolExercise.StudentInfo;
import SchoolExercise.Teacher;

import java.util.List;

public interface IStudentDAO {

    List<Student> findAllStudentsByFirstName(String firstName);


    List<Student> findAllStudentsByLastName(String lastName);


    long findTotalNumberOfStudentsBySemester(String semesterName);


    long findTotalNumberOfStudentsByTeacher(Teacher teacher);


    Teacher findTeacherWithMostSemesters();


    Semester findSemesterWithFewestStudents();


    StudentInfo getAllStudentInfo(int id);
}