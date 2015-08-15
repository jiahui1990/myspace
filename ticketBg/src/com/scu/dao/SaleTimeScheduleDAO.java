package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.SaleTimeSchedule;

@Scope("prototype")
@Repository
public class SaleTimeScheduleDAO extends BaseDAO<SaleTimeSchedule> {}