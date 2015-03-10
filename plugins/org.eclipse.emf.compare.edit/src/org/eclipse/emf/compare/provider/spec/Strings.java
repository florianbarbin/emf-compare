/*******************************************************************************
 * Copyright (c) 2012, 2015 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *     Philip Langer - adds method removeLineBreaks(String)
 *******************************************************************************/
package org.eclipse.emf.compare.provider.spec;

/**
 * Utility class for {@link String}s objects.
 * 
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public final class Strings {

	/**
	 * Private constructor to avoid instantiation.
	 */
	private Strings() {
	}

	/**
	 * Shorten the given {@code original} string and append the given {@code suffix} if it is longest than the
	 * defined {@code max} length minus the length of the {@code suffix}.
	 * <p>
	 * The returned String length will always be in {@code Math.min(original.length, max)}.
	 * 
	 * @param original
	 *            the original string to elide if necessary.
	 * @param max
	 *            the maximum length of the returned string.
	 * @param suffix
	 *            the suffix to append in case the original String is too long.
	 * @return the elided string or the original string.
	 */
	public static String elide(String original, int max, String suffix) {
		if (original.length() > max) {
			String elided = original.substring(0, max - suffix.length());
			return elided + suffix;
		}
		return original;
	}

	/**
	 * Removes line-breaks (\r\n or \r or \n) from the given {@code original} and replaces it with blanks.
	 * 
	 * @param original
	 *            the original string to remove line breaks from.
	 * @return the string without line breaks but blanks instead.
	 */
	public static String removeLineBreaks(String original) {
		return original.replaceAll("\\r\\n|\\r|\\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
