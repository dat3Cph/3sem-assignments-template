package SchoolExercise;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createNativeQuery("insert into public.semester(description, name) values ('Introduction semester','First Semester'),('Algebra university level for beginners', 'Algebra 1'), ('Calculus university level for beginners', 'Calculus1')").executeUpdate();
            em.createNativeQuery("insert into public.student (firstname, lastname, currentsemester_id) values ('John','Doe',1),('Lise','Petersen',2), ('Morten', 'Larsen',1)").executeUpdate();
            em.createNativeQuery("insert into public.teacher (firstname, lastname) values ('Cathrine','Abagnail'), ('Jonathan', 'Meier')").executeUpdate();
            Teacher teacher1 = em.find(Teacher.class, 1);
            Semester semester = em.find(Semester.class, 1);
            teacher1.addSemester(semester);
            em.merge(teacher1);

            Teacher teacher2 = em.find(Teacher.class, 2);
            Semester semester2 = em.find(Semester.class, 2);
            teacher2.addSemester(semester2);
            em.merge(teacher2);

            em.getTransaction().commit();
        }



    }
}
