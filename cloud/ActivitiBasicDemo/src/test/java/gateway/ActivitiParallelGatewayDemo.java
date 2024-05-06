package gateway;

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

public class ActivitiParallelGatewayDemo {


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
                .addClasspathResource("bpmn/parallel.bpmn") // 添加bpmn资源
                //png资源命名是有规范的。Leave.[key].[png|jpg|gif|svg]  或者Leave. [png|jpg|gif|svg]
                .addClasspathResource("bpmn/parallel.png")  // 添加png资源
                .name("parallelGateway")
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
                .startProcessInstanceByKey("parallelGateway", variables);


        //        输出内容
        System.out.println("流程定义id：" +
                processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }

    @Test
    public void handleWorker(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、获取RunTimeService

        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().taskAssignee("worker").processDefinitionKey("parallelGateway").list();

        System.out.println("任务总数=" + tasks.size());

        tasks.forEach(task -> {
            taskService.complete(task.getId());
        });

    }

    @Test
    public void queryParallelTasks(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、获取RunTimeService

        TaskService taskService = processEngine.getTaskService();

        //并行网关会生成三个任务，会查出三条记录
        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("parallelGateway").list();

        System.out.println("任务总数=" + tasks.size());

        System.out.println("任务列表 ： " + tasks);

        tasks.forEach(task -> {
            taskService.complete(task.getId());
        });
    }

    @Test
    public void queryFinanceTask(){
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("parallelGateway").taskAssignee("financer").list();

        System.out.println("任务总数 ： " + tasks.size());

    }

    @Test
    public void doFinanceTask(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("parallelGateway").taskAssignee("financer").list();

        System.out.println("任务总数 ： " + tasks.size());

        tasks.forEach(task -> {
            taskService.complete(task.getId());
        });

    }
}
