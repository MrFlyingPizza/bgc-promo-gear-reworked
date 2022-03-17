import React from 'react';
import logo from './logo.svg';
import './App.css';
import {AppBar, Container, Menu, Toolbar, Typography} from "@mui/material";

function App() {
  return (
      <AppBar position={"static"}>
        <Container maxWidth={"xl"}>
          <Toolbar>
            <Typography variant={"h6"} noWrap component={"div"}>

            </Typography>
          </Toolbar>
        </Container>
      </AppBar>
  )
}

export default App;
