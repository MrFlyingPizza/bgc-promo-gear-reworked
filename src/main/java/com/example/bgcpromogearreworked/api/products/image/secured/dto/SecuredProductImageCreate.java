package com.example.bgcpromogearreworked.api.products.image.secured.dto;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.validfilecontenttype.ValidFileContentType;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.validfilesize.ValidFileSize;
import com.example.bgcpromogearreworked.api.shared.validation.groups.FirstValidationGroup;
import com.example.bgcpromogearreworked.api.shared.validation.groups.SecondValidationGroup;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({SecuredProductImageCreate.class, FirstValidationGroup.class, SecondValidationGroup.class})
public class SecuredProductImageCreate {

    @JsonIgnore
    @Setter
    private Long productId;

    @NotNull
    @JsonIgnore
    @Setter
    @ValidFileContentType(acceptedMediaTypes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE},
            groups = FirstValidationGroup.class)
    @ValidFileSize(max = 2000000, groups = SecondValidationGroup.class)
    private MultipartFile image;

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
