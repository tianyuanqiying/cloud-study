package basic;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.junit.Test;

import java.util.List;

public class ActivitiProcessDemo {

    /**
     * 查询流程实例
     * 通过查询ACT_RU_EXECUTION、ACT_RE_PROCDEF联合查询
     */
    @Test
    public void queryPrcessInstance(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

        List<ProcessInstance> processInstances = processInstanceQuery.processDefinitionKey("myleave").list();

        processInstances.forEach(processInstance -> {
            System.out.println("部署ID " + processInstance.getDeploymentId());
            System.out.println("实例名称 " + processInstance.getName());
            System.out.println("业务类型key " + processInstance.getBusinessKey());
            System.out.println("流程定义ID " + processInstance.getProcessDefinitionId());
        });
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
                .startProcessInstanceByKey("myleave", "tmpType");
        //        输出内容
        System.out.println("流程定义id：" +
                processInstance.getProcessDefinitionId());
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("当前活动Id：" + processInstance.getActivityId());
    }


    /**
     * 设置流程定义为悬挂/激活；
     * 设置为悬挂后、流程定义关联的流程实例无法继续推进；
     */
    @Test
    public void doSuspendProcessDef(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        List<ProcessDefinition> definitions = processDefinitionQuery.processDefinitionKey("myleave").list();

        definitions.forEach(processDefinition -> {
            System.out.println("状态 : " + processDefinition.isSuspended());
            if (processDefinition.isSuspended()) {
                repositoryService.activateProcessDefinitionById(processDefinition.getId());
            }else {
                repositoryService.suspendProcessDefinitionById(processDefinition.getId());
            }
        });
    }

    @Test
    public void doSuspendProcessInstance(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();

        //若是根据定义key查询， 是可以查询出多个流程实例的。 即一个定义对应多个实例；
        //List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionKey("myleave").list();
        //根据流程实例ID查出一个流程实例、阻塞该实例；
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processInstanceId("20001").list();

        processInstances.forEach(processInstance -> {
            System.out.println("状态： " + processInstance.isSuspended());

            if (processInstance.isSuspended()) {
                runtimeService.activateProcessInstanceById(processInstance.getId());
            }else {
                runtimeService.suspendProcessInstanceById(processInstance.getId());
            }
        });
    }

}
