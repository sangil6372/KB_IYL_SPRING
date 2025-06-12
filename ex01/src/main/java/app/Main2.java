package app;

import config.ProjectConfig2;
import domain.Parrot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig2.class);
        Parrot p = context.getBean("miki", Parrot.class);
        System.out.println(p.getName()); } }
