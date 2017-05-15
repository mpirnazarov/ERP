package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

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

    public static List<UsersEntity> getAllUsers(){
        List<UsersEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity");
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

    public static List<UserLocalizationsEntity> getUserLocByUserId(int userId){
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
            Query query = session.createQuery("select distinct langLoc from LanguageLocalizationsEntity langLoc where langLoc.localizationLanguage = :langId");
            query.setParameter("langId",Language.eng.getCode());
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


    public static List<UserLocalizationsEntity> getAllUserLocs() {
        return getAllUserLocs(3);
    }
    public static List<UserLocalizationsEntity> getAllUserLocs(int language) {
        List<UserLocalizationsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserLocalizationsEntity loc where loc.languageId = :language");
            query.setParameter("language", language);
            list = (List<UserLocalizationsEntity>)query.list();
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

    public static void insertUserLoc(List<UserLocalizationsEntity> locs){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Save the objects in database

            for(UserLocalizationsEntity temp : locs){
                temp.setLanguagesByLanguageId(session.load(LanguagesEntity.class, temp.getLanguageId()));
                temp.setUsersByUserId(session.load(UsersEntity.class, temp.getUserId()));
                session.save(temp);
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
            query.setParameter("roleId", usersEntity.getRoleId());
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
            Query query = session.createQuery("from UserInPostsEntity where userId = :userId order by dateFrom asc ");
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
            if(!query.list().isEmpty())
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

    public static String getUserFullNameInLanguageById(int userId, int languageCode) {
        List<UserLocalizationsEntity> userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserLocalizationsEntity roleLoc where userId = :userId and languageId = :languageId");
            query.setParameter("userId", userId);
            query.setParameter("languageId", languageCode);
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
        return userLoc.get(0).getFirstName() + " " + userLoc.get(0).getLastName();
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

    public static List<FamiliyInfoLocalizationsEntity> getFamilyInfosLoc(int familyInfosEntities) {
        List<FamiliyInfoLocalizationsEntity> userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from FamiliyInfoLocalizationsEntity famLoc where familyInfoid = :userId");
            query.setParameter("userId", familyInfosEntities);
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

    public static List<SalaryHistoriesEntity> getSalaryHistories(int userId) {
        List<SalaryHistoriesEntity> salaryHistories = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from SalaryHistoriesEntity where userId = :userId");
            query.setParameter("userId", userId);
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

    public static DocumentsEntity getDocumentById(int id, int userId) {
        DocumentsEntity documentsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DocumentsEntity where userId = :userId and id = :docId");
            query.setParameter("docId", id);
            query.setParameter("userId", userId);
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

    public static UsersEntity getUserById(int id) {

        UsersEntity usersEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UsersEntity where id = :userId");
            query.setParameter("userId", id);
            usersEntity = (UsersEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return usersEntity;
    }

    public static List<PostLocalizationsEntity> getPostLocalizations(int language) {
        List<PostLocalizationsEntity> postLocalizations = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from PostLocalizationsEntity where languageId=:language");
            query.setParameter("language", language);
            postLocalizations = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return postLocalizations;
    }


    public static void updateUsersEntity(ProfileViewModel person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set dateOfBirth = :dateOfBirth, mobilePhone = :mobilePhone, " +
                    "homePhone = :homePhone, eMail = :companyEmail, personalEmail = :personalEmail, statusId = :statusId, hiringDate=:hiringDate, " +
                    "political = :isPolitical, passport =:passport, roleId = :roleId  where id = :userid");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("dateOfBirth", person.getPersonalInfo().getDateOfBirth());
            query.setParameter("mobilePhone", person.getPersonalInfo().getMobilePhone());
            query.setParameter("homePhone", person.getPersonalInfo().getHomePhone());
            query.setParameter("companyEmail", person.getPersonalInfo().getEmailCompany());
            query.setParameter("personalEmail", person.getPersonalInfo().getEmailPersonal());
            query.setParameter("statusId", person.getStatusId());
            query.setParameter("hiringDate", person.getEntryDate());
            query.setParameter("isPolitical", person.getIsPolitical());
            query.setParameter("passport", person.getPassportNumber());
            query.setParameter("roleId", person.getRoleId());
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

    public static int updateUsersLocEntityEn(ProfileViewModel person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserLocalizationsEntity set lastName = :lastName, firstName = :firstName, " +
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where userId = :userid and languageId = 3");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[2]);
            query.setParameter("firstName", person.getFirstName()[2]);
            query.setParameter("fathersName", person.getFathersName()[2]);
            query.setParameter("address", person.getAddress()[2]);
            System.out.println("BirthPlace: "+person.getPersonalInfo().getBirthPlace()[2]);
            query.setParameter("birthPlace", person.getPersonalInfo().getBirthPlace()[2]);
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
        return 0;
    }
    public static int updateUsersLocEntityRu(ProfileViewModel person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserLocalizationsEntity set lastName = :lastName, firstName = :firstName, " +
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where userId = :userid and languageId = 1");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[0]);
            query.setParameter("firstName", person.getFirstName()[0]);
            query.setParameter("fathersName", person.getFathersName()[0]);
            query.setParameter("address", person.getAddress()[0]);
            query.setParameter("birthPlace", person.getPersonalInfo().getBirthPlace()[0]);
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
        return 0;
    }
    public static int updateUsersLocEntityUz(ProfileViewModel person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserLocalizationsEntity set lastName = :lastName, firstName = :firstName, " +
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where userId = :userid and languageId = 2");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[1]);
            query.setParameter("firstName", person.getFirstName()[1]);
            query.setParameter("fathersName", person.getFathersName()[1]);
            query.setParameter("address", person.getAddress()[1]);
            query.setParameter("birthPlace", person.getPersonalInfo().getBirthPlace()[1]);
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
        return 0;
    }

    public static UsersEntity disableUser(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set enabled = 'false' where id = :userid");
            query.setParameter("userid", id);
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
        return null;
    }

    public static void enableUser(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set enabled = 'true' where id = :userid");
            query.setParameter("userid", id);
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

    public static String getUsernameById(int userId) {

        String username=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select userName from UsersEntity where id = :userId");
            query.setParameter("userId", userId);
            username = (String) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return username;
    }

    public static int getIdByUsername(String name){
        int id = 0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select id from UsersEntity where userName = :userName");
            query.setParameter("userName", name);
            id = (Integer) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return id;
    }

    public static void insertEvaluation(PersonalEvalutionsEntity personalEvalutionsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            personalEvalutionsEntity.setUsersByUserId(session.load(UsersEntity.class, personalEvalutionsEntity.getUserId()));
            //Save the object in database
            session.save(personalEvalutionsEntity);


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

    public static void updateUsersFamilyInfo(FamilyMember familyProfile) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update FamilyInfosEntity set dateOfBirth = :dateOfBirth where id = :famId");
            query.setParameter("dateOfBirth", familyProfile.getDateOfBirth());
            query.setParameter("famId", familyProfile.getId());
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
    public static void updateUsersFamilyInfoLocEn(FamilyMember familyProfile) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update FamiliyInfoLocalizationsEntity set lastName = :lastName, firstName=:firstName, relation=:relation, jobTitle=:jobTitle where familyInfoid = :famId and languageId=3");
            query.setParameter("lastName", familyProfile.getLastName()[2]);
            query.setParameter("firstName", familyProfile.getFirstName()[2]);
            query.setParameter("relation", familyProfile.getRelation()[2]);
            query.setParameter("jobTitle", familyProfile.getJobTitle()[2]);
            query.setParameter("famId", familyProfile.getId());
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
    public static void updateUsersFamilyInfoLocRu(FamilyMember familyProfile) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update FamiliyInfoLocalizationsEntity set lastName = :lastName, firstName=:firstName, relation=:relation, jobTitle=:jobTitle where familyInfoid = :famId and languageId=1");
            query.setParameter("lastName", familyProfile.getLastName()[0]);
            query.setParameter("firstName", familyProfile.getFirstName()[0]);
            query.setParameter("relation", familyProfile.getRelation()[0]);
            query.setParameter("jobTitle", familyProfile.getJobTitle()[0]);
            query.setParameter("famId", familyProfile.getId());
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
    public static void updateUsersFamilyInfoLocUz(FamilyMember familyProfile) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update FamiliyInfoLocalizationsEntity set lastName = :lastName, firstName=:firstName, relation=:relation, jobTitle=:jobTitle where familyInfoid = :famId and languageId=2");
            query.setParameter("lastName", familyProfile.getLastName()[1]);
            query.setParameter("firstName", familyProfile.getFirstName()[1]);
            query.setParameter("relation", familyProfile.getRelation()[1]);
            query.setParameter("jobTitle", familyProfile.getJobTitle()[1]);
            query.setParameter("famId", familyProfile.getId());
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

    public static int insertUsersFamilyInfo(FamilyInfosEntity familyInfosEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            familyInfosEntity.setUsersByUserId(session.load(UsersEntity.class, familyInfosEntity.getUserId()));

            //Save the object in database
            session.save(familyInfosEntity);


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
        return familyInfosEntity.getId();
    }

    public static void insertUsersFamilyInfoLocEn(FamiliyInfoLocalizationsEntity familiyInfoLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            familiyInfoLocalizationsEntity.setFamilyInfosByFamilyInfoid(session.load(FamilyInfosEntity.class, familiyInfoLocalizationsEntity.getFamilyInfoid()));
            familiyInfoLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, familiyInfoLocalizationsEntity.getLanguageId()));
            //Save the object in database
            session.save(familiyInfoLocalizationsEntity);


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
    public static void insertUsersFamilyInfoLocRu(FamiliyInfoLocalizationsEntity familiyInfoLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            familiyInfoLocalizationsEntity.setFamilyInfosByFamilyInfoid(session.load(FamilyInfosEntity.class, familiyInfoLocalizationsEntity.getFamilyInfoid()));
            familiyInfoLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, familiyInfoLocalizationsEntity.getLanguageId()));
            //Save the object in database
            session.save(familiyInfoLocalizationsEntity);


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
    public static void insertUsersFamilyInfoLocUz(FamiliyInfoLocalizationsEntity familiyInfoLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            familiyInfoLocalizationsEntity.setFamilyInfosByFamilyInfoid(session.load(FamilyInfosEntity.class, familiyInfoLocalizationsEntity.getFamilyInfoid()));
            familiyInfoLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, familiyInfoLocalizationsEntity.getLanguageId()));
            //Save the object in database
            session.save(familiyInfoLocalizationsEntity);


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

    public static void deleteUsersFamilyInfoLoc(String famId) {

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from FamiliyInfoLocalizationsEntity where familyInfoid = :famId");
            query.setParameter("famId", Integer.parseInt(famId));
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

    public static void deleteUsersFamilyInfo(String famId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from FamilyInfosEntity where id = :famId");
            query.setParameter("famId", Integer.parseInt(famId));
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

    public static void insertSalary(SalaryHistoriesEntity salaryHistoriesEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            salaryHistoriesEntity.setUsersByUserId(session.load(UsersEntity.class, salaryHistoriesEntity.getUserId()));
            salaryHistoriesEntity.setCurrenciesByCurrencyId(session.load(CurrenciesEntity.class, salaryHistoriesEntity.getCurrencyId()));
            //Save the object in database
            session.save(salaryHistoriesEntity);


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

    public static SalaryHistoriesEntity getSalary(int salId) {
        SalaryHistoriesEntity salaryHistoriesEntity=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from SalaryHistoriesEntity where id = :salId");
            query.setParameter("salId", salId);
            salaryHistoriesEntity = (SalaryHistoriesEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return salaryHistoriesEntity;
    }
    // Hibernate @many to one
    public static void updateSalaryPost(SalaryHistoriesEntity salaryVewModel, int salId) {
        /*Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            SalaryHistoriesEntity salaryHistoriesEntity =
                    (SalaryHistoriesEntity) session.get(SalaryHistoriesEntity.class, salId);
            salaryHistoriesEntity.setSalaryBefore(salaryVewModel.getSalaryBefore());
            salaryHistoriesEntity.setPit(salaryVewModel.getPit());
            salaryHistoriesEntity.setInps(salaryVewModel.getInps());
            salaryHistoriesEntity.setPf(salaryVewModel.getPf());
            salaryHistoriesEntity.setDate(salaryVewModel.getDate());
            salaryHistoriesEntity.setSalaryAfter(salaryVewModel.getSalaryAfter());
            salaryHistoriesEntity.setCurrencyId(salaryVewModel.getCurrencyId());
            session.update(salaryHistoriesEntity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }*/

        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update SalaryHistoriesEntity set salaryBefore = :gross, pit=:pit, inps=:inps, pf=:pf, date=:date, salaryAfter=:net, currencyId=:currencyId where id = :id");

            query.setParameter("gross", salaryVewModel.getSalaryBefore());
            query.setParameter("pit", salaryVewModel.getPit());
            query.setParameter("inps", salaryVewModel.getInps());
            query.setParameter("pf", salaryVewModel.getPf());

            query.setParameter("date", salaryVewModel.getDate());
            query.setParameter("net", salaryVewModel.getSalaryAfter());
            query.setParameter("id", salId);
            query.setParameter("currencyId", salaryVewModel.getCurrencyId());
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

    public static int insertWorks(WorksEntity worksEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            worksEntity.setUsersByUserId(session.load(UsersEntity.class, worksEntity.getUserId()));
            //Save the object in database
            session.save(worksEntity);
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
        return worksEntity.getId();
    }

    public static void insertWorksLoc(WorkLocalizationsEntity workLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            workLocalizationsEntity.setWorksByWorkId(session.load(WorksEntity.class, workLocalizationsEntity.getWorkId()));
            workLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, workLocalizationsEntity.getLanguageId()));
            //Save the object in database
            session.save(workLocalizationsEntity);
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

    public static WorksEntity getWorkEntity(int jobId) {
        WorksEntity worksEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from WorksEntity where id = :jobId");
            query.setParameter("jobId", jobId);
            worksEntity = (WorksEntity) query.getSingleResult();
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

    public static int insertTrainings(TrainingsEntity trainingsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            trainingsEntity.setUsersByUserId(session.load(UsersEntity.class, trainingsEntity.getUserId()));
            //Save the object in database
            session.save(trainingsEntity);
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
        return trainingsEntity.getId();
    }

    public static void insertTrainingLoc(TrainingLocalizationsEntity trainingLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            trainingLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, trainingLocalizationsEntity.getLanguageId()));
            trainingLocalizationsEntity.setTrainingsByTrainingId(session.load(TrainingsEntity.class, trainingLocalizationsEntity.getTrainingId()));
            //Save the object in database
            session.save(trainingLocalizationsEntity);
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

    public static TrainingsEntity getTrainingEntity(int trainId) {
        TrainingsEntity trainingsEntity=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TrainingsEntity where id = :trainId");
            query.setParameter("trainId", trainId);
            trainingsEntity = (TrainingsEntity) query.getSingleResult();
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

    public static void updateTrainVM(TrainingsEntity trainingsEntity, int trainId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update TrainingsEntity set dateFrom = :dateFrom, dateTo = :dateTo, " +
                    "numberOfHours = :numberOfHours, mark = :mark where id = :id");
            query.setParameter("id", trainId);
            query.setParameter("dateFrom", trainingsEntity.getDateFrom());
            query.setParameter("dateTo", trainingsEntity.getDateTo());
            query.setParameter("numberOfHours", trainingsEntity.getNumberOfHours());
            query.setParameter("mark", trainingsEntity.getMark());
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

    public static int getUserIdByUsername(String userName) {
        int id = 0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select id from UsersEntity where userName = :userName");
            query.setParameter("userName", userName);
            id = (Integer) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return id;
    }

    public static DocumentsEntity getDocumentByDocId(int docId) {
        DocumentsEntity documentsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DocumentsEntity where id = :docId");
            query.setParameter("docId", docId);
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

    public static void insertDocumentUser(DocumentsEntity documentsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            documentsEntity.setUsersByUserId(session.load(UsersEntity.class, documentsEntity.getUserId()));
            //Save the object in database
            session.save(documentsEntity);
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

    public static void deleteDocument(int docId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from DocumentsEntity where id = :docId");
            query.setParameter("docId", docId);
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

    public static List<PersonalEvalutionsEntity> getEvaluationsByUserId(int userIdByUsername) {
        List<PersonalEvalutionsEntity> evalutionsEntities=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from PersonalEvalutionsEntity userLoc where userId=:userId");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            query.setParameter("userId", userIdByUsername);
            evalutionsEntities = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return evalutionsEntities;
    }

    public static List<DocumentsEntity> getDocumentsGen(int typeId) {
        List<DocumentsEntity> documentsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from DocumentsEntity where documentType = :typeId");
            query.setParameter("typeId", typeId);
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


    public static UserInPostsEntity getUserInPostById(int id) {
        UserInPostsEntity userInPostsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInPostsEntity where id = :id");
            query.setParameter("id", id);
            //query.setParameter("roleId", userInRoles.getRoleId());
            userInPostsEntity = (UserInPostsEntity) query.getSingleResult();
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

    public static UserInPostsEntity getMaxUserInPostByUserId(int id) {
        UserInPostsEntity userInPostsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInPostsEntity where userId = :id and dateFrom = (select max(dateFrom) from UserInPostsEntity where userId = :id)");
            query.setParameter("id", id);
            //query.setParameter("roleId", userInRoles.getRoleId());
            userInPostsEntity = (UserInPostsEntity) query.getSingleResult();
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


    public static void updateUserInPosts(UserInPostsEntity userInPostsEntity, int appId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserInPostsEntity set dateFrom = :dateFrom, contractType=:contractType, " +
                    "postId=:postId, dateEnd=:dateEnd, departmentId=:departmentId, externalId=:externalId where id = :appId");
            query.setParameter("dateFrom", userInPostsEntity.getDateFrom());
            query.setParameter("contractType", userInPostsEntity.getContractType());
            query.setParameter("postId", userInPostsEntity.getPostId());
            query.setParameter("externalId", userInPostsEntity.getExternalId());
            query.setParameter("dateEnd", userInPostsEntity.getDateEnd());
            query.setParameter("departmentId", userInPostsEntity.getDepartmentId());
            query.setParameter("appId", appId);
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

    public static void updateDepartmentId(int departmentId, int userId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set departmentId = :departmentId where id = :userid");
            query.setParameter("userid", userId);
            query.setParameter("departmentId", departmentId);
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

    public static void insertUserInPosts(UserInPostsEntity userInPostsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            System.out.println("Inserting User in post");
            transaction = session.beginTransaction();
            //Set foreign key items
            userInPostsEntity.setUsersByUserId(session.load(UsersEntity.class, userInPostsEntity.getUserId()));
            userInPostsEntity.setPostsByPostId(session.load(PostsEntity.class, userInPostsEntity.getPostId()));
            userInPostsEntity.setExternalsByExternalId(session.load(ExternalLocalizationsEntity.class, userInPostsEntity.getExternalId()));
            //Save the object in database
            session.save(userInPostsEntity);
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

    public static List<CurrencyLocalizationsEntity> getCurrencies(int i) {
        List<CurrencyLocalizationsEntity> currencyLocalizationsEntities=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CurrencyLocalizationsEntity userLoc where languageId=:languageId");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            query.setParameter("languageId", i);
            currencyLocalizationsEntities = (List<CurrencyLocalizationsEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return currencyLocalizationsEntities;
    }

    public static int insertEducations(EducationsEntity educationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            educationsEntity.setUsersByUserId(session.load(UsersEntity.class, educationsEntity.getUserId()));
            //Save the object in database
            session.save(educationsEntity);
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
        return educationsEntity.getId();
    }

    public static void insertEducationLocalizations(EducationLocalizationsEntity educationLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            educationLocalizationsEntity.setEducationsByEducationId(session.load(EducationsEntity.class, educationLocalizationsEntity.getEducationId()));
            educationLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, educationLocalizationsEntity.getLanguageId()));
            //Save the object in database
            session.save(educationLocalizationsEntity);
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

    public static EducationsEntity getEducationById(int eduId) {
        EducationsEntity educationsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from EducationsEntity where id = :id");
            query.setParameter("id", eduId);
            //query.setParameter("roleId", userInRoles.getRoleId());
            educationsEntity = (EducationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return educationsEntity;
    }

    public static void updateEducations(EducationsEntity educationsEntity, int eduId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update EducationsEntity set startDate = :startDate, endDate = :endDate where id = :eduId");
            query.setParameter("startDate", educationsEntity.getStartDate());
            query.setParameter("endDate", educationsEntity.getEndDate());
            query.setParameter("eduId", eduId);
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

    public static void updateEducationLocalizations(EducationLocalizationsEntity edu) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update EducationLocalizationsEntity set name = :name, major = :major, degree = :degree where educationId = :eduId and languageId=:langId");
            query.setParameter("name", edu.getName());
            query.setParameter("major", edu.getMajor());
            query.setParameter("degree", edu.getDegree());
            query.setParameter("eduId", edu.getEducationId());
            query.setParameter("langId", edu.getLanguageId());
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

    public static List<LanguageLocalizationsEntity> getLanguageLocalizations(int i) {
        List<LanguageLocalizationsEntity> languageLocalizationsEntities=null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from LanguageLocalizationsEntity userLoc where localizationLanguage=:languageId");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            query.setParameter("languageId", i);
            languageLocalizationsEntities = (List<LanguageLocalizationsEntity>)query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return languageLocalizationsEntities;
    }

    public static void insertUserInLanguages(UserInLanguagesEntity userInLanguagesEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            userInLanguagesEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, userInLanguagesEntity.getLanguageId()));
            userInLanguagesEntity.setUsersByUserId(session.load(UsersEntity.class, userInLanguagesEntity.getUserId()));
            //Save the object in database
            session.save(userInLanguagesEntity);
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

    public static void updateUserInLanguages(UserInLanguagesEntity languagesEntity, String userId, String langId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserInLanguagesEntity set listening = :listening, " +
                    "writing = :writing, speaking = :speaking, reading = :reading where id = :id");
            query.setParameter("id", Integer.parseInt(langId));
            query.setParameter("listening", languagesEntity.getListening());
            query.setParameter("writing", languagesEntity.getWriting());
            query.setParameter("speaking", languagesEntity.getSpeaking());
            query.setParameter("reading", languagesEntity.getReading());
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

    public static int insertCertificates(CertificatesEntity certificatesEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            certificatesEntity.setUsersByUserId(session.load(UsersEntity.class, certificatesEntity.getUserId()));
            //Save the object in database
            session.save(certificatesEntity);
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
        return certificatesEntity.getId();
    }

    public static void insertCertificateLocalizations(CertificateLocalizationsEntity certificateLocalizationsEntity, int langId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        int id = 0;
        try {
            transaction = session.beginTransaction();
            //Set foreign key items
            certificateLocalizationsEntity.setCertificatesByCertificateId(session.load(CertificatesEntity.class, certificateLocalizationsEntity.getCertificateId()));
            certificateLocalizationsEntity.setLanguagesByLanguageId(session.load(LanguagesEntity.class, langId));
            //Save the object in database
            session.save(certificateLocalizationsEntity);
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

    public static CertificatesEntity getCertificate(int certId) {
        CertificatesEntity certificatesEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from CertificatesEntity where id = :id");
            query.setParameter("id", certId);
            //query.setParameter("roleId", userInRoles.getRoleId());
            certificatesEntity = (CertificatesEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return certificatesEntity;
    }

    public static void updateUsersEntityUser(UsersEntity usersEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set mobilePhone = :mobilePhone, " +
                    "homePhone = :homePhone, eMail = :companyEmail, personalEmail = :personalEmail where id = :userid");
            query.setParameter("userid", usersEntity.getId());

            query.setParameter("mobilePhone", usersEntity.getMobilePhone());
            query.setParameter("homePhone", usersEntity.getHomePhone());
            query.setParameter("companyEmail", usersEntity.geteMail());
            query.setParameter("personalEmail", usersEntity.getPersonalEmail());

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

    public static void updateWorks(WorksEntity worksEntity, int jobId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update WorksEntity set startDate = :startDate, endDate=:endDate, " +
                    "contractType = :contractType where id = :jobId");
            query.setParameter("jobId", jobId);
            query.setParameter("startDate", worksEntity.getStartDate());
            query.setParameter("endDate", worksEntity.getEndDate());
            query.setParameter("contractType", worksEntity.getContractType());
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

    public static void updateWorksLoc(WorkLocalizationsEntity workLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update WorkLocalizationsEntity set organization = :org, post=:post where workId = :jobId");
            query.setParameter("jobId", workLocalizationsEntity.getWorkId());
            query.setParameter("org", workLocalizationsEntity.getOrganization());
            query.setParameter("post", workLocalizationsEntity.getPost());
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

    public static UserInLanguagesEntity getUserInLanguage(int langId) {
        UserInLanguagesEntity userInLanguagesEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInLanguagesEntity where id = :id");
            query.setParameter("id", langId);
            //query.setParameter("roleId", userInRoles.getRoleId());
            if(!query.list().isEmpty())
                userInLanguagesEntity = (UserInLanguagesEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return userInLanguagesEntity;
    }

    public static void updateCertificates(CertificatesEntity certificatesEntity, int certId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update CertificatesEntity set dateTime = :dateTime, mark=:mark, type=:type, " +
                    "degree=:degree, number=:number where id = :certId");
            query.setParameter("dateTime", certificatesEntity.getDateTime());
            query.setParameter("mark", certificatesEntity.getMark());
            query.setParameter("type", certificatesEntity.getType());
            query.setParameter("degree", certificatesEntity.getDegree());
            query.setParameter("number", certificatesEntity.getNumber());
            query.setParameter("certId", certId);
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

    public static void updateCertificateLocalizations(CertificateLocalizationsEntity certificateLocalizationsEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update CertificateLocalizationsEntity set organization = :org, name=:name " +
                    "where certificateId = :certId");
            query.setParameter("org", certificateLocalizationsEntity.getOrganization());
            query.setParameter("name", certificateLocalizationsEntity.getName());
            query.setParameter("certId", certificateLocalizationsEntity.getCertificateId());
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

    public static int getRoleByUserName(String name) {
        int roleId =0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("select roleId from UsersEntity where userName = :username");//" inner join user.usersByUserId");//" on user.id = userLoc.userId where userLoc.languageId = :languageId");
            query.setParameter("username", name);
            roleId = (Integer) query.getSingleResult();

            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return roleId;
    }

    public static UserLocalizationsEntity getUserLocByUserId(int id, int langId) {
        UserLocalizationsEntity userLoc = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserLocalizationsEntity locs where locs.userId = :userId and languageId=:langId");
            query.setParameter("userId", id);
            query.setParameter("langId", langId);
            userLoc = (UserLocalizationsEntity) query.getSingleResult();
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

    public static void updateHead(Integer userId, int headId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UsersEntity set chiefId = :chiefId where id = :userId");
            query.setParameter("chiefId", headId);
            query.setParameter("userId", userId);
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

    public static String getExternalLoc(int externalId) {
        ExternalLocalizationsEntity externalLocalizationsEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ExternalLocalizationsEntity where id=:externId");
            query.setParameter("externId", externalId);
            externalLocalizationsEntity = (ExternalLocalizationsEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return externalLocalizationsEntity.getExternalName();
    }

    public static List<ExternalLocalizationsEntity> getExternalLoc() {
        List<ExternalLocalizationsEntity> externalLocalizationsEntities = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ExternalLocalizationsEntity");
            externalLocalizationsEntities = query.list();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return externalLocalizationsEntities;
    }


    public static int getUserFromIdByUserLocName(String attribute){
        for (UserLocalizationsEntity userLocalizationsEntity : getAllUserLocs()) {
            if (userLocalizationsEntity.getFirstName().compareTo(attribute)==0){
                return userLocalizationsEntity.getUserId();
            }
        }
        return -1;
    }

    public static String getUserJobTitle(int userId) {
        String jobTitle = "";
        UserInPostsEntity userInPostsEntity = null;

        try {
            userInPostsEntity = UserService.getMaxUserInPostByUserId(userId);
            jobTitle = UserService.getJobTitle(userInPostsEntity.getPostId(), 3).getName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return jobTitle;
    }

    public static void insertAuthToken(AuthTokenEntity authTokenEntity) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            //Set foreign key items
            authTokenEntity.setUsersByUserId(session.load(UsersEntity.class, authTokenEntity.getUserId()));
            //Save the object in database
            session.save(authTokenEntity);


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

    public static AuthTokenEntity getAuthTokenEntityByToken(String token) {
        AuthTokenEntity authTokenEntity = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from AuthTokenEntity where token=:token");
            query.setParameter("token", token);
            authTokenEntity = (AuthTokenEntity) query.getSingleResult();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return authTokenEntity;
    }

    public static void deleteAuthTokenWithId(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from AuthTokenEntity where id = :id");
            query.setParameter("id", id);
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
