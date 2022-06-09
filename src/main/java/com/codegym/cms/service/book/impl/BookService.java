package com.codegym.cms.service.book.impl;

import com.codegym.cms.exception.BookAmountIsZero;
import com.codegym.cms.model.Book;
import com.codegym.cms.repository.IBookRepository;
import com.codegym.cms.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByBookName(String bookName) {
        return bookRepository.findByName(bookName);
    }

    @Override
    public void save(Book t) {
        bookRepository.save(t);
    }

    @Override
    public void bringOut(Book t) throws BookAmountIsZero {
        Long amount = t.getAmount();
        if (amount == 0){
            throw new BookAmountIsZero();
        } else {
            t.setAmount(amount-1);
            bookRepository.save(t);
        }
    }

    @Override
    public void bringIn(Book t) {
        t.setAmount(t.getAmount()+1);
        bookRepository.save(t);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
