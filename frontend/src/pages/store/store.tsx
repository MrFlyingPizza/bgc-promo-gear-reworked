import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Store from "components/store/Store";
import {QueryClient, QueryClientProvider} from "react-query";

const queryClient = new QueryClient();

const storeContainer = document.getElementById('store-root');
storeContainer && ReactDOM.createRoot(storeContainer).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <Store/>
        </QueryClientProvider>
    </React.StrictMode>
);