package org.movieverse.movieverse_backend.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateCheckoutSessionResponse {
    private String url;
}
