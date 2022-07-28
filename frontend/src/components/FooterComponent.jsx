import React, { Component } from 'react';

class FooterComponent extends Component {
    
    constructor(props){
        super(props);

        this.state = {

        }
    }
    
    render() {
        return (
            <div className='div-footer'>
                <footer className="footer">
                    <span className="text-footer">MyContacts</span>
                </footer>
            </div>
        );
    }
}

export default FooterComponent;