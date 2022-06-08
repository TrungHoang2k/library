package com.codegym.cms.repository;

import com.codegym.cms.model.Form;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFormRepository extends CrudRepository<Form, Long> {
    Form findFormByCode(int code);
}
