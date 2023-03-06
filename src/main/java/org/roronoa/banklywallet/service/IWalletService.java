package org.roronoa.banklywallet.service;

import jakarta.validation.Valid;
import org.roronoa.banklywallet.entity.Wallet;

import java.io.IOException;
import java.util.List;

public interface IWalletService {
    Wallet saveWallet(String token, Wallet wallet) throws IOException;
    Wallet getWallet(String reference);
    List<Wallet> getListWallets();
    Wallet updateWallet(String reference,Double sold);
}