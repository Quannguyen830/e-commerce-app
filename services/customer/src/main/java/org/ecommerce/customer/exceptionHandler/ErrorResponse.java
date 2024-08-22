package org.ecommerce.customer.exceptionHandler;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
) {
}
