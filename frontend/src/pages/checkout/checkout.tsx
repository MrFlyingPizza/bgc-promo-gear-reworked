import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Checkout from "components/cart/Checkout";
import {QueryClient, QueryClientProvider} from "react-query";

const queryClient = new QueryClient();

const cartContainer = document.getElementById('cart-root');
cartContainer && ReactDOM.createRoot(cartContainer).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <Checkout/>
        </QueryClientProvider>
    </React.StrictMode>
);