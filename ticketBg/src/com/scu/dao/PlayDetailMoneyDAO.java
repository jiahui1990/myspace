package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.PlayDetailMoney;

@Scope("prototype")
@Repository
public class PlayDetailMoneyDAO extends BaseDAO<PlayDetailMoney> {}