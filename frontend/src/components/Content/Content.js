import {Routes, Route} from "react-router-dom";
import Dashboard from "../Dashboard/Dashboard";
import Login from "../Login/Login";
import Setup from "../Setup/Setup";

export default function Content() {
    return (
        <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/login" element={<Login />} />
            <Route path="/setup" element={<Setup />} />
        </Routes>
    );
}