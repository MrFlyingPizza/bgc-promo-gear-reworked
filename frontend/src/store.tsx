import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import Store from "./store/Store";

const storeContainer = document.getElementById('store-root');
storeContainer && ReactDOM.createRoot(storeContainer).render(<Store/>);