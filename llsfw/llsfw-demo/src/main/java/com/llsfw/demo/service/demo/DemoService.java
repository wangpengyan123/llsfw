/**
 * 
 */
package com.llsfw.demo.service.demo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.Uuid;
import com.llsfw.core.exception.SystemException;
import com.llsfw.core.pagequery.PageInterceptor;
import com.llsfw.core.pagequery.PageResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.demo.mapper.expand.demo.DemoMapper;
import com.llsfw.demo.mapper.standard.demo.TtDbsDemoMapper;
import com.llsfw.generator.mapper.standard.system.TtScheduledLogMapper;
import com.llsfw.generator.model.standard.system.TtScheduledLog;

/**
 * @author kkll
 *
 */
@Service
public class DemoService extends BaseService {

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

    @Autowired
    private DemoMapper dm;

    /**
     * 测试列为空值的时候,map映射的请情况
     * 
     * @return
     */
    public void testNullCol() {
        List<Map<String, Object>> tt = dm.testNullCol();
        LOG.info(tt + "");
    }

    public void pageQuery() throws SystemException {
        PageInterceptor.startPage(100, 1);
        PageResult<Map<String, Object>> pr = PageInterceptor.getPageResult(dm.ttDbsDemoPageQuery("%1%"));
        LOG.debug(pr.getTotalRecords() + "");
        LOG.debug(pr.size() + "");
    }

    public void testCallSql() throws Exception {
        LOG.info("testCallSql");
        // this.getPrs().pageQuery("SELECT * FROM TT_SCHEDULED_LOG", 5, 1); 
        TtScheduledLog tsd = new TtScheduledLog();
        tsd.setLogid(Uuid.getUuid(false));
        tsd.setCreateDate(new Date());
        tsd.setMsg("testCallSql");
        tslm.insertSelective(tsd);
        insertTtScheduledLog();
        // throw new Exception("测试异常"); 
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

    public void throwException() throws Exception {
        LOG.info("throwException");
        throw new Exception("测试异常");
    }
}
