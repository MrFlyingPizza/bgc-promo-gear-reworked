import React from 'react';
import './App.css';
import StoreAppBar from "./StoreAppBar";
import {Button, Card, CardActions, CardContent, Container, Grid, Typography} from "@mui/material";

function App() {
    return (
        <React.Fragment>
            <StoreAppBar>
            </StoreAppBar>,
            <Grid container spacing={4}>
                <Grid item xs={3}>
                </Grid>
                <Grid item xs={2}>
                    <Card>
                        <CardContent sx={{minHeight: 200, maxHeight: 200, maxWidth: 100}}>
                            <Typography>
                                Some text here
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                </Grid>
                <Grid item xs={2}>
                    <Card>
                        <CardContent sx={{minHeight: 200, maxHeight: 200}}>
                            <Typography>
                                Some text here
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                </Grid>
                <Grid item xs={2}>
                    <Card>
                        <CardContent sx={{minHeight: 200, maxHeight: 200}}>
                            <Typography>
                                Some text here
                            </Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small">Learn More</Button>
                        </CardActions>
                    </Card>
                </Grid>
                <Grid item xs={3}>
                </Grid>
            </Grid>
        </React.Fragment>
    )
}

export default App;
