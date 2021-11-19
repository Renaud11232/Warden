import Input from "../Input/Input";

export default function Setup() {
    return (
        <div className="content-wrapper">
            <div className="content">
                <h1 className="content-title">Setup</h1>
                <p>
                    Hello and welcome to the setup page for Warden.<br/>
                    This page will help you create your first Warden administrator account.
                </p>
            </div>
            <div className="card p-0">
                <div className="content">
                    <form className="w-400 mw-full" action="/waza" method="POST">
                        <Input name="username" id="username" label="Your username" type="text" required={true} />
                        <Input name="password" id="password" label="Your password" type="password" required={true} />
                        <Input name="passwordConfirmation" id="passwordConfirmation" label="Confirm your password" type="password" required={true} />
                        <input className="btn btn-primary" type="submit" value="Create user" />
                    </form>
                </div>
            </div>
        </div>
    );
}