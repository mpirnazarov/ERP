package com.lgcns.erp.tapps.mapper;

import com.lgcns.erp.tapps.model.DbEntities.*;
import com.lgcns.erp.tapps.viewModel.Manager.Form;
import com.lgcns.erp.tapps.viewModel.HR.DocsViewModel;
import com.lgcns.erp.tapps.viewModel.RegistrationLocInfo;
import com.lgcns.erp.tapps.viewModel.RegistrationViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.Appointment.AppointmentSummary;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.Certificates;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.Educations;
import com.lgcns.erp.tapps.viewModel.usermenu.Education.LanguageSummary;
import com.lgcns.erp.tapps.viewModel.usermenu.FamilyMember;
import com.lgcns.erp.tapps.viewModel.usermenu.JobexpViewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.SalaryVewModel;
import com.lgcns.erp.tapps.viewModel.usermenu.TrainViewModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Rafatdin on 27.10.2016.
 */
public class UserMapper {
    public static UsersEntity mapRegModelToUserInfo(RegistrationViewModel model){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //for hashing

        UsersEntity user = new UsersEntity();
        user.setDateOfBirth(new Date(model.getDateOfBirth().getTime()));
        user.setDepartmentId(model.getDepartmentId());
        user.setMobilePhone(model.getMobilePhone());
        user.setHomePhone(model.getHomePhone());
        user.seteMail(model.getCompanyEmail());
        user.setUserName(model.getUserName());
        user.setStatusId(model.getStatusId());
        user.setPasswordHash(passwordEncoder.encode(model.getPassword()));
        user.setHiringDate(new Date(model.getHiringDate().getTime()));
        user.setChiefId(model.getChiefId());
        user.setPersonalEmail(model.getEmail());
        user.setInPoliticalParty(model.isIsInPoliticalParty());
        user.setPassport(model.getPassportNumber());
        user.setRoleId(model.getRoleId());
        user.setEnabled(true);

        return user;
    }

    public static List<UserLocalizationsEntity> mapRegModelToUserLocInfo(RegistrationViewModel model, int userId)
    {
        List<UserLocalizationsEntity> returning = new ArrayList<UserLocalizationsEntity>();
        UserLocalizationsEntity locEntity;
        for(RegistrationLocInfo locInfo : model.getRegistrationLocInfos()){
            locEntity = new UserLocalizationsEntity();
            locEntity.setAddress(locInfo.getAddress());
            locEntity.setFirstName(locInfo.getFirstName());
            locEntity.setLastName(locInfo.getLastName());
            locEntity.setFatherName(locInfo.getFathersName());
            locEntity.setLanguageId(locInfo.getLanguageId());
            locEntity.setUserId(userId);
            locEntity.setBirthPlace(locInfo.getBirthPlace());
            returning.add(locEntity);
        }
        return returning;
    }

    public static FamilyInfosEntity mapAddFamily(FamilyMember familyProfile, String userId) {
        FamilyInfosEntity familyInfosEntity = new FamilyInfosEntity();
        familyInfosEntity.setDateOfBirth(familyProfile.getDateOfBirth());
        familyInfosEntity.setUserId(Integer.parseInt(userId));
        return familyInfosEntity;
    }

    public static FamiliyInfoLocalizationsEntity mapAddFamilyLoc(FamilyMember familyProfile, int id, int langId) {
        FamiliyInfoLocalizationsEntity familiyInfoLocalizationsEntity = new FamiliyInfoLocalizationsEntity();
        familiyInfoLocalizationsEntity.setFirstName(familyProfile.getFirstName()[langId-1]);
        familiyInfoLocalizationsEntity.setLastName(familyProfile.getLastName()[langId-1]);
        familiyInfoLocalizationsEntity.setJobTitle(familyProfile.getJobTitle()[langId-1]);
        familiyInfoLocalizationsEntity.setRelation(familyProfile.getRelation()[langId-1]);
        familiyInfoLocalizationsEntity.setFamilyInfoid(id);
        familiyInfoLocalizationsEntity.setLanguageId(langId);
        return familiyInfoLocalizationsEntity;
    }

    public static SalaryHistoriesEntity mapSalaryEntity(SalaryVewModel salaryVewModel, String userId) {
        SalaryHistoriesEntity salaryHistoriesEntity = new SalaryHistoriesEntity();
        salaryHistoriesEntity.setUserId(Integer.parseInt(userId));
        salaryHistoriesEntity.setDate(salaryVewModel.getDate());
        salaryHistoriesEntity.setCurrencyId(salaryVewModel.getCurrency());
        salaryHistoriesEntity.setInps(salaryVewModel.getInps());
        salaryHistoriesEntity.setPf(salaryVewModel.getPf());
        salaryHistoriesEntity.setPit(salaryVewModel.getPit());
        salaryHistoriesEntity.setSalaryAfter(Integer.parseInt(salaryVewModel.getNet()));
        salaryHistoriesEntity.setSalaryBefore(Integer.parseInt(salaryVewModel.getGross()));
        return salaryHistoriesEntity;
    }

    public static WorksEntity mapAddWorks(JobexpViewModel jobexpViewModel, String userId) {
        WorksEntity worksEntity = new WorksEntity();
        worksEntity.setUserId(Integer.parseInt(userId));
        worksEntity.setEndDate(jobexpViewModel.getEndDate());
        worksEntity.setStartDate(jobexpViewModel.getStartDate());
        worksEntity.setContractType(jobexpViewModel.getContractType());
        return worksEntity;
    }

