package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValueAndDeleted(String value, boolean isDeleted);
    Optional<Token> findByValueAndDeletedEqualsAndExpiryAtGreaterThan(String value, boolean isDeleted, Date expiryGreaterThan);

}
