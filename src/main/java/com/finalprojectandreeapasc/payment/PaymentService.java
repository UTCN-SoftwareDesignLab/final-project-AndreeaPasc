package com.finalprojectandreeapasc.payment;

import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;

import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static spark.Spark.*;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public void pay(Recipe recipe) {
        port(8082);

        // This is a sample test API key. Sign in to see examples pre-filled with your key.
        Stripe.apiKey = "pk_test_51J89KNBjLDxLADXyJgvbRCtn8OVS587AqYg2Dtor1Y298N47W440PG2BYXwQt5eWgHkZRLeScpk7q3LHM8TfbRuC00PiKiXPSG";

        staticFiles.externalLocation(
                Paths.get("").toAbsolutePath().toString());

        post("/create-checkout-session", (request, response) -> {

            final String YOUR_DOMAIN = "http://localhost:8082/checkout";
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(YOUR_DOMAIN + "?success=true")
                            .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(
                                                    SessionCreateParams.LineItem.PriceData.builder()
                                                            .setCurrency("usd")
                                                            .setUnitAmount(recipe.getPrice().longValue())
                                                            .setProductData(
                                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                            .setName(recipe.getTitle())
                                                                            .build())
                                                            .build())
                                            .build())
                            .build();
            Session session = Session.create(params);

            response.redirect(session.getUrl(), 303);
            return "";
        });
    }
}