package global;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitiGlobalVariablesDemo {


    @Test
    public void doDeploymentVariables(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、得到RepositoryService实例
        RepositoryService repositoryService =
                processEngine.getRepositoryService();
        //        3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/Variable.bpmn") // 添加bpmn资源
                //png资源命名是有规范的。Leave.[key].[png|jpg|gif|svg]  或者Leave. [png|jpg|gif|svg]
                .addClasspathResource("bpmn/Variable.png")  // 添加png资源
                .name("myGlobalVariables")
                .deploy();
        //        4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     *在启动流程实例时设置流程变量，这时流程变量的作用域是整个流程实例。相当于
     是Global作用域。
     */
    @Test
    public void setVariablesForStartProcess(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        HashMap<String, Object> variables = new HashMap<String, Object>();
        variables.put("num", 3);

        //        3、根据流程定义Id启动流程
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("myVariable", variables);


        //        输出内容
        System.out.println("流程定义id：" +
                processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }

    /**
     * 在完成任务时设置流程变量，该流程变量只有在该任务完成后其它结点才可使用该
     变量，它的作用域是整个流程实例，如果设置的流程变量的key在流程实例中已存在
     相同的名字则后设置的变量替换前边设置的变量。
     *
     */
    @Test
    public void setVariablesForTask(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = defaultProcessEngine.getTaskService();

        //查出代办任务； 获取任务ID；
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("myVariable").taskAssignee("worker").list();
        System.out.println("任务总数 ： " + tasks.size());

        //完成代办任务过程中设置变量
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("num", 3);
        tasks.forEach(task -> {
            taskService.complete(task.getId(), variables);
        });
    }
    /**
     * 注意：ececutionId必须是当前未完成的流程实例的执行ID。通常此ID设
     置流程实例的ID。流程变量设计完成后，也可以通过
     runtimeService.getVariable()获取流程变量
     */
    @Test
    public void setVariablesForProcessInstance(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstanceQuery instanceQuery = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> processInstances = instanceQuery.processDefinitionKey("myVariable").list();

        System.out.println("流程实例总数： " + processInstances.size());

        HashMap<String, Object> variables = new HashMap<>();
        variables.put("num", 4);
        processInstances.forEach(processInstance -> {
            runtimeService.setVariables(processInstance.getId(), variables);
        });

        //获取流程变量；
        processInstances.forEach(processInstance -> {
            Map<String, Object> runtimeServiceVariables = runtimeService.getVariables(processInstance.getId());
            System.out.println("获取变量： " + runtimeServiceVariables);
        });
    }

    /**
     * 注： 任务id必须是当前待办任务id，act_ru_task中存在。如果该任务已
     结束，会报错。也可以通过taskService.getVariable()获取流程变量。
     */
    @Test
    public void setVariablesForTaskId(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("myVariable").list();
        System.out.println("任务总数 " + tasks.size());
        Map<String, Object> variables = new HashMap<>();
        variables.put("num", 3);
        tasks.forEach(task -> {
            taskService.setVariables(task.getId(), variables);
        });

    }


    /**
     * 查询流程定义信息
     */
    @Test
    public void testQueryProcessDefinition(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myleave").orderByProcessDefinitionVersion().asc().list();

        processDefinitionList.forEach(processDefinition -> {
            System.out.println("ID = " + processDefinition.getId());
            System.out.println("部署ID = " + processDefinition.getDeploymentId());
            System.out.println("App 版本 =" + processDefinition.getAppVersion());
            System.out.println("版本" + processDefinition.getVersion());
        });
    }
}
