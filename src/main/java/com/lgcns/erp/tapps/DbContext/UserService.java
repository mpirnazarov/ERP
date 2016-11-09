package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.model.UserInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafatdin on 16.09.2016.
 */
public class UserService {

    public static Integer Authenticate(UserInfo userInfo) {
        String curHashedPassword = null;
        try {
            curHashedPassword = BuildPassword(userInfo.getUsername(), userInfo.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        List list = null;
        //SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory(); //!This is the wrong way
        Session session = HibernateUtility.getSessionFactory().openSession();                    //!This is the singleton way
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity where userName = :username and passwordHash = :password");
            query.setParameter("username",userInfo.getUsername());
            query.setParameter("password",curHashedPassword);
            list = (List<UserInfo>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return list.size();
    }

    private static String BuildPassword(String userName, String password) throws NoSuchAlgorithmException {
        if (userName.length() < 3)
            try {
                throw new Exception("Your Username is less than 3 characters");
            } catch (Exception e) {
                e.printStackTrace();
            }
        String result = userName.substring(0,3)+password+userName.substring(userName.length() - 3);
        return sha256(result);
    }

    static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    static List<UsersEntity> getAllUsers(){
        List<UsersEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity ");
            list = (List<UsersEntity>)query.list();
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

    public static Collection<UsersEntity> getDirectHeads()
    {
        Collection list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity userLoc join userLoc.userLocalizationsesById");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            //query.setParameter("languageId", Language.eng.getCode());
            list = (Collection<UsersEntity>)query.list();
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

    public static UsersEntity getUserByUsername(String username)
    {
        UsersEntity user=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity users where users.userName = :username");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            query.setParameter("username", username);
            user = (UsersEntity)query.getSingleResult();

            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return user;
    }

    public static List<UserLocalizationsEntity> getUserLocByUserId(int userId)
    {
        List list = null;
        UserLocalizationsEntity userLoc=null;
        List<UserLocalizationsEntity> userLocalizationsEntities=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserLocalizationsEntity userLoc where userLoc.userId = :userId");
            query.setParameter("userId", userId);
            //userLoc = (UserLocalizationsEntity) query.getSingleResult();
            userLocalizationsEntities = query.getResultList();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return userLocalizationsEntities;
    }


    public static List<DepartmentLocalizationsEntity> getDepartments(int language){
        List<DepartmentLocalizationsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DepartmentLocalizationsEntity locs where locs.languageId = :language");
            query.setParameter("language",language);
            list = query.list();
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
    public static List<DepartmentLocalizationsEntity> getDepartments(){
        return getDepartments(3);
    }

    public static List<StatusLocalizationsEntity> getStatuses(int language){
        List<StatusLocalizationsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from StatusLocalizationsEntity statLoc where statLoc.languageId = :language");
            query.setParameter("language", language);
            list = (List<StatusLocalizationsEntity>)query.list();
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

    public static List<StatusLocalizationsEntity> getStatuses() {
        return getStatuses(3);
    }

    public static Map<Integer, String> getLanguageIdAndName()
    {
        Map<Integer,String> map = new LinkedHashMap<Integer, String>();

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select distinct langLoc from LanguageLocalizationsEntity langLoc");
            for(LanguageLocalizationsEntity langloc : (List<LanguageLocalizationsEntity>)query.list()){
                map.put(langloc.getLanguageId(), langloc.getName());
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

        return map;
    }

    public static int insertUser(UsersEntity user){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            user.setDepartmentsByDepartmentId(session.load(DepartmentsEntity.class, user.getDepartmentId()));
            user.setStatusesByStatusId(session.load(StatusesEntity.class, user.getStatusId()));
            user.setUsersByChiefId(session.load(UsersEntity.class, user.getChiefId()));

            //Save the object in database
            session.save(user);


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
        return user.getId();
    }

    public static void insertUserLoc(List<UserLocalizationsEntity> userLocs){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Save the objects in database
            for(UserLocalizationsEntity temp : userLocs){
                temp.setLanguagesByLanguageId(session.load(LanguagesEntity.class, temp.getLanguageId()));
                temp.setUsersByUserId(session.load(UsersEntity.class, temp.getUserId()));
                session.save(temp);

                /*Query insertQuery = session.createSQLQuery(""+
                        "INSERT INTO public.user_localizations "+
                        "(last_name, first_name, father_name, address, user_id, language_id) "+
                        "VALUES (?,?,?,?,?,?)"
                );
                insertQuery.setParameter(0, temp.getLastName());
                insertQuery.setParameter(1, temp.getFirstName());
                insertQuery.setParameter(2, temp.getFatherName());
                insertQuery.setParameter(3, temp.getAddress());
                insertQuery.setParameter(4, temp.getUserId());
                insertQuery.setParameter(5, temp.getLanguageId());
                insertQuery.executeUpdate();*/
            }

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



    public static RoleLocalizationsEntity getPosition(UsersEntity userInRoles) {
        RoleLocalizationsEntity roleLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInRolesEntity locs where locs.userId = :userId");
            query.setParameter("userId", userInRoles.getId());
            userInRoles = (UsersEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return roleLoc;
    }



    public static RoleLocalizationsEntity getRoleLoc(UsersEntity usersEntity) {
        RoleLocalizationsEntity roleLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RoleLocalizationsEntity locs where locs.roleId = :roleId");
            query.setParameter("roleId", usersEntity.getId());
            //query.setParameter("roleId", userInRoles.getRoleId());
            roleLoc = (RoleLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return roleLoc;

    }

    public static List<FamilyInfosEntity> getFamilyInfos(UsersEntity user) {
        List<FamilyInfosEntity> familyInfosEntities = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FamilyInfosEntity fi where fi.userId = :userId");
            query.setParameter("userId", user.getId());
            //query.setParameter("roleId", userInRoles.getRoleId());
            familyInfosEntities = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return familyInfosEntities;
    }

    public static List<UserInPostsEntity> getUserInPost(UsersEntity user) {
        List<UserInPostsEntity> userInPostsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInPostsEntity locs where locs.userId = :userId order by dateFrom asc ");
            query.setParameter("userId", user.getId());
            //query.setParameter("roleId", userInRoles.getRoleId());
            userInPostsEntity = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userInPostsEntity;
    }

    public static PostLocalizationsEntity getJobTitle(int postId, int languageId) {
        PostLocalizationsEntity postLocalizationEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from PostLocalizationsEntity locs where locs.postId = :postId and locs.languageId=:languageId");
            query.setParameter("postId", postId);
            query.setParameter("languageId", languageId);
            //query.setParameter("roleId", userInRoles.getRoleId());
            postLocalizationEntity = (PostLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return postLocalizationEntity;
    }

    public static List<RoleLocalizationsEntity> getRolesLoc(int languageId) {
        List<RoleLocalizationsEntity> roles = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from RoleLocalizationsEntity roleLoc where lenguageId = :langId");
            query.setParameter("langId", languageId);
            roles = query.getResultList();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return roles;
    }
    public static List<RoleLocalizationsEntity> getRolesLoc(){
        return getRolesLoc(Language.eng.getCode());
    }

    public static List<UserLocalizationsEntity> getUserLocalizations(UsersEntity user) {
        List<UserLocalizationsEntity> userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserLocalizationsEntity roleLoc where userId = :userId");
            query.setParameter("userId", user.getId());
            userLoc =  query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userLoc;
    }

    public static List<FamiliyInfoLocalizationsEntity> getFamilyMembers(List<FamilyInfosEntity> familyInfosEntities, int languageId) {
        List<FamiliyInfoLocalizationsEntity> userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FamiliyInfoLocalizationsEntity famLoc where familyInfoid = :userId and languageId=:languageId");
            /*query.setParameter("userId", user.getId());
            query.setParameter("languageId", user.getId());
            userLoc = (UserLocalizationsEntity) query.getSingleResult();*/
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userLoc;

    }

    public static List<FamiliyInfoLocalizationsEntity> getFamilyInfosLoc(FamilyInfosEntity familyInfosEntities) {
        List<FamiliyInfoLocalizationsEntity> userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FamiliyInfoLocalizationsEntity famLoc where familyInfoid = :userId");
            query.setParameter("userId", familyInfosEntities.getId());
            userLoc = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userLoc;
    }

    public static List<EducationsEntity> getEducationsByUsername(UsersEntity user) {
        List<EducationsEntity> educations = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from EducationsEntity edu where userId = :userId");
            query.setParameter("userId", user.getId());
            educations = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return educations;
    }

    public static EducationLocalizationsEntity getEducationLocalization(EducationsEntity edu, int languageId) {
        EducationLocalizationsEntity eduLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from EducationLocalizationsEntity eduLoc where educationId = :eduId and languageId = :languageId");
            query.setParameter("eduId", edu.getId());
            query.setParameter("languageId", languageId);
            eduLoc = (EducationLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return eduLoc;
    }

    public static List<UserInLanguagesEntity> getUserInLanguages(UsersEntity user) {

        List<UserInLanguagesEntity> userInLanguagesEntities = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInLanguagesEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            userInLanguagesEntities = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return userInLanguagesEntities;
    }

    public static List<CertificatesEntity> getCertificates(UsersEntity user) {
        List<CertificatesEntity> certificates = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CertificatesEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            certificates = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return certificates;
    }

    public static CertificateLocalizationsEntity getCertificatesLoc(CertificatesEntity cert, int languageId) {
        CertificateLocalizationsEntity certificatesLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CertificateLocalizationsEntity where certificateId = :certId and languageId=:langId");
            query.setParameter("certId", cert.getId());
            query.setParameter("langId", languageId);
            certificatesLoc = (CertificateLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return certificatesLoc;
    }

    public static List<SalaryHistoriesEntity> getSalaryHistories(UsersEntity user) {
        List<SalaryHistoriesEntity> salaryHistories = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from SalaryHistoriesEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            salaryHistories = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return salaryHistories;
    }

    public static List<WorksEntity> getWorksEntity(UsersEntity user) {
        List<WorksEntity> worksEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from WorksEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            worksEntity = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return worksEntity;
    }

    public static WorkLocalizationsEntity getWorkLocal(WorksEntity we) {
        WorkLocalizationsEntity workLocal = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from WorkLocalizationsEntity where workId = :workId and languageId=3");
            query.setParameter("workId", we.getId());
            workLocal = (WorkLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return workLocal;
    }

    public static List<TrainingsEntity> getTrainingsEntity(UsersEntity user) {
        List<TrainingsEntity> trainingsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TrainingsEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            trainingsEntity = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return trainingsEntity;
    }

    public static TrainingLocalizationsEntity getTrainingLoc(TrainingsEntity trainEn) {
        TrainingLocalizationsEntity trainLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TrainingLocalizationsEntity where trainingId = :trainId and languageId=3");
            query.setParameter("trainId", trainEn.getId());
            trainLoc = (TrainingLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return trainLoc;
    }

    public static void updatePassword(UsersEntity user, String hashedPassword) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set passwordHash = :password where id = :userid");
            query.setParameter("userid", user.getId());
            query.setParameter("password", hashedPassword);
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

    public static List<DocumentsEntity> getDocuments(UsersEntity user) {
        List<DocumentsEntity> documentsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DocumentsEntity where userId = :userId");
            query.setParameter("userId", user.getId());
            documentsEntity = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return documentsEntity;
    }

    public static DocumentsEntity getDocumentById(int id, UsersEntity user) {
        DocumentsEntity documentsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DocumentsEntity where userId = :userId and id = :docId");
            query.setParameter("docId", id);
            query.setParameter("userId", user.getId());
            documentsEntity = (DocumentsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return documentsEntity;
    }
}
