package edu.greenriver.edu.saasproject.services;
import edu.greenriver.edu.saasproject.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService
{

    private List<Student> students = new ArrayList<>(List.of(
            new Student("None", "N/A", "N/A", "N/A")
    ));

    //CREATE
    public Student addStudent(String studentFName, String studentLName,
                           String studentRaag, String studentInstrument)
    {
        Student added = new Student(studentFName, studentLName, studentRaag, studentInstrument);
        students.add(added);
        return added;
    }

    //READ
    public List<Student> allStudents()
    {
        return students;
    }

    public List<Student> searchStudents(String queryValue)
    {
        return students.stream()
                .filter(student -> student.getStudentFName().toLowerCase()
                        .contains(queryValue.toLowerCase()) || student.getStudentLName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    //UPDATE
    public Student updateStudent(UUID id, String studentFName, String studentLName,
                           String studentRaag, String studentInstrument)
    {
        Optional<Student> foundStudent = students.stream()
                .filter(student -> student.getStudentID().equals(id))
                .findFirst();

        if (foundStudent.isPresent())
        {
            //update it
            Student student = foundStudent.get();
            student.setStudentFName(studentFName);
            student.setStudentLName(studentLName);
            student.setStudentRaag(studentRaag);
            student.setStudentInstrument(studentInstrument);
            return student;
        }
        else
        {
            //otherwise, return null
            return null;
        }
    }

    //DELETE
    public void deleteStudent(UUID id)
    {
        students = students.stream()
                .filter(student -> !student.getStudentID().equals(id))
                .toList();
    }

    public boolean studentExists(UUID id)
    {
        return students.stream()
                .anyMatch(student -> student.getStudentID().equals(id));
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "students=" + students +
                '}';
    }
}
