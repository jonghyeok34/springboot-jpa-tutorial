package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.example.demo.model.jpa.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaDao extends JpaRepository<Person, Long> {

    // @Query("SELECT id, name FROM person")
    // List<Person> findAllPeople();

    default UUID generateRandomId(){
        return UUID.randomUUID();
    }
    @Query(value="SELECT id, name FROM person WHERE id= ?1", nativeQuery=true)
    Optional<Person> findById(UUID id);
    
    @Modifying
    @Transactional
    @Query(value="DELETE FROM person WHERE id= ?1", nativeQuery=true)
    void deleteById(@Param("id") UUID id);

    // @Query("UPDATE SET name=?2 WHERE id=?1")
    // int updatePersonById(UUID id, String name);
}