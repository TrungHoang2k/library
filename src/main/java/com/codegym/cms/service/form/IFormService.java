package com.codegym.cms.service.form;

import com.codegym.cms.model.Book;
import com.codegym.cms.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IFormService {
    void save(Form f);
    Form createNewForm(String bookName);
    Form findByCode(int code);
    void remove(Long id);
}
