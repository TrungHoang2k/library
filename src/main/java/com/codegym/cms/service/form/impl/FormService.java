package com.codegym.cms.service.form.impl;

import com.codegym.cms.model.Form;
import com.codegym.cms.repository.IFormRepository;
import com.codegym.cms.service.form.IFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormService implements IFormService {
    @Autowired
    private IFormRepository formRepository;

    @Override
    public void save(Form f) {
        formRepository.save(f);
    }

    @Override
    public Form createNewForm(String bookName) {
        int code = (int)Math.floor(Math.random()*(99999-10000+1)+10000);
        return new Form(bookName, code);
    }

    @Override
    public Form findByCode(int code) {
        return formRepository.findFormByCode(code);
    }

    @Override
    public void remove(Long id) {
        formRepository.deleteById(id);
    }
}
