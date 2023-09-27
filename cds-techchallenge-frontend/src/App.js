import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.js";
import 'font-awesome/css/font-awesome.min.css';
import 'react-toastify/dist/ReactToastify.css'
import HeaderComponent from './components/HeaderComponent';
import RestaurantForm from './components/RestaurantFormComponent';
import { ToastContainer } from 'react-toastify';



function App() {
  return (
    <div>
      <HeaderComponent/>
      <ToastContainer autoClose={5000} theme="colored"/>
      <RestaurantForm/>
    </div>
  );
}

export default App;
