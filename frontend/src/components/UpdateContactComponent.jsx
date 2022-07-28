import React, { Component } from 'react';
import ContactService from '../services/ContactService';
import swal from 'sweetalert';


class CreateContactComponent extends Component {

    

    constructor(props){
        super(props);

        this.state = {
            id:'',
            firstname:'',
            lastname:'',
            phone:'',
            birthday:'',
            createdAt:'',
            hidden:''
        };

        this.changeFirstName =this.changeFirstName.bind(this);
        this.changeLastName =this.changeLastName.bind(this);
        this.saveContact = this.saveContact.bind(this);
        this.cancelCreation= this.cancelCreation.bind(this);
        

    }

    componentDidMount(){
        let path = window.location.pathname.split("/");
        console.log(path[path.length - 1]);
        ContactService.showContact(path[path.length - 1]).then((res)=>{
            let contact = res.data.data;
            console.log(res.data.data);
            this.setState({
                id: contact.id,
                firstname: contact.firstname,
                lastname: contact.lastname,
                phone: contact.phone,
                birthday: contact.birthday,
                createdAt: contact.createdAt,
                hidden: contact.hidden
            });
        });
    }

    
    changeFirstName(event){
        this.setState({firstname: event.target.value});
    }

    changeLastName(event){
        this.setState({lastname: event.target.value});
    }

    changePhone(event){
        this.setState({phone: event.target.value});
    }

    changeBirtDay(event){
        this.setState({birthday: event.target.value});
    }

    saveContact = (e)=>{
        e.preventDefault();
        let contact = {
            id: this.state.id,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            phone: this.state.phone,
            birthday: this.state.birthday,
            createdAt: this.state.createdAt,
            hidden: this.state.hidden
        }
        console.log(contact);
        ContactService.updateContact(contact,this.state.id).then((res)=>{
            swal({
                title:"Contact updated.",
                text: "   ",
                icon: "success",
                button: false
            });
            setTimeout(function(){
                window.location.assign("/");
            },1000);
            
        }).catch(error => {
            swal({
                title:"Sorry, There was a problem.",
                text: "Please verify the contact information.",
                icon: "warning",
                button:"Ok"
            });
        });
    }

    cancelCreation = (e)=>{
        e.preventDefault()
        window.location.assign("/");
    }
    

    render() {
        return (
            <div>{(this.createdAt !== '')?
            <div className="form-container">
                <h3 className="fs-2 form-text">Update contact</h3>
                <div className="form-body">
                    <form>
                        <h3 className="form-text">First Name</h3>
                        <input className="form-control" placeholder={this.state.firstname} type="text" name="firstname"
                        value={this.state.firstname} onChange={event => this.changeFirstName(event)} minLength="2" maxLength = "100" />
                        <h3 className="form-text">Last Name</h3>
                        <input className="form-control" placeholder={this.state.lastname} type="text" name="lastname"
                        value={this.state.lastname} onChange={event => this.changeLastName(event)} minLength="2"  maxLength = "100"/>
                        <h3 className="form-text">Phone</h3>
                        <input className="form-control" placeholder={this.state.phone} type="number" name="Phone"
                        value={this.state.phone} onChange={event => this.changePhone(event)} min="99"  max = '9999999999' />
                        <h3 className="form-text">Birtday</h3>
                        <input className="form-control" placeholder={this.state.birthday} type="date" name="birthday"
                        value={this.state.birthday} onChange={event => this.changeBirtDay(event)} min="0000-01-01" />
                        <div></div>
                        <button className='btn btn-success' onClick={this.saveContact} >Update</button>
                        <button className='btn btn-danger' onClick={this.cancelCreation}>Cancel</button>
                    </form>
                </div>
            </div>
            :
            <div className="contact-container">
                <div>
                    <h3 className="fw-bold text-center fs-1 form-text contact-names text-break">Sorry :(</h3>
                    <p className="fw-normal fs-2 text-center form-text contact-names text-break">We couldn´t get the contact information.</p>
                </div>
                <div className=" go-back-btn">
                    <a href="/" className="btn btn-primary btn-home">Go home</a>
                </div>
            </div>}
        </div>
            
        );
    }
}

export default CreateContactComponent;