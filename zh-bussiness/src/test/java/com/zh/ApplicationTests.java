package com.zh;

import com.zh.service.meteorological.MeteorologicalService;
import com.zh.service.sys.SysRolesService;
import com.zh.service.task.ExceptionTaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {


    @Resource
    private MeteorologicalService meteorologicalService;

    @Resource
    private SysRolesService sysRolesService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private ExceptionTaskService exceptionTaskService;


    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        String rainStormWarn = meteorologicalService.getRainStormWarn("");
        System.out.println(rainStormWarn);

    }

    @Test
    public void test1(){
        String admin = passwordEncoder.encode("admin");
        System.out.println(admin);
    }

    @Test
    public void test2(){
        long l = LocalDateTime.of(LocalDate.now(), LocalTime.of(LocalTime.now().getHour(), 0, 0)).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(l);
    }


}
