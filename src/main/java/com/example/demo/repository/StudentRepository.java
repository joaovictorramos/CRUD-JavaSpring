package com.example.demo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Student;

import jakarta.transaction.Transactional;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student, Long>{
    List<Student> findByRegistration(String registration);

    @Transactional
    @Modifying
    @Query("UPDATE Student s SET s.name = :name, s.lastname = :lastname WHERE s.registration = :registration")
    void updateByRegistration(@Param("name") String name, @Param("lastname") String lastname, @Param("registration") String registration);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.registration = :registration")
    void deleteByRegistration(String registration);
}
