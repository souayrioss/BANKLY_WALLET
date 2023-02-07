package org.roronoa.banklywallet.dto;

import lombok.Data;

@Data
public class WalletDto {
    private Integer id;

    private String reference;

    private String owner;
    private Double sold;
}
