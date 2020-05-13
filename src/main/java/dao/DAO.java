package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<A> {
    List<A> getAll();
    Optional<A> get(String name);
    boolean delete(String name);
}
