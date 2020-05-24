package org.example.bankApp.repository;

import org.example.bankApp.model.Branch;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rohitsharma
 */

@Repository
public interface HeadOfficeRepository extends CrudRepository<Branch, Integer> {

}
