package SchoolExercise.dao;

import Recycling.config.HibernateConfig;
import SchoolExercise.Semester;
import SchoolExercise.Student;
import SchoolExercise.Teacher;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDAOImplTest {

    static EntityManagerFactory emf;
    static StudentDAOImpl studentDAO;
    static List<Integer> studentIds = new ArrayList<>();
    static List<Integer> teacherIds = new ArrayList<>();
    static List<Integer> semesterIds = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static List<Teacher> teachers = new ArrayList<>();
    static List<Semester> semesters = new ArrayList<>();

    @BeforeEach
    void beforeAll(){
        emf = SchoolExercise.HibernateConfig.getEntityManagerFactoryConfigForTesting();
        studentDAO = StudentDAOImpl.getInstance(emf);
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student student1 = new Student("John","Doe");
            Student student2 = new Student("Genevieve","Gyllehand");
            Student student3 = new Student("Peter","Spangsberg");
            Student student4 = new Student("Morten", "Petersen");
            Student student5 = new Student("Sigrud","Torden");
            Student student6 = new Student("Knud","Ygdrasen");

            Semester semester1 = new Semester("First Semester","A very cool first semester!");
            Semester semester2 = new Semester("Second Semester","A very cool second semester!");
            Semester semester3 = new Semester("Third Semester","A very cool Third semester!");

            Teacher teacher1 = new Teacher("Karen","Smith");
            Teacher teacher2 = new Teacher("James", "Charles");

            student1.addSemester(semester1);
            student2.addSemester(semester1);
            student3.addSemester(semester2);
            student4.addSemester(semester2);
            student5.addSemester(semester2);
            student6.addSemester(semester3);

            teacher1.addSemester(semester1);
            teacher1.addSemester(semester3);
            teacher2.addSemester(semester2);

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);
            em.persist(student6);

            em.persist(teacher1);
            em.persist(teacher2);

            em.persist(semester1);
            em.persist(semester2);
            em.persist(semester3);

            em.getTransaction().commit();
        }


        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            TypedQuery<Student> query1 = em.createQuery("select s from SchoolExercise.Student s", SchoolExercise.Student.class);
            TypedQuery<Teacher> query2 = em.createQuery("select t from SchoolExercise.Teacher t", SchoolExercise.Teacher.class);
            TypedQuery<Semester> query3 = em.createQuery("select s from SchoolExercise.Semester s", SchoolExercise.Semester.class);
            students = query1.getResultList();
            teachers = query2.getResultList();
            semesters = query3.getResultList();
            em.getTransaction().commit();
        }
        for(Student s : students){
            studentIds.add(s.getId());
        }
        for(Teacher t : teachers){
            teacherIds.add(t.getId());
        }
        for(Semester s: semesters){
            semesterIds.add(s.getId());
        }

    }

    @Test
    void findAllStudentsByFirstName() {
        List<Student> studentsNamedJohn = studentDAO.findAllStudentsByFirstName("John");
        for(Student s: studentsNamedJohn){
            assertEquals("John",s.getFirstName());
        }
    }

    @Test
    void findAllStudentsByLastName() {
        List<Student> studentsNamedDoe = studentDAO.findAllStudentsByLastName("Doe");
        for(Student s: studentsNamedDoe){
            assertEquals("Doe",s.getLastName());
        }
    }

    @Test
    void findTotalNumberOfStudentsBySemester() {
       long res = studentDAO.findTotalNumberOfStudentsBySemester("Second Semester");
       assertEquals(3,res);
    }

    @Test
    void findTotalNumberOfStudentsByTeacher() {
        long res = studentDAO.findTotalNumberOfStudentsByTeacher(teachers.get(0));
        assertEquals(2,res);
    }

    @Test
    void findTeacherWithMostSemesters() {
        assertEquals(teachers.get(0).getId(),studentDAO.findTeacherWithMostSemesters().getId());
    }

    @Test
    void findSemesterWithFewestStudents() {
        assertEquals(semesterIds.get(1),studentDAO.findSemesterWithFewestStudents().getId());
    }

    @Test
    void getAllStudentInfo() {
        assertNotNull(studentDAO.getAllStudentInfo(1));
        assertEquals(students.get(0).getId(),studentDAO.getAllStudentInfo(1).getStudentId());

    }
}