import {Navigate} from "react-router-dom";
import {useContext} from "react";
import TokenContext from "../Auth/TokenContext";

export default function Private({children}) {
    const [token] = useContext(TokenContext);

    if(token) {
        return children;
    }

    return <Navigate to="/login" />;
}