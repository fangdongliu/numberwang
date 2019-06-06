package cn.fdongl.numberwangmock;

import cn.fdongl.numberwangmock.aware.WorkSend;
import cn.fdongl.numberwangmock.repository.JobRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 张博
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkSendTest {

    @Autowired
    JobRepository jobRepository;

    @Test
    public void job(){
        System.out.println(jobRepository.updateJobStatus(0L, 1L, 1L));
    }

}