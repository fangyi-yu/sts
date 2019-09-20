package com.sts.stock.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by fangyi on 2019/9/19
 */
@Getter
@Setter
@Document(collection = "company")
public class Company {

    @Id
    private String id;

    //公司股票代码
    private String code;

    //公司名
    private String name;

    //公司全拼
    private String pinYin;

    //公司拼音首字母
    private String pinYinFirstChar;

    //创建日期
//    @JsonFormat(pattern = Constant.DATE_FORMAT_YMD_HMS, timezone = Constant.TIME_ZONE_GMT8)//东八区
    private Date createDate;

    //更新日期
//    @JsonFormat(pattern = Constant.DATE_FORMAT_YMD_HMS, timezone = Constant.TIME_ZONE_GMT8)//东八区
    private Date updateDate;
}
