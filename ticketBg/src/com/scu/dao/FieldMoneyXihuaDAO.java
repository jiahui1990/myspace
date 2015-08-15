package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.FieldMoneyXihua;

@Scope("prototype")
@Repository
public class FieldMoneyXihuaDAO extends BaseDAO<FieldMoneyXihua> {}