package com.nex3z.examples.springes;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter @Setter @ToString
public class AccountRecord {

    @JSONField(name = "account_number")
    private long accountNumber;
    private BigDecimal balance;
    @JSONField(name = "firstname")
    private String firstName;
    @JSONField(name = "lastname")
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String employer;
    private String email;
    private String city;
    private String state;

}
