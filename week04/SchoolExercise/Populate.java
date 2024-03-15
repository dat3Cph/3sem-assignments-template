package SchoolExercise;

import SchoolExercise.dao.StudentDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class Populate {

    static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {

        //populate();

        StudentDAOImpl studentDAO = StudentDAOImpl.getInstance(emf);
        List<Student> firstNameList = studentDAO.findAllStudentsByFirstName("John");
        for (Student student : firstNameList) {
            System.out.println(student.getFirstName());
        }
        List<Student> lastNameList = studentDAO.findAllStudentsByLastName("Doe");
        for (Student student : lastNameList) {
            System.out.println(student.getLastName());
        }

        long count = studentDAO.findTotalNumberOfStudentsBySemester("First Semester");
        System.out.println(count);


        Teacher teacher = null;
        try(var em = emf.createEntityManager()){
            teacher = em.find(Teacher.class, 1);
        }

        long count2 = studentDAO.findTotalNumberOfStudentsByTeacher(teacher);
        System.out.println(count2);

        Teacher teacherWithMostSemesters = studentDAO.findTeacherWithMostSemesters();
        System.out.println(teacherWithMostSemesters.getFirstName());

        Semester semester = studentDAO.findSemesterWithFewestStudents();
        System.out.println(semester.getName());

    }


    public static void populate(){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createNativeQuery("insert into public.semester(description, name) values ('Introduction semester','First Semester'),('Algebra university level for beginners', 'Algebra 1'), ('Calculus university level for beginners', 'Calculus1')").executeUpdate();
            em.createNativeQuery("insert into public.student (firstname, lastname, currentsemester_id) values ('John','Doe',1),('Lise','Petersen',2), ('Morten', 'Larsen',1)").executeUpdate();
            em.createNativeQuery("insert into public.teacher (firstname, lastname) values ('Cathrine','Abagnail'), ('Jonathan', 'Meier')").executeUpdate();
            Teacher teacher1 = em.find(Teacher.class, 1);
            Semester semester = em.find(Semester.class, 1);
            Semester semester3 = em.find(Semester.class, 3);
            teacher1.addSemester(semester);
            teacher1.addSemester(semester3);
            em.merge(teacher1);

            Teacher teacher2 = em.find(Teacher.class, 2);
            Semester semester2 = em.find(Semester.class, 2);
            teacher2.addSemester(semester2);
            em.merge(teacher2);

            em.getTransaction().commit();
        }
    }

}
