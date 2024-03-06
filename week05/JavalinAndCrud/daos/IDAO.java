package JavalinAndCrud.daos;

import java.util.List;

public interface IDAO<T, K> {

    List<T> getAll();

    T getById(int id);

    void create(K ent);

    K update(T in);

    K delete(int id);


}
