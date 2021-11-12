package com.app.epolice.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "e-bank")
public interface FeignEBankService {

    @GetMapping("/currency/bank")
    public String checkCurrency();
}
