package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", // 탐색 시작 위치 지정
        excludeFilters =  @ComponentScan.Filter(type = FilterType.ANNOTATION , classes =  Configuration.class) // 제외 클래스 지정
)
public class AutoAppConfig {

}
