package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * This class builds a Student object to store into a list.
 *
 * @author Tarsem Bhachu
 * @version 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student
{
    @Id
    @GeneratedValue
    private UUID studentID;

    private String studentFName;
    private String studentLName;
    private String studentRaag;
    private String studentInstrument;

    /**
     * @param studentFName Name of the first name of student
     * @param studentLName Name of the last name of student
     * @param studentRaag Name of Raag student wants to learn
     * @param studentInstrument Instrument student wants to learn
     */
    public Student(String studentFName, String studentLName, String studentRaag, String studentInstrument) {
        studentID = UUID.randomUUID();
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.studentRaag = studentRaag;
        this.studentInstrument = studentInstrument;
    }

    /**
     * @return Unique ID of the student
     */
    public UUID getStudentID() {
        return studentID;
    }

    /**
     * @return Returns the student's first name
     */
    public String getStudentFName() {
        return studentFName;
    }

    /**
     * @param studentFName Accepts the student's first name
     */
    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    /**
     * @return Returns the student's last name
     */
    public String getStudentLName() {
        return studentLName;
    }

    /**
     * @param studentLName Sets the student's last name
     */
    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    /**
     * @return Returns the name of the Raag that the student wants to learn
     */
    public String getStudentRaag() {
        return studentRaag;
    }

    /**
     * @param studentRaag Accepts the name of the Raag the student wants to learn
     */
    public void setStudentRaag(String studentRaag) {
        this.studentRaag = studentRaag;
    }

    /**
     * @return Returns the name of the instrument that the student wants to learn
     */
    public String getStudentInstrument() {
        return studentInstrument;
    }

    /**
     * @param studentInstrument Accepts the name of the instrument the student wants to learn
     */
    public void setStudentInstrument(String studentInstrument) {
        this.studentInstrument = studentInstrument;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentFName='" + studentFName + '\'' +
                ", studentLName='" + studentLName + '\'' +
                ", studentRaag='" + studentRaag + '\'' +
                ", studentInstrument='" + studentInstrument + '\'' +
                '}';
    }
}
