import TokenContext from "../Auth/TokenContext";
import MainPage from "./MainPage";
import useToken from "../../hooks/token";
import TokenRenewal from "../Auth/TokenRenwal";

export default function App() {

    const [token, setToken] = useToken();

    return (
        <TokenContext.Provider value={[token, setToken]} >
            <MainPage />
            <TokenRenewal />
        </TokenContext.Provider>
    );
}