    public static WorkLocalizationsEntity mapAddWorksLoc(JobexpViewModel jobexpViewModel, int id) {
        WorkLocalizationsEntity workLocalizationsEntity = new WorkLocalizationsEntity();
        workLocalizationsEntity.setLanguageId(3);
        workLocalizationsEntity.setOrganization(jobexpViewModel.getOrganization());
        workLocalizationsEntity.setWorkId(id);
        workLocalizationsEntity.setPost(jobexpViewModel.getPosition());
        return workLocalizationsEntity;
    }

    public static TrainingsEntity mapTrainings(TrainViewModel trainViewModel, String userId) {
        TrainingsEntity trainingsEntity = new TrainingsEntity();
        trainingsEntity.setUserId(Integer.parseInt(userId));
        trainingsEntity.setDateFrom(trainViewModel.getDateFrom());
        trainingsEntity.setDateTo(trainViewModel.getDateTo());
        trainingsEntity.setMark(trainViewModel.getMark());
        trainingsEntity.setNumberOfHours(trainViewModel.getNumberOfHours());
        return trainingsEntity;
    }

    public static TrainingLocalizationsEntity mapTrainingLoc(TrainViewModel trainViewModel, int id) {
        TrainingLocalizationsEntity trainingLocalizationsEntity = new TrainingLocalizationsEntity();
        trainingLocalizationsEntity.setOrganization(trainViewModel.getOrganization());
        trainingLocalizationsEntity.setLanguageId(3);
        trainingLocalizationsEntity.setName(trainViewModel.getName());
        trainingLocalizationsEntity.setTrainingId(id);
        return trainingLocalizationsEntity;
    }

    public static PersonalEvalutionsEntity mapCTOEvaluation(Form form, int id) {
        PersonalEvalutionsEntity personalEvalutionsEntity = new PersonalEvalutionsEntity();

        personalEvalutionsEntity.setUserId(Integer.parseInt(form.getId()));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        personalEvalutionsEntity.setDate(new Date(year,month,day));
        personalEvalutionsEntity.setEvaluatorId(id);
        personalEvalutionsEntity.setGrade(form.getGrade());

        return personalEvalutionsEntity;
    }

    public static DocumentsEntity mapDocuments(DocsViewModel docsViewModel) {
        DocumentsEntity documentsEntity = new DocumentsEntity();
        documentsEntity.setUserId(docsViewModel.getUserId());
        documentsEntity.setDocumentType(docsViewModel.getDocumentType());
        documentsEntity.setLink(docsViewModel.getLink());
        documentsEntity.setName(docsViewModel.getName());
        return documentsEntity;
    }

    public static UserInPostsEntity mapUserInPosts(AppointmentSummary appointmentSummary, String userId) {
        UserInPostsEntity userInPostsEntity = new UserInPostsEntity();
        userInPostsEntity.setUserId(Integer.parseInt(userId));
        userInPostsEntity.setDateFrom(appointmentSummary.getAppointDate());
        userInPostsEntity.setContractType(appointmentSummary.getContractType());
        userInPostsEntity.setPostId(appointmentSummary.getPostId());
        userInPostsEntity.setDateEnd(appointmentSummary.getEndDate());
        return userInPostsEntity;
    }

    public static EducationsEntity mapEducations(Educations educations, String userId) {
        EducationsEntity educationsEntity = new EducationsEntity();
        educationsEntity.setUserId(Integer.parseInt(userId));
        educationsEntity.setStartDate(educations.getStartDate());
        educationsEntity.setEndDate(educations.getEndDate());
        return educationsEntity;
    }

    public static EducationLocalizationsEntity mapEducationLocalizations(Educations educations, int eduId) {
        EducationLocalizationsEntity educationLocalizationsEntity = new EducationLocalizationsEntity();
        educationLocalizationsEntity.setName(educations.getName());
        educationLocalizationsEntity.setMajor(educations.getMajor());
        educationLocalizationsEntity.setDegree(educations.getDegree());
        educationLocalizationsEntity.setEducationId(eduId);
        educationLocalizationsEntity.setLanguageId(3);
        return educationLocalizationsEntity;
    }

    public static UserInLanguagesEntity mapUserInLanguages(LanguageSummary languageSummary, String userId) {
        UserInLanguagesEntity userInLanguagesEntity = new UserInLanguagesEntity();
        userInLanguagesEntity.setUserId(Integer.parseInt(userId));
        userInLanguagesEntity.setLanguageId(languageSummary.getLangId());
        userInLanguagesEntity.setListening(languageSummary.getListeningId());
        userInLanguagesEntity.setReading(languageSummary.getReadingId());
        userInLanguagesEntity.setSpeaking(languageSummary.getSpeakingId());
        userInLanguagesEntity.setWriting(languageSummary.getWritingId());
        return userInLanguagesEntity;
    }

    public static CertificatesEntity mapCertificates(Certificates certificates, String userId, int type) {
        CertificatesEntity certificatesEntity = new CertificatesEntity();
        certificatesEntity.setUserId(Integer.parseInt(userId));
        certificatesEntity.setDateTime(certificates.getDateTime());
        certificatesEntity.setMark(certificates.getMark());
        certificatesEntity.setPass(certificates.getPassed());
        certificatesEntity.setNumber(certificates.getNumber());
        certificatesEntity.setDegree(certificates.getDegree());
        certificatesEntity.setType(type);
        return certificatesEntity;
    }

    public static CertificateLocalizationsEntity mapCertificateLocalizations(Certificates certificates, int certId) {
        CertificateLocalizationsEntity localizationsEntity = new CertificateLocalizationsEntity();
        localizationsEntity.setCertificateId(certId);
        localizationsEntity.setOrganization(certificates.getOrganization());
        localizationsEntity.setName(certificates.getName());
        localizationsEntity.setLanguageId(3);
        return localizationsEntity;
    }
}
