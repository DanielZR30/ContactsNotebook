import React, { Component } from 'react';
import ContactService from '../services/ContactService';
import image from './img/Contact.jpg'
import swal from 'sweetalert';


class ShowContactComponent extends Component {
    constructor(props){
        super(props);

        this.state = { 
            id: '',
            firstname:'',
            lastname:'',
            phone:'',
            birthday:'',
            hidden:'',
            createdAt:''
        }

        this.wDeleteContact = this.wDeleteContact.bind(this);
        this.updateContact = this.updateContact.bind(this);
        
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
                hidden: contact.hidden,
                createdAt: contact.createdAt
            });
        });
    }

    wDeleteContact(){
        let hidden = this.state.hidden;
        swal({
            title:"Delete",
            icon:"warning",
            text: `${(!hidden)?"Do you want to delete this contact?":
            "If you delete it you cannot recover it."}`,
            buttons:{
                cancel:{
                    text:"No",
                    visible:true,
                    className:"btn btn-primary"
                },
                confirm:{
                    text:"Yes",
                    visible: true,
                    className:"btn btn-danger"
                }
            }
        }).then((isConfirm) => {
            if(isConfirm){
                swal({
                    title:"Contact Deleted",
                    icon:"success",
                    text:" ",
                    button: false
                });
                this.deleteContact();
            }
        });
        
    }

    deleteContact(){
        if(this.state.hidden){
            ContactService.confirmDelete(this.state,this.state.id);
            setTimeout(function(){
                window.location.assign("/");
            },1000);
        }else{
            ContactService.deleteContact(this.state,this.state.id);
            setTimeout(function(){
                window.location.assign("/");
            },1000);
        }
    }

    updateContact(){
        let hidden = this.state.hidden;
        console.log(hidden);
        if(hidden){
            swal({
                title:"Recovered",
                icon: "success",
                text:" ",
                button: false
            })
            ContactService.recoverContact(this.state,this.state.id);
            setTimeout(function(){
                window.location.assign("/");
            },1000);
        }else{
            window.location.assign(`/contact/update/${this.state.id}`);
        }
    }

    render() {
        return (
            <div> 
                {(this.state.createdAt !== '')?
                <div className="contact-container">
                    <h3 className="fw-bold fs-2 form-text contact-names text-break">{this.state.firstname} {this.state.lastname}</h3>
                    <div className="contact-img"><img src={image} className="rounded" alt="" /></div>
                    <div className="contact-info">
                        <h3 className="fw-normal fs-3 form-text">{this.state.phone}</h3>
                        <h3 className="fw-normal fs-3 form-text">{this.state.birthday}</h3>
                    </div>
                    <div className="option-btns">
                        <button className='btn btn-success' onClick={this.updateContact} >
                            {(this.state.hidden)?"Recover":"Edit" }</button>
                        <button className='btn btn-danger' onClick={this.wDeleteContact}>Delete</button>
                    </div>
                    <div className="go-back-btn">
                        <a href="/" className="btn btn-primary">Go home</a>
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
export default ShowContactComponent;