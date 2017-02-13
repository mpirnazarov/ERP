package com.lgcns.erp.workflow.Mapper;

import com.lgcns.erp.tapps.DbContext.UserService;
import com.lgcns.erp.tapps.model.DbEntities.UserLocalizationsEntity;
import com.lgcns.erp.workflow.DBContext.WorkflowService;
import com.lgcns.erp.workflow.DBEntities.RequestsEntity;
import com.lgcns.erp.workflow.DBEntities.StepCommentsEntity;
import com.lgcns.erp.workflow.DBEntities.StepsEntity;
import com.lgcns.erp.workflow.Enums.Status;
import com.lgcns.erp.workflow.Model.StepComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DS on 07.02.2017.
 */
public class CommentMapper {
    public static List<StepComment> MapToStepComment(RequestsEntity requestsEntity){
        List<StepCommentsEntity> commentsEntities = getInvolvedSteps(requestsEntity);
        List<StepComment> comments = new ArrayList<>();

        System.out.println(commentsEntities);

        for (StepCommentsEntity commentsEntity : commentsEntities) {
            StepComment comment = new StepComment();
            comment.setId(commentsEntity.getId());
            comment.setAction(getFromStatusIdToName(commentsEntity.getStatusId()));
            comment.setAuthor(getAutherFromSteps(commentsEntity.getStepsId()));
            comment.setComment(commentsEntity.getComments());

            comments.add(comment);
        }
        return comments;
    }

    private static String getFromStatusIdToName(int statusId){
        String statusName ="";
        for (Status status : Status.values()) {
            if (status.getValue()==statusId){
                statusName = status.name();
            }
        }
        return statusName;
    }

    private static String getAutherFromSteps(int stepId){
        StepsEntity entity = WorkflowService.getStepById(stepId);
        UserLocalizationsEntity userLoc = UserService.getUserLocByUserId(entity.getUserId(), 3);

        return userLoc.getFirstName()+" "+userLoc.getLastName();
    }

    private static List<StepCommentsEntity> getInvolvedSteps(RequestsEntity requestsEntity){
        List<StepsEntity> involvedSteps = new ArrayList<>();

        for (StepsEntity stepsEntity : WorkflowService.getStepsList()) {
            if (stepsEntity.getRequestId()==requestsEntity.getId()){
                involvedSteps.add(stepsEntity);
            }
        }
        return getStepComments(involvedSteps);
    }

    private static List<StepCommentsEntity> getStepComments(List<StepsEntity> stepsEntities){
        List<StepCommentsEntity> comments = new ArrayList<>();
        for (StepsEntity stepsEntity : stepsEntities) {
            comments.addAll(WorkflowService.getStepCommentsByStepId(stepsEntity.getId()));
        }
        return comments;
    }

    public static StepCommentsEntity getCommentsFromModel(int stepId, int status, String comment){
        StepCommentsEntity commentsEntity = new StepCommentsEntity();
        if (comment.equals(""))
            comment = "No comment";
        commentsEntity.setComments(comment);
        commentsEntity.setStatusId(status);
        commentsEntity.setStepsId(stepId);

        return commentsEntity;
    }
}
