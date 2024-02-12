package Part2;

import Part1.Unicorn;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {
    public static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
        //Entity is in transient state:
        Student student1 = new Student("Lars","Petersen","lars@coolmail.dk",54);
        Student student2 = new Student("Peter","Larsen","Peter@nicemail.dk",22);
        Student student3 = new Student("Christian","Høj","Christian@kommunise.dk",34);
        createStudent(student1);
        createStudent(student2);
        createStudent(student3);

        Student found = readStudent(1);
        System.out.println(found);

        found.setFirstName("ChangedFirstName");
        Student updatedStudent = updateStudent(found);
        System.out.println(updatedStudent);

        deleteStudent(3);

        List<Student> listOfStudents = readAllStudents();
        listOfStudents.forEach(System.out::println);

    }


    public static void createStudent(Student student){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            //Entity is in managed state after persist:
            em.persist(student);
            //Entity is in detached state after transaction commit.
            em.getTransaction().commit();
        }
    }

    public static Student readStudent(int id){
        try(var em = emf.createEntityManager()){
            //Managed and detached immediately :
            return em.find(Student.class, id);
        }
    }

    public static Student updateStudent(Student in){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();

            //Managed
            Student found = em.find(Student.class,in.getId());
            if(found != null){
                //Managed
                Student updatedStudent = em.merge(in);
                //Detached
                em.getTransaction().commit();
                return updatedStudent;
            }
            return null;
        }
    }

    public static void deleteStudent(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Student found = readStudent(id);
            if(found != null){
                //Removed
                em.remove(found);
            }
            em.getTransaction().commit();
        }
    }

    public static List<Student> readAllStudents(){
        try(var em = emf.createEntityManager()){
            //Managed:
            var query = em.createQuery("select s from Student s", Student.class);
            //Detached:
            return query.getResultList();
        }
    }

}
