/**
 * 
 */
package com.llsfw.demo.controller.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.datasource.DbContextHolder;
import com.llsfw.core.exception.SystemException;
import com.llsfw.demo.service.demo.DemoService;
import com.llsfw.demo.service.demo.DemoServiceNoExtends;
import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * @author Administrator
 *
 */
@Controller
public class DemoLoginController extends BaseController {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DemoService ds;

    @Autowired
    private DemoServiceNoExtends dsne;

    /**
     * 测试列为空值的时候,map映射的请情况
     * 
     * @return
     */
    @RequestMapping("testNullCol")
    public String testNullCol() {
        ds.testNullCol();
        return "main";
    }

    @RequestMapping("jsontest")
    @ResponseBody
    public TtDynamicDataSource jsontest() throws SystemException {
        TtDynamicDataSource ds = new TtDynamicDataSource();
        ds.setDbsName("我是中文");
        return ds;
    }

    @RequestMapping("pageQuery")
    public String pageQuery() throws SystemException {
        TtDynamicDataSource dataSource = null;
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_1");
        DbContextHolder.setDbType(dataSource);
        ds.pageQuery();

        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.pageQuery();

        return "main";
    }

    @RequiresPermissions("test:add")
    @RequestMapping("/t/a")
    public String aa() {
        return "main";
    }

    @RequestMapping("/testTransactionalNoExtendsBaseService")
    public String testTransactionalNoExtendsBaseService() throws Exception {
        dsne.insertTtScheduledLog();
        dsne.testCallSql();
        return "main";
    }

    @RequestMapping("/testTransactional")
    public String testTransactional() throws Exception {
        ds.insertTtScheduledLog();
        ds.testCallSql();
        return "main";
    }

    @RequestMapping("/testTtDynamicDataSource")
    public String testTtDynamicDataSource() throws Exception {
        LOG.error("error");
        LOG.warn("warn");
        LOG.info("info");
        LOG.debug("debug");
        LOG.trace("trace");
        ds.insertTtScheduledLog();
        ds.testCallSql();

        TtDynamicDataSource dataSource = null;

        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_1");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        ds.testCallSql();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_1");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        ds.testCallSql();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        ds.testCallSql();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        DbContextHolder.setDbType(null);
        ds.testCallSql();

        DbContextHolder.setDbType(null);
        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_2");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        dataSource = this.getDynamicDataSoucre("LLSFW_DEMO_1");
        DbContextHolder.setDbType(dataSource);
        ds.testReadDemo();

        return "main";
    }

    @RequestMapping("/")
    public String toMainPage() {
        return "main";
    }

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest req) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String rv = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户名或者密码错误";
        } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
            rv = "用户被锁定";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            rv = "用户名或者密码错误";
        } else if (exceptionClassName != null) {
            rv = "未知错误,请联系管理员";
            LOG.info(exceptionClassName);
        }
        req.setAttribute("rv", rv);
        return "login";
    }

}
