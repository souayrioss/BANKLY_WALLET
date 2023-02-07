package org.roronoa.banklywallet.util;



import org.modelmapper.ModelMapper;
import org.roronoa.banklywallet.dto.WalletDto;
import org.roronoa.banklywallet.entity.Wallet;



public class EntityUtils {




    public static WalletDto walletToWalletDTO(Wallet wallet) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(wallet, WalletDto.class);
    }
    public static Wallet walletDTOToWallet(WalletDto walletDto) {
        ModelMapper modelMapper=new ModelMapper();
        return modelMapper.map(walletDto, Wallet.class);
    }


}
