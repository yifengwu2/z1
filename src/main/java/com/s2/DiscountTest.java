package com.s2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 策略模式
 * 场景（促销折扣引擎）
 * 电商商品结算时，需根据用户等级应用不同折扣策略：
 * - 普通用户：无折扣
 * - VIP：9 折
 * - 黑卡：85 折 + 免运费
 */
@Slf4j
public class DiscountTest {
    public static void main(String[] args) {
        VipUser vipUser = new VipUser();
        System.out.println(vipUser.getStrategy(100));
    }

}

/**
 * 折扣策略
 */
interface Strategy {
    SettlementReceipt getStrategy(double original);
}

class SettlementReceipt {
    //折扣
    private final Double discount;
    //运费
    private final Double freight;
    //优惠券
    private final Boolean coupons;
    //其他
    public final String remark;

    public SettlementReceipt(Double discount, Double freight, Boolean coupons, String remark) {
        this.discount = discount;
        this.freight = freight;
        this.coupons = coupons;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SettlementReceipt{" +
                "discount=" + discount +
                ", freight=" + freight +
                ", coupons=" + coupons +
                ", remark='" + remark + '\'' +
                '}';
    }
}


//普通用户
@Slf4j
class CommandUser implements Strategy {

    @Override
    public SettlementReceipt getStrategy(double original) {
        return new SettlementReceipt(original, 5.0, false, "无赠品");
    }
}

//vip用户
@Slf4j
class VipUser implements Strategy {
    @Override
    public SettlementReceipt getStrategy(double original) {
        return new SettlementReceipt(original * 0.9, 5.0, false, "无赠品");
    }
}

//黑卡
@Slf4j
class BlackCard implements Strategy {
    @Override
    public SettlementReceipt getStrategy(double original) {
        return new SettlementReceipt(original * 0.85, 0.0, true, "优先发货");
    }
}
