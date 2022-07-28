import React, { Component } from 'react';

class HeaderComponent extends Component {
    
    constructor(props){
        super(props);

        this.state = {

        }
    }
    
    render() {
        return (
            <div className='div-header'>
                <header className='header'>
                    <div className="header-image"><h2>MyContacts</h2></div>
                </header>
            </div>
        )
    }
}

export default HeaderComponent;