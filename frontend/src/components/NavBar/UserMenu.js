import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import * as fas from "@fortawesome/free-solid-svg-icons";
import {useContext, useEffect, useState} from "react";
import TokenContext from "../Auth/TokenContext";
import Api from "../../api/api";

export default function UserMenu() {

    const [token, setToken] = useContext(TokenContext);
    const [user, setUser] = useState();

    const logout = () => {
        setToken(null);
    }

    useEffect(() => {
        Api.Auth.WhoAmI(token)
            .then(r => setUser(r.data))
            .catch(() => setToken(null))

    }, [token]); // eslint-disable-line react-hooks/exhaustive-deps

    if(token) {
        return (
            <li className="nav-item dropdown with-arrow">
                <a className="nav-link" data-toggle="dropdown" id="user-dropdown-toggle" href="#">
                    {user && user.username}
                    <FontAwesomeIcon icon={fas.faAngleDown} className="ml-5"/>
                </a>
                <div className="dropdown-menu dropdown-menu-right" aria-labelledby="user-dropdown-toggle">
                    <a href="#" className="dropdown-item">Change password</a>
                    <a href="#" className="dropdown-item" onClick={logout}>Logout</a>
                </div>
            </li>
        )
    } else {
        return null;
    }

}