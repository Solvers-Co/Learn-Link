import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Rotas from "./routes";

function App() {
  return (
    <>
      <Rotas />
      <ToastContainer />
    </>
  );
}
export default App;