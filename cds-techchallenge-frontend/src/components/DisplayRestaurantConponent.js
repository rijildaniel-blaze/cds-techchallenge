import React, { useState, useEffect } from 'react'
import { toast } from 'react-toastify';

export default function DisplayRestaurantConponent(props) {

    const [restaurant, setRestaurant] = useState([]);
    const [restaurantInput, setRestaurantInput] = useState('');

    const fetchRestaurantData = () => {
        fetch("http://localhost:8080/api/restaurant")
          .then(response => {
            return response.json()
          })
          .then(result => {
            setRestaurant(result.data.map((rest) => {
                return {...rest, edit: false};
            }))
          }).catch(error => {
            setRestaurant([]);
        });
    }

    const editRestaurantData = (id, restaurantName) => {
        fetch(`http://localhost:8080/api/restaurant/${id}`, {
            method: "PUT",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              name: restaurantName,
            }),
          })
          .then(response => {
            if(response.status === 200) {
                return response.json();
            }
            return response.json().then(json => { throw new Error(json.message) });
          })
          .then(result => {
            setRestaurant(restaurant.map((res, index) => {
                if(res.id === id) return {...res, name: result.data.name, edit: false}
                else return res
            }));
            toast.success(result.message, {
                position: toast.POSITION.TOP_CENTER
            });
            
          }).catch(error => {
            toast.error(error.message, {
                position: toast.POSITION.TOP_CENTER
              });
            setRestaurant(restaurant.map((res, index) => {
                if(res.id === id) return {...res, edit: false}
                else return res
            }))
          })
    }


    const deleteRestaurantData = (id) => {
        fetch(`http://localhost:8080/api/restaurant/${id}`, {
            method: "DELETE",
            body: null
          })
          .then(response => {
            if(response.status === 204) {
                setRestaurant(restaurant.filter(res => res.id !== id));
                toast.success("Deleted Successfully ", {
                    position: toast.POSITION.TOP_CENTER
                });
                return;
            }
            return response.json().then(json => { throw new Error(json.message) });
          }).catch(error => {
            toast.error(error.message, {
                position: toast.POSITION.TOP_CENTER
              });
          });
    }

    const editRestaurant = (id, dataEdit, name, rid)   =>  {
        if(dataEdit)    {
            editRestaurantData(rid, name);
        } else{
            setRestaurant(restaurant.map((res, index) => {
                if(index === id) {
                    setRestaurantInput(res.name)
                    return {...res, edit: !res.edit}
                }
                else {
                    return {...res, edit: false}
                }
            }))
        }
    }

    const changeRestaurantName = (e) => {
        setRestaurantInput(e.target.value);
    }

    const removeEdit = (event,id)   => {
        setRestaurant(restaurant.map((res, index) => {
            if(index === id) {
                return {...res, edit: false}
            }
            else {
                return res
            }
        }));
    }

    useEffect(() => {
      if(props.restaurantData)  {
          setRestaurant([...restaurant,{...props.restaurantData, edit: false}]); 
      } else    {
        fetchRestaurantData();
      }
    }, [props.restaurantData]);
    

    props.list(restaurant);
    return (
        <div className="conatiner scroll-container" style={{marginBottom: '2%'}}>
            <div className="row">
            {
                restaurant.map((data, index) => {
                    return (<div className="card mt-4" style={{width: '18rem', marginRight: '5%'}} key={index}>
                        <div className="card-body">
                            <h5 className="card-title text-center">
                                {
                                    data.edit ?
                                    <input type="text" className="form-control" placeholder="" autoComplete='off' value={restaurantInput} onChange={(e) => changeRestaurantName(e)}/>:
                                    <>{data.name}</>
                                } 
                            </h5>
                        </div>
                        <div className="card-body text-center">
                            <button className={`btn ${data.edit ? 'btn-primary':'btn-success'} btn-sm rounded-0`} type="button" data-toggle="tooltip" data-placement="top" title="Edit" style={{marginRight: '5%'}} onClick={() => editRestaurant(index, data.edit, restaurantInput, data.id)}><i className={`fa ${data.edit ? 'fa-check':'fa-edit'}`}></i></button>
                            <button className="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete" onClick={() => deleteRestaurantData(data.id)}><i className="fa fa-trash"></i></button>
                        </div>
                    </div>);
                })
            }
            {
                restaurant.length < 2 && <div className="alert alert-warning text-center fw-bolder mt-5" role="alert">At least add two restaurant</div>
            }
            </div>
        </div>
    )
}
