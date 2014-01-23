package br.com.dextraining.service;

public class ServiceFactory {

	@SuppressWarnings("unchecked")
    public static <T> T service(Class<T> clazz) {
		String name = clazz.getName();
		String pack = name.substring(0, name.indexOf(clazz.getSimpleName()));
		try {
			Class<?> forName = Class.forName(pack + "impl." + clazz.getSimpleName() + "Impl");
			return (T) forName.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
