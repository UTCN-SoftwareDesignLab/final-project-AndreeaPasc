package com.finalprojectandreeapasc.payment;

import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.stripe.model.Invoice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finalprojectandreeapasc.UrlMapping.EXPORT_REPORT;

@RestController
@RequestMapping("/create-checkout-session")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public void pay(@RequestBody Recipe recipe){
        paymentService.pay(recipe);
    }
}
