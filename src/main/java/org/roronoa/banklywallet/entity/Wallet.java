package org.roronoa.banklywallet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_seq")
    @SequenceGenerator(name = "wallet_seq",allocationSize = 1)
    @Column(nullable = false)
    private Integer id;
    @NotNull
    @NotEmpty
    @Column(nullable = false,unique = true)
    private String reference;
    @NotNull @NotEmpty @Email
    @Column(nullable = false)
    private String owner;
    private Double sold;


    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }
}
