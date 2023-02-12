package org.roronoa.banklywallet.service;

import jakarta.validation.Valid;
import org.roronoa.banklywallet.entity.Wallet;

import java.util.List;

public interface IWalletService {
    Wallet saveWallet(Wallet wallet);
    Wallet getWallet(String reference);
    List<Wallet> getListWallets();
    Wallet updateWallet(String reference,Double sold);
}