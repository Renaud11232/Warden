import {Navigate} from "react-router-dom";

export default function Private({children}) {
    const dindon = null;
    return dindon ? children : <Navigate to="/login" />;
}