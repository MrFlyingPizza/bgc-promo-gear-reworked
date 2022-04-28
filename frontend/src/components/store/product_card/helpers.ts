import {ProductVariant} from "types/Product";

export function makeVariantChunks(variants: ProductVariant[], chunkSize: number) {
    if (chunkSize == 0) {
        throw "chunk size cannot be 0";
    }
    const chunks: ProductVariant[][] = [];
    for (let i = 0; i < variants.length; i += chunkSize) {
        chunks.push(variants.slice(i, i + chunkSize));
    }
    return chunks;
}