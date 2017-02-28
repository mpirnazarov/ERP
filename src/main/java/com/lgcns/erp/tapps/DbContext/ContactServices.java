package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.DbEntities.ContactsEntity;
import com.lgcns.erp.tapps.model.DbEntities.OrganizationEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafatdin on 10.11.2016.
 */
public class ContactServices{
    public static List<ContactsEntity> getAllContacts(){
        List<ContactsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ContactsEntity locs");
            list = (List<ContactsEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return list;
    }
    public static List<OrganizationEntity> getAllOrganizations(){
        List<OrganizationEntity> list = null, result = new ArrayList<>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from OrganizationEntity locs");
            list = (List<OrganizationEntity>)query.list();

            for(OrganizationEntity oe : list){
                if(oe.getId() != 0)
                    result.add(oe);
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return result;
    }

    public static OrganizationEntity getOrganizationtById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            return session.get(OrganizationEntity.class, id);
        } catch (Exception e) {
            return new OrganizationEntity();
        }
        finally {
            session.close();
        }
    }
    public static ContactsEntity getContactById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            return session.get(ContactsEntity.class, id);
        } catch (Exception e) {
            return new ContactsEntity();
        }
        finally {
            session.close();
        }
    }

    public static void insert(ContactsEntity contact){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ContactsEntity newContact = contact;
            newContact.setOrganizationByOrganizationId(session.load(OrganizationEntity.class, contact.getOrganizationId()));
            session.save(newContact);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    public static void insert(OrganizationEntity entity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void update(OrganizationEntity entity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            OrganizationEntity temp = session.get(OrganizationEntity.class, entity.getId());
            temp.setName(entity.getName());
            temp.setAddress(entity.getAddress());
            temp.setTin(entity.getTin());
            session.save(temp);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    public static void update(ContactsEntity entity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ContactsEntity temp = session.get(ContactsEntity.class, entity.getId());
            temp.setName(entity.getName());
            temp.setOrganizationByOrganizationId(session.load(OrganizationEntity.class, entity.getOrganizationId()));
            temp.setWorkPhone(entity.getWorkPhone());
            temp.setMobilePhone(entity.getMobilePhone());
            session.save(temp);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void delete(OrganizationEntity entity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            OrganizationEntity temp = session.get(OrganizationEntity.class, entity.getId());
            session.delete(temp);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    public static void delete(ContactsEntity entity){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ContactsEntity temp = session.get(ContactsEntity.class, entity.getId());
            session.delete(temp);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
