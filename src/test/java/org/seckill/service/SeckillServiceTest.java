package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExcution;
import org.seckill.entity.SecKill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 胡庆泽 on 2017/2/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSecKillList() throws Exception {
        List<SecKill> list = seckillService.getSecKillList();
        logger.info("list={}",list);
        //Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5454d35e]
        //不是在事务控制下
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        SecKill secKill = seckillService.getById(id);
        logger.info("seckill={}",secKill);
    }

    //测试代码完整逻辑，注意可重复执行
    @Test
    public void SeckillLogic() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(1000);
        if(exposer.isExposed()){
            logger.info("exposer = {}",exposer);
            long phone = 13644054928L;
            String md5  = exposer.getMd5();
            try {
                SeckillExcution seckillExcution = seckillService.excuteSeckill(id,phone,md5);
                logger.info("seckillExcution={}",seckillExcution);
                //seckillExcution=SeckillExcution{seckillId=1000, status=1, statusInfo='秒杀成功', successKilled=SuccessKilled{seckillId=1000, userPhone=13644054928, status=0, createTime=Mon Feb 27 14:14:17 CST 2017}}
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            //秒杀未开启
            logger.warn("exposer = {}",exposer);
        }
    }
}