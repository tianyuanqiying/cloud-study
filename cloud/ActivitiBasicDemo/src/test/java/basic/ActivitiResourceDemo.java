package basic;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ActivitiResourceDemo {

    /**
     * 注： 在获取资源文件名时，png图片资源的文件名是
     processDefinition.getDiagramResourceName()，他来自于
     ACT_RE_PROCDEF表中的DGRM_RESOURCE_NAME字段。这个字段
     的值是在部署流程时根据文件名后缀判断出来的。 支持的格式为
     [ResourceName].[key].[png|jpg|gif|svg]或者[ResourceName].
     [png|jpg|gif|svg]
     而bpmn文件的文件名是processDefinition.getResourceName()，他
     来自于ACT_RE_PROCDEF表中的RESOURCE_NAME字段。
     */
    @Test
    public void doDownloadResource(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();

        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();

        Deployment deployment = deploymentQuery.processDefinitionKey("myleave").singleResult();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionKey("myleave").singleResult();

        InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());

        InputStream pngStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());

        File bpmnBak = new File("bpmn/bk/Leave.bpmn.bk");
        bpmnBak.getParentFile().mkdir();
        File pngBak = new File("bpmn/bk/Leave.png");
        pngBak.getParentFile().mkdir();
        try {
            FileUtils.copyToFile(bpmnStream, bpmnBak);
            FileUtils.copyToFile(pngStream, pngBak);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
