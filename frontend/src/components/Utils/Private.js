import {Navigate} from "react-router-dom";
import {useToken} from "../../hooks/token";

export default function Private({children}) {
    const {token} = useToken();


    if(token) {
        return children;
    }

    return <Navigate to="/login" />;
}