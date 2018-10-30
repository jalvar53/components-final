package co.edu.eafit.componentes.bankagent.dto;

import co.edu.eafit.componentes.bankagent.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountDao implements Dao<Account> {

    private List<Account> accountList = new ArrayList<>();

    @Override
    public Optional<Account> get(int id) {
        return Optional.ofNullable(accountList.get(id));
    }

    @Override
    public Collection<Account> getAll() {
        return accountList.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    @Override
    public void save(Account account) {
        accountList.add(account);
        int index = accountList.size() - 1;
        account.setId(index);
    }

    @Override
    public void update(Account account) {
        accountList.set(account.getId(), account);
    }

    @Override
    public void delete(Account account) {
        accountList.set(account.getId(), null);
    }
}