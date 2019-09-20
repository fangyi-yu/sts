package com.sts.stock.domain.repository;

import com.sts.stock.domain.entity.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by fangyi on 2019/9/19
 */
public interface CompanyRepository extends CrudRepository<Company, String> {

    Optional<Company> findByName(String name);
}
