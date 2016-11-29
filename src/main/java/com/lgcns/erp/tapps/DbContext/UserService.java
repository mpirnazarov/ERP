package com.lgcns.erp.tapps.DbContext;

import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.model.UserInfo;
import com.lgcns.erp.tapps.viewModel.CTO.Form;
import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
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
            Query query = session.createQuery("update UsersEntity set dateOfBirth = :dateOfBirth, departmentId = :departmentId, mobilePhone = :mobilePhone, " +
                    "homePhone = :homePhone, eMail = :companyEmail, personalEmail = :personalEmail, statusId = :statusId, hiringDate=:hiringDate, " +
                    "political = :isPolitical, passport =:passport, roleId = :roleId  where id = :userid");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("dateOfBirth", person.getPersonalInfo().getDateOfBirth());
            query.setParameter("departmentId", Integer.parseInt(person.getDepartment()));
            query.setParameter("mobilePhone", person.getPersonalInfo().getMobilePhone());
            query.setParameter("homePhone", person.getPersonalInfo().getHomePhone());
            query.setParameter("companyEmail", person.getPersonalInfo().getEmailCompany());
            query.setParameter("personalEmail", person.getPersonalInfo().getEmailPersonal());
            query.setParameter("statusId", Integer.parseInt(person.getStatus()));
            query.setParameter("hiringDate", person.getEntryDate());
            query.setParameter("isPolitical", person.getIsPolitical());
            query.setParameter("passport", person.getPassportNumber());
            query.setParameter("roleId", Integer.parseInt(person.getPosition()));
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
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where id = :userid and languageId = 3");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[2]);
            query.setParameter("firstName", person.getFirstName()[2]);
            query.setParameter("fathersName", person.getFathersName()[2]);
            query.setParameter("address", person.getAddress()[2]);
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
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where id = :userid and languageId = 1");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[2]);
            query.setParameter("firstName", person.getFirstName()[2]);
            query.setParameter("fathersName", person.getFathersName()[2]);
            query.setParameter("address", person.getAddress()[2]);
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
    public static int updateUsersLocEntityUz(ProfileViewModel person) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("update UserLocalizationsEntity set lastName = :lastName, firstName = :firstName, " +
                    "fatherName = :fathersName, address = :address, birthPlace = :birthPlace where id = :userid and languageId = 2");
            query.setParameter("userid", Integer.parseInt(person.getId()));
            query.setParameter("lastName", person.getLastName()[2]);
            query.setParameter("firstName", person.getFirstName()[2]);
            query.setParameter("fathersName", person.getFathersName()[2]);
            query.setParameter("address", person.getAddress()[2]);
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

    public static int getIdByUsername(String name) {
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

    public static void insertEvaluation(Form f, int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            int gr=0;
            if (f.getGrade().compareTo("S")==0){
                gr=1;
            }else if(f.getGrade().compareTo("A")==0)
                gr=2;
            else if (f.getGrade().compareTo("B")==0)
                gr=3;
            else if (f.getGrade().compareTo("C")==0)
                gr=4;
            else if (f.getGrade().compareTo("D")==0)
                gr=5;
            transaction = session.beginTransaction();
            PersonalEvalutionsEntity evalutionsEntity = new PersonalEvalutionsEntity();
            evalutionsEntity.setUserId(id);
            evalutionsEntity.setGrade(gr);
            evalutionsEntity.setDate(new Date(2016,01,26));
            evalutionsEntity.setComments(f.getComments());
            evalutionsEntity.setEvaluatorId(id);

            session.save(evalutionsEntity);
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
}
