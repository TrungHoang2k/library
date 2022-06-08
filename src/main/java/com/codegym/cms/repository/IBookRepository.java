package com.codegym.cms.repository;

import com.codegym.cms.model.Book;
import com.codegym.cms.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBookRepository extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findByName(String bookName);
}
