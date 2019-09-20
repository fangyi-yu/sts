package com.sts.stock.controller;

import com.sts.stock.application.CompanyService;
import com.sts.stock.domain.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fangyi on 2019/9/19
 */
@RestController
@RequestMapping("/companys")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{name}")
    public List<Company> findByName(@PathVariable(value = "name") String name) throws Exception {

        return companyService.findByName(name);
    }
}
