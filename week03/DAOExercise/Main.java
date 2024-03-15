package DAOExercise;

import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        StudentDAOImpl studentDAO = StudentDAOImpl.getInstance(emf);

        studentDAO.create(new Student("Peter"));
        studentDAO.create(new Student("Lars"));
        studentDAO.create(new Student("Morten"));
        studentDAO.create(new Student(""));
        studentDAO.create(new Student("Kurt"));

        Student readStudent = studentDAO.read(4);
        System.out.println(readStudent.getName());

        readStudent.setName("PeterChanged");
        studentDAO.update(readStudent, readStudent.getId());

        Student found2 = studentDAO.read(4);
        System.out.println(found2.getName());

        studentDAO.delete(4);

    }
}
