package com.hww.service.impl;


import com.hww.dao.AccountDao;
import com.hww.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 账户业务实现类
 * Created by zzyy on 2019/11/11.
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {



    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        accountDao.decrease(userId, money);
        log.info("------->account-service中扣减账户余额结束");
    }
}
