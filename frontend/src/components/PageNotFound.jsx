
import React, { Component } from 'react';

class FooterComponent extends Component {
    
    constructor(props){
        super(props);

        this.state = {
            text:''
        }

        this.randomText = this.randomText.bind(this);
    }

    componentDidMount(){
        let random = this.randomText();
        this.setState({
            text: random
        })
    }

    
    randomText(){
        let option;
        let text = [
            "Sorry, it looks like it was a lost call.",
            "Sorry, we couldn´t call this page.",
            "I think, I lost my contact lenses, because i couldn´t find the page."
        ];

        return text[ Math.floor(Math.random() * (text.length))];
    }

    render() {
        return (
            <div className='contact-container'>
                <h3 className="fw-bold fs-1 text-center form-text contact-names text-break">Error 404.</h3>
                <p className="fw-normal fs-3 text-center form-text contact-names text-break" >{this.state.text}</p>
                <div className="go-back-btn">
                        <a href="/" className="btn btn-primary btn-home">Go home</a>
                </div>
            </div>
        );
    }
}

export default FooterComponent;