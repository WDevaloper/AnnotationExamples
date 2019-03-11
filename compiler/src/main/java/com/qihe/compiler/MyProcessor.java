package com.qihe.compiler;

import com.google.common.collect.ImmutableList;
import com.qihe.lib.MyAnnotation;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;


@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.qihe.lib.MyAnnotation")
public final class MyProcessor extends AbstractProcessor {


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        Collection<? extends Element> annotatedElements = env.getElementsAnnotatedWith(MyAnnotation.class);
        //转换类型
        List<TypeElement> types = new ImmutableList.Builder<TypeElement>()
                .addAll(ElementFilter.typesIn(annotatedElements))
                .build();
        for (TypeElement type : types) {
            processType(type);
        }
        return true;
    }

    private void processType(TypeElement type) {
        String className = generatedClassName(type);
        StringBuffer sb = new StringBuffer();
        sb.append("public class " + type.getSimpleName() + "{");
        sb.append("private int age;");
        sb.append("private String name;");
        sb.append("}");
        String source = sb.toString();
        writeSourceFile(className, source, type);
    }

    private void writeSourceFile(String className, String text, TypeElement originatingType) {
        try {
            JavaFileObject sourceFile = processingEnv.getFiler().createSourceFile(className, originatingType);
            Writer writer = sourceFile.openWriter();
            try {
                writer.write(text);
            } finally {
                writer.close();
            }
        } catch (IOException e) {
        }
    }

    private String generatedClassName(TypeElement type) {
        String name = type.getSimpleName().toString();
        while (type.getEnclosingElement() instanceof TypeElement) {
            type = (TypeElement) type.getEnclosingElement();
            name = type.getSimpleName() + "_" + name;
        }
        String pkg = TypeUtil.packageNameOf(type);
        String dot = pkg.isEmpty() ? "" : ".";
        return pkg + dot + name;
    }
}
