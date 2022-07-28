import axios from 'axios';


const CONTACT_API_BASE = "http://localhost:9090/api/v1/index";
const CONTACT_API_CONTACT = "http://localhost:9090/api/v1/contact/";


class ContactService{

    getContacts(){
        console.log(axios.get(CONTACT_API_BASE));
        return axios.get(CONTACT_API_BASE);
    }

    getDeletedContacts(){
        return axios.get(CONTACT_API_CONTACT+"deleted");
    }

    createContact(contact){
        return axios.post(CONTACT_API_CONTACT,contact);
        
    }

    showContact = (id) =>{
        console.log(id);
        return axios.get(CONTACT_API_CONTACT+`${id}`);
    }

    deleteContact = (contact,id) =>{
        console.log(id)
        return axios.patch(CONTACT_API_CONTACT+`delete/${id}`,contact);
    }

    confirmDelete= (contact,id)=>{
        return axios.delete(CONTACT_API_CONTACT+`deleted/${id}`,contact);
    }

    recoverContact(contact,id){
        return axios.patch(CONTACT_API_CONTACT+`recover/${id}`,contact);
    }

    updateContact(contact,id){
        return axios.put(CONTACT_API_CONTACT+`update/${id}`,contact);
    }
    


}

export default new ContactService();
