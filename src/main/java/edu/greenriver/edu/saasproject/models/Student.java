package edu.greenriver.edu.saasproject.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

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

    public Student(String studentFName, String studentLName, String studentRaag, String studentInstrument) {
        studentID = UUID.randomUUID();
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.studentRaag = studentRaag;
        this.studentInstrument = studentInstrument;
    }

    public UUID getStudentID() {
        return studentID;
    }

    public void setStudentID(UUID studentID) {
        this.studentID = studentID;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    public String getStudentRaag() {
        return studentRaag;
    }

    public void setStudentRaag(String studentRaag) {
        this.studentRaag = studentRaag;
    }

    public String getStudentInstrument() {
        return studentInstrument;
    }

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
