package com.lgcns.erp.workflow.DBContext;

import com.lgcns.erp.tapps.DbContext.HibernateUtility;
import com.lgcns.erp.workflow.DBEntities.*;
import com.lgcns.erp.workflow.Enums.Status;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Muslimbek on 1/23/2017.
 */
public class WorkflowService {

    public static List<RequestsEntity> getRequestList(){
        List<RequestsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RequestsEntity");
            list = (List<RequestsEntity>)query.list();
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

    public static List<RequestsEntity> filter(String whereClause){
        String q = "select r from RequestsEntity r ";

        List<RequestsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(q+whereClause+" order by r.dateCreated desc, r.statusId");
            list = (List<RequestsEntity>)query.list();
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

    public static List<StepsEntity> getStepsList(){
        List<StepsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepsEntity");
            list = (List<StepsEntity>)query.list();
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

    public static RequestsEntity getRequestsEntityById(int id){
        RequestsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RequestsEntity where id="+id);
            entity = (RequestsEntity)query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return entity;
    }

    public static List<AttachmentsEntity> getAttachmentList(){
        List<AttachmentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttachmentsEntity ");
            list = (List<AttachmentsEntity>)query.list();
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


    public static String getAttachmentPathNameById(Long id){
        AttachmentsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttachmentsEntity WHERE id="+id);
            entity = (AttachmentsEntity)query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return entity.getUrl();
    }

    public static List<TripTypesEntity> getTripTypes() {
        List<TripTypesEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TripTypesEntity ");
            list = (List<TripTypesEntity>)query.list();
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

    public static List<StepCommentsEntity> getStepComments(){
        List<StepCommentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepCommentsEntity ");
            list = (List<StepCommentsEntity>)query.list();
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

    public static List<StepCommentsEntity> getStepCommentsByStepId(int id){
        List<StepCommentsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepCommentsEntity WHERE stepsId="+id);
            list = (List<StepCommentsEntity>)query.list();
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

    public static void stepApprove(int reqId, int statusId, String comment){
        int newRowId = WorkflowToDoApproveService.getTheNextSequence(reqId, statusId, comment);
        if (newRowId != -1){
            WorkflowToDoApproveService.approve(reqId, statusId);
            WorkflowToDoApproveService.setNewStep(newRowId, reqId);
        }
    }

    public static void stepReject(int reqId, int statusId, String comment){
        WorkFlowToDoRejectService.reject(reqId, statusId, comment);
    }

    public static void stepReview(int reqId, int statusId, String comment){
        WorkFlowToDoReviewService.review(reqId, statusId, comment);
    }

    public static StepsEntity getStepById(int id){
        StepsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StepsEntity where id="+id);
            entity = (StepsEntity)query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return entity;
    }

    public static int insertRequests(RequestsEntity requestsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
/*
            requestsEntity.setTripTypesByTripTypeId(session.load(TripTypesEntity.class, requestsEntity.getTripTypeId()));
*/
            //Save the object in database
           int id = (int)session.save(requestsEntity);
            System.out.println(id);
            //Commit the transaction
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return requestsEntity.getId();
    }

    public static void insertMembers(MembersEntity membersEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            membersEntity.setRequestsByRequestId(session.load(RequestsEntity.class, membersEntity.getRequestId()));
            //Save the object in database
            session.save(membersEntity);
            //Commit the transaction
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

    public static void insertToDo(ToDoEntity toDoEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            toDoEntity.setRequestsByRequestId(session.load(RequestsEntity.class, toDoEntity.getRequestId()));
            //Save the object in database
            session.save(toDoEntity);
            //Commit the transaction
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

    public static void insertAttachments(AttachmentsEntity attachmentsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            attachmentsEntity.setRequestsByRequestId(session.load(RequestsEntity.class, attachmentsEntity.getRequestId()));
            //Save the object in database
            session.save(attachmentsEntity);
            //Commit the transaction
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

    public static void insertSteps(StepsEntity stepsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            stepsEntity.setRequestsByRequestId(session.load(RequestsEntity.class, stepsEntity.getRequestId()));
            stepsEntity.setInvolvementTypesByInvolvementTypeId(session.load(InvolvementTypesEntity.class, stepsEntity.getInvolvementTypeId()));

            //Save the object in database
            session.save(stepsEntity);

            //Commit the transaction
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

    public static List<MembersEntity> getMembersByRequestId(int requestId) {
        List<MembersEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from MembersEntity WHERE requestId=:reqId");
            query.setParameter("reqId", requestId);
            list = (List<MembersEntity>)query.list();
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

    public static List<ToDoEntity> getToDoByRequestId(int requestId) {
        List<ToDoEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ToDoEntity WHERE requestId=:reqId");
            query.setParameter("reqId", requestId);
            list = (List<ToDoEntity>)query.list();
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

    public static void deleteAttachment(Long id) {

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from AttachmentsEntity where id = :docId");
            query.setParameter("docId", id);
            query.executeUpdate();
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

    public static AttachmentsEntity getAttachmentById(Long id) {
        AttachmentsEntity entity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AttachmentsEntity where id="+id);
            entity = (AttachmentsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return entity;
    }

    public static void updateRequestUnformatted(RequestsEntity requestsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        RequestsEntity requestsEntityForStatus = getRequestsEntityById(requestsEntity.getId());

        if (requestsEntityForStatus.getStatusId()== Status.Draft.getValue()){
            setDraftToInProgressSteps(requestsEntity.getId());
        }else {
            requestHasReviewed(requestsEntity.getId());
        }

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update RequestsEntity set subject = :subject, statusId=1, description=:descrtiption, " +
                    "dateFrom=:dateFrom, dateTo=:dateTo where id = :id");

            query.setParameter("id", requestsEntity.getId());
            query.setParameter("subject", requestsEntity.getSubject());
            query.setParameter("descrtiption", requestsEntity.getDescription());
            query.setParameter("dateFrom", requestsEntity.getDateFrom());
            query.setParameter("dateTo", requestsEntity.getDateTo());

            query.executeUpdate();
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

    private static void setDraftToInProgressSteps(int id) {

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        setDraftInactiveToActiveSteps(id);
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set statusId = 1 where requestId= :reqId");
            query.setParameter("reqId", id);
            query.executeUpdate();
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

    private static void setDraftInactiveToActiveSteps(int id){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set active=true where requestId= :reqId AND stepSequence=1");
            query.setParameter("reqId", id);
            query.executeUpdate();
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

    private static void requestHasReviewed(int id){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        setDraftInactiveToActiveSteps(id);
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update StepsEntity set statusId = 1 where requestId= :reqId AND active=true");
            query.setParameter("reqId", id);
            query.executeUpdate();
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

    public static void updateRequestLeaveApprove(RequestsEntity requestsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        RequestsEntity requestsEntityForStatus = getRequestsEntityById(requestsEntity.getId());

        if (requestsEntityForStatus.getStatusId()==Status.Draft.getValue()){
            setDraftToInProgressSteps(requestsEntity.getId());
        }else {
            requestHasReviewed(requestsEntity.getId());
        }

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update RequestsEntity set leaveTypeId = :leaveTypeId, statusId = 1, description= :description, " +
                    "dateFrom=:dateFrom, dateTo=:dateTo where id = :id");

            query.setParameter("id", requestsEntity.getId());
            query.setParameter("leaveTypeId", requestsEntity.getLeaveTypeId());
            query.setParameter("description", requestsEntity.getDescription());
            query.setParameter("dateFrom", requestsEntity.getDateFrom());
            query.setParameter("dateTo", requestsEntity.getDateTo());

            query.executeUpdate();
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

    public static void setIsViewed(int req_id){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update RequestsEntity set viewed=true WHERE id=:req_id");
            query.setParameter("req_id", req_id);

            int s = query.executeUpdate();
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

    public static void deleteMembers(int reqId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from MembersEntity where requestId = :reqId");
            query.setParameter("reqId", reqId);
            query.executeUpdate();
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

    public static void deleteToDo(int reqId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from ToDoEntity where requestId = :reqId");
            query.setParameter("reqId", reqId);
            query.executeUpdate();
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

    public static void updateRequestBusinessTrip(RequestsEntity requestsEntity, int reqId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        RequestsEntity requestsEntityForStatus = getRequestsEntityById(requestsEntity.getId());

        if (requestsEntityForStatus.getStatusId()==Status.Draft.getValue()){
            setDraftToInProgressSteps(reqId);
        }else {
            requestHasReviewed(reqId);
        }

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update RequestsEntity set subject = :subject, statusId=1," +
                    "tripTypeId = :tripTypeId, destination = :destination, description= :description, " +
                    "dateFrom=:dateFrom, dateTo=:dateTo where id = :id");

            query.setParameter("id", reqId);
            query.setParameter("subject", requestsEntity.getSubject());
            query.setParameter("tripTypeId", requestsEntity.getTripTypeId());
            query.setParameter("destination", requestsEntity.getDestination());
            query.setParameter("description", requestsEntity.getDescription());
            query.setParameter("dateFrom", requestsEntity.getDateFrom());
            query.setParameter("dateTo", requestsEntity.getDateTo());

            query.executeUpdate();
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
