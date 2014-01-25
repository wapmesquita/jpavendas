package br.com.dextraining.service;

import java.lang.reflect.Proxy;

public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> T service(Class<T> clazz) {
		return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(),
				new Class<?>[] { clazz }, new ServiceHook());
	}

}
