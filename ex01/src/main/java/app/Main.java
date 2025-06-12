package app;

import config.ProjectConfig;
import config.ProjectConfig3;
import domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig3.class);
        Parrot p = context.getBean(Parrot.class);
        System.out.println(p);
        System.out.println(p.getName());
    }
}
