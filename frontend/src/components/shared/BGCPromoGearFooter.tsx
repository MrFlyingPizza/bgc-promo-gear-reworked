import React from "react";

const BGCPromoGearFooter = () => {
    return (
        <footer className="footer-dark" style={{background: "#232345"}}>
            <div className="container">
                <div className="row">
                    <div className="col-sm-6 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li><a href="/">Homepage</a></li>
                            <li><a href="/product_care">Product Care</a></li>
                            <li><a href="/warranty">Warranty</a></li>
                            <li><a href="#">My Orders</a></li>
                        </ul>
                    </div>
                    <div className="col-sm-6 col-md-3 item">
                        <h3>Need help?</h3>
                        <ul>
                            <li><a href="#">Contact Us</a></li>
                            <li><a href="/guidelines">Guidelines</a></li>
                            <li><a href="/faq">FAQ</a></li>
                        </ul>
                    </div>
                    <div className="col-md-6 item text">
                        <h3>BGC Engineering Promo Gear</h3>
                        <p>BGC's philosophy on promotional gear aims to reflect our Core Values in our everyday
                            practice. High quality, functional and practical promo gear is offered to full-time regular
                            staff for personal or client use.</p>
                    </div>
                </div>
                <p className="copyright">BGC Engineering © 2021</p>
            </div>
        </footer>
    );
};

export default BGCPromoGearFooter;