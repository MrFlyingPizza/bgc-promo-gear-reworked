package ca.bgcengineering.promogearreworked.api.products.image.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
public class SecuredProductImagePartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long productId;

    @Min(1)
    private final Integer position;

    @Size(min = 1, max = 200)
    private final String alt;

    @JsonCreator
    public SecuredProductImagePartialUpdate(@JsonProperty("position") Integer position, @JsonProperty("alt") String alt) {
        this.position = position;
        this.alt = alt;
    }
}
