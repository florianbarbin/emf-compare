/*******************************************************************************
 * Copyright (c) 2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.compare.match;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.compare.utils.IEqualityHelper;
import org.eclipse.emf.ecore.EObject;

/**
 * @author <a href="mailto:mikael.barbero@obeo.fr">Mikael Barbero</a>
 */
public class DefaultEqualityHelperFactory implements IEqualityHelperFactory {

	private final CacheBuilder<Object, Object> cacheBuilder;

	public DefaultEqualityHelperFactory() {
		this(CacheBuilder.newBuilder());
	}

	public DefaultEqualityHelperFactory(CacheBuilder<Object, Object> cacheBuilder) {
		this.cacheBuilder = cacheBuilder;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.emf.compare.match.IEqualityHelperFactory#createEqualityHelper()
	 */
	public IEqualityHelper createEqualityHelper() {
		Cache<EObject, URI> cache = EqualityHelper.createDefaultCache(cacheBuilder);
		IEqualityHelper equalityHelper = new EqualityHelper(cache);
		return equalityHelper;
	}
}
