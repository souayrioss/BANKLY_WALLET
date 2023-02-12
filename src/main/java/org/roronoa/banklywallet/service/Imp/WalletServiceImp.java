package org.roronoa.banklywallet.service.Imp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.roronoa.banklywallet.entity.Wallet;
import org.roronoa.banklywallet.repository.WalletRepository;
import org.roronoa.banklywallet.service.IWalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class WalletServiceImp implements IWalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet saveWallet(Wallet walletReq) {
        var wallet = Wallet.builder()
                .reference(UUID.randomUUID().toString())
                .owner(walletReq.getOwner())
                .sold(walletReq.getSold())
                .build();
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWallet(String reference) {
        Optional<Wallet> wallet = walletRepository.findByReference(reference);
        return wallet.orElse(null);
    }

    @Override
    public List<Wallet> getListWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet updateWallet(String reference, Double sold) {
        Wallet wallet = getWallet(reference);
        wallet.setSold(sold);
        return walletRepository.save(wallet);
    }


}