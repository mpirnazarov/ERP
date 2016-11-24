package com.lgcns.erp.tapps.validator;

import com.lgcns.erp.tapps.viewModel.ProfileViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class UserFormValidator implements Validator {

	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
    private UserFormValidator validator;

    @Override
	public boolean supports(Class<?> clazz) {
		return ProfileViewModel.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ProfileViewModel user = (ProfileViewModel) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName[0]", "NotEmpty.userForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName[1]", "NotEmpty.userForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName[2]", "NotEmpty.userForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName[0]", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName[1]", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName[2]", "NotEmpty.userForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address[0]", "NotEmpty.userForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address[1]", "NotEmpty.userForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address[2]", "NotEmpty.userForm.address");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "NotEmpty.userForm.department");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty.userForm.position");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jointType", "NotEmpty.userForm.jointType");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passportNumber", "NotEmpty.userForm.passportNumber");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entryDate", "NotEmpty.userForm.entryDate");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.dateOfBirth", "NotEmpty.userForm.dateOfBirth");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.emailCompany", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalInfo.emailPersonal", "NotEmpty.userForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword","NotEmpty.userForm.confirmPassword");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.userForm.sex");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.userForm.country");

		if(!emailValidator.valid(user.getPersonalInfo().getEmailCompany())){
			errors.rejectValue("personalInfo.emailCompany", "Pattern.userForm.email");
		}
        if(!emailValidator.valid(user.getPersonalInfo().getEmailPersonal())) {
            errors.rejectValue("personalInfo.emailCompany", "Pattern.userForm.email");
        }

        /*if(user.getNumber()==null || user.getNumber()<=0){
			errors.rejectValue("number", "NotEmpty.userForm.number");
		}
		
		if(user.getCountry().equalsIgnoreCase("none")){
			errors.rejectValue("country", "NotEmpty.userForm.country");
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
		}
		
		if (user.getFramework() == null || user.getFramework().size() < 2) {
			errors.rejectValue("framework", "Valid.userForm.framework");
		}

		if (user.getSkill() == null || user.getSkill().size() < 3) {
			errors.rejectValue("skill", "Valid.userForm.skill");
		}*/

	}

    public void setValidator(UserFormValidator validator) {
        this.validator = validator;
    }

    public UserFormValidator getValidator() {
        return validator;
    }
}
