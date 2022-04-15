import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Cart from "./cart/Cart";

const cartContainer = document.getElementById('cart-root');
if (cartContainer) {
    ReactDOM.createRoot(cartContainer).render(<Cart/>);
}