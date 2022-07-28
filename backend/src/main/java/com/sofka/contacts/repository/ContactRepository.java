package com.sofka.contacts.repository;

import com.sofka.contacts.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {


    /**
     * Shows the contacts and the deleted contacts but not at the same time
     * @param hidden false to show non-deleted items
     * @return list of contacts
     */
    @Query(value="SELECT c FROM Contact c WHERE c.hidden=:hidden")
    public List<Contact> findList(@Param("hidden") Boolean hidden);
    /**
     * Searches by phone number
     * @param data the phone number to search
     * @return list of contacts JSON
     */
    @Query(value="SELECT c FROM Contact c " +
            "WHERE c.phone =:data AND (c.hidden = false)" +
            "ORDER BY c.firstname ASC")
    public List<Contact> findByPhone(@Param("data") long data);

    /**
     * Searches by if the name or lastname starts with dataToSearch
     * @param dataToSearch search this data
     * @return list with all the contacts which start with dataToSearch
     */
   @Query(value="SELECT c FROM Contact c " +
            "WHERE (c.firstname LIKE :data% OR " +
            "c.lastname LIKE :data%)  AND (c.hidden = false)" +
            "ORDER BY c.firstname ASC")
    public List<Contact> findByFLNameStartWith(@Param("data")String dataToSearch);

    /**
     * Searches by if the name or lastname contains dataToSearch
     * @param dataToSearch search this data
     * @return list with all the contacts which contains dataToSearch
     */
    @Query(value="SELECT c FROM Contact c " +
            "WHERE (c.firstname LIKE %:data% OR " +
            "c.lastname LIKE %:data%) AND (c.hidden = false)" +
            "ORDER BY c.firstname ASC")
    public List<Contact> findByFLNameContain(@Param("data")String dataToSearch);

    /**
     * Searches by if the name or lastname ends with dataToSearch
     * @param dataToSearch search this data
     * @return list with all the contacts which ends with dataToSearch
     */
    @Query(value="SELECT c FROM Contact c " +
            "WHERE (c.firstname LIKE %:data OR " +
            "c.lastname LIKE %:data) AND (c.hidden = false)" +
            "ORDER BY c.firstname ASC")
    public   List<Contact> findByFLNameEndWith(@Param("data")String dataToSearch);

    /**
     * Update the name from an item
     * @param id id for the item to update
     * @param firstname new name
     */
    @Modifying
    @Query(value="UPDATE Contact c SET c.firstname = :firstname, c.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id = :id AND (c.hidden = false)")
    void updateFirstname(@Param("id") Integer id,@Param("firstname") String firstname);

    /**
     * Update the lastname from an item
     * @param id id for the item to update
     * @param lastname new lastname
     */
    @Modifying
    @Query(value="UPDATE Contact c SET c.lastname = :lastname, c.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id = :id AND (c.hidden = false)")
    void updateLastname(@Param("id") Integer id,@Param("lastname") String lastname);

    /**
     * Update the phone number from an item
     * @param id id for the item to update
     * @param phone new phone number
     */
    @Modifying
    @Query(value="UPDATE Contact c SET c.phone = :phone, c.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id = :id AND (c.hidden = false)")
    void updatePhone(@Param("id") Integer id,@Param("phone") long phone);

    /**
     * Update the birthday from an item
     * @param id id for the item to update
     * @param birthday new birthday
     */
    @Modifying
    @Query(value="UPDATE Contact c SET c.birthday = :birthday, c.updatedAt = CURRENT_TIMESTAMP " +
            "WHERE c.id = :id AND (c.hidden = false) ")
    void updateBirthday(@Param("id") Integer id, @Param("birthday") LocalDate birthday);

   @Modifying
   @Query(value="UPDATE Contact c SET c.hidden = :hidden, c.updatedAt = CURRENT_TIMESTAMP WHERE c.id = :id")
   void updateHidden(@Param("id") Integer id,@Param("hidden") Boolean hidden);
}