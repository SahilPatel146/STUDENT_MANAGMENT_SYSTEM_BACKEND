package in.ac.charusat.studentmgmtsystem.controller;

import in.ac.charusat.studentmgmtsystem.model.Student;
import in.ac.charusat.studentmgmtsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="https://app-dummy123.herokuapp.com")
public class StudentController {
    List<Student> student = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Tom", "US"),
                    new Student(2, "Harry", "Canada"),
                    new Student(3, "Nick", "UK")
            )
    );

    @Autowired
    StudentRepository studentRepository;
//    List<Student> students = new ArrayList<>(
    //    List<Student> students = new ArrayList<>(
//            Arrays.asList(
//                    new Student(1, "Tom", "US"),
//                    new Student(2, "Harry", "Canada"),
//                    new Student(3, "Nick", "UK")
//            )
//    );

    // Mappings - URL endpoints
    // Get the list of all student
    @GetMapping("/listStudents")
    public List<Student> getAllStudents(){
        return student;
        public List<Student> getAllStudents() {
            return studentRepository.findAll();
        }

        // Get the student information
        @GetMapping("/student/{id}")
        public Student getStudent(@PathVariable Integer id){
            for(int i=0; i< student.size(); i++){
                if(student.get(i).getId()==id){
                    return (List<Student>) student.get(i);
                }
            }
            return null;
            public Student getStudent(@PathVariable Integer id) {
                return studentRepository.findById(id).get();
            }

            // Delete the student
            @DeleteMapping("/student/{id}")
            public List<Student> deleteStudent(@PathVariable Integer id){
                for(int i=0; i< student.size(); i++){
                    if(student.get(i).getId()==id){
                        student.remove(i);
                    }
                }
                return student;
                public List<Student> deleteStudent(@PathVariable Integer id) {
                    studentRepository.delete(studentRepository.findById(id).get());
                    return studentRepository.findAll();
                }

                // Add new student
                @PostMapping("/student")
                public List<Student> addStudent(@RequestBody Student student){
                    student.add(student);
                    return student;
                    public List<Student> addStudent(@RequestBody Student student) {
                        studentRepository.save(student);
                        return studentRepository.findAll();
                    }

                    // Update the student information
                    @PutMapping("/student/{id}")
                    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id){
                        for(int i=0; i< student.size(); i++){
                            if(student.get(i).getId()==id){
                                student.get(i).setName(student.getName());
                                student.get(i).setAddress(student.getAddress());
                            }
                        }
                        return student;
                        public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
                            Student studentObj = studentRepository.findById(id).get();
                            studentObj.setName(student.getName());
                            studentObj.setAddress(student.getAddress());
                            studentRepository.save(studentObj);
                            return studentRepository.findAll();
                        }

                    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    // Get the student information
    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentRepository.findById(id).get();
    }
    // Delete the student
    @DeleteMapping("/student/{id}")
    public List<Student> deleteStudent(@PathVariable Integer id) {
        studentRepository.delete(studentRepository.findById(id).get());
        return studentRepository.findAll();
    }
    // Add new student
    @PostMapping("/student")
    public List<Student> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return studentRepository.findAll();
    }
    // Update the student information
    @PutMapping("/student/{id}")
    public List<Student> updateStudent(@RequestBody Student student, @PathVariable Integer id) {
        Student studentObj = studentRepository.findById(id).get();
        studentObj.setName(student.getName());
        studentObj.setAddress(student.getAddress());
        studentRepository.save(studentObj);
        return studentRepository.findAll();
    }
}