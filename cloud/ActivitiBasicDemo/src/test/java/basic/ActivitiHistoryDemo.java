package basic;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import java.util.List;

public class ActivitiHistoryDemo {

    /**
     * 1. 查询流程实例；
     * 2. 查询历史流程活动实例；
     *
     *
     * 注：1、关于流程历史信息，要注意，在删除流程时，如果是采取级联删
     除的方式，那这个历史信息也会随着一起删除。而普通删除方式不会删
     除历史信息。
     2、历史信息有不同的种类，具体可以通过historyService构建不同类型
     的Query对象来获取结果。
     */
    @Test
    public void testQueryHistory(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey("myleave").orderByProcessDefinitionVersion().asc().singleResult();

        HistoryService historyService = defaultProcessEngine.getHistoryService();

        HistoricProcessInstanceQuery instanceQuery = historyService.createHistoricProcessInstanceQuery();
        List<HistoricProcessInstance> historicProcessInstances = instanceQuery.processDefinitionId(processDefinition.getId()).list();
        historicProcessInstances.forEach(historicProcessInstance -> {
            HistoricActivityInstanceQuery activityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
            List<HistoricActivityInstance> activityInstances = activityInstanceQuery.processInstanceId(historicProcessInstance.getId()).orderByHistoricActivityInstanceStartTime().desc().list();
            for (HistoricActivityInstance activityInstance : activityInstances) {
                System.out.println("id " + activityInstance.getActivityId());
                System.out.println("名称 " + activityInstance.getActivityName());
                System.out.println("负责人" + activityInstance.getAssignee());
                System.out.println("任务ID " + activityInstance.getTaskId());
            }
        });

    }

}
