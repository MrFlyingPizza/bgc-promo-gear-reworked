import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Store from "components/store/Store";
import {QueryClient, QueryClientProvider} from "react-query";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: false,
            refetchOnMount: false,
            refetchOnReconnect: false,
            refetchOnWindowFocus: false,
            retryOnMount: false
        }
    }
});

const storeContainer = document.getElementById('store-root');
storeContainer && ReactDOM.createRoot(storeContainer).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <Store/>
        </QueryClientProvider>
    </React.StrictMode>
);