package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.model.DbEntities.*;
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

    public static void insertContactInProjects(List<ContactInProjectsEntity> contactsAndProjects){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for(ContactInProjectsEntity item : contactsAndProjects){
                item.setProjectsByProjectId(session.load(ProjectsEntity.class, item.getProjectId()));
                item.setContactsByContactId(session.load(ContactsEntity.class, item.getContactId()));
                session.save(item);
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
    }

    public static void insertContact(ContactsEntity contact){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(contact);
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
