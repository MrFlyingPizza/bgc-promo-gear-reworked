import * as ReactDOM from "react-dom/client";
import React from "react";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import OrdersView from "components/orders/OrdersView";

const ordersContainer = document.getElementById('orders-root');

const queryClient = new QueryClient();
ordersContainer && ReactDOM.createRoot(ordersContainer).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <OrdersView/>
        </QueryClientProvider>
    </React.StrictMode>
);