package co.edu.eafit.componentes.bankagent.dto;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id) throws SQLException;

    Collection<T> getAll() throws SQLException;

    void save(T t) throws SQLException;

    void update(T t) throws SQLException;

    void delete(T t) throws SQLException;
}