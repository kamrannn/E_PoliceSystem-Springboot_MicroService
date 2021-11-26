package com.app.epolice.service.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The interface Feign e bank service.
 */
@FeignClient(name = "e-bank")
public interface FeignEBankService {

    /**
     * Check currency string.
     *
     * @return the string
     */
    @GetMapping("/currency/bank")
    String checkCurrency();
}
