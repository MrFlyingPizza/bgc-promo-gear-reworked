import * as React from "react";
import axios from "axios";
import {Alert, AlertColor, CircularProgress, Container, Grid} from "@mui/material";

import Category from "types/Category";
import ProductCard, {LoadingCard} from "./product_card/ProductCard";
import {useQueries, useQuery, UseQueryResult} from "react-query";
import BGCPromoGearHeader from "components/shared/BGCPromoGearHeader";
import BGCPromoGearFooter from "components/shared/BGCPromoGearFooter";
import CategorySelection from "components/store/category_list/CategorySelection";
import {useState} from "react";
import Product from "types/Product";

type StoreAlertProps = {
    id: number,
    severity: AlertColor,
    content: React.ReactNode,
    onClose: () => void
};

function Store() {

    const [selectedCategoryIds, setSelectedCategoryIds] = useState<Set<number>>(new Set());

    function handleCategoryChange(previousCategoryIds: number[], updatedCategoryIds: number[]) {
        previousCategoryIds.forEach(id => selectedCategoryIds.delete(id));
        updatedCategoryIds.forEach(id => selectedCategoryIds.add(id));
        setSelectedCategoryIds(new Set(selectedCategoryIds));
    }

    //region Alert Control
    const [alerts, setAlerts] = useState<StoreAlertProps[]>([]);

    function indexOfAlert(alertId: number) {
        return alerts.findIndex(({id}) => id == alertId);
    }

    function removeAlert(alertId: number) {
        setAlerts(alerts.filter(({id}) => id != alertId));
    }

    function addAlert(alert: StoreAlertProps) {
        setAlerts([...alerts.filter(({id}) => id != alert.id), alert]);
    }

    //endregion

    const {
        isLoading: isLoadingCategories,
        isError: isErrorCategories,
        data: categories
    } = useQuery("categories", () => axios.get("/api/categories")
        .then<Category[]>(response => response.data.categories.filter(({parent}: Category) => !parent))
    );

    //region Product Fetching
    const productQueries: UseQueryResult<Product[]>[] = useQueries(selectedCategoryIds.size > 0 ?
        Array.from(selectedCategoryIds).map(categoryId => ({
            queryKey: ["products", categoryId],
            queryFn: () => {
                return axios.get("/api/products", {params: {"category.id": categoryId}})
                    .then<Product[]>(({data}) => data.products);
            }
        })) : [{
            queryKey: ["products", null],
            queryFn: () => {
                return axios.get("/api/products").then<Product[]>(({data}) => data.products);
            }
        }]
    );

    //endregion

    const cardSize = {xs: 12, sm: 6, md: 4, lg: 3};

    return (
        <>
            <BGCPromoGearHeader/>
            <Container className={"mt-5 mb-5 min-vh-100"}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <h3>Promotional Gear</h3>
                    </Grid>
                    <Grid item xs={12}>{
                        isLoadingCategories && <div><CircularProgress/></div>
                        || isErrorCategories && "Failed to load categories."
                        || <CategorySelection categories={categories} onChange={handleCategoryChange}/>}
                    </Grid>
                    <Grid item xs={12}>{alerts.map(({id, severity, onClose, content}) =>
                        <Alert key={id} severity={severity} onClose={() => onClose()}>
                            {content}
                        </Alert>)}
                    </Grid>
                    {productQueries.map(({data: products, isLoading, isError}) =>
                        isLoading &&
                        <Grid item xs={12} {...cardSize}>
                            <LoadingCard/>
                        </Grid>
                        || isError && <Alert severity={"error"}>Failed to load products.</Alert>
                        || products && products.map(product =>
                            <Grid item key={product.id} {...cardSize}>
                                <ProductCard product={product}/>
                            </Grid>)) || <Alert severity={"info"}>No product found.</Alert>
                    }
                </Grid>
            </Container>
            <BGCPromoGearFooter/>
        </>
    );
}

export default Store;
