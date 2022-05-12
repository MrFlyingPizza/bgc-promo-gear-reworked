import React from "react";

const BGCPromoGearHeader = () => {
    return (
        <nav
            className="navbar navbar-light navbar-expand-lg shadow-sm d-inline d-lg-flex justify-content-lg-center align-items-lg-end navigation-clean-button"
            style={{fontFamily: "poppins", padding: "10 0", color: "#333333", paddingBottom: 0}}>
            <div className="container"><a className="navbar-brand" href="/"><img src="/images/Logo_PromoGear.svg"
                                                                                 style={{height: 55, paddingBottom: 13}}
                                                                                 alt={"BGC Promo Gear Logo"}/></a>
                <button data-bs-toggle="collapse" className="navbar-toggler" data-bs-target="#navcol-1"><span
                    className="visually-hidden">Toggle navigation</span><span className="navbar-toggler-icon"/>
                </button>
                <div className="collapse navbar-collapse d-lg-flex align-items-lg-center" id="navcol-1"
                     style={{paddingTop: 13}}>
                    <ul className="navbar-nav me-auto">
                        <li className="nav-item dropdown"><a className="dropdown-toggle nav-link" aria-expanded="false"
                                                             data-bs-toggle="dropdown">about</a>
                            <div className="dropdown-menu"><a className="dropdown-item" href="/faq">FAQ</a><a
                                className="dropdown-item" href="/guidelines">guidelines</a><a className="dropdown-item"
                                                                                              href="/product_care">product
                                care</a><a className="dropdown-item" href="/warranty">warranty</a><a
                                className="dropdown-item" href="/team">meet the team</a></div>
                        </li>
                        <li className="nav-item"><a className="nav-link" href="/store">store</a></li>
                        <li className="nav-item"><a className="nav-link" href="/store/orders">orders</a></li>
                        <li className="nav-item"><a className="nav-link" href="/management/panel">management</a></li>
                    </ul>
                    <span
                        className="d-md-flex d-lg-flex justify-content-md-start align-items-md-end align-items-lg-center navbar-text actions"
                        style={{paddingLeft: 16, paddingRight: 16}}> <a className="btn button-checkout"
                                                                        role="button"
                                                                        style={{
                                                                            padding: "3 10",
                                                                            borderColor: "#333333",
                                                                            borderRadius: 20,
                                                                            color: "#333333"
                                                                        }}
                                                                        href="/store/checkout"
                                                                        data-bs-target="checkout.html"><span>checkout&nbsp;
                        <i className="fa fa-shopping-cart m-auto"/></span></a><a className="login" href="#"
                                                                                 style={{
                                                                                     marginLeft: 16,
                                                                                     marginRight: 0
                                                                                 }}
                                                                                 data-bs-target="logoff.html">log off</a></span>
                </div>
            </div>
        </nav>
    );
}

export default BGCPromoGearHeader;