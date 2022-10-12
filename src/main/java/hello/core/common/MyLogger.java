package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// proxyMode를 사용하면 처음 스프링빈 등록시 가짜 클래스 등록
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        System.out.println("["+uuid+"] ["+requestUrl+"] message = " + message);
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] create" + this);
    }
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] close" + this);
    }
}
