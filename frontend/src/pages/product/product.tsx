import * as ReactDOM from "react-dom/client";
import ProductView from "components/product/ProductView"
import React from "react";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";

const productContainer = document.getElementById('product-root');
const productId = productContainer.dataset.productid;

const queryClient = new QueryClient();
productContainer && ReactDOM.createRoot(productContainer).render(productId &&
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <ProductView productId={Number.parseInt(productId)}/>
        </QueryClientProvider>
    </React.StrictMode>
    || "No product"
);