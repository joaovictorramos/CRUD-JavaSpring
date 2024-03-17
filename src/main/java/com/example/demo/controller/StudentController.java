package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.dtos.StudentRecordDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String start(StudentRecordDto studentRecordDto){
        return "start";
    }

    @GetMapping("/post-window")
    public String register(StudentRecordDto studentRecordDto) {
        return "event/register";
    }

    @GetMapping("/get-window")
    public String list(StudentRecordDto studentRecordDto){
        return "event/list";
    }
    
    @GetMapping("/getall-window")
    public String listAll(StudentRecordDto studentRecordDto){
        return "event/listAll";
    }

    @GetMapping("/put-window")
    public String update(StudentRecordDto studentRecordDto){
        return "event/update";
    }

    @GetMapping("/delete-window")
    public String delete(StudentRecordDto studentRecordDto){
        return "event/delete";
    }

    @PostMapping("/register-student")
    public String registerStudent(@Valid StudentRecordDto studentRecordDto, Model model){
        var student = new Student();
        BeanUtils.copyProperties(studentRecordDto, student);
        if(studentService.save(student)){
            model.addAttribute("postMessage", "Registered student sucessfully!");

        } else{
            model.addAttribute("postMessage", "Register erro");
        }
 
        return "event/register";
    }

    @GetMapping("/list-student")
    public String listStudent(@Valid String registration, Model model){
        List<Student> students = studentService.findByRegistration(registration.trim());
        if(students != null){
            model.addAttribute("showTable", true);
            model.addAttribute("students", students);

        }else{
            model.addAttribute("showTable", false);
            model.addAttribute("getMessageError", "Student not found!");
        }

        return "event/list";
    }

    @GetMapping("/find-all") 
    public String listAll(Model model){
        List<Student> students = studentService.findAll();
        if(students != null){
            model.addAttribute("students", students);

        }else {
            model.addAttribute("getMessageError", "Students not found!");
        }

        return "event/listAll";
    }

    @PostMapping("/update")
    public String update(@Valid StudentRecordDto studentRecordDto, Model model){
        var student = new Student();
        BeanUtils.copyProperties(studentRecordDto, student);
        if(studentService.update(student)){
            model.addAttribute("updateMessage", "Updated");

        }else{
            model.addAttribute("updateMessage", "Error!");
        }

        return "event/update";
    }  

    @PostMapping("/delete")
    public String delete(@Valid String registration, Model model){
        if(studentService.delete(registration)){
            model.addAttribute("deleteMessage", "Sucess!");

        }else{
            model.addAttribute("deleteMessage", "Error!");
        }
        return "event/delete";
    }
}
