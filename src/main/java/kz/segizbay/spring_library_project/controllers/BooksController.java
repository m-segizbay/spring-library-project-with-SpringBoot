package kz.segizbay.spring_library_project.controllers;

import kz.segizbay.spring_library_project.models.Book;
import kz.segizbay.spring_library_project.models.Person;
import kz.segizbay.spring_library_project.repositories.BooksRepositories;
import kz.segizbay.spring_library_project.services.BooksService;
import kz.segizbay.spring_library_project.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BooksRepositories booksRepositories;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BooksRepositories booksRepositories){
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.booksRepositories = booksRepositories;
    }
    @GetMapping
    public String indexAll(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }

    @GetMapping(params = {"page", "books_per_page"})
    public String indexPaginator(Model model, @RequestParam("page") int page,
                                 @RequestParam("books_per_page") int booksPerPage){
        model.addAttribute("books", booksService.paginatorBook(page, booksPerPage));
        System.out.println("Paginator");
        return "books/index";
    }

    @GetMapping(params = "sort_by_year")
    public String indexSorter(Model model, @RequestParam("sort_by_year") boolean sortByYear){
        if (sortByYear){
            model.addAttribute("books", booksService.sorterBook(sortByYear));
        } else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping(params = {"page", "books_per_page", "sort_by_year"})
    public String indexPaginatorAndSorter(Model model, @RequestParam("page") int page,
                                          @RequestParam("books_per_page") int booksPerPage,
                                          @RequestParam("sort_by_year") boolean sortByYear){
        if (sortByYear=true){
            model.addAttribute("books", booksService.paginatorAndSorter(page, booksPerPage, sortByYear));
        } else {
            model.addAttribute("books", booksService.paginatorBook(page, booksPerPage));
        }
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", booksService.findById(id));
        Optional<Person> owner = booksService.findBookOwner(id);
        if (owner.isPresent()){
            model.addAttribute("owner", owner.get());
        } else {
            model.addAttribute("people", peopleService.finaAll());
        }
        return "books/show";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/new";
        }
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "books/edit";
        }
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("book") Person person){
        booksService.assign(id, person);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksService.release(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/search")
    public String searchBook(Model model, @RequestParam(value = "title", required = false) String startwith){
        if (startwith==null){
            model.addAttribute("books", Collections.emptyList());
        } else {
            model.addAttribute("books", booksService.findBookByTitleStartingWith(startwith));
        }
        return "books/search";
    }
}
