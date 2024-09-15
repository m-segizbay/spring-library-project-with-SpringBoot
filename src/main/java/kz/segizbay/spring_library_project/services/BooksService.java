package kz.segizbay.spring_library_project.services;

import kz.segizbay.spring_library_project.models.Book;
import kz.segizbay.spring_library_project.models.Person;
import kz.segizbay.spring_library_project.repositories.BooksRepositories;
import kz.segizbay.spring_library_project.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepositories booksRepositories;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepositories booksRepositories, PeopleRepository peopleRepository) {
        this.booksRepositories = booksRepositories;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll(){
        return booksRepositories.findAll();
    }

    public Book findById(int id){
        return booksRepositories.findById(id).orElse(null);
    }

    public Optional<Person> findBookOwner(int id){
        Book book = booksRepositories.findById(id).orElse(null);

        return Optional.ofNullable(book.getOwner());
    }

    public List<Book> paginatorBook(int page, int booksPerPage){
        return booksRepositories.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> sorterBook(boolean sortByYear) {
        return booksRepositories.findAll(Sort.by("year"));
    }

    public List<Book> paginatorAndSorter(int page, int booksPerPage, boolean sortByYear) {
        return booksRepositories.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public List<Book> findBookByTitleStartingWith(String startWith){
        return booksRepositories.findBookByTitleStartingWith(startWith);
    }

    @Transactional
    public void save(Book book){
        booksRepositories.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        Book book =  booksRepositories.findById(id).orElse(null);

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setYear(updatedBook.getYear());

        booksRepositories.save(book);
    }

    @Transactional
    public void delete(int id){
        booksRepositories.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person){
        Book book = booksRepositories.findById(id).orElse(null);
        book.setOwner(person);
        book.setDateOfTaken(new Date());
        booksRepositories.save(book);
    }

    @Transactional
    public void release(int id){
        Book book = booksRepositories.findById(id).orElse(null);
        book.setOwner(null);
        booksRepositories.save(book);
    }
}
