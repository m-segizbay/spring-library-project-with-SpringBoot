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
//public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public void create(Book book) {
//        jdbcTemplate.update("INSERT INTO book(title, author, year) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void update(int id, Book book) {
//        jdbcTemplate.update("UPDATE book SET title = ?, author = ?, year = ? WHERE book_id = ?", book.getTitle(), book.getAuthor(), book.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
//    }
//
//    public void assign(int id, Person person) {
//        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?", person.getPersonId(), id);
//    }
//
//    public Optional<Person> getBookOwner(int id) {
//        return jdbcTemplate.query("SELECT person.person_id, full_name, year_of_birth FROM person JOIN book ON person.person_id=book.person_id WHERE book_id=?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }
//
//    public void release(int id) {
//        jdbcTemplate.update("UPDATE book SET person_id = NULL WHERE book_id = ?", id);
//    }
//}
