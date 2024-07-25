package org.automate.demand.ltc.repository;

import org.automate.demand.ltc.entity.Token;
import org.automate.demand.ltc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select e from User e where e.deleted=false and e.id =:id")
    Optional<User> findById(Long id);

    @Query("select e from User e where e.deleted=false")
    List<User> findAll();

    @Modifying
    @Query("update User e set e.deleted = true where e.id = :id")
     void deleteById(Long id);

    User save(User user);

    Token save(Token token);

    Optional<User> findByEmail(@Param("email") String email);







}
