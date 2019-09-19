package com.sts.stock.application.impl;

import com.sts.stock.application.CompanyService;
import com.sts.stock.domain.entity.Company;
import com.sts.stock.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by fangyi on 2019/9/19
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Company> findByName(String name) {
        List<Company> company = null;
        int numericValue = Character.getNumericValue(name.charAt(0));
        if (numericValue > 9 && numericValue < 36) {
            company = findByPinYinFirstCharRegexI(name);
        }else {
            Optional<Company> byName = companyRepository.findByName(name);
            if (byName.isPresent()){
                company = new ArrayList<>(1);
                company.add(byName.get());
            }
        }
        return company;
    }

    @Override
    public List<Company> findByPinYinFirstCharRegexI(String pinYinFirstChar) {
        Criteria firstCharCriteria = Criteria.where("pinYinFirstChar").regex(pinYinFirstChar, "i");
        Query query = new Query().addCriteria(firstCharCriteria);
        return mongoTemplate.find(query, Company.class);
    }
}
