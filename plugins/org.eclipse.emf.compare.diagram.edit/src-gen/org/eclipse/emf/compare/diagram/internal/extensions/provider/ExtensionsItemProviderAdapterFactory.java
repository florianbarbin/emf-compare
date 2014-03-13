/**
 * Copyright (c) 2013 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 */
package org.eclipse.emf.compare.diagram.internal.extensions.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.compare.diagram.internal.extensions.util.ExtensionsAdapterFactory;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExtensionsItemProviderAdapterFactory extends ExtensionsAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtensionsItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IItemColorProvider.class);
		supportedTypes.add(IItemFontProvider.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.Show} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ShowItemProvider showItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.Show}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createShowAdapter() {
		if (showItemProvider == null) {
			showItemProvider = new ShowItemProvider(this);
		}

		return showItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.Hide} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HideItemProvider hideItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.Hide}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHideAdapter() {
		if (hideItemProvider == null) {
			hideItemProvider = new HideItemProvider(this);
		}

		return hideItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.NodeChange} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeChangeItemProvider nodeChangeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.NodeChange}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodeChangeAdapter() {
		if (nodeChangeItemProvider == null) {
			nodeChangeItemProvider = new NodeChangeItemProvider(this);
		}

		return nodeChangeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.CoordinatesChange} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoordinatesChangeItemProvider coordinatesChangeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.CoordinatesChange}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCoordinatesChangeAdapter() {
		if (coordinatesChangeItemProvider == null) {
			coordinatesChangeItemProvider = new CoordinatesChangeItemProvider(this);
		}

		return coordinatesChangeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.EdgeChange} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeChangeItemProvider edgeChangeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.EdgeChange}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEdgeChangeAdapter() {
		if (edgeChangeItemProvider == null) {
			edgeChangeItemProvider = new EdgeChangeItemProvider(this);
		}

		return edgeChangeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.compare.diagram.internal.extensions.DiagramChange} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramChangeItemProvider diagramChangeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.compare.diagram.internal.extensions.DiagramChange}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDiagramChangeAdapter() {
		if (diagramChangeItemProvider == null) {
			diagramChangeItemProvider = new DiagramChangeItemProvider(this);
		}

		return diagramChangeItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (showItemProvider != null) showItemProvider.dispose();
		if (hideItemProvider != null) hideItemProvider.dispose();
		if (nodeChangeItemProvider != null) nodeChangeItemProvider.dispose();
		if (coordinatesChangeItemProvider != null) coordinatesChangeItemProvider.dispose();
		if (edgeChangeItemProvider != null) edgeChangeItemProvider.dispose();
		if (diagramChangeItemProvider != null) diagramChangeItemProvider.dispose();
	}

}
