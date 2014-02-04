package br.com.dextraining.service;

import java.lang.reflect.Proxy;

public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> T service(Class<T> clazz) {
		if (!clazz.isInterface()) {
			throw new IllegalArgumentException("Argumento nao permitido");
		}

		String name = clazz.getName();
		String pack = name.substring(0, name.indexOf(clazz.getSimpleName()));

		Object instance;
		try {
			instance = Class.forName(pack + "impl." + clazz.getSimpleName() + "Impl").newInstance();
			return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), new Class<?>[] { clazz }, new ServiceHook(instance));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
