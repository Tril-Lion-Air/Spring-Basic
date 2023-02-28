package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class);
        System.out.println("find prototypeBean1");
        Prototype prototype1 = ac.getBean(Prototype.class);
        System.out.println("find prototypeBean2");
        Prototype prototype2 = ac.getBean(Prototype.class);

        System.out.println("prototype1 = " + prototype1);
        System.out.println("prototype2 = " + prototype2);
        assertThat(prototype1).isNotSameAs(prototype2);

        ac.close();
    }

    @Scope("prototype")
    static class Prototype {
        @PostConstruct
        public void init() {
            System.out.println("Prototype.init");
        }
        @PreDestroy
        public void destroy() {
            System.out.println("Prototype.destroy");
        }
    }
}
