package com.ebanking.ebanking.repository;

import com.ebanking.ebanking.model.PrimaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryAccountRepository extends JpaRepository<PrimaryAccount, Long> {
    PrimaryAccount findByNumber(int number);
    PrimaryAccount findByUsername(String username);
}
