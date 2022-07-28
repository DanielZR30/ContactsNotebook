package com.sofka.contacts.controller;


import com.sofka.contacts.domain.Contact;
import com.sofka.contacts.service.ContactsService;
import com.sofka.contacts.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@Slf4j
@CrossOrigin(origins ="http://localhost:3000")
@RestController
public class ContactsController {

    /**
     * Service to use the contacts
     */
    @Autowired
    private ContactsService contactsService;

    /**
     * Varible to manage the API
     */
    private Response response = new Response();

    /**
     * HTTP which answer in the API
     */
    private HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Creates contact in the backend
     * @param contact object type contact to create
     * @return response object JSON
     */

    /************************************************CREATE************************************************************/
    @PostMapping(path = "/api/v1/contact")
    public ResponseEntity<Response> createContact(@RequestBody Contact contact) {
        response.restart();
        try {
            log.info("Create contact: {}", contact);
            contact.setHidden(false);
            response.data = contactsService.createContact(contact);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /********************************************READ******************************************************************/

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @GetMapping(path = "/")
    public ResponseEntity<Response> homeIndex1(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    /**
     * Atención a la dirección raíz del sistema, este redirige a /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse usado para redireccionar el controlador
     * @return Objeto Response en formato JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @GetMapping(path = "/api")
    public ResponseEntity<Response> homeIndex2(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "/api/v1")
    public ResponseEntity<Response> homeIndex3(HttpServletResponse httpResponse) {
        return getResponseHome(httpResponse);
    }

    @GetMapping(path = "/api/v1/index")
    public ResponseEntity<Response> index(HttpServletResponse httpResponse) {
        response.restart();
        try {
            response.data = contactsService.getList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> showContact(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = contactsService.getContact(id);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    @GetMapping(path = "/api/v1/contact/deleted")
    public ResponseEntity<Response> showDeleted(HttpServletResponse httpResponse) {
        response.restart();
        try {
            response.data = contactsService.getDeletedList();
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Search contact with a condition
     * @param dataToSearch String to find
     * @return
     */
    @GetMapping(path = "/api/v1/search/contact/{dataToSearch}")
    public ResponseEntity<Response> searchContactBy(
            @PathVariable(value="dataToSearch") String dataToSearch
    ) {
        response.restart();
        try {
            response.data = contactsService.searchContact(dataToSearch);
            httpStatus = HttpStatus.OK;
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /*********************************************UPDATES**************************************************************/

    /**
     * UPDATE ALL THE CONTACT
     * @param contact
     * @param id
     * @return response object JSON
     */
    @PutMapping(path = "/api/v1/contact/update/{id}")
    public ResponseEntity<Response> updateContact(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.updateContact(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Update contact's firstname by id
     *
     * @param contact Objet Contact
     * @param id contact's id to update
     * @return Response JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/contact/update/{id}/name")
    public ResponseEntity<Response> updateFirstName(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.updateFirstName(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Update contact's Lastname by id
     *
     * @param contact Objet Contact
     * @param id contact's id to update
     * @return Response JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/contact/update/{id}/lastname")
    public ResponseEntity<Response> updateLastName(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.updateLastName(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Update contact's phone by id
     *
     * @param contact Objet Contact
     * @param id contact's id to update
     * @return Response JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/contact/update/{id}/phone")
    public ResponseEntity<Response> updatePhone(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.updatePhone(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Update contact's birthday by id
     *
     * @param contact Objet Contact
     * @param id contact's id to update
     * @return Response JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    @PatchMapping(path = "/api/v1/contact/update/{id}/date")
    public ResponseEntity<Response> updateBirthday(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.updateBirthday(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /*******************************************DELETE AND RECOVER*****************************************************/

    /**
     *Deletes temporally a contact
     * @return the contact deleted
     */
    @PatchMapping(path = "/api/v1/contact/delete/{id}")
    public ResponseEntity<Response> deleteHideContact(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.changeHidden(id, contact,true);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     *Recovers a contact with a logical deletion
     * @return the contact deleted
     */
    @PatchMapping(path = "/api/v1/contact/recover/{id}")
    public ResponseEntity<Response> recoverContact(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactsService.changeHidden(id,contact, false);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     *Deletes a contact already hidden
     * @return the contact deleted
     */
    @DeleteMapping(path = "/api/v1/contact/deleted/{id}")
    public ResponseEntity deleteContact(@PathVariable(value="id") Integer id){
        try {
            response.data = contactsService.deleteContact(id);
            if (response.data == null) {
                response.message = "Contact does not exist.";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Contact was deleted successfully.";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }


    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para la redirección al controllador /api/v1/index
     *
     * @param httpResponse Objeto HttpServletResponse para el manejo de la redirección
     * @return Objeto Response en formato JSON
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private ResponseEntity<Response> getResponseHome(HttpServletResponse httpResponse) {
        response.restart();
        try {
            httpResponse.sendRedirect("/api/v1/index");
        } catch (IOException exception) {
            response.error = true;
            response.data = exception.getCause();
            response.message = exception.getMessage();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity(response, httpStatus);

    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     *
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
