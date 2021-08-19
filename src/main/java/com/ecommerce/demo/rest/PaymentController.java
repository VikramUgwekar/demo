package com.ecommerce.demo.rest;

import com.ecommerce.demo.entity.ShoppingCart;
import com.ecommerce.demo.rest.domain.PaymentsRequest;
import com.ecommerce.demo.rest.domain.PaymentsResponse;
import com.ecommerce.demo.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);

    @PostMapping("/checkout/initiate-payment")
    public ResponseEntity<PaymentsResponse> payments(@RequestHeader("referer") String referer, @RequestBody PaymentsRequest req, HttpServletRequest request) throws IOException {
        PaymentsRequest paymentRequest = new PaymentsRequest();
        /*Code to set payment Request parameter */
        ShoppingCart activeCart = getUserCart();
        PaymentsResponse response = payments(paymentRequest);

        return ResponseEntity.ok()
                .body(response);
    }

    private ShoppingCart getUserCart() {
        /*Code to get Current logged in user Cart*/
        return new ShoppingCart();
    }

    public PaymentsResponse payments(PaymentsRequest paymentsRequest){
        /*Invoke Payment Gateway*/
       return new PaymentsResponse();
    }



}
