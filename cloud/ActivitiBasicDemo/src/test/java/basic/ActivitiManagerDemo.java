package basic;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

public class ActivitiManagerDemo {

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

        List<Task> list = taskService.createTaskQuery().processDefinitionKey("myleave").taskAssignee("manager").list();
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
        Task task = taskService.createTaskQuery().taskAssignee("manager").processDefinitionKey("myleave").singleResult();
        System.out.println("任务 " + task);

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        System.out.println("执行 " + execution);

        //添加评论
        Comment comment = taskService.addComment("12502", "10001", "经理审批通过");

        taskService.complete(task.getId());

        Task taskInfo = taskService.createTaskQuery().taskAssignee("manager").processDefinitionKey("myleave").singleResult();
        Execution execution2 = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
        System.out.println("任务 " +taskInfo);
        System.out.println("执行 " +execution2);
    }
}
