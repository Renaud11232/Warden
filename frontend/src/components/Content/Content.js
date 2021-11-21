import {Routes, Route} from "react-router-dom";
import Dashboard from "../Dashboard/Dashboard";
import Login from "../Auth/Login";
import Private from "../Utils/Private";

export default function Content() {
    return (
        <Routes>
            <Route index element={
                <Private>
                    <Dashboard/>
                </Private>
            }/>
            <Route path="/login" element={<Login/>}/>
        </Routes>
    );
}