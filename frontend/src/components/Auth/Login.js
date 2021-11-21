import Input from "./Input";
import * as fas from '@fortawesome/free-solid-svg-icons';
import {useState} from "react";
import {Navigate} from "react-router-dom";
import {useToken} from "../../hooks/token";

async function doLogin(credentials) {
    return fetch("/api/v1/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    }).then(data => data.json())
}

export default function Login() {
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const {token, setToken} = useToken();

    const handleSubmit = async e => {
        e.preventDefault();
        const response = await doLogin({
            username,
            password
        });
        setToken(response.token);
    }

    if(token) {
        return <Navigate to="/" />
    }
    return (
        <div className="content-wrapper d-flex align-items-center">
            <div className="w-400 m-auto">
                <div className="card p-0">
                    <div className="px-card py-10 border-bottom">
                        <h2 className="card-title font-size-18 m-0">
                            Login
                        </h2>
                    </div>
                    <div className="content">
                        <form onSubmit={handleSubmit}>
                            <Input label="Your username" id="username" type="text" name="username" icon={fas.faUser} onChange={e => setUsername(e.target.value)} />
                            <Input label="Your password" id="password" type="password" name="password" icon={fas.faLock} onChange={e => setPassword(e.target.value)} />
                            <div className="text-right">
                                <input className="btn btn-primary" type="submit" value="Log in" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}