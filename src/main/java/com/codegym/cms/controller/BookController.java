package com.codegym.cms.controller;

import com.codegym.cms.model.Book;
import com.codegym.cms.model.Form;
import com.codegym.cms.service.book.IBookService;
import com.codegym.cms.service.form.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private IFormService formService;

    @GetMapping("/create-book")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/book/create");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @PostMapping("/create-book")
    public ModelAndView saveBook(@ModelAttribute("book") Book book, Pageable pageable) {
        bookService.save(book);
        ModelAndView modelAndView = new ModelAndView("/book/list");
        Page<Book> books = bookService.findAll(pageable);
        modelAndView.addObject("books", books);
        modelAndView.addObject("message", "New book created successfully");
        return modelAndView;
    }

    @GetMapping("/books")
    public ModelAndView listBooks(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/book/list");
        Page<Book> books = bookService.findAll(pageable);
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @GetMapping("/borrow-book/{id}")
    public ModelAndView showBorrowForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/book/borrow");
        Book book = bookService.findById(id).get();
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @GetMapping ("/update-book-out/{id}")
    public ModelAndView updateBookOut(@PathVariable Long id, Pageable pageable){
        try {
            ModelAndView modelAndView = new ModelAndView("/book/list");
            Book book = bookService.findById(id).get();
            bookService.out(book);
            Form form = formService.createNewForm(book.getName());
            formService.save(form);
            Page<Book> books = bookService.findAll(pageable);
            modelAndView.addObject("books", books);
            modelAndView.addObject("message", "Borrow successfully with code: " + form.getCode());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("/book/error.404");
        }

    }

    @GetMapping("/return-book-form")
    public ModelAndView showReturnForm() {
        ModelAndView modelAndView = new ModelAndView("/book/returnForm");
        return modelAndView;
    }

    @GetMapping("/return-book")
    public ModelAndView showReturnInfo(@RequestParam("code") int code) {
        try {
            ModelAndView modelAndView = new ModelAndView("/book/return");
            Form form = formService.findByCode(code);
            Book book = bookService.findByBookName(form.getBookName()).get();
            bookService.in(book);
            formService.remove(form.getId());
            modelAndView.addObject("book", book);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("/book/error.404");
        }
    }
}
