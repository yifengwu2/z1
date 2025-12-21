package com.s2;

import lombok.extern.slf4j.Slf4j;

import java.sql.Statement;

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
        CommandUser commandUser = new CommandUser(100);
        log.debug("{}", commandUser.getSum());
        VipUser vipUser = new VipUser(100);
        log.debug("{}", vipUser.getSum());
    }

}

/**
 * 折扣策略
 */
interface Strategy {
    //价格折扣
    Double disCountStrategy();

    //运费策略
    Double freightStrategy();

    //营销动作（是否发优惠券）
    boolean isSendCoupons();
}


//普通用户
@Slf4j
class CommandUser implements Strategy {
    private int price;

    public CommandUser(int price) {
        this.price = price;
    }

    @Override
    public Double disCountStrategy() {
        log.debug("普通用户原本价格{}->折扣后价格{}", price, price);
        return (double) price;
    }

    @Override
    public Double freightStrategy() {
        log.debug("默认运费{}元", 5.0);
        return 5.0;
    }

    @Override
    public boolean isSendCoupons() {
        log.debug("普通用户没有优惠券");
        return false;
    }

    public Double getSum() {
        log.debug("{}", isSendCoupons());
        double count = disCountStrategy() + freightStrategy();
        log.debug("用户总共价格{}", count);
        return count;
    }

}

//vip用户
@Slf4j
class VipUser implements Strategy {
    private int price;

    public VipUser(int price) {
        this.price = price;
    }

    @Override
    public Double disCountStrategy() {
        double count = price * 0.9;
        log.debug("Vip用户原本价格{}->折扣后价格{}", price, count);
        return count;
    }

    @Override
    public Double freightStrategy() {
        log.debug("默认运费{}元", 5.0);
        return 5.0;
    }

    @Override
    public boolean isSendCoupons() {
        log.debug("vip用户没有优惠券");
        return false;
    }

    public Double getSum() {
        log.debug("{}", isSendCoupons());
        return disCountStrategy() + freightStrategy();
    }
}

//黑卡
@Slf4j
class BlackCard implements Strategy {
    private int price;

    public BlackCard(int price) {
        this.price = price;
    }

    @Override
    public Double disCountStrategy() {
        double v = price * 0.85;
        log.debug("黑卡用户原本价格{}->折扣后价格{}", price, v);
        return v;
    }

    @Override
    public Double freightStrategy() {
        log.debug("默认运费{}元", 0.0);
        return 0.0;
    }

    @Override
    public boolean isSendCoupons() {
        log.debug("黑卡用户送优惠券，优先发货");
        return true;
    }

    public Double getSum() {
        log.debug("{}", isSendCoupons());
        return disCountStrategy() + freightStrategy();
    }

}
