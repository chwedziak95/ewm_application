package com.kc6379.zarzadaniemagazynem.repository;

import com.kc6379.zarzadaniemagazynem.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{

    Optional<VerificationToken> findByToken(String token);
}
