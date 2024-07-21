package org.automate.demand.ltc.service;

import org.automate.demand.ltc.domain.Student;
import org.automate.demand.ltc.repository.StudentRepository;
import org.automate.demand.ltc.entity.StudentEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentsService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public List<Student> findAll(){
        return studentRepository.findAll().stream()
                .map(studentEntity -> modelMapper.map(studentEntity, Student.class)).collect(Collectors.toList());
    }

    public Student findById(Long id){
        StudentEntity existingStudent = studentRepository.findById(id).orElseThrow();
        return modelMapper.map(existingStudent, Student.class);
    }

    public Student add(Student student){
        StudentEntity studentEntity = modelMapper.map(student, StudentEntity.class);
        studentEntity = studentRepository.save(studentEntity);
        return modelMapper.map(studentEntity, Student.class);
    }

    public Student update(Student student){
        StudentEntity existingStudent = studentRepository.findById(student.id()).orElseThrow();
        existingStudent.setFirstName(student.firstName());
        existingStudent.setLastName(student.lastname());
        return modelMapper.map(existingStudent, Student.class);
    }
}
