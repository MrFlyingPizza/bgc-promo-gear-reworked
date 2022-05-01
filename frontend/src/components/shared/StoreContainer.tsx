import React from "react";
import {Card, Container} from "react-bootstrap";

type Props = {
    children?: React.ReactNode
}

const StoreContainer: React.FC<Props> = ({children}) => {
    return (
        <Container className="">
            <Card className="shadow-sm">
                <Card.Body>
                    <div className="align-items-stretch front-content" style={{fontFamily: "Poppins, sans-serif"}}>
                        {children}
                    </div>
                </Card.Body>
            </Card>
        </Container>
    )
}

export default StoreContainer;