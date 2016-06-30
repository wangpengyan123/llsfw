
# EGRID框架
基于spring mvc,spring,mybatis,quartz,activiti,shiro,等开源技术,作为JAVA项目的基础框架,提供稳定的框架整合以及依赖关系.<br />
以扩展和增强的方式整合,无过度封装,可自由的使用以及扩展.

# DEMO系统访问地址(测试系统性能有限,如访问速度较慢,还请耐心等待)
[点击我](http://121.40.16.139 "DEMO系统访问地址") (demo/qqqqqq)

## 特点
*   基于主流开源框架搭建,扩展性高,稳定性强
*   quartz在线管理模块
*   多租户数据源动态切换
*   基于插件的分页查询
*   细颗粒的权限管控
*   分布式session,使用redis存储和缓存session(可切换为内存session)
*   可自动生成部分的代码
*   配置文件少
*   依赖定期更新
*   项目包结构强制规范
*   添加发送email,后端校验,国际化支持
*   框架支持的,FTP操作,HTTP操作,ZIP压缩解压缩操作,Des加密解密操作,等等....
*   activiti工作流示例

## 工程用途说明
*   llsfw-core : 框架核心工程,包含所有依赖,以及功能实现
*   llsfw-generator : llsfw-core的附属工程,存放所有自动生成的文件
*   llsfw-demo : 完整的框架使用样例
*   llsfw-documentation : 存放项目相关的文档,PDM,以及代码生成工具
*   llsfw-web : 框架基础功能前端代码
*   llsfw-webcore : 框架基础功能后端代码
*   llsfw-webgen : 框架基础功能自动生成代码
*   llsfw-activiti  :   工作流集成模块
*   llsfw-webdemo : 框架基础功能DEMO样例工程

##Maven坐标
可在[Maven中央库](http://search.maven.org/ "Maven中央库")或者[OSC的MAVEN仓库](http://maven.oschina.net/home.html "OSC的MAVEN仓库")中搜索最新版本的llsfw来添加依赖,具体的依赖代码如下:

        <!--使用core-->
	    <dependency>
            <groupId>com.llsfw</groupId>
            <artifactId>llsfw-generator</artifactId>
            <version>2.3.2-RELEASE</version>
        </dependency>
        <dependency>
            <groupId>com.llsfw</groupId>
            <artifactId>llsfw-core</artifactId>
            <version>2.3.2-RELEASE</version>
        </dependency>
        
        <!--******************************************************************-->
        
        <!--使用webcore-->
        <dependency>
            <groupId>com.llsfw</groupId>
            <artifactId>llsfw-webcore</artifactId>
            <version>2.3.2-RELEASE</version>
        </dependency>
        <dependency>
            <groupId>com.llsfw</groupId>
            <artifactId>llsfw-web</artifactId>
            <version>2.3.2-RELEASE</version>
            <type>war</type>
        </dependency>
        
        <!--添加plugin-->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.6</version>
            <configuration>
                <overlays>
                    <overlay>
                        <groupId>com.llsfw</groupId>
                        <artifactId>llsfw-web</artifactId>
                    </overlay>
                </overlays>
            </configuration>
        </plugin>

## 组件依赖
![组件依赖](http://git.oschina.net/wangkang/llsfw/wikis/image/dependencies.png "组件依赖") 

## 技术栈
![技术栈](http://git.oschina.net/wangkang/llsfw/wikis/image/framework.png "架构图") 

## 必要使用的工具
*   依赖管理 : maven
*   版本管理 : git(优选) , svn

## 建议使用的工具
*   质量管理 : findbug和checkStyle(相关配置文件在/llsfw-documentation/checkStyleConf中) 
*   其他插件 : properties Editor(应用商店搜索,即可安装)

## 工具设置相关说明
*   eclipse中使用space来缩进,而不是使用tab,[参考配置地址](http://my.oschina.net/xunxun10/blog/110074 "参考配置地址")
*   eclipse中对文件fommter的长度默认较小,可以调整,[参考配置地址](http://qq85609655.iteye.com/blog/1164664 "参考配置地址") 
*   eclipse中文件编码设定为UTF-8,在windiw-->Perferences-->General-->Content Types中选择Text,然后在下方的Default encoding框中填写UTF-8,然后apply即可
*   eclipse中的字体调整,在windiw-->Perferences-->General-->Appearance-->Colors and Fonts,然后在Basis下找到Text Font,点击edit,一般将字体设置为Courier New,大小设置为10

##快速搭建EGRID环境
*   使用CORE,参考llsfw-demo项目-->[详情点这里](http://git.oschina.net/wangkang/llsfw/wikis/InstallationManual "点这里提交BUG") 
*   五步即可完成项目搭建([视频演示](http://v.youku.com/v_show/id_XMTQ4ODQ4ODUwOA==.html "视频演示") ) (吐槽:优酷,90秒的广告....,各位,有其他好的视屏网站可推荐一下..)


    1   签出llsfw-web项目
    
    2   签出llsfw-webdemo项目,并重命名为你想要的项目名称
    
    3   执行llsfw-documentation/llsfw_db_model/LLSFW-CORE-DB.pdm中的脚本
    
    4   执行llsfw-documentation/data/中的数据初始化脚本
    
    5   部署TOMCAT,启动


##配置文件详细说明
*   i18n : 存放国际化消息文件,文件名例如 : messages_zh_CN.properties , messages_en_US.properties
*   sqlmap : 存放Mybatis的SqlMap文件,详情参考"使用者必要的包结构"这个模块
*   log4j2.xml : 标准的log4j2配置文件,详情可在官网查询
*   [resources.properties](http://git.oschina.net/wangkang/llsfw/wikis/resourcesFile "")
*   spring-interceptors.xml : url拦截器,spring标准配置
*   [spring-scheduler.xml](http://git.oschina.net/wangkang/llsfw/wikis/scheduler "")
*   [spring-security.xml](http://git.oschina.net/wangkang/llsfw/wikis/security "")
*   [spring-systemParam.xml](http://git.oschina.net/wangkang/llsfw/wikis/systemParam "")
*   [web.xml](http://git.oschina.net/wangkang/llsfw/wikis/web "")

## 使用者项目中必要的包结构
    > 前端
    *  jsp(src/main/webapp/WEB-INF) : /jsp/*/**
    *  js (src/main/webapp/WEB-INF) : /static/*/**
    > 配置文件
    *  spring容器(src/main/resources) : /config/*/*/spring/spring-*.xml
    *  spring mvc(src/main/resources) : /config/*/*/springmvc/spring-*.xml
    > 后端
	*  控制层的扫描规则(src/main/java) : com.*.*.controller.*
	*  业务逻辑层的扫描规则 (src/main/java): com.*.*.service.*
	*  Mapper层的扫描规则 (src/main/java): com.*.*.mapper.standard.* , com.*.*.mapper.expand.*
	*  model层的扫描规则 (src/main/java): com.*.*.model.standard.* , com.*.*.model.expand.*
	*  SqlMap层的扫描规则 (src/main/java/resources): sqlmap/*/*/standard/*/*.xml , sqlmap/*/*/expand/*/*.xml

## 使用者项目中必要包结构的说明,以及必要的要求
*   在所有层(contoller,service,mapper,model,sqlmap)中,类或者文件都必须用包隔开,包的含义可认为是"模块名"
*   控制层(controller)添加@RequiresPermissions,则有权限管控,反之则无权限管控
*   业务逻辑层(service)继承com.llsfw.core.service.BaseService,默认事务传播机制为Propagation.REQUIRED
*   业务逻辑层(service)不继承com.llsfw.core.service.BaseService,默认没有事务管控
*   业务逻辑层(service)如需要使用其他的事务传播机制,则在方法上注明@Transactional
*   在mapper,model,sqlmap层下的自动生成的文件需放在standard包中,而自定义的文件需放在expand包中
*   DBContextHolder数据库切换,必须在"非业务逻辑层"调用,并且尽可能的减少数据库切换的次数,过多的切换,同样会造成性能降低
*   使用者需自己实现验证的Realm和登陆contoller(可参考llsfw-demo项目)

## 建议使用者项目遵循规范要求
*   所有项目,web类型的项目中只能存放前端的内容
*   所有项目,自动生成的代码,应放在独立的工程中,并且禁止人为修改
*   所有项目,接口,以及VO的定义,以及需公开的类,应放在独立的工程中
*   所有mvc端异常必须抛出到最外一层,如无特殊原因,不得catch异常
*   不允许在SqlMap之外的其他任何地方出现SQL语句
*   在SqlMap中禁止使用${}来传递参数,因为会有SQL注入风险
*   尽量保证所有的业务逻辑在业务逻辑层(service)完成
*   在整个项目的java package中不允许存放.java之外的文件
*   如项目需要国际化,则项目中所有的文字描述,都需配置在国际化文件中,不能在页面中写死
*   整体项目中,不允许出现警告
*   使用代码检查工具,建议findbug和checkStyle(规范配置文件在llsfw-documentation项目中)
## 使用者项目对框架进行扩展的说明
*   动态库的连接池默认为DBCP,如果需要更换,则继承DynamicDataSource类,并且重写getDataSource方法,最后调整resources.properties文件的dynamicDataSource.class属性即可
*   异常信息格式,如要变更,需实现IExceptionOp接口,最后调整resources.properties文件的mvc.exceptionOp属性即可
*   MyBatis物理分页拦截器默认实现为PageInterceptor,方言目前只有Oracle和MySql,如需要扩展,可继承PageInterceptor类,并且重写generatePageSql方法,最后调整resources.properties文件的pageResult.plugin等属性即可

##其他说明
*   框架默认的国际化方式是基于cookie的.
*   国际化文件文件默认放在classpath的i18n目录下,文件以messages开头
*   切换国际化语言的参数为locale

## 问题汇总

*   国内maven下载依赖包的速度过慢([解决点我](http://maven.oschina.net/help.html "国内maven下载依赖速度过慢"))

*   V2为开发分支,经常更新的,可能会有与maven中版本不匹配的地方,解决方式有2种,1:下载V2的全部包,在本地构建,2:下载tag的包每个版本中的内容与maven都是对应的.

## 提交BUG
[点这里](http://git.oschina.net/wangkang/llsfw/issues/new?issue%5Bassignee_id%5D=&issue%5Bmilestone_id%5D= "点这里提交BUG") 

## 联系方式
*   邮箱 : llsfw_admin@163.com

## 更新日志

### V2.3.3版本修改记录:

1   版本更新到2.3.3

2   修改代码检查的描述

3   mybatis-spring更新1.2.5

4   修改分页查询显示，当总数据为0时，显示查询sql语句

5   base.js新增一个替换空格为nbsp的方法

6   分页查询插件修正了一个sql语句中有in时，count数据为0的问题

### (已经发布)V2.3.2版本修改记录:

1   添加无状态登陆认证,适用接口调用

2   基础表TMM前缀更换为TS(坑,这里面故事很多,不细说)

3   修复删除用户,但是用户和PORTAL关联表数据没有删除的问题

4   基于POI做文件导入导出的封装

5   添加运行时异常子类,分页异常处理修改为运行时异常

6   处理菜单树,点击旁边空白地方的事件响应,脚本错误修复

7   修改easyui远程校验,添加提示信息参数

8   修改左边菜单,关闭可拖拉,打开伸缩按钮

9   添加plupload-2.1.8组件

10  添加生成GUID的JS

11  修复左边菜单在IE下会出现纵向滚动条的问题

12  在登录页添加项目主页的超链接[demo环境用]

13  登陆添加验证码

14  修复密码修改,在后端没有旧密码验证的漏洞

15  mybatis的LIKE语句写法调整($改为#)

16  excel导出,可定义表头宽度

17  规范代码

18  更新maven依赖

19  版本更新到2.3.2

### (已经发布)V2.3.1版本修改记录:

1   添加行为日志相关的报表,添加在poratl面板中

2   将项目的JDK要求由JDK1.7+降级到JDK1.6+

3   将日志组件更换为logback

4   更换权限系统表名前缀为TMM

5   抽象出AdminService中操作用户表地方,做成接口

6   分页配置文件,去掉方法名匹配判断

7   mybatis分页拦截器,count方法中SQL语句,去除order by

8   处理菜单树,点击旁边空白地方的事件响应

9   将版本号变更为2.3.1

### (已经发布)V2.3版本修改记录:

1   将mybatis的版本由3.3.0降级为3.2.8,原因是acitviti与mybatis最新版本不兼容,会导致activiti的SQL查询错误.

### (已经发布)V2.2版本修改记录:

1   mybatis拦截器,增加拦截判断,判断被拦截的sqlMap,在调用之前是否调用过PageInterceptor.startPage方法,如果没有,则默认为不分页查询,详细逻辑如下:
    
    
    2016-01-15日修改:
    *.将原有强制定义的返回值类型List<Map<String,Object>>更换为支持泛型PageResult<T>,可随意设定返回值类型
    *.被拦截到的分页方法可直接返回PageResult<T>,移除endPage方法,增加getPageResult方法
    *.更新分页写法标准,如下:
    
    //例子1
    //分页查询标准写法    
    PageInterceptor.startPage(100, 1);
    PageResult<T> pr = PageInterceptor.getPageResult(dm.ttDbsDemoPageQuery("%1%"));
    
    //例子2:
    PageInterceptor.startPage(100, 1);
    //此语句会分页
    PageResult<T> pr = PageInterceptor.getPageResult(dm.ttDbsDemoPageQuery("%1%"));
    //此语句会报错
    pr=PageInterceptor.getPageResult(dm.ttDbsDemoPageQuery("%1%"));
    
    //例子3:
    PageInterceptor.startPage(100, 1);
    //此语句会分页
    PageResult<T> pr = PageInterceptor.getPageResult(dm.ttDbsDemoPageQuery("%1%"));
    //此语句正常执行,但是不会分页
    List<T> list=dm.ttDbsDemoPageQuery("%1%");
    
    //例子4:
    PageInterceptor.startPage(100, 1);
    //此语句会分页
    List<T> list=dm.ttDbsDemoPageQuery("%1%");
    //此语句不会分页
    list=dm.ttDbsDemoPageQuery("%1%");
    
    //例子5:
    PageInterceptor.startPage(100, 1);
    //此语句会分页
    PageResult<T> list = (PageResult) dm.ttDbsDemoPageQuery("%1%");
    //此语句会报错
    list = (PageResult) dm.ttDbsDemoPageQuery("%1%");
    
2   mybatis拦截器,增加一个startPage方法,添加了总行数的参数,目的是,可以允许调用者自行编写count查询,以便于在特殊情况下,编写相关优化后的count语句.

3   鉴于fastjson的issues较多,更换json支持为jackson

4   添加了一些常用的工具类

5   添加记录用户行为的拦截器LogInterceptor

6   添加RequestAttrLocaleChangeInterceptor拦截器,继承LocaleChangeInterceptor,主要是将国际化的类型放入request作用域中

7   (BUG修复)MySql分页拼接COUNT语句,在末尾添加一个别名

8   (BUG修复)mysql分页,limit的两个参数设置错误

9   修改mybatis的设置,当结果集中有空值的时候,将空值的键也映射到结果集中

10  添加判断请求来源(PC端或者移动端)的工具类

## 系统样例图
![1](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/1.png "1") 
![9](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/9.png "9") 
![2](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/2.png "2") 
![3](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/3.png "3") 
![4](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/4.png "4") 
![5](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/5.png "5") 
![6](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/6.png "6") 
![7](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/7.png "7") 
![8](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/8.png "8") 
![10](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/10.png "10") 
![11](http://git.oschina.net/wangkang/llsfw/wikis/image/demo/11.png "11") 