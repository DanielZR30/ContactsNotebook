import React, { Component } from 'react';
import ContactService from '../services/ContactService';
import swal from 'sweetalert';

class ListContactComponent extends Component {
    
    constructor(props){
        super(props);

        this.state = { 
            contacts: [],
            show: false,
        }

        this.btnContact = this.btnContact.bind(this);
        this.showContact = this.showContact.bind(this);
        this.changeShow = this.changeShow.bind(this);

    }

    componentDidMount(){
        ContactService.getContacts().then((res)=>{
            console.log(res.data.data);
            this.setState({
                contacts: res.data.data,
                show: true,
            });
        });
    }

    btnContact(){
        if(this.state.show){
            window.location.assign("/add-contact");
        }else{swal({
            title:"Delete",
            icon:"warning",
            text:"This data won't be recover it. \nContinue?",
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
                this.state.contacts.forEach(element => {
                    console.log(element);
                    ContactService.confirmDelete(element,element.id);
                    setTimeout(function(){
                        window.location.assign("/");
                    },1000);
                });
            }
        });
        }
    }

    showContact(id){
        window.location.assign(`/show/contact/${id}`);
    }
    
    changeShow(){
        let oldState = this.state;
        this.setState({
            contacts: [],
            show: !oldState.show,
        });
        this.showTable();
    }

    showTable(){


        if(!this.state.show){
            ContactService.getContacts().then((res)=>{
                console.log(res.data.data);
                this.setState({
                    contacts: res.data.data,
                    show: true,
                });
            });
        }else{
            ContactService.getDeletedContacts().then((res)=>{
                console.log(res.data.data);
                this.setState({
                    contacts: res.data.data,
                    show: false,
                });
            });
        }
    }

    render() {
        return (
            <div className='container list-container'>
                <h2 className="page-title">{(!this.state.show)?"Deleted Contacts ":"Contacts"}</h2>
                <div className="row">
                    <button className={(!this.state.show)?"btn btn-danger":"btn btn-primary" } onClick={this.btnContact}>
                        {(!this.state.show)?"Delete All":"Add contact" }</button>
                    <button className="btn btn-primary" onClick={this.changeShow}>
                        {(this.state.show)?"Deleted":"Contacts"}</button>
                </div>
                <div className="row container table-container overflow-auto">
                    {(this.state.contacts.length<=0)?
                    <div className='contact-container'>
                        <p className='text-no-data text-center text-wrap fs-1 fw-bold'>No data to show</p>
                    </div>
                    :
                    <table className="table table-striped table-bordered table-responsive">
                        <thead>
                            <tr>
                                <th>First Name </th>
                                <th>Last Name </th>
                                <th>Phone </th>
                                <th>Action </th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.contacts.map(
                                    contact =>
                                    <tr key={contact.id}>
                                        <td>{contact.firstname}</td>
                                        <td>{contact.lastname}</td>
                                        <td>{contact.phone}</td>
                                        <td>
                                            <button className="btn btn-info" onClick={() => this.showContact(contact.id)}>More</button>
                                        </td>
                                    </tr>
                                )
                            }
                        </tbody>
                    </table>}
                </div>
            </div>
        );
    }
}

export default ListContactComponent;