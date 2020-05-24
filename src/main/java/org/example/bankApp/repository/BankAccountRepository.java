package org.example.bankApp.repository;

import org.example.bankApp.model.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rohitsharma
 */

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
}
