package com.assetguard.core.dto.shared;

import java.util.List;
import java.util.UUID;

public record ValidationErrorResponse(
        String code, String message, List<FieldError> errors, UUID traceId) {

    public record FieldError(String field, String message) {}
}
