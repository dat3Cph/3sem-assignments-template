package SchoolExercise.dao;

import SchoolExercise.Semester;
import SchoolExercise.Student;
import SchoolExercise.StudentInfo;
import SchoolExercise.Teacher;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO{
    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        return null;
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        return 0;
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        return 0;
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        return null;
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        return null;
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        return null;
    }
}
