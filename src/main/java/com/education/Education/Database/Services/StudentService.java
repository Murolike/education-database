package com.education.Education.Database.Services;

import com.education.Education.Database.Forms.StudentForm;
import com.education.Education.Database.Models.Student;
import com.education.Education.Database.Repositories.StudentRepository;
import com.education.Education.Database.Responses.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Iterable<StudentResponse> findAll() {
        Iterable<Student> cursor = this.repository.findAll();
        ArrayList<StudentResponse> models = new ArrayList<>();

        for (Student student : cursor) {
            StudentResponse model = StudentResponse.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .middleName(student.getMiddleName())
                    .build();

            models.add(model);
        }

        return models;
    }

    public Student findById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    public Student create(StudentForm studentForm) {
        Student student = new Student();
        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setMiddleName(studentForm.getMiddleName());

        return this.repository.save(student);
    }

    public StudentResponse getSingleResponse(Student student) {
        return StudentResponse
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .middleName(student.getMiddleName())
                .build();
    }

    public Student update(Student student, StudentForm studentForm) {
        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setMiddleName(studentForm.getMiddleName());

        return this.repository.save(student);
    }

    public void delete(Student student) {
        this.repository.delete(student);
    }
}
