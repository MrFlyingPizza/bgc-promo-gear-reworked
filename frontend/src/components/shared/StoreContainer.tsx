import React from "react";

type Props = {
    children?: React.ReactNode
}

const StoreContainer: React.FC<Props> = ({children}) => {
    return (
        <div className="d-flex flex-column" id="store-container">
            {children}
        </div>
    )
}

export default StoreContainer;