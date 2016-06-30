/**
 * CustomGroupEntityManager.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.custom.session.factories.group;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.llsfw.webgen.mapper.standard.permissions.TsJobMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserJobMapper;
import com.llsfw.webgen.model.standard.permissions.TsJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJobCriteria;

/**
 * <p>
 * ClassName: CustomGroupEntityManager
 * </p>
 * <p>
 * Description: 组管理
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class CustomGroupEntityManager extends GroupEntityManager {

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
