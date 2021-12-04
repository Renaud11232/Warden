import halfmoon from "halfmoon";
import NavBar from "./NavBar";
import SideBar from "./SideBar";
import MainRouter from "../routing/MainRouter";
import {useContext} from "react";
import TokenContext from "../context/TokenContext";

export default function MainPage() {

    const [token] = useContext(TokenContext);

    return (
        <div className={"page-wrapper with-navbar with-sidebar"} data-sidebar-type="overlayed-sm-and-down" {...(!token && {"data-sidebar-hidden": "hidden"})}>
            <div className="sticky-alerts"/>
            <div className="sidebar-overlay" onClick={() => {halfmoon.toggleSidebar()}}/>
            <NavBar />
            <SideBar />
            <div className="content-wrapper">
                <MainRouter />
            </div>
        </div>
    );
}