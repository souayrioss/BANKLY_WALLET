package org.roronoa.banklywallet.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "banklyUser")
public interface IUserService {

}
