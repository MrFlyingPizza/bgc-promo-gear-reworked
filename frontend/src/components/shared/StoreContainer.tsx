import React from "react";
import {Card, Container} from "react-bootstrap";

type Props = {
    children?: React.ReactNode
}

const StoreContainer: React.FC<Props> = ({children}) => {
    return (
        <Container className="card-store-container" style={{padding: "0 0", marginTop: "20px", marginBottom: "20px"}}>
            <Card className="shadow-sm" style={{margin: "18px", minHeight: "80vh"}}>
                <Card.Body style={{padding: "18px 40px 0"}}>
                    <div className="align-items-stretch front-content" style={{fontFamily: "Poppins, sans-serif"}}>
                        {children}
                    </div>
                </Card.Body>
            </Card>
        </Container>
    )
}

export default StoreContainer;