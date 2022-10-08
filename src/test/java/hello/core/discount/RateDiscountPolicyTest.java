package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("한글")
    void discount() {

        Member member = new Member(1L, "VIP", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member,10000);
        assertEquals(discount,1000);

    }
}