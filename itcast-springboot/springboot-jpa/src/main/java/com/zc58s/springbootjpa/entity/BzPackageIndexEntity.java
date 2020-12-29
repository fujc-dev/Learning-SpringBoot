package com.zc58s.springbootjpa.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:17
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "bz_package_index")
public class BzPackageIndexEntity {
    private Long id;
    private Integer totalIty;
    private String payerAccount;
    private Integer moneySum;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "total_ity")
    public Integer getTotalIty() {
        return totalIty;
    }

    public void setTotalIty(Integer totalIty) {
        this.totalIty = totalIty;
    }

    @Basic
    @Column(name = "payer_account")
    public String getPayerAccount() {
        return payerAccount;
    }

    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    @Basic
    @Column(name = "money_sum")
    public Integer getMoneySum() {
        return moneySum;
    }

    public void setMoneySum(Integer moneySum) {
        this.moneySum = moneySum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BzPackageIndexEntity that = (BzPackageIndexEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(totalIty, that.totalIty) &&
                Objects.equals(payerAccount, that.payerAccount) &&
                Objects.equals(moneySum, that.moneySum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalIty, payerAccount, moneySum);
    }
}
