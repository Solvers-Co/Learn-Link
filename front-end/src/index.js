import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  // <React.StrictMode>
    <App />
  // </React.StrictMode>
);

// isso estava atrapalhando o funcionamento do feed geral chamando a requisição de publicações duas vezes;
//documentação do StrictMode
// https://legacy.reactjs.org/docs/strict-mode.html

//como consegui resolver
// https://cursos.alura.com.br/forum/topico-tudo-esta-sendo-duplicado-no-react-252726

// root.render(
//   <React.StrictMode>
//     <App/>
//   </React.StrictMode>
// );