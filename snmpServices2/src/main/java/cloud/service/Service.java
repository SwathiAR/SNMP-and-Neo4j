package cloud.service;

/**
 * Created by swathi on 8/6/2015.
 */
public interface Service<T> {

    Iterable<T> findAll();

    T find(Long id);

    void delete(Long id);

    T createOrUpdate(T object);

}