package com.ebanking.ebanking.repository;

import com.ebanking.ebanking.model.PrimaryTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryTransactionRepository extends JpaRepository<PrimaryTransaction, Long> {
}
