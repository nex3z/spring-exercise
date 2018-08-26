package com.nex3z.examples.springes;

import java.util.List;
import java.util.stream.Collectors;

public class AccountRecordMapper {

    public static List<Account> transform(List<AccountRecord> records) {
        return records.stream().map(AccountRecordMapper::transform).collect(Collectors.toList());
    }

    public static Account transform(AccountRecord record) {
        return Account.newBuilder()
                .setAccountNumber(record.getAccountNumber())
                .setBalance(record.getBalance().toPlainString())
                .setFirstname(record.getFirstName())
                .setLastname(record.getLastName())
                .setAge(record.getAge())
                .setGender(record.getGender())
                .setAddress(record.getAddress())
                .setEmployer(record.getEmployer())
                .setEmail(record.getEmail())
                .setCity(record.getCity())
                .setState(record.getState())
                .build();
    }
}
