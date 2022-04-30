import * as ReactDOM from "react-dom/client";
import ProductView from "components/product/ProductView"
import React from "react";
import {QueryClient, QueryClientProvider} from "react-query";

const productContainer = document.getElementById('product-root');
const productId = productContainer.dataset.productid;

const queryClient = new QueryClient();
productContainer && ReactDOM.createRoot(productContainer).render(productId &&
    <QueryClientProvider client={queryClient}>
        <ProductView productId={Number.parseInt(productId)}/>
    </QueryClientProvider>
    || "No product"
);