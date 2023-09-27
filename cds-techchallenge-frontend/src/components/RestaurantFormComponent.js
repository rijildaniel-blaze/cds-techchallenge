import React, { useState } from "react";
import DisplayRestaurantConponent from "./DisplayRestaurantConponent";
import { toast } from "react-toastify";
import RandomChoiceComponent from "./RandomChoiceComponent";

export const DataContext = React.createContext([]);

export default function RestaurantForm() {
    const [restaurantName, setRestaurantName] = useState("");

    const [restaurantData, setRestaurantData] = useState(null);

    const [restaurantListState, setRestaurantListState] = useState([]);

    function addRestaurant(event) {
      if(restaurantName)  {
        event.preventDefault();
        fetch("http://localhost:8080/api/restaurant", {
          method: "POST",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: restaurantName,
          }),
        })
        .then(response => {
            if(response.status === 201) {
              return response.json();
            }
            return response.json().then(json => { throw new Error(json.message) });
        })
        .then(result => {
          if(result.status == 201)  {
            setRestaurantData(result.data);
            setRestaurantName('');
            toast.success(result.message, {
              position: toast.POSITION.TOP_CENTER
            });
          }
          
        }).catch(error => {
            setRestaurantName('');
            toast.error(error.message, {
              position: toast.POSITION.TOP_CENTER
            });
        });
      }
    }

    const setRestaurantList = (list) => {
      setRestaurantListState(list)
    }
    

    return (
      <DataContext.Provider value={restaurantListState}>
      <div className="container-fluid">
        <div className="row">
          <div className="col-lg-6 vh-100 bg-danger">
            <form className="form-inline" onSubmit={addRestaurant}>
              <div className="input-group input-group-lg w-75 center mt-5 mb-4">
                <input type="text" className="form-control" placeholder="Restaurant Name" id="restaurant-name" value={restaurantName} onChange={e => setRestaurantName(e.target.value)} autoComplete="off"/>
                <input type="submit" className="btn btn-primary" value="Add"/>
              </div>
            </form>
            <DisplayRestaurantConponent restaurantData={restaurantData} list={setRestaurantList}/>
          </div>
          <div className="col-lg-6 vh-100 bg-primary">
            <RandomChoiceComponent/>
          </div>
        </div>
      </div>
      </DataContext.Provider>
    )
  }

