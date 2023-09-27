import React, { useContext, useEffect, useState } from 'react'
import { toast } from 'react-toastify';
import { DataContext } from './RestaurantFormComponent';

export default function RandomChoiceComponent() {

    const [restaurantName, setRestaurantName] = useState("");
    const [buttonDisabled,setButtonDisabled] = useState(false);

    const restaurantList = useContext(DataContext);

    const chooseRandomly = (e)  => {
        setButtonDisabled(true);
        fetch("http://localhost:8080/api/restaurant/random-choice")
          .then(response => {
            if(response.ok) {
                return response.json();
            }
          })
          .then(result => {
            setRestaurantName(result.data.name);
          }).catch(error => {
            toast.error(error.message, {
                position: toast.POSITION.TOP_CENTER
              });
          }).finally(() => {
            setButtonDisabled(false);
          });
    }

    useEffect(() => {
        if(restaurantList.length < 2)
            setRestaurantName('');
    }, [restaurantList])
    

    return (
    <div className='container'>
        <div className='row mt-5'>
            <button type="button" className="btn btn-dark btn-lg btn-block" onClick={(e) => chooseRandomly(e)} disabled={buttonDisabled || restaurantList.length < 2}>Click here to choose randomly</button>
        </div>
        {
            (restaurantList.length >= 2 && restaurantName) &&
            <div className='row'>
                <div className="card border-secondary mb-3" style={{maxWidth: '100%', marginTop: '10%'}}>
                    <div className="card-header"><p className='text-success fw-bold fs-4'>Randomly choosen!!!</p></div>
                    <div className="card-body text-secondary">
                        <p className="card-text text-center fs-1 fw-bolder">{restaurantName}</p>
                    </div>
                </div>
            </div>
        }
    </div>
  )
}
