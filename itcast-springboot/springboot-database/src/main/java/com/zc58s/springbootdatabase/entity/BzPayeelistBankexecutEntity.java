package com.zc58s.springbootdatabase.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/29 15:17
 * springboot-database
 * com.zc58s.springbootdatabase.entity
 */
@Entity
@Table(name = "bz_payeelist_bankexecut")
public class BzPayeelistBankexecutEntity {
    private Long id;
    private String packageId;
    private BigDecimal money;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "package_id")
    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @Basic
    @Column(name = "money")
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BzPayeelistBankexecutEntity that = (BzPayeelistBankexecutEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(packageId, that.packageId) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageId, money);
    }
}
