package br.com.dextraining.service;


public class ServiceFactory {

	@SuppressWarnings("unchecked")
	public static <T> T service(Class<T> clazz) {
		String name = clazz.getName();
		String pack = name.substring(0, name.indexOf(clazz.getSimpleName()));

		try {
			return (T) Class.forName(pack + "impl." + clazz.getSimpleName() + "Impl").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
