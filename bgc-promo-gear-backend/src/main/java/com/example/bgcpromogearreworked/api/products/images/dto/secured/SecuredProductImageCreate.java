package com.example.bgcpromogearreworked.api.products.images.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredProductImageCreate {

    @JsonIgnore
    @Setter
    private Long productId;

    @NotNull
    @JsonIgnore
    @Setter
    private MultipartFile image; // TODO: 2022-03-16 implement validation for image file

    @NotNull
    @Min(1)
    private final Integer position;

    @NotNull
    @Size(min = 1, max = 200)
    private final String alt;

    @JsonCreator
    public SecuredProductImageCreate(@JsonProperty Integer position, @JsonProperty String alt) {
        this.position = position;
        this.alt = alt;
    }
}
