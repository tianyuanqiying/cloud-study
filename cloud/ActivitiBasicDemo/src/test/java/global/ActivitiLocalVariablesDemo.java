package global;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiLocalVariablesDemo {

    @Test
    public void doQueryDeployment(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        List<Deployment> deployments = repositoryService.createDeploymentQuery().deploymentName("myGlobalVariables").list();

        System.out.println("部署有：" + deployments.size());
    }

    /**
     * 任务ID必须是当前待办任务id，要在act_ru_task中存在
     */
    @Test
    public void setLocalVariableForTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = defaultProcessEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("myVariable").list();

        System.out.println("任务总数：" + tasks.size());

        HashMap<String, Object> localVariables = new HashMap<>();
        localVariables.put("num", 5);
        tasks.forEach(task -> {
            taskService.setVariablesLocal(task.getId(), localVariables);
        });

        tasks.forEach(task -> {
            Map<String, Object> variables = taskService.getVariablesLocal(task.getId());
            System.out.println(variables);
        });


        tasks.forEach(task -> {
            taskService.complete(task.getId());
        });
    }
}
