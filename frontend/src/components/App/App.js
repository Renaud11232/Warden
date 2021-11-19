import NavBar from "../NavBar/NavBar";
import SideBar from "../SideBar/SideBar";
import Content from "../Content/Content";
import {useState} from "react";
import {Navigate, useLocation} from "react-router-dom";
const halfmoon = require("halfmoon");

export default function App() {
    const [user] = useState();
    const location = useLocation();

    /*if(!user && location.pathname !== "/login") {
        return <Navigate to="/login" />
    }*/

    return (
        <div className={"page-wrapper with-navbar" + (user ? " with-sidebar" : "")} data-sidebar-type="overlayed-sm-and-down">
            <div className="sticky-alerts"/>
            <div className="sidebar-overlay" onClick={() => {halfmoon.toggleSidebar()}}/>
            <NavBar />
            {user && <SideBar />}
            <div className="content-wrapper">
                <Content />
            </div>
        </div>
    );
}
