package com.example.Ex23;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final List<Student> students;

    public StudentService(){
        students = new ArrayList<>();
        students.add(new Student(1L,"Eduard","Ancuta","eduard962010@gmail.com"));
        students.add(new Student(2L,"Marco","Rossi","marcorossi32@gmail.com"));
        students.add(new Student(3L,"Marta","Bianchi","martabianchi@gmail.com"));
        students.add(new Student(4L,"Giuseppe","Verdi","giuseppeverdi21@gmail.com"));
    }

    public void sendEmailToStudent(long contactId, String subject, String text) {
        Optional<Student> optionalStudent = students.stream().filter(s -> s.getId() == contactId).findFirst();
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(student.getEmail());
            message.setSubject(subject);
            message.setText(text);
            message.setReplyTo("dantesandwich3@gmail.com");
            message.setFrom("alessio.limina90@gmail.com");
            javaMailSender.send(message);
        } else {
            throw new IllegalArgumentException("Student not found with contact ID " + contactId);
        }
    }






}
