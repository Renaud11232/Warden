import {Routes, Route} from "react-router-dom";
import Dashboard from "../../pages/Dashboard";
import Login from "../../pages/Login";
import Private from "./Private";
import Logout from "../../pages/Logout";

export default function MainRouter() {
    return (
        <Routes>
            <Route index element={<Private><Dashboard/></Private>}/>
            <Route path="/login" element={<Login/>}/>
            <Route path="/logout" element={<Private><Logout/></Private>} />
        </Routes>
    );
}