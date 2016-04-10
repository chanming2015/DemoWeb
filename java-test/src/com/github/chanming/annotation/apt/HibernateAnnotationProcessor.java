package com.github.chanming.annotation.apt;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

@SupportedAnnotationTypes(value = {"com.github.chanming.annotation.apt.*"})
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class HibernateAnnotationProcessor extends AbstractProcessor
{

    private Filer filer;
    private Messager messager;

    private int i = 1;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv)
    {
        super.init(processingEnv);
        this.filer = processingEnv.getFiler();
        this.messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv)
    {
        messager.printMessage(Kind.NOTE, "process() is excute...");

        System.out.println("input class is：");
        for (TypeElement t : annotations)
        {
            System.out.println(">>> " + t.getSimpleName());

            Persistent per = t.getAnnotation(Persistent.class);

            if (per != null)
            {
                System.out.println("table name: " + per.table());
            }
            else
            {
                System.out.println("is null");
            }
        }

        return true;
    }
    // G:\gitProject\DemoWeb\java-test>javac -cp bin/ -processor
    // com.github.chanming.annotation.apt.HibernateAnnotationProcessor
    // -d bin/ -s src/com/github/chanming/annotation/apt
    // src/com/github/chanming/annotation/apt/Person.java
}
