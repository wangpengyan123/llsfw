/**
 * MassSendJobFinishInputMessage.java
 * Created at 2016-04-26
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.message.input.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.llsfw.wx.api.impl.message.input.BaseInputMessage;

/**
 * <p>
 * ClassName: MassSendJobFinishInputMessage
 * </p>
 * <p>
 * Description: 事件推送群发结果
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月26日
 * </p>
 */
@XmlRootElement(name = "xml")
public class MassSendJobFinishInputMessage extends BaseInputMessage {

    /**
     * <p>
     * Field msgId: 群发的消息ID
     * </p>
     */
    private String msg_Id;

    /**
     * <p>
     * Field status: 群发的结构，为“send success”或“send fail”或“err(num)”。但send
     * success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。err(num)是审核失败的具体原因，可能的情况如下： err(10001), //涉嫌广告 err(20001), //涉嫌政治
     * err(20004), //涉嫌社会 err(20002), //涉嫌色情 err(20006), //涉嫌违法犯罪 err(20008), //涉嫌欺诈 err(20013), //涉嫌版权 err(22000),
     * //涉嫌互推(互相宣传) err(21000), //涉嫌其他
     * </p>
     */
    private String status;

    /**
     * <p>
     * Field totalCount: group_id下粉丝数；或者openid_list中的粉丝数
     * </p>
     */
    private Long totalCount;

    /**
     * <p>
     * Field filterCount: 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
     * </p>
     */
    private Long filterCount;

    /**
     * <p>
     * Field sentCount: 发送成功的粉丝数
     * </p>
     */
    private Long sentCount;

    /**
     * <p>
     * Field errorCount: 发送失败的粉丝数
     * </p>
     */
    private Long errorCount;

    public String getMsg_Id() {
        return this.msg_Id;
    }

    @XmlElement(name = "MsgID")
    public void setMsg_Id(String msg_Id) {
        this.msg_Id = msg_Id;
    }

    public String getStatus() {
        return this.status;
    }

    @XmlElement(name = "Status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalCount() {
        return this.totalCount;
    }

    @XmlElement(name = "TotalCount")
    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getFilterCount() {
        return this.filterCount;
    }

    @XmlElement(name = "FilterCount")
    public void setFilterCount(Long filterCount) {
        this.filterCount = filterCount;
    }

    public Long getSentCount() {
        return this.sentCount;
    }

    @XmlElement(name = "SentCount")
    public void setSentCount(Long sentCount) {
        this.sentCount = sentCount;
    }

    public Long getErrorCount() {
        return this.errorCount;
    }

    @XmlElement(name = "ErrorCount")
    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }

}
