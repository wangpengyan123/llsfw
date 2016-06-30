待完成: 
    *.开发响应式布局后台

    *.添加AngularJS模块
    *.考虑动态报表模块
    *.数据国际化(数据库方式)
已完成:
    *.(完成)修改代码检查的描述
    *.(完成)版本更新到2.3.3
    *.(完成)更新maven依赖
    *.(完成)版本更新到2.3.2
    *.(完成)规范代码
    *.(完成)excel导出,可定义表头宽度
    *.(完成)登陆添加验证码
    *.(完成)修复密码修改,在后端没有旧密码验证的漏洞
    *.(完成)mybatis的LIKE语句写法调整($改为#)
    *.(完成)在登录页添加项目主页的超链接[demo环境用]
    *.(完成)修复左边菜单在IE下会出现纵向滚动条的问题
    *.(完成)添加生成GUID的JS
    *.(完成)添加plupload-2.1.8组件
    *.(完成)修改左边菜单,关闭可拖拉,打开伸缩按钮
    *.(完成)修改easyui远程校验,添加提示信息参数
    *.(完成)处理菜单树,点击旁边空白地方的事件响应,脚本错误修复
    *.(完成)添加运行时异常子类,分页异常处理修改为运行时异常
    *.(完成)基于POI做文件导入导出的封装
    *.(完成)修复删除用户,但是用户和PORTAL关联表数据没有删除的问题
    *.(完成)基础表TMM前缀更换为TS(坑,这里面故事很多,不细说)
    *.(完成)添加无状态登陆认证,适用接口调用
    *.(完成)将版本号变更为2.3.1
    *.(完成)处理菜单树,点击旁边空白地方的事件响应
    *.(完成)mybatis分页拦截器,count方法中SQL语句,去除order by
    *.(完成)抽象出AdminService中操作用户表地方,做成接口
    *.(完成)分页配置文件,去掉方法名匹配判断
    *.(完成)添加发送email
    *.(完成)添加hibernate validator,来支持后端校验
    *.(完成)国际化支持
    *.(完成)在mapper,model,sqlmap层下expand包,需要支持按"业务模块"隔开
    *.(完成)多数据源切换
    *.(完成)dbcp动态配置与优化
    *.(完成)更换日志组件为Log4J2
    *.(完成)动态库DataSource类型可配置
    *.(完成)分页接口优化
    *.(完成)上传框架至maven中央库
    *.(完成)整理权限模块的表结构
    *.(完成)用户行为拦截器
    *.(完成)工作流
    *.(完成)实现根据[用户][自动][切换数据库]这个切面,定义用户需实现的接口,非强制性功能,做好相关的开关
    
模块拆分:
    *."llsfw-core"模块,核心内容的实现与配置
    *."llsfw-generator"模块,存放所有自动生成的代码
    *."llsfw-webbase"模块,封装前端的默认选型,以及监控等基础功能
    *."llsfw-basecode"模块,封装前端功能的代码
    
activiti集成activiti-explorer纪要:
    1.将modeler.html更改为modeler.jsp
    2.在modeler.jsp的head中添加上下文根路径的代码
    3.修改app-cfg的ACTIVITI.CONFIG,将contextRoot的值改为 basePath + 'service'
    4.将diagram-viewer文件夹中的index.html更改为index.jsp,并且将ActivitiRest.options中的路径替换为服务端路径basePath

    
发布到中央库命令:
mvn clean install deploy -P release -Dgpg.passphrase=密码 -f pom.xml

模板地址:
https://wrapbootstrap.com/
        