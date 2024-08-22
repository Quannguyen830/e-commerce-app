package org.ecommerce.customer.api.exceptionHandler;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
) {
}
