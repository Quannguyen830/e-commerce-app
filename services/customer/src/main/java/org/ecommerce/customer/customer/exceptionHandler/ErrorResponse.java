package org.ecommerce.customer.customer.exceptionHandler;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
) {
}
