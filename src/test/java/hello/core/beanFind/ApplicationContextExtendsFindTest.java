package hello.core.beanFind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 오류 발생")
    void findBeanByParentType(){
//        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,() -> ac.getBean(DiscountPolicy.class));

    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 이름 지정으로 해결")
    void findBeanByName(){
        DiscountPolicy discountPolicy = ac.getBean("retaDiscountPolicy",DiscountPolicy.class);
        Assertions.assertInstanceOf(DiscountPolicy.class,discountPolicy);

    }
    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 구체 타입 지정으로 찾기")
    void findSubBeanType(){
       DiscountPolicy discountPolicy = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertInstanceOf(RateDiscountPolicy.class,discountPolicy);

    }
    @Test
    @DisplayName("부모탑일으 모드 조회")
    void findAll(){
        Map<String, DiscountPolicy> map =  ac.getBeansOfType(DiscountPolicy.class);
        for (String key : map.keySet()) {
            System.out.println("key : " + key +" : " + map.get(key));
        }
    }

    @Test
    @DisplayName("부모탑일으 모드 조회 -> Object")
    void findAllObject(){
        Map<String, Object> map =  ac.getBeansOfType(Object.class);
        for (String key : map.keySet()) {
            System.out.println("key : " + key +" : " + map.get(key));
        }
    }


    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy retaDiscountPolicy(){
            return new RateDiscountPolicy();
        }
        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
