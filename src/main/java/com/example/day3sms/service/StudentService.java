package com.example.day3sms.service;

import com.example.day3sms.DTO.StudentRequestDTO;
import com.example.day3sms.DTO.StudentResponseDTO;
import com.example.day3sms.Exception.StudentNotFoundException;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public StudentResponseDTO addStudent( StudentRequestDTO dto){
        StudentModel student = new StudentModel(); // 1st object for database
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student); //2nd object for user, client, response

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }
    private final StudentRepo repository;

    public StudentService(StudentRepo repository) {
        this.repository = repository;
    }

    //Create
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }



    //Display Structure

    public List<StudentResponseDTO> getStudents(){
        return repository.findAll() // convert studentModel data into StudentResponseDTO
                .stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getEmail()
                )).toList();
    }


    //Update Structure
    public StudentResponseDTO updateStudent(String id , StudentRequestDTO student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("No Student Found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        StudentModel updatedStudent = repository.save(existingStudent);

        return new StudentResponseDTO(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getEmail()
        );
    }

    //Delete Structure
    public StudentResponseDTO deleteStudent(String id) {

        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));

        repository.deleteById(id);
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail()
        );
    }


}
