package com.example.spring_boot_example.chapter4.exception;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class UrlNotAccessibleFailureAnalyzer extends AbstractFailureAnalyzer<UrlNotAccessibleException> implements BeanFactoryAware, EnvironmentAware {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, UrlNotAccessibleException cause) {
        System.out.println("hello");
        return new FailureAnalysis("Unable to access the URL=" + cause.getUrl(),
                "Validate the URL and ensure it is accessible", cause);
    }

    // If you need access to the BeanFactory or the Environment,
    // your FailureAnalyzer can implement BeanFactoryAware or EnvironmentAware respectively.
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setEnvironment(Environment environment) {
    }
}
