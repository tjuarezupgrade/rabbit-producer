package com.upgrade.poc.rabbitproducer.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SpectrumMessage {

    private String transaction;

    private String amount;
}
