package co.edu.eafit.componentes.bankagent.dto;

import co.edu.eafit.componentes.bankagent.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TransactionDao implements Dao<Transaction> {

    private List<Transaction> transactionList = new ArrayList<>();

    @Override
    public Optional<Transaction> get(int id) {
        return Optional.ofNullable(transactionList.get(id));
    }

    @Override
    public Collection<Transaction> getAll() {
        return transactionList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public void save(Transaction transaction) {
        transactionList.add(transaction);
        int index = transactionList.size() - 1;
        transaction.setId(index);
    }

    @Override
    public void update(Transaction transaction) {
        transactionList.set(transaction.getId(), transaction);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionList.set(transaction.getId(), null);
    }
}