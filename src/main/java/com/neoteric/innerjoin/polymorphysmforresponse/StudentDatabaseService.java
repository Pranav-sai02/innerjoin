package com.neoteric.innerjoin.polymorphysmforresponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDatabaseService {

    @Autowired
    private DataSource dataSource;

    public String generateStudentId() {
        return "Neo" + (int) (Math.random() * 10000);
    }

    public String insertStudent(Students student) {
        String generatedStudentId = generateStudentId();
        student.setStudentId(generatedStudentId);

        String sql1 = "INSERT INTO students (studentId, studentName, studentAge) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO marks (studentId, subject, marks) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection()) {

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql1)) {
                preparedStatement.setString(1, generatedStudentId);
                preparedStatement.setString(2, student.getStudentName());
                preparedStatement.setInt(3, student.getStudentAge());
                preparedStatement.executeUpdate();
            }

            try (PreparedStatement preparedStatement = conn.prepareStatement(sql2)) {
                for (Subjects s : student.getSubjects()) {
                    preparedStatement.setString(1, generatedStudentId);
                    preparedStatement.setString(2, s.getSubject());
                    preparedStatement.setInt(3, s.getMarks());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

        } catch (Exception e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }

        return generatedStudentId;
    }

    public Students getStudentById(String studentId) {
        String sql = "SELECT students.studentName, students.studentAge, marks.subject, marks.marks " +
                "FROM students INNER JOIN marks ON students.studentId = marks.studentId " +
                "WHERE students.studentId = ?";

        Students student = null;
        List<Subjects> subjectList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, studentId);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (student == null) {
                    student = new Students();
                    student.setStudentId(studentId);
                    student.setStudentName(resultSet.getString("studentName"));
                    student.setStudentAge(resultSet.getInt("studentAge"));
                }

                Subjects subject = new Subjects();
                subject.setSubject(resultSet.getString("subject"));
                subject.setMarks(resultSet.getInt("marks"));
                subjectList.add(subject);
            }

            if (student != null) {
                student.setSubjects(subjectList);
            }

        } catch (Exception e) {
            System.out.println("Error fetching student: " + e.getMessage());
        }

        return student;
    }


    public double calculateAverage(List<Subjects> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            return 0;
        }
        double totalMarks = 0;
        for (Subjects subject : subjects) {
            totalMarks += subject.getMarks();
        }
        return totalMarks / subjects.size();
    }

}
