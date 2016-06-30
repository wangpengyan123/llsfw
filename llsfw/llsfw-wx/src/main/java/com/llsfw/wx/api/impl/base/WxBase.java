/**
 * WxBase.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.base;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: WxBase
 * </p>
 * <p>
 * Description: 微信平台基础支持基类对象
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class WxBase implements Serializable {

    /**
     * 全局微信接口URL
     */
    private static final Map<String, String> WX_API_URL_MAP = new HashMap<String, String>();

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = 2210017144983849380L;

    /**
     * 全局返回码说明接收
     */
    private static final Map<String, String> RETURN_CODE_MAP = new HashMap<String, String>();

    static {

        //全局微信URL
        WX_API_URL_MAP.put("0", "https://api.weixin.qq.com/cgi-bin/token"); // 获得TOKEN
        WX_API_URL_MAP.put("1", "https://api.weixin.qq.com/cgi-bin/getcallbackip"); // 获取微信服务器IP地址
        WX_API_URL_MAP.put("2", "https://api.weixin.qq.com/cgi-bin/groups/create"); // 创建分组
        WX_API_URL_MAP.put("3", "https://api.weixin.qq.com/cgi-bin/groups/get"); // 查询所有分组
        WX_API_URL_MAP.put("4", "https://api.weixin.qq.com/cgi-bin/groups/getid"); // 查询用户所在分组
        WX_API_URL_MAP.put("5", "https://api.weixin.qq.com/cgi-bin/groups/update"); // 修改分组名
        WX_API_URL_MAP.put("6", "https://api.weixin.qq.com/cgi-bin/groups/members/update"); //移动用户分组
        WX_API_URL_MAP.put("7", "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate"); //批量移动用户分组
        WX_API_URL_MAP.put("8", "https://api.weixin.qq.com/cgi-bin/groups/delete"); //批量移动用户分组
        WX_API_URL_MAP.put("9", "https://api.weixin.qq.com/cgi-bin/user/info/updateremark"); //删除分组
        WX_API_URL_MAP.put("10", "https://api.weixin.qq.com/cgi-bin/user/info"); //获取用户基本信息（包括UnionID机制）
        WX_API_URL_MAP.put("11", "https://api.weixin.qq.com/cgi-bin/user/info/batchget"); //批量获取用户基本信息
        WX_API_URL_MAP.put("12", "https://open.weixin.qq.com/connect/oauth2/authorize"); //用户同意授权，获取code
        WX_API_URL_MAP.put("13", "https://api.weixin.qq.com/sns/oauth2/access_token"); //通过code换取网页授权access_token
        WX_API_URL_MAP.put("14", "https://api.weixin.qq.com/sns/oauth2/refresh_token"); //刷新access_token
        WX_API_URL_MAP.put("15", "https://api.weixin.qq.com/sns/userinfo"); //拉取用户信息(需scope为 snsapi_userinfo)
        WX_API_URL_MAP.put("16", "https://api.weixin.qq.com/sns/auth"); //检验授权凭证（access_token）是否有效
        WX_API_URL_MAP.put("17", "https://api.weixin.qq.com/customservice/kfaccount/add"); //添加客服帐号
        WX_API_URL_MAP.put("18", "https://api.weixin.qq.com/customservice/kfaccount/update"); //修改客服帐号
        WX_API_URL_MAP.put("19", "https://api.weixin.qq.com/customservice/kfaccount/del"); //删除客服帐号
        WX_API_URL_MAP.put("20", "http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg"); //设置客服帐号的头像
        WX_API_URL_MAP.put("21", "https://api.weixin.qq.com/cgi-bin/customservice/getkflist"); //获取所有客服账号
        WX_API_URL_MAP.put("22", "https://api.weixin.qq.com/cgi-bin/message/custom/send"); //客服接口-发消息
        WX_API_URL_MAP.put("23", "https://api.weixin.qq.com/cgi-bin/media/uploadimg"); //上传图文消息内的图片获取URL
        WX_API_URL_MAP.put("24", "https://api.weixin.qq.com/cgi-bin/media/uploadnews"); //上传图文消息素材
        WX_API_URL_MAP.put("25", "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo"); //上传视频
        WX_API_URL_MAP.put("26", "https://api.weixin.qq.com/cgi-bin/message/mass/sendall"); //根据分组进行群发
        WX_API_URL_MAP.put("27", "https://api.weixin.qq.com/cgi-bin/message/mass/send"); //根据OpenID列表群发
        WX_API_URL_MAP.put("28", "https://api.weixin.qq.com/cgi-bin/message/mass/delete"); //删除群发
        WX_API_URL_MAP.put("29", "https://api.weixin.qq.com/cgi-bin/message/mass/preview"); //预览接口
        WX_API_URL_MAP.put("30", "https://api.weixin.qq.com/cgi-bin/message/mass/get"); //查询群发消息发送状态
        WX_API_URL_MAP.put("31", "https://api.weixin.qq.com/cgi-bin/user/get"); //获取用户列表

        //全局返回码说明
        RETURN_CODE_MAP.put("-1", "系统繁忙，此时请开发者稍候再试");
        RETURN_CODE_MAP.put("0", "请求成功");
        RETURN_CODE_MAP.put("40001",
                "获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口");
        RETURN_CODE_MAP.put("40002", "不合法的凭证类型");
        RETURN_CODE_MAP.put("40003", "不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID");
        RETURN_CODE_MAP.put("40004", "不合法的媒体文件类型");
        RETURN_CODE_MAP.put("40005", "不合法的文件类型");
        RETURN_CODE_MAP.put("40006", "不合法的文件大小");
        RETURN_CODE_MAP.put("40007", "不合法的媒体文件id");
        RETURN_CODE_MAP.put("40008", "不合法的消息类型");
        RETURN_CODE_MAP.put("40009", "不合法的图片文件大小");
        RETURN_CODE_MAP.put("40010", "不合法的语音文件大小");
        RETURN_CODE_MAP.put("40011", "不合法的视频文件大小");
        RETURN_CODE_MAP.put("40012", "不合法的缩略图文件大小");
        RETURN_CODE_MAP.put("40013", "不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写");
        RETURN_CODE_MAP.put("40014", "不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口");
        RETURN_CODE_MAP.put("40015", "不合法的菜单类型");
        RETURN_CODE_MAP.put("40016", "不合法的按钮个数");
        RETURN_CODE_MAP.put("40017", "不合法的按钮个数");
        RETURN_CODE_MAP.put("40018", "不合法的按钮名字长度");
        RETURN_CODE_MAP.put("40019", "不合法的按钮KEY长度");
        RETURN_CODE_MAP.put("40020", "不合法的按钮URL长度");
        RETURN_CODE_MAP.put("40021", "不合法的菜单版本号");
        RETURN_CODE_MAP.put("40022", "不合法的子菜单级数");
        RETURN_CODE_MAP.put("40023", "不合法的子菜单按钮个数");
        RETURN_CODE_MAP.put("40024", "不合法的子菜单按钮类型");
        RETURN_CODE_MAP.put("40025", "不合法的子菜单按钮名字长度");
        RETURN_CODE_MAP.put("40026", "不合法的子菜单按钮KEY长度");
        RETURN_CODE_MAP.put("40027", "不合法的子菜单按钮URL长度");
        RETURN_CODE_MAP.put("40028", "不合法的自定义菜单使用用户");
        RETURN_CODE_MAP.put("40029", "不合法的oauth_code");
        RETURN_CODE_MAP.put("40030", "不合法的refresh_token");
        RETURN_CODE_MAP.put("40031", "不合法的openid列表");
        RETURN_CODE_MAP.put("40032", "不合法的openid列表长度");
        RETURN_CODE_MAP.put("40033", "不合法的请求字符，不能包含\\uxxxx格式的字符");
        RETURN_CODE_MAP.put("40035", "不合法的参数");
        RETURN_CODE_MAP.put("40038", "不合法的请求格式");
        RETURN_CODE_MAP.put("40039", "不合法的URL长度");
        RETURN_CODE_MAP.put("40050", "不合法的分组id");
        RETURN_CODE_MAP.put("40051", "分组名字不合法");
        RETURN_CODE_MAP.put("41001", "缺少access_token参数");
        RETURN_CODE_MAP.put("41002", "缺少appid参数");
        RETURN_CODE_MAP.put("41003", "缺少refresh_token参数");
        RETURN_CODE_MAP.put("41004", "缺少secret参数");
        RETURN_CODE_MAP.put("41005", "缺少多媒体文件数据");
        RETURN_CODE_MAP.put("41006", "缺少media_id参数");
        RETURN_CODE_MAP.put("41007", "缺少子菜单数据");
        RETURN_CODE_MAP.put("41008", "缺少oauthcode");
        RETURN_CODE_MAP.put("41009", "缺少openid");
        RETURN_CODE_MAP.put("42001", "access_token超时，请检查access_token的有效期，请参考基础支持-获取access_token中，对access_token的详细机制说明");
        RETURN_CODE_MAP.put("42002", "refresh_token超时");
        RETURN_CODE_MAP.put("42003", "oauth_code超时");
        RETURN_CODE_MAP.put("43001", "需要GET请求");
        RETURN_CODE_MAP.put("43002", "需要POST请求");
        RETURN_CODE_MAP.put("43003", "需要HTTPS请求");
        RETURN_CODE_MAP.put("43004", "需要接收者关注");
        RETURN_CODE_MAP.put("43005", "需要好友关系");
        RETURN_CODE_MAP.put("44001", "多媒体文件为空");
        RETURN_CODE_MAP.put("44002", "POST的数据包为空");
        RETURN_CODE_MAP.put("44003", "图文消息内容为空");
        RETURN_CODE_MAP.put("44004", "文本消息内容为空");
        RETURN_CODE_MAP.put("45001", "多媒体文件大小超过限制");
        RETURN_CODE_MAP.put("45002", "消息内容超过限制");
        RETURN_CODE_MAP.put("45003", "标题字段超过限制");
        RETURN_CODE_MAP.put("45004", "描述字段超过限制");
        RETURN_CODE_MAP.put("45005", "链接字段超过限制");
        RETURN_CODE_MAP.put("45006", "图片链接字段超过限制");
        RETURN_CODE_MAP.put("45007", "语音播放时间超过限制");
        RETURN_CODE_MAP.put("45008", "图文消息超过限制");
        RETURN_CODE_MAP.put("45009", "接口调用超过限制");
        RETURN_CODE_MAP.put("45010", "创建菜单个数超过限制");
        RETURN_CODE_MAP.put("45015", "回复时间超过限制");
        RETURN_CODE_MAP.put("45016", "系统分组，不允许修改");
        RETURN_CODE_MAP.put("45017", "分组名字过长");
        RETURN_CODE_MAP.put("45018", "分组数量超过上限");
        RETURN_CODE_MAP.put("46001", "不存在媒体数据");
        RETURN_CODE_MAP.put("46002", "不存在的菜单版本");
        RETURN_CODE_MAP.put("46003", "不存在的菜单数据");
        RETURN_CODE_MAP.put("46004", "不存在的用户");
        RETURN_CODE_MAP.put("47001", "解析JSON/XML内容错误");
        RETURN_CODE_MAP.put("48001", "api功能未授权，请确认公众号已获得该接口，可以在公众平台官网-开发者中心页中查看接口权限");
        RETURN_CODE_MAP.put("50001", "用户未授权该api");
        RETURN_CODE_MAP.put("61451", "参数错误(invalidparameter)");
        RETURN_CODE_MAP.put("61452", "无效客服账号(invalidkf_account)");
        RETURN_CODE_MAP.put("61453", "客服帐号已存在(kf_accountexsited)");
        RETURN_CODE_MAP.put("61454", "客服帐号名长度超过限制(仅允许10个英文字符，不包括@及@后的公众号的微信号)(invalidkf_acountlength)");
        RETURN_CODE_MAP.put("61455", "客服帐号名包含非法字符(仅允许英文+数字)(illegalcharacterinkf_account)");
        RETURN_CODE_MAP.put("61456", "客服帐号个数超过限制(10个客服账号)(kf_accountcountexceeded)");
        RETURN_CODE_MAP.put("61457", "无效头像文件类型(invalidfiletype)");
        RETURN_CODE_MAP.put("61450", "系统错误(systemerror)");
        RETURN_CODE_MAP.put("61500", "日期格式错误");
        RETURN_CODE_MAP.put("61501", "日期范围错误");
    }

    /**
     * <p>
     * Description: 返回微信api的url
     * </p>
     * 
     * @param code url代码
     * @return url
     */
    public static String getWxApiUrl(String code) {
        return WX_API_URL_MAP.get(code);
    }

    /**
     * 异常代码识别
     *
     * @param result 错误信息
     * @return 错误信息
     */
    protected String getCause(BaseResult result) {
        if (RETURN_CODE_MAP.containsKey(result.getErrcode())) {
            //根据错误码返回错误代码
            return result.getErrcode() + ":" + result.getErrmsg() + ":" + RETURN_CODE_MAP.get(result.getErrcode());
        }
        return result.getErrcode() + ":操作异常";
    }

    /**
     * <p>
     * Description: 文件上传(备用)
     * </p>
     * 
     * @param url 上传地址
     * @param filePath 文件路径
     * @param flag true:多媒体,false:头像
     * @return 结果
     * @throws WxException 异常
     */
    //    protected String uploadback(String url, String filePath, boolean flag) throws WxException {
    //        String result = null;
    //        File file = new File(filePath);
    //        if (!file.exists() || !file.isFile()) {
    //            throw new WxException("文件不存在");
    //        }
    //        HttpURLConnection con = null; //http连接
    //        try {
    //
    //            //设置连接
    //
    //            URL urlObj = new URL(url);
    //            con = (HttpURLConnection) urlObj.openConnection();
    //            //设置关键字
    //            con.setRequestMethod("POST"); // 以POST方式提交表单，默认get方式
    //            con.setDoInput(true);
    //            con.setDoOutput(true);
    //            con.setUseCaches(false); // POST方式不能使用缓存
    //            // 设置请求头信息
    //            con.setRequestProperty("Connection", "Keep-Alive");
    //            con.setRequestProperty("Charset", Constants.DEF_CHARACTER_SET_ENCODING);
    //            String boundary = "----------" + System.currentTimeMillis();
    //            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    //            // 第一部分
    //            StringBuilder sb = new StringBuilder();
    //            sb.append("--"); // 必须多两道线
    //            sb.append(boundary);
    //            sb.append("\r\n");
    //            //上传头像和上传多媒体主要是下面这句话有区别
    //            if (flag) {
    //                sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
    //            } else {
    //                sb.append("Content-Disposition: 
    //                form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
    //            }
    //            sb.append("Content-Type:application/octet-stream\r\n\r\n");
    //            byte[] head = sb.toString().getBytes(Constants.DEF_CHARACTER_SET_ENCODING);
    //
    //            //开始写入请求数据
    //
    //            OutputStream out = null; //输出流
    //            DataInputStream in = null; //输入流
    //            try {
    //                // 获得输出流
    //                out = new DataOutputStream(con.getOutputStream());
    //
    //                // 输出表头
    //                out.write(head);
    //
    //                // 文件正文部分
    //                in = new DataInputStream(new FileInputStream(file));
    //                int bytes = 0;
    //                byte[] bufferOut = new byte[Constants.NUMBER_1024];
    //                while ((bytes = in.read(bufferOut)) != -1) {
    //                    out.write(bufferOut, 0, bytes);
    //                }
    //
    //                // 结尾部分
    //                byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8"); // 定义最后数据分隔线
    //                out.write(foot);
    //            } catch (IOException e) {
    //                throw new WxException(e.getMessage(), e);
    //            } finally {
    //                in.close();
    //                out.flush();
    //                out.close();
    //            }
    //
    //            //拿到响应
    //
    //            StringBuffer buffer = new StringBuffer();
    //            BufferedReader reader = null;
    //            try {
    //                // 定义BufferedReader输出流来读取URL的响应
    //                reader = new BufferedReader(
    //                        new InputStreamReader(con.getInputStream(), Constants.DEF_CHARACTER_SET_ENCODING));
    //                String line = null;
    //                while ((line = reader.readLine()) != null) {
    //                    buffer.append(line);
    //                }
    //                if (result == null) {
    //                    result = buffer.toString();
    //                }
    //            } catch (IOException e) {
    //                throw new WxException(e.getMessage(), e);
    //            } finally {
    //                out.flush();
    //                out.close();
    //                in.close();
    //            }
    //            return result;
    //        } catch (IOException e) {
    //            throw new WxException(e.getMessage(), e);
    //        } finally {
    //            if (con != null) {
    //                con.disconnect();
    //            }
    //        }
    //    }

    /**
     * <p>
     * Description: 文件上传
     * </p>
     * 
     * @param url 上传地址
     * @param filePath 文件路径
     * @param flag true:多媒体,false:头像
     * @return 结果
     * @throws WxException 异常
     */
    protected String upload(String url, String filePath, boolean flag) throws WxException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new WxException("文件不存在");
        }
        try {
            //请求正文信息
            FileBody bin = new FileBody(file); // 把文件转换成流对象FileBody
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            String name = null;
            if (flag) {
                name = "file";
            } else {
                name = "media";
            }
            HttpEntity entity = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532).addPart(name, bin)
                    .build();
            post.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity(), Constants.DEF_CHARACTER_SET_ENCODING);
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: post请求
     * </p>
     * 
     * @param url 地址
     * @param json 请求消息体
     * @return 结果
     * @throws WxException 异常
     */
    protected String post(String url, String json) throws WxException {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(json,
                    ContentType.create("text/plain", Constants.DEF_CHARACTER_SET_ENCODING));
            post.setEntity(entity);
            CloseableHttpResponse response = httpclient.execute(post);
            return EntityUtils.toString(response.getEntity(), Constants.DEF_CHARACTER_SET_ENCODING);
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: get请求
     * </p>
     * 
     * @param url 地址
     * @return 结果
     * @throws WxException 异常
     */
    protected String get(String url) throws WxException {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(get);
            return EntityUtils.toString(response.getEntity(), Constants.DEF_CHARACTER_SET_ENCODING);
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: 对象转JSON
     * </p>
     * 
     * @param t 对象
     * @param hasNull 是否包含null的字段(true:包含,false:不包含)
     * @return json
     * @throws WxException 异常
     */
    protected static String objectToJson(Object t, boolean hasNull) throws WxException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (!hasNull) {
                mapper.setSerializationInclusion(Include.NON_NULL);
            }
            return mapper.writeValueAsString(t);
        } catch (JsonParseException e) {
            throw new WxException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new WxException(e.getMessage(), e);
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: json转换为对象
     * </p>
     * 
     * @param result json
     * @param t 类型
     * @param <T> 类型
     * @return 对象
     * @throws WxException 异常
     */
    protected static <T> T jsonToObject(String result, Class<T> t) throws WxException {
        try {
            return new ObjectMapper().readValue(result, t);
        } catch (JsonParseException e) {
            throw new WxException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new WxException(e.getMessage(), e);
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: 检查返回值
     * </p>
     * 
     * @param br 返回值
     * @throws WxException 异常
     */
    protected void checkResult(BaseResult br) throws WxException {
        //判断是否拿到返回值
        if (br == null) {
            //返回异常信息
            throw new WxException("error");
        }
        //是否有错误(0为正常处理)
        if (br.getErrcode() != null && !"0".equals(br.getErrcode())) {
            //返回异常信息
            throw new WxException(getCause(br));
        }
    }

}
