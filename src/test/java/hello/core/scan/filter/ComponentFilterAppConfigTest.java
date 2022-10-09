package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class ComponentFilterAppConfigTest {

    /*
    * FilterType 옵션
    * Annotation 기본값, 어노테이션을 인식해서 동작
    * Assignable_Type 지정한 타입과 자식 타입을 인식해서 동작 (클래스 지정 가능)
    * Aspectj Aspect 패턴 사용
    * Regex 정규식
    * Custom 'TypeFilter' 라는 인터페이스를 구현해서 처리
    * 
    * 옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 사용하는 것을 권장, 선호 하는편
    * */

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
//        BeanB beanB = ac.getBean("beanB",BeanB.class);
        Assertions.assertThrows(NoSuchBeanDefinitionException.class,() ->ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
