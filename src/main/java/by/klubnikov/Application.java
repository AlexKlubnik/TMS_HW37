package by.klubnikov;

import by.klubnikov.config.AppConfig;
import by.klubnikov.model.Course;
import by.klubnikov.model.Student;
import by.klubnikov.service.CourseService;
import by.klubnikov.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CourseService courseService = context.getBean("courseService", CourseService.class);
        StudentService studentService = context.getBean("studentService", StudentService.class);

//        Student deletableStudent = studentService.findById(5).orElseThrow();
        System.out.println(studentService.findAll());
        studentService.save(new Student(4, "Yarik", "Minsk"));
//        studentService.delete(deletableStudent);
        System.out.println(studentService.findAll());
        System.out.println("===============================");

//        Course deletableCourse = courseService.findById(1).orElseThrow();
        System.out.println(courseService.findAll());
        System.out.println(courseService.findById(2));
        courseService.save(new Course(4, "Physics", 14));
        System.out.println(courseService.findAll());
//        courseService.delete(deletableCourse);
//        System.out.println(courseService.findAll());

    }


}