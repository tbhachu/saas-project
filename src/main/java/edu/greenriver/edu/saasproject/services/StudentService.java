package edu.greenriver.edu.saasproject.services;
import edu.greenriver.edu.saasproject.models.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for supporting CRUD functionality methods for Student objects
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Service
public class StudentService
{

    private List<Student> students = new ArrayList<>(List.of(
            new Student("None", "N/A", "N/A", "N/A")
    ));

    /**
     * @param studentFName Name of the student's first name
     * @param studentLName Name of the student's last name
     * @param studentRaag Name of the Raag the student wants to learn
     * @param studentInstrument Name of the instrument the student wants to learn
     * @return Returns a student object
     */
    //CREATE
    public Student addStudent(String studentFName, String studentLName,
                           String studentRaag, String studentInstrument)
    {
        Student added = new Student(studentFName, studentLName, studentRaag, studentInstrument);
        students.add(added);
        return added;
    }

    /**
     * @return Returns list of student objects
     */
    //READ
    public List<Student> allStudents()
    {
        return students;
    }

    /**
     * @param queryValue The specific student being searched for
     * @return Returns list of students
     */
    public List<Student> searchStudents(String queryValue)
    {
        return students.stream()
                .filter(student -> student.getStudentFName().toLowerCase()
                        .contains(queryValue.toLowerCase()) || student.getStudentLName().toLowerCase()
                        .contains(queryValue.toLowerCase()))
                .toList();
    }

    /**
     * @param uuid Unique ID of the student
     * @param studentFName Name of the student's first name
     * @param studentLName Name of the student's last name
     * @param studentRaag Name of the Raag that student wants to learn
     * @param studentInstrument Name of the instrument the student wants to learn
     * @return Returns Student object
     */
    //UPDATE
    public Student updateStudent(UUID uuid, String studentFName, String studentLName,
                           String studentRaag, String studentInstrument)
    {
        Optional<Student> foundStudent = students.stream()
                .filter(student -> student.getStudentID().equals(uuid))
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

    /**
     * @param uuid Unique ID of the student
     */
    //DELETE
    public void deleteStudent(UUID uuid)
    {
        students = students.stream()
                .filter(student -> !student.getStudentID().equals(uuid))
                .toList();
    }

    /**
     * @param uuid Unique ID of the student
     * @return Returns true or false value of whether student is in the list
     */
    public boolean studentExists(UUID uuid)
    {
        return students.stream()
                .anyMatch(student -> student.getStudentID().equals(uuid));
    }

    @Override
    public String toString() {
        return "StudentService{" +
                "students=" + students +
                '}';
    }
}
