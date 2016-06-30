/**
 * 
 */
package com.llsfw.demo.service.demo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.Uuid;
import com.llsfw.demo.mapper.standard.demo.TtDbsDemoMapper;
import com.llsfw.generator.mapper.standard.system.TtScheduledLogMapper;
import com.llsfw.generator.model.standard.system.TtScheduledLog;

/**
 * @author kkll
 *
 */
@Service
public class DemoServiceNoExtends {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TtScheduledLogMapper tslm;

    @Autowired
    private TtDbsDemoMapper tddm;

    public void testCallSql() throws Exception {
        this.LOG.info("testCallSql");
        tslm.selectByExample(null);
        TtScheduledLog tsd = new TtScheduledLog();
        tsd.setLogid(Uuid.getUuid(false));
        tsd.setCreateDate(new Date());
        tsd.setMsg("testCallSql");
        tslm.insertSelective(tsd);
        insertTtScheduledLog();
        throw new Exception("测试异常");
    }

    public void testReadDemo() {
        tddm.selectByExample(null);
    }

    public void insertTtScheduledLog() {
        TtScheduledLog tsd = new TtScheduledLog();
        tsd.setLogid(Uuid.getUuid(false));
        tsd.setCreateDate(new Date());
        tsd.setMsg("insertTtScheduledLog");
        tslm.insertSelective(tsd);
    }
}
