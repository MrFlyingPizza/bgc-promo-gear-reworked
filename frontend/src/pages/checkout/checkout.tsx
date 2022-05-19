import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Checkout from "components/checkout/Checkout";
import {QueryClient, QueryClientProvider} from "react-query";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {LocalizationProvider} from '@mui/x-date-pickers';

const queryClient = new QueryClient();

const cartContainer = document.getElementById('checkout-root');
cartContainer && ReactDOM.createRoot(cartContainer).render(
    <React.StrictMode>
        <QueryClientProvider client={queryClient}>
            <LocalizationProvider dateAdapter={AdapterDateFns}>
                <Checkout/>
            </LocalizationProvider>
        </QueryClientProvider>
    </React.StrictMode>
);