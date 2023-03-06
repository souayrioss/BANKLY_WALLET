package org.roronoa.banklywallet.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.roronoa.banklywallet.dto.ResponseDTO;
import org.roronoa.banklywallet.dto.WalletDto;
import org.roronoa.banklywallet.entity.Wallet;
import org.roronoa.banklywallet.service.IWalletService;
import org.roronoa.banklywallet.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WalletResource {
    private final IWalletService walletService;
    @PostMapping(path = "/wallet")
    public ResponseEntity<WalletDto> saveWallet(@RequestHeader Map<String, String> headers,@RequestBody WalletDto walletDto){
        String token = headers.get("authorization");
        Wallet wallet = EntityUtils.walletDTOToWallet(walletDto);
        wallet = walletService.saveWallet(token,wallet);
        return new ResponseEntity<>(EntityUtils.walletToWalletDTO(wallet), HttpStatus.OK);
    }
    @GetMapping(path = "/wallet/{reference}")
    public ResponseEntity<ResponseDTO<Double>> findWallet(@PathVariable @NotNull @NotEmpty String reference){
            Wallet wallet = walletService.getWallet(reference);
            ResponseDTO<Double> response = new ResponseDTO<>();
            if (Objects.nonNull(wallet)){
                response.setStatus("SUCCESS");
                response.setData(wallet.getSold());
                return  new ResponseEntity<>( response , HttpStatus.OK);
            }
            response.setStatus("WALLET NOt EXISTING");
            return new ResponseEntity<>(response, HttpStatus.OK);
         }
    @GetMapping(path = "/wallets")
    public ResponseEntity<List<WalletDto>> getListWallet(){
            List<Wallet> wallets = walletService.getListWallets();
            List<WalletDto> walletDtoList = wallets.stream().map(EntityUtils::walletToWalletDTO).collect(Collectors.toList());
            return new ResponseEntity<>(walletDtoList, HttpStatus.OK);
    }
    @PutMapping(path = "/wallet")
    public ResponseEntity<ResponseDTO<Boolean>> update(@RequestParam @NotNull @NotEmpty String reference,@RequestParam @NotNull @NotEmpty Double sold){
        Wallet wallet = walletService.updateWallet(reference,sold);
        ResponseDTO<Boolean> response= new ResponseDTO<>();
        response.setStatus("SUCCESS");
        if (Objects.isNull(wallet)) {
            response.setData(false);
        }
        response.setData(true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
