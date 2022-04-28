import * as React from "react";
import {useEffect, useState} from "react";
import {ProductVariant} from "types/Product";
import {makeVariantChunks} from "components/store/product_card/helpers";
import {Carousel, Container} from "react-bootstrap";
import VariantOptionRadio from "components/store/product_card/VariantOptionRadio";

export type VariantSelectionProps = {
    initialVariant: ProductVariant,
    variants: ProductVariant[],
    onChange: (value: ProductVariant) => void
};

const VariantSelection = ({initialVariant, variants, onChange}: VariantSelectionProps) => {

    const [selectedVariant, setSelectedVariant] = useState(initialVariant);

    useEffect(() => {
        onChange(selectedVariant);
    }, [selectedVariant]);

    const variantChunks = makeVariantChunks(variants, 5);

    const VariantOptionRadios = ({variants}: { variants: ProductVariant[] }) => {
        return (
            <Container className={"d-flex justify-content-center"}>{variants.map(variant =>
                <VariantOptionRadio key={variant.id}
                                    checked={variant == selectedVariant}
                                    variant={variant}
                                    onClick={(newVariant) => setSelectedVariant(newVariant)}
                />)}
            </Container>
        );
    };

    return variantChunks.length > 1 && (
        <Carousel interval={null} indicators={false} variant={"dark"}>{variantChunks.map((variantChunk, index) =>
            <Carousel.Item key={index}>
                <VariantOptionRadios variants={variantChunk}/>
            </Carousel.Item>)}
        </Carousel>
    ) || <VariantOptionRadios variants={variantChunks[0]}/>;

}

export default VariantSelection;