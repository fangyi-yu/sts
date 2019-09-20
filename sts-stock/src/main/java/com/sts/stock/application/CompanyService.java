package com.sts.stock.application;

import com.sts.stock.domain.entity.Company;

import java.util.List;

/**
 * Created by fangyi on 2019/9/19
 */
public interface CompanyService {

    /**
     *  支持中文全面，和拼音首字母
     * @return
     */
    List<Company> findByName(String name);

    /**
     *  根据公司名称拼音首字母查找，不区分大小写
     * @param pinYinFirstChar
     * @return
     */
    List<Company> findByPinYinFirstCharRegexI(String pinYinFirstChar);
}
