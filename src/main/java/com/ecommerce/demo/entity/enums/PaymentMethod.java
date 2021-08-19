package com.ecommerce.demo.entity.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentMethod {
    CREDIT_CARD("scheme"), IDEAL("ideal");

    public final String label;

    private static final Map<String, PaymentMethod> BY_LABEL = new HashMap<>();

    static {
        for (PaymentMethod e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    PaymentMethod(final String label) {
        this.label = label;
    }

    public static PaymentMethod fromLabel(String label) {
        return BY_LABEL.get(label);
    }
}
