package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("중복 오류 테스트")
    void findBeanByTypeDuplicate(){

//         MemberRepository memberRepository= ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,() -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("이름 지정 으로 검색")
    void findBeanByName(){

         MemberRepository memberRepository= ac.getBean("memberRepository1",MemberRepository.class);
        Assertions.assertInstanceOf(MemberRepository.class,memberRepository);
    }

    @Test
    @DisplayName("특정 타입 모드 조회")
    void findAllBeanByType(){
        Map<String,MemberRepository> map = ac.getBeansOfType(MemberRepository.class);
        for (String key: map.keySet()) {
            System.out.println("key : " + key +" : " +map.get(key));
        }
    }
    
    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
