package com.dh.order.dao;

import com.dh.order.model.Merchant;

import java.util.List;

public interface MerchantMapper {
    int deleteByPrimaryKey(Integer merchantId);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    Merchant selectByPrimaryKey(Integer merchantId);

    List<Merchant> selectAll();

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKeyWithBLOBs(Merchant record);

    int updateByPrimaryKey(Merchant record);
}