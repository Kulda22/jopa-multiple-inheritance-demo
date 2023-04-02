package cz.cvut.kbss.jopa.multipleinheritancedemo.rest;


import com.github.javafaker.Faker;
import cz.cvut.kbss.jopa.model.EntityManager;
import cz.cvut.kbss.jopa.model.EntityManagerFactory;
import cz.cvut.kbss.jopa.multipleinheritancedemo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    Faker f = new Faker();

    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    EntityManager em;

    public StudentController(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent() {
        String key = Long.toString(System.currentTimeMillis());
        Student s = new Student();
        s.setKey(key);

        s.setFirstName(f.address().firstName());
        s.setLastName(f.address().lastName());
        s.generateUri();

        assert s != null;
        assert s.getUri() != null;

        LOG.error("saving {} ", s);
        em.persist(s);
        em.clear();


        return em.find(Student.class, s.getUri());

//        return studentService.findAll();
    }

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createStudent(@RequestBody Student student) {
//        studentService.persist(student);
//        LOG.debug("Student successfully created.");
//        final HttpHeaders headers = RestUtils.createLocationHeader("/{key}", student.getKey());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteStudent(@PathVariable("key") String key) {
//        final Student s = studentService.findByKey(key);
//        if (s == null) {
//            throw new NotFoundException("Student with key " + key + " not found.");
//        }
//        studentService.delete(s);
//        LOG.debug("Student {} was successfully deleted.", s);
//    }
}
