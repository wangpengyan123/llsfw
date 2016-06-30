/**
 * CsvFileUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.custom.session.factories.user;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsJobMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserJobMapper;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;
import com.llsfw.webgen.model.standard.permissions.TsJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJobCriteria;

/**
 * <p>
 * ClassName: CustomUserEntityManager
 * </p>
 * <p>
 * Description: 用户管理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class CustomUserEntityManager extends UserEntityManager {

    /**
     * <p>
     * Field taum: 用户mapper
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * <p>
     * Field tjm: 岗位mapper
     * </p>
     */
    @Autowired
    private TsJobMapper tjm;

    /**
     * <p>
     * Field tujm: 用户岗位mapper
     * </p>
     */
    @Autowired
    private TsUserJobMapper tujm;

    @Override
    public User findUserById(String userId) {
        TsApplicationUser tau = this.taum.selectByPrimaryKey(userId);
        User user = new UserEntity();
        user.setEmail(tau.getUserEmail());
        user.setFirstName(tau.getUserName());
        user.setId(tau.getLoginName());
        user.setLastName(StringUtils.EMPTY);
        user.setPassword(tau.getLoginPassword());
        return user;
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Group> groupList = new ArrayList<Group>();
        TsUserJobCriteria tujc = new TsUserJobCriteria();
        tujc.createCriteria().andLoginNameEqualTo(userId);
        List<TsUserJob> tujList = this.tujm.selectByExample(tujc);
        if (!CollectionUtils.isEmpty(tujList)) {
            for (TsUserJob tuj : tujList) {
                TsJob tj = this.tjm.selectByPrimaryKey(tuj.getJobCode());
                Group group = new GroupEntity();
                group.setId(tj.getJobCode());
                group.setName(tj.getJobName());
                group.setType(tj.getOrgCode());
                groupList.add(group);
            }
        }
        return groupList;
    }

}
