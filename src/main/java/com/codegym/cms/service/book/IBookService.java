package com.codegym.cms.service.book;

import com.codegym.cms.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IBookService {
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findById(Long id);
    Optional<Book> findByBookName(String bookName);
    void save(Book t);

    void out(Book t) throws Exception;

    void in(Book t);

    void remove(Long id);
}
