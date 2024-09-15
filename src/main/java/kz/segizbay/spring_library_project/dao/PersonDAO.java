//package kz.segizbay.spring_library_project.dao;
//
//import kz.segizbay.springlibrary.models.Book;
//import kz.segizbay.springlibrary.models.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.util.List;
//import java.util.Optional;
//
////@Component
//public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//    private final BookDAO bookDAO;
//
//    @Autowired
//    public PersonDAO(JdbcTemplate jdbcTemplate, BookDAO bookDAO) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.bookDAO = bookDAO;
//    }
//
//    public List<Person> index() {
//        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Optional<Person> findByName(String name) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE full_name = ?", new Object[]{name}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }
//
//    public void save(Person person) {
//        jdbcTemplate.update("INSERT INTO person(full_name, year_of_birth) VALUES (?, ?)", person.getFullName(), person.getYearOfBirth());
//    }
//
//    public Person show(int id) {
//        return jdbcTemplate.query("SELECT * FROM person WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void update(int id, Person person) {
//        jdbcTemplate.update("UPDATE person SET full_name = ?, year_of_birth = ? WHERE person_id = ?", person.getFullName(), person.getYearOfBirth(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM person WHERE person_id = ?", id);
//    }
//
//    public List<Book> getBooksByPersonId(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
//    }
//}
