package com.pixnetics.wallet_backend.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class TransferController {
    private static final Logger log = LoggerFactory.getLogger(TransferController.class);

    @PostMapping("/topup")
    public String topup() {
        log.info("Doing topup");
        return "topup done!";
    }

    @PostMapping("/withdraw")
    public String withdraw() {
        return "withdraw done!";
    }



    @PostMapping("/settle")
    public String settle() {
        return "settle done!";
    }
}
