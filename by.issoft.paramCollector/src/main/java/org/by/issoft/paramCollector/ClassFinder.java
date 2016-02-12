package org.by.issoft.paramCollector;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Load Classes from package
 * 
 * @author AlexeyLysenok
 *
 */

public final class ClassFinder {


	public final static List<Class<?>> find(final String scannedPackage) {
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		final String scannedPath = scannedPackage.replace('.', '/');
		final Enumeration<URL> resources;
		try {
			resources = classLoader.getResources(scannedPath);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					String.format("Unable to get resources from path '%s'. Are you sure the given '%s' package exists?",
							scannedPath, scannedPackage),
					e);
		}
		final List<Class<?>> classes = new LinkedList<Class<?>>();
		while (resources.hasMoreElements()) {
			final File file = new File(resources.nextElement().getFile());
			classes.addAll(find(file, scannedPackage));
		}
		return classes;
	}

	private final static List<Class<?>> find(final File file, final String scannedPackage) {
		final List<Class<?>> classes = new LinkedList<Class<?>>();
		if (file.isDirectory()) {
			for (File nestedFile : file.listFiles()) {
				classes.addAll(find(nestedFile, scannedPackage));
			}
		} else if (file.getName().endsWith(".class") && !file.getName().contains("$")) {

			final int beginIndex = 0;
			final int endIndex = file.getName().length() - ".class".length();
			final String className = file.getName().substring(beginIndex, endIndex);
			try {
				final String resource = scannedPackage + '.' + className;
				classes.add(Class.forName(resource));
			} catch (ClassNotFoundException ignore) {
			}
		}
		return classes;
	}

	public static void main(String[] args) {

		String packageName = "org.by.issoft.paramCollector.paramObtainers";

		List<Class<?>> classes = find(packageName);
		for (Class<?> c : classes) {
			System.out.println(c);
		}
	}

}