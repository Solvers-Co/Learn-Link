import { ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import Rotas from "./routes";

function App() {
  return (
    <>
      <Rotas />
      <ToastContainer
        position="top-right"
        autoClose={1000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="colored"
      />
    </>
  );
}
export default App;