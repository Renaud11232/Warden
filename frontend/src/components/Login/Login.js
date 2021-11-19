import Input from "./Input";
import * as fas from '@fortawesome/free-solid-svg-icons';
import {useState} from "react";
import {Navigate} from "react-router-dom";

export default function Login() {
    const [user] = useState();

    if(user) {
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
                        <form action="/login" method="POST">
                            <Input label="Your username" id="username" type="text" name="username" icon={fas.faUser} />
                            <Input label="Your password" id="password" type="password" name="password" icon={fas.faLock} />
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