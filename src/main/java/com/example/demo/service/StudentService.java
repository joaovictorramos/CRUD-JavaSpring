package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public boolean save(Student student){
        String name = student.getName().trim();
        String lastname = student.getLastname().trim();
        String registration = student.getRegistration().trim();

        if(name.isEmpty() || lastname.isEmpty() || registration.isEmpty()){
            return false;
    
        } else{
            studentRepository.save(student);
        }
        return true;
    }

    public List<Student> findByRegistration(String registration){
        List<Student> students = null;
        if(registration.isEmpty()){
            return null;

        } else{
            students = studentRepository.findByRegistration(registration);
            if(students.isEmpty()){
                return null;
            }
        }
        return students;
    }

    public List<Student> findAll(){
        List<Student> students = (List<Student>) studentRepository.findAll();
        if(students.isEmpty()){
            return null;
        }
        return students;
    }

    public boolean update(Student student){
        String name = student.getName().trim();
        String lastname = student.getLastname().trim();
        String registration = student.getRegistration().trim();

        if(name.isEmpty() || lastname.isEmpty() || registration.isEmpty()){
            return false;

        }else{
            studentRepository.updateByRegistration(name, lastname, registration);
            return true;
        }
    }

    public boolean delete(String registration){
        if(registration.trim().isEmpty()){
            return false;

        }else{
            studentRepository.deleteByRegistration(registration);
            return true;
        }
    }
}
