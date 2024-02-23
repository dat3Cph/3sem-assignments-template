package SchoolExercise.dao;

import SchoolExercise.Semester;
import SchoolExercise.Student;
import SchoolExercise.StudentInfo;
import SchoolExercise.Teacher;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO{

    private static EntityManagerFactory emf;
    private static StudentDAOImpl instance;

    public static StudentDAOImpl getInstance(EntityManagerFactory emf_){
        if(instance == null){
            emf = emf_;
            instance = new StudentDAOImpl();
        }
        return instance;
    }


    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        try(var em = emf.createEntityManager()){
            TypedQuery<Student> query = em.createNamedQuery("Student.findAllByFirstName", Student.class);
            query.setParameter("value",firstName);
            return query.getResultList();
        }
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        try(var em = emf.createEntityManager()){
            TypedQuery<Student> query = em.createNamedQuery("Student.findAllByLastName", Student.class);
            query.setParameter("value",lastName);
            return query.getResultList();
        }
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        try(var em = emf.createEntityManager()){
            Query query = em.createNamedQuery("Student.findTotalNumberOfStudentsBySemester");
            query.setParameter("value",semesterName);
            return Long.parseLong(query.getSingleResult().toString());
        }
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        try(var em = emf.createEntityManager()){
            Query query = em.createNamedQuery("Student.findTotalNumberOfStudentsByTeacher");
            query.setParameter("value",teacher.getId());
            return Long.parseLong(query.getSingleResult().toString());
        }
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        try(var em = emf.createEntityManager()){
            TypedQuery<Teacher> query = em.createQuery("select t from SchoolExercise.Teacher t join t.teaches ts group by t.id order by count(ts) desc", SchoolExercise.Teacher.class);
            return query.getResultList().get(0);
        }
    }

    @Override
    public Semester findSemesterWithFewestStudents() {
        try(var em = emf.createEntityManager()){
            TypedQuery<Semester> query = em.createQuery("select s from SchoolExercise.Semester s join SchoolExercise.Student s2 on s.id = s2.id group by s.id order by count(s2) asc", SchoolExercise.Semester.class);
            return query.getResultList().get(0);
        }
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        try(var em = emf.createEntityManager()){
            TypedQuery<StudentInfo> query = em.createQuery("select new SchoolExercise.StudentInfo(concat(s.firstName,' ', s.lastName), s.id,s.currentSemester.name, s.currentSemester.description) from SchoolExercise.Student s where s.id = :value", SchoolExercise.StudentInfo.class);
            query.setParameter("value",id);
            return query.getSingleResult();
        }
    }
}
