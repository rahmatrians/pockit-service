package com.bjb.pockit.dto;


public interface TransactionDaily {

    Long getId();
    String getDescription();
    String getTag();
    Double getAmount();
    String getTransactionDate();
    String getPocket();
    String getTransactionType();
}
