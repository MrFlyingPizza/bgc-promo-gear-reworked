import * as ReactDOM from "react-dom/client";
import ProductView from "components/product/ProductView"
import React from "react";

const productContainer = document.getElementById('product-root');
const productId = productContainer.dataset.productid;
productContainer && ReactDOM.createRoot(productContainer).render(productId && <ProductView productId={Number.parseInt(productId)}/> || "No product");