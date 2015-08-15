package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.SaleTicketForm;

@Scope("prototype")
@Repository
public class TicketDAO extends BaseDAO<SaleTicketForm> {}