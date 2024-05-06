package basic;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class ActivitiWorkerDemo {

    /**
     * 流程任务查询
     */
    @Test
    public void testQueryTask(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();

        //2. 任务服务
        TaskService taskService = processEngine.getTaskService();

        List<Task> list = taskService.createTaskQuery().processDefinitionKey("myleave").taskAssignee("worker").list();
        for (Task task : list) {
            System.out.println("任务ID"+task.getId());
            System.out.println("负责人" + task.getAssignee());
            System.out.println("执行ID" + task.getExecutionId());
            //TODO:乱码待排查
            System.out.println("名称" + task.getName());
            System.out.println("任务定义key" + task.getTaskDefinitionKey());
            System.out.println("业务key" + task.getBusinessKey());
        }

    }

    @Test
    public void testExecuteTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = defaultProcessEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskAssignee("worker").processDefinitionKey("myleave").singleResult();
        System.out.println(task);

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        System.out.println(execution);
        taskService.complete(task.getId());

        Task taskInfo = taskService.createTaskQuery().taskAssignee("worker").processDefinitionKey("myleave").singleResult();
        Execution execution2 = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        System.out.println(taskInfo);
        System.out.println(execution2);
    }

    @Test
    public void taskAddCommentForTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = defaultProcessEngine.getTaskService();


        Comment comment = taskService.addComment("12502", "10001", "通过");

        System.out.println(comment);
    }
}
