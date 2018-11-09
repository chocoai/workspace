package com.smart.dao.impl;

import org.springframework.stereotype.Repository;
import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.UserDao;
import com.smart.model.User;

/**
 * UserDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {

}
