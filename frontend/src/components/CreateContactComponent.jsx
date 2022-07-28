import React, { Component } from 'react';
import ContactService from '../services/ContactService';
import swal from 'sweetalert';


class CreateContactComponent extends Component {

    

    constructor(props){
        super(props);

        this.state = {
            firstname:'',
            lastname:'',
            phone:'',
            birthday:''
        };

        this.changeFirstName =this.changeFirstName.bind(this);
        this.changeLastName =this.changeLastName.bind(this);
        this.saveContact = this.saveContact.bind(this);
        this.cancelCreation= this.cancelCreation.bind(this);
        

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
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            phone: this.state.phone,
            birthday: this.state.birthday
        }
        console.log(contact);
        ContactService.createContact(contact).then((res)=>{
            swal({
                title:"Contact created.",
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
            
            <div className="form-container">
                <h3 className="fs-2 form-text">Create contact</h3>
                <div className="form-body">
                    <form>
                        <h3 className="form-text">First Name</h3>
                        <input className="form-control" placeholder="First Name" type="text" name="firstname"
                        value={this.state.firstname} onChange={event => this.changeFirstName(event)} minLength="2" maxLength = "100" />
                        <h3 className="form-text">Last Name</h3>
                        <input className="form-control" placeholder="Last Name" type="text" name="lastname"
                        value={this.state.lastname} onChange={event => this.changeLastName(event)} minLength="2"  maxLength = "100"/>
                        <h3 className="form-text">Phone</h3>
                        <input className="form-control" placeholder="Phone" type="number" name="Phone"
                        value={this.state.phone} onChange={event => this.changePhone(event)} min="99"  max = '9999999999' />
                        <h3 className="form-text">Birtday</h3>
                        <input className="form-control" placeholder="YYYY-MM-DD" type="date" name="birthday"
                        value={this.state.birthday} onChange={event => this.changeBirtDay(event)} min="0000-01-01" />
                        <button className='btn btn-success' onClick={this.saveContact} >Create</button>
                        <button className='btn btn-danger' onClick={this.cancelCreation}>Cancel</button>
                    </form>
                </div>
            </div>
            
        );
    }
}

export default CreateContactComponent;