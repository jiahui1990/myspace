package com.scu.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.scu.bean.SalorForm;

@Scope("prototype")
@Repository
public class SalorDAO extends BaseDAO<SalorForm> {}