package ca.bgcengineering.promogearreworked.api.products.image.secured.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredProductImageUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long productId;

    @NotNull
    @Min(1)
    private final Integer position;

    @NotNull
    @Size(min = 1, max = 200)
    private final String alt;

    @JsonCreator
    public SecuredProductImageUpdate(@JsonProperty("position") Integer position, @JsonProperty("alt") String alt) {
        this.position = position;
        this.alt = alt;
    }
}
