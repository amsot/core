package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름 검색")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService",MemberService.class);
        Assertions.assertInstanceOf(MemberService.class,memberService);
    }

    @Test
    @DisplayName("빈 타입 검색")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertInstanceOf(MemberService.class,memberService);
    }

    @Test
    @DisplayName("구체 타입으로 검색")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService",MemberServiceImpl.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class,memberService);
    }
    @Test
    @DisplayName("빈 이름 검색X")
    void findBeanByNameX(){
//        MemberService memberService = ac.getBean("xxxxx",MemberService.class);
        // assertThrows 오른쪽 로직 실행에서 오류 발생시 왼쪽 Exception이 발생해야 성공으로 출력
        Assertions.assertThrows(NoSuchBeanDefinitionException.class
                ,() -> ac.getBean("xxxxx",MemberService.class));
    }
}
