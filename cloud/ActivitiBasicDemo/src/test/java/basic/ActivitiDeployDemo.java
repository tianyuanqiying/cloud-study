package basic;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

public class ActivitiDeployDemo {
    @Test
    public void listComponent(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、得到RepositoryService实例
        RepositoryService repositoryService =
                processEngine.getRepositoryService();
        //        3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/Leave.bpmn") // 添加bpmn资源
                //png资源命名是有规范的。Leave.[key].[png|jpg|gif|svg]  或者Leave. [png|jpg|gif|svg]
                .addClasspathResource("bpmn/Leave.png")  // 添加png资源
                .name("myleaveprocess")
                .deploy();
        //        4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }
    @Test
    public void testDeployment(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、得到RepositoryService实例
        RepositoryService repositoryService =
                processEngine.getRepositoryService();
        //        3、使用RepositoryService进行部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("bpmn/Leave.bpmn") // 添加bpmn资源
                //png资源命名是有规范的。Leave.[key].[png|jpg|gif|svg]  或者Leave. [png|jpg|gif|svg]
                .addClasspathResource("bpmn/Leave.png")  // 添加png资源
                .name("myleaveprocess")
                .deploy();
        //        4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

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
                .name("myleaveprocess")
                .deploy();
        //        4、输出部署信息
        System.out.println("流程部署id：" + deployment.getId());
        System.out.println("流程部署名称：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void testStartProcess(){
        //        1、创建ProcessEngine
        ProcessEngine processEngine =
                ProcessEngines.getDefaultProcessEngine();
        //        2、获取RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //        3、根据流程定义Id启动流程
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey("myleave");
        //        输出内容
        System.out.println("流程定义id：" +
                processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
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

    /**
     * 1、这里只删除了流程定义，不会删除历史表信息
     2、删除任务时，可以选择传入一个boolean型的变量cascade ，表示是
     否级联删除。默认是false，表示普通删除。
     如果该流程下存在已经运行的流程，使用普通删除会报错，而级联删除
     可以将流程及相关记录全部删除。删除没有完成的流程节点后，就可以
     完全删除流程定义信息了。
     项目开发中，级联删除操作一般只开放给管理员使用
     */
    @Test
    public void doDeleteProcessDefinition(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        repositoryService.deleteDeployment("5001");
        System.out.println("deleteDeployment end");

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myleave").orderByProcessDefinitionVersion().asc().list();
        processDefinitionList.forEach(processDefinition -> {
            System.out.println("ID = " + processDefinition.getId());
            System.out.println("部署ID = " + processDefinition.getDeploymentId());
            System.out.println("App 版本 =" + processDefinition.getAppVersion());
            System.out.println("版本" + processDefinition.getVersion());
        });

    }
}
