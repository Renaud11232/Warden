import {useState} from "react";

export function useToken() {
    const getToken = () => {
        return localStorage.getItem("warden_token")
    }

    const [token, setToken] = useState(getToken());

    const saveToken = token => {
        if(token === null) {
            localStorage.removeItem("warden_token");
        } else {
            localStorage.setItem("warden_token", token);
        }
        setToken(token);
    }

    return {
        setToken: saveToken,
        token
    };
}