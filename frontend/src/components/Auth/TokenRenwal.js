import {useContext} from "react";
import TokenContext from "./TokenContext";
import Api from "../../api/api";
import ReactInterval from "react-interval";

export default function TokenRenewal() {

    const [token, setToken] = useContext(TokenContext)

    const renewToken = () => {
        Api.Auth.Renew(token)
            .then(r => setToken(r.data.token))
            .catch(() => setToken(null))
    }

    return <ReactInterval timeout={1000 * 60 * 10} callback={renewToken} enabled={!!token} />;
}