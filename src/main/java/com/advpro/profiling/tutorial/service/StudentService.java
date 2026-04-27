package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author muhammad.khadafi
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    /**
     * cukup call findAll() dari repository yang menampung relasi.
     * JPA/Hibernate akan menangani pengambilan data student dan course-nya
     * dalam query yang jauh lebih efisien daripada looping manual satu-satu.
     */
    public List<StudentCourse> getAllStudentsWithCourses() {
        return studentCourseRepository.findAll();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        // SQL-nya nanti bakal jadi -> SELECT * FROM students ORDER BY gpa DESC LIMIT 1;
        return studentRepository.findFirstByOrderByGpaDesc();
    }

    public String joinStudentNames() {
        List<Student> students = studentRepository.findAll();
        // pake StringBuilder, supaya gak makan banyak memori.
        return students.stream().map(Student::getName).collect(Collectors.joining(", "));
    }
}

