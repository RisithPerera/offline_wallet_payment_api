package com.pixnetics.wallet_backend.payment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @PostMapping("/topup")
    public String topup() {
        return "topup done!";
    }

    @PostMapping("/withdraw")
    public String withdraw() {
        return "withdraw done!";
    }

    @PostMapping("/pay")
    public String pay() {
        return "Pay done!";
    }

    @PostMapping("/settle")
    public String settle() {
        return "settle done!";
    }
}
