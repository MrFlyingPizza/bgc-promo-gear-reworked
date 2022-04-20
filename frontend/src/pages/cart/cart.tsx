import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Cart from "components/cart/Cart";

const cartContainer = document.getElementById('cart-root');
cartContainer && ReactDOM.createRoot(cartContainer).render(<Cart/>);