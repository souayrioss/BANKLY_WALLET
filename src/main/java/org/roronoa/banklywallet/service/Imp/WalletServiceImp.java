package org.roronoa.banklywallet.service.Imp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.roronoa.banklywallet.entity.Wallet;
import org.roronoa.banklywallet.repository.WalletRepository;
import org.roronoa.banklywallet.service.IWalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class WalletServiceImp implements IWalletService {

    private final WalletRepository walletRepository;

    @Override
    public Wallet saveWallet(String token, Wallet walletReq) throws IOException {
        if (!walletReq.getOwner().equals(getUserIdFromToken(token))) {
            return null;
        }
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
    public String getUserIdFromToken(String jwt) throws IOException {
        String[] token = jwt.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        //String header = new String(decoder.decode(token[0]));
        String payload = new String(decoder.decode(token[1]));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(payload, JsonNode.class);
        JsonNode userIdNode = node.get("sub");
        return userIdNode.asText();
    }


}