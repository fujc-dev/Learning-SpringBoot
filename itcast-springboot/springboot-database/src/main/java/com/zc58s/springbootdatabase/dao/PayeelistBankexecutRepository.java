package com.zc58s.springbootdatabase.dao;

import com.zc58s.springbootdatabase.entity.BzPayeelistBankexecutEntity;
import com.zc58s.springbootdatabase.vo.IPackageIndexAndExecuteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:06
 * springboot-database
 * com.zc58s.springbootdatabase.dao
 * PayeelistBankexecutRepository持久层类，定义跟数据库操作的接口
 */
@Repository
public interface PayeelistBankexecutRepository extends JpaRepository<BzPayeelistBankexecutEntity, Long> {

    // 通过BzPackageIndex状态筛选待执行的银行收款数据。

    @Query(nativeQuery = true, value = "SELECT money_sum as moneySum,total_ity as totalIty,idx.payer_account as payerAccount,package_id as packageId,money FROM `bz_payeelist_bankexecut` list, bz_package_index idx WHERE idx.id = list.package_id  limit 5")
    // 原生SQL方法
    List<IPackageIndexAndExecuteList> findPackageIndexAndExecuteList();
}