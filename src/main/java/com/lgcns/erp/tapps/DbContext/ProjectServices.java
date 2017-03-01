package com.lgcns.erp.tapps.DbContext;


import com.lgcns.erp.hr.enums.ProjectRole;
import com.lgcns.erp.hr.viewModel.AppointViewModels.AppointViewModel;
import com.lgcns.erp.hr.viewModel.AppointViewModels.ProjectMembers;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectCreate;
import com.lgcns.erp.hr.viewModel.ProjectViewModel.ProjectHistoryModel;
import com.lgcns.erp.tapps.Enums.Language;
import com.lgcns.erp.tapps.model.DbEntities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Rafatdin on 31.10.2016.
 */
public class ProjectServices {
    public static List<ProjectsEntity> getUsersAllProjects(int userId, Date from, Date to) {
        List<UserInProjectsEntity> list = null;
        List<ProjectsEntity> projectsList = new ArrayList<ProjectsEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.userId= :userId and uip.dateFrom<= :fromDate and uip.dateTo>=:toDate");
            query.setParameter("userId", userId);
            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);
            list = (List<UserInProjectsEntity>) query.list();
            transaction.commit();

            for (UserInProjectsEntity uip : list) {
                projectsList.add(uip.getProjectsByProjectId());
            }
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return projectsList;
    }

    public static List<ProjectsEntity> getAllProjects(Date from, Date to) {
        List<ProjectsEntity> list = null;
        List<ProjectsEntity> projectsList = new ArrayList<ProjectsEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity uip where uip.startDate <= :fromDate and uip.endDate >= :toDate");
            query.setParameter("fromDate", from);
            query.setParameter("toDate", to);
            list = (List<ProjectsEntity>) query.list();
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    public static List<ProjectsEntity> getAllProjects() {
        List<ProjectsEntity> list = null, result = new ArrayList<>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity uip");
            list = (List<ProjectsEntity>) query.list();
            result.addAll(list.stream().filter(project -> project.getId() != 0 && project.getId() != 1).collect(Collectors.toList()));
            transaction.commit();

        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }


    public static List<UserInProjectsEntity> getUserInProjectsInfoByUserId(int userId){
        List<UserInProjectsEntity> list = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.userId= :userId");
            query.setParameter("userId", userId);
            list = (List<UserInProjectsEntity>) query.list();
            transaction.commit();

        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }
    public static List<ProjectHistoryModel> getProjectHistoryInfoByUserId(int userId){
        List<UserInProjectsEntity> list = null;
        List<ProjectHistoryModel> returning = new ArrayList<ProjectHistoryModel>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.userId= :userId");
            query.setParameter("userId", userId);
            list = (List<UserInProjectsEntity>) query.list();

            //Sorting the project appointments by date
            Collections.sort(list, new Comparator<UserInProjectsEntity>() {
                @Override
                public int compare(UserInProjectsEntity o1, UserInProjectsEntity o2) {
                    return o1.getDateFrom().compareTo(o2.getDateTo());
                }
            });

            //mapping the entity model into the viewmodel
            ProjectHistoryModel temp;
            for(UserInProjectsEntity uip : list){
                temp = new ProjectHistoryModel();
                ProjectsEntity p = uip.getProjectsByProjectId();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
                c.setTime(p.getStartDate());
                String managerFullName = UserService.getUserFullNameInLanguageById(getManagerIdByProjectId(p.getId()),Language.eng.getCode());
                temp.setProjectManager(managerFullName);
                temp.setProjectName("PJ "+c.get(Calendar.YEAR)+"-"+p.getCode()+"-"+p.getType());
                temp.setPeriod(f.format(p.getStartDate())+" - "+f.format(p.getEndDate()));
                temp.setDescription(ProjectRole.getName(uip.getRoleId()));

                returning.add(temp);
            }
            transaction.commit();

        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returning;
    }

    public static int insertProject(ProjectsEntity project){
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return project.getId();
    }

    public static void insertProjectMember(int projectId, int userId, ProjectRole role, Date dateFrom, Date dateTo){
        UserInProjectsEntity uipInfo = new UserInProjectsEntity();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            uipInfo.setProjectsByProjectId(session.load(ProjectsEntity.class, projectId));
            uipInfo.setUsersByUserId(session.load(UsersEntity.class, userId));
            uipInfo.setRoleId(role.getValue());
            uipInfo.setDateFrom(dateFrom);
            uipInfo.setDateTo(dateTo);
            session.save(uipInfo);
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

    public static String getNextCode() {
        String returning = "";
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity pe order by pe.code asc");
            List<ProjectsEntity> list = (List<ProjectsEntity>) query.list();
            String curCode = list.get(list.size()-1).getCode();
            returning = String.format("%0" + curCode.length() + "d", Integer.parseInt(curCode)+1);


            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return returning;
    }

    public static ProjectsEntity getProjectById(int id) {
        ProjectsEntity returning = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from ProjectsEntity pe where pe.id = :id");
            query.setParameter("id", id);
            List<ProjectsEntity> projects = (List<ProjectsEntity>)query.list();
            if(!projects.isEmpty()){
                returning = projects.get(0);
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
        return returning;
    }

    public static int getManagerIdByProjectId(int id) {
        int returning = 0;
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.projectId = :pid and uip.roleId = :rid");
            query.setParameter("pid", id);
            query.setParameter("rid", ProjectRole.Manager.getValue());
            List<UserInProjectsEntity> projects = (List<UserInProjectsEntity>)query.list();
            if(!projects.isEmpty()){
                returning = projects.get(0).getUserId();
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
        return returning;
    }

    public static void updateProject(Integer id, ProjectCreate viewModel) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ProjectsEntity project = session.get(ProjectsEntity.class, id);
            project.setStatus(viewModel.getStatus());
            project.setMoneyUsd(viewModel.getMoneyUsd());
            project.setMoneyUzs(viewModel.getMoneyUzs());
            project.setType(viewModel.getType());
            project.setName(viewModel.getName());
            project.setCode(viewModel.getCode());
            project.setCustomerId(viewModel.getCustomerId());
            project.setStartDate(new java.sql.Date(viewModel.getStartDate().getTime()));
            project.setEndDate(new java.sql.Date(viewModel.getEndDate().getTime()));

            session.save(project);
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void updateManager(Integer id, Integer managerId) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.projectId = :projectId and uip.roleId = :roleId");
            query.setParameter("projectId", id);
            query.setParameter("roleId", ProjectRole.Manager.getValue());
            List<UserInProjectsEntity> uips = (List<UserInProjectsEntity>)query.list();
            if(!uips.isEmpty()){
                UserInProjectsEntity uip = uips.get(0);
                uip.setUserId(managerId);
                session.save(uip);
                transaction.commit();
            }
            else throw new HibernateException("Record with given ProjectId and its manager is not found");
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void deleteProject(Integer id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ProjectsEntity project = session.get(ProjectsEntity.class, id);
            session.delete(project);
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static List<ProjectsEntity> getProjectsByManager(int userId) {
        List<ProjectsEntity> returning = new ArrayList<ProjectsEntity>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.roleId = :roleId and userId = :userId");
            query.setParameter("userId", userId);
            query.setParameter("roleId", ProjectRole.Manager.getValue());
            List<UserInProjectsEntity> uips = (List<UserInProjectsEntity>)query.list();
            for(UserInProjectsEntity uip : uips){
                returning.add(uip.getProjectsByProjectId());
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return returning;
    }

    public static List<ProjectMembers> getProjectMemberForAppoint(int projectId) {
        List<ProjectMembers> returning = new ArrayList<ProjectMembers>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where uip.roleId != :roleId and projectId = :projectId");
            query.setParameter("projectId", projectId);
            query.setParameter("roleId", ProjectRole.Manager.getValue());
            List<UserInProjectsEntity> uips = (List<UserInProjectsEntity>)query.list();
            for(UserInProjectsEntity uip : uips){

                returning.add(
                        new ProjectMembers(uip.getId(),
                        UserService.getUserFullNameInLanguageById(uip.getUserId(), Language.eng.getCode()),
                        uip.getUserId(),
                        uip.getDateFrom(),
                        uip.getDateTo(),
                        uip.getRoleId(),
                        ProjectRole.getName(uip.getRoleId())
                        )
                );
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return returning;
    }

    public static List<AppointViewModel> getAppointmentsByUserId(int userId) {
        List<AppointViewModel> returning = new ArrayList<AppointViewModel>();
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserInProjectsEntity uip where userId = :userId");
            query.setParameter("userId", userId);
            List<UserInProjectsEntity> uips = (List<UserInProjectsEntity>)query.list();
            for(UserInProjectsEntity uip : uips){
                returning.add(
                        new AppointViewModel(
                                uip.getProjectsByProjectId().getName() + " - " + uip.getProjectsByProjectId().getType(),
                                uip.getProjectId(),
                                uip.getDateFrom(),
                                uip.getDateTo(),
                                uip.getProjectsByProjectId().getStatus()
                        )
                );
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return returning;
    }

    public static UserInProjectsEntity getAppointmentById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        try {
            return session.get(UserInProjectsEntity.class, id);
        } catch (Exception e) {
            return new UserInProjectsEntity();
        }
        finally {
            session.close();
        }
    }

    public static void updateAppointment(UserInProjectsEntity model) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserInProjectsEntity existingObject = session.get(UserInProjectsEntity.class, model.getId());
            if(existingObject !=null){
                existingObject.setUsersByUserId(session.load(UsersEntity.class, model.getUserId()));
                existingObject.setRoleId(model.getRoleId());
                existingObject.setDateFrom(model.getDateFrom());
                existingObject.setDateTo(model.getDateTo());
                session.save(existingObject);
                transaction.commit();
            }
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public static void deleteAppointment(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserInProjectsEntity uip = session.get(UserInProjectsEntity.class, id);
            session.delete(uip);
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction!=null) transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
