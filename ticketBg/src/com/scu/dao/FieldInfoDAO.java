package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.FieldInfo;

@Scope("prototype")
@Repository
public class FieldInfoDAO extends BaseDAO<FieldInfo> {}