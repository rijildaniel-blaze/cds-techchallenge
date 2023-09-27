import React from 'react'

export default function HeaderComponent() {
  return (
    <nav className="navbar navbar-light" style={{backgroundColor: '#e3f2fd'}}>
      <div className="container-fluid justify-content-center">
        <a className="navbar-brand fs-1 fw-bold" href="#">
          <img src="favicon.ico" alt="" width="50" height="54" className="d-inline-block align-text-top" style={{marginRight: '2%'}}/>
          Restaurant Random Picker
        </a>
      </div>
    </nav>
  )
}
