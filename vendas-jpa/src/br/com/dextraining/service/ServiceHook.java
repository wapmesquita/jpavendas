package br.com.dextraining.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import br.com.dextraining.annotation.Loggable;
import br.com.dextraining.annotation.Transaction;
import br.com.dextraining.jpa.EntityManagerFactoryWrapper;

public class ServiceHook implements InvocationHandler {

	private String logMessage;
	private boolean loggable = false;
	private boolean transaction = false;

	@Override
	public Object invoke(Object object, Method method, Object[] args)
			throws Throwable {
		Class<?> clazz = method.getDeclaringClass();

		before(method);

		String name = clazz.getName();
		String pack = name.substring(0, name.indexOf(clazz.getSimpleName()));

		try {
			Object instance = Class.forName(
					pack + "impl." + clazz.getSimpleName() + "Impl")
					.newInstance();
			return method.invoke(instance, args);
		} finally {
			after();
		}
	}

	private void after() {
		if (transaction) {
			System.out.println("===> Commiting transaction");
			EntityManagerFactoryWrapper.commit();
			System.out.println("===> Transaction Commited");
		}
	}

	private void before(Method method) {
		transaction = method.getAnnotation(Transaction.class) != null;

		Loggable logAnnotation = method.getAnnotation(Loggable.class);
		loggable = logAnnotation != null;

		if (loggable) {
			logMessage = logAnnotation.value();
		}

		startTransaction();
		log();

	}

	private void log() {
		if (loggable) {
			System.out.println(logMessage);
		}
	}

	private void startTransaction() {
		if (transaction) {
			System.out.println("===> Starting transaction");
			EntityManagerFactoryWrapper.init();
		}
	}

}
