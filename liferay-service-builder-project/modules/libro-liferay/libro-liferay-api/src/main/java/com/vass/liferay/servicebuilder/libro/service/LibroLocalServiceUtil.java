/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vass.liferay.servicebuilder.libro.service;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Libro. This utility wraps
 * <code>com.vass.liferay.servicebuilder.libro.service.impl.LibroLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LibroLocalService
 * @generated
 */
@ProviderType
public class LibroLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.vass.liferay.servicebuilder.libro.service.impl.LibroLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addEscritorLibro(
		long escritorId,
		com.vass.liferay.servicebuilder.libro.model.Libro libro) {

		getService().addEscritorLibro(escritorId, libro);
	}

	public static void addEscritorLibro(long escritorId, long libroId) {
		getService().addEscritorLibro(escritorId, libroId);
	}

	public static void addEscritorLibros(
		long escritorId,
		java.util.List<com.vass.liferay.servicebuilder.libro.model.Libro>
			libros) {

		getService().addEscritorLibros(escritorId, libros);
	}

	public static void addEscritorLibros(long escritorId, long[] libroIds) {
		getService().addEscritorLibros(escritorId, libroIds);
	}

	/**
	 * Adds the libro to the database. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was added
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro addLibro(
		com.vass.liferay.servicebuilder.libro.model.Libro libro) {

		return getService().addLibro(libro);
	}

	public static void clearEscritorLibros(long escritorId) {
		getService().clearEscritorLibros(escritorId);
	}

	/**
	 * Creates a new libro with the primary key. Does not add the libro to the database.
	 *
	 * @param libroId the primary key for the new libro
	 * @return the new libro
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro createLibro(
		long libroId) {

		return getService().createLibro(libroId);
	}

	public static void deleteEscritorLibro(
		long escritorId,
		com.vass.liferay.servicebuilder.libro.model.Libro libro) {

		getService().deleteEscritorLibro(escritorId, libro);
	}

	public static void deleteEscritorLibro(long escritorId, long libroId) {
		getService().deleteEscritorLibro(escritorId, libroId);
	}

	public static void deleteEscritorLibros(
		long escritorId,
		java.util.List<com.vass.liferay.servicebuilder.libro.model.Libro>
			libros) {

		getService().deleteEscritorLibros(escritorId, libros);
	}

	public static void deleteEscritorLibros(long escritorId, long[] libroIds) {
		getService().deleteEscritorLibros(escritorId, libroIds);
	}

	/**
	 * Deletes the libro from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was removed
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro deleteLibro(
		com.vass.liferay.servicebuilder.libro.model.Libro libro) {

		return getService().deleteLibro(libro);
	}

	/**
	 * Deletes the libro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro that was removed
	 * @throws PortalException if a libro with the primary key could not be found
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro deleteLibro(
			long libroId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLibro(libroId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.vass.liferay.servicebuilder.libro.model.Libro fetchLibro(
		long libroId) {

		return getService().fetchLibro(libroId);
	}

	/**
	 * Returns the libro matching the UUID and group.
	 *
	 * @param uuid the libro's UUID
	 * @param groupId the primary key of the group
	 * @return the matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro
		fetchLibroByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchLibroByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro> getEscritorLibros(
			long escritorId) {

		return getService().getEscritorLibros(escritorId);
	}

	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro> getEscritorLibros(
			long escritorId, int start, int end) {

		return getService().getEscritorLibros(escritorId, start, end);
	}

	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro> getEscritorLibros(
			long escritorId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.vass.liferay.servicebuilder.libro.model.Libro>
					orderByComparator) {

		return getService().getEscritorLibros(
			escritorId, start, end, orderByComparator);
	}

	public static int getEscritorLibrosCount(long escritorId) {
		return getService().getEscritorLibrosCount(escritorId);
	}

	/**
	 * Returns the escritorIds of the escritors associated with the libro.
	 *
	 * @param libroId the libroId of the libro
	 * @return long[] the escritorIds of escritors associated with the libro
	 */
	public static long[] getEscritorPrimaryKeys(long libroId) {
		return getService().getEscritorPrimaryKeys(libroId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the libro with the primary key.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro
	 * @throws PortalException if a libro with the primary key could not be found
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro getLibro(
			long libroId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLibro(libroId);
	}

	/**
	 * Returns the libro matching the UUID and group.
	 *
	 * @param uuid the libro's UUID
	 * @param groupId the primary key of the group
	 * @return the matching libro
	 * @throws PortalException if a matching libro could not be found
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro
			getLibroByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLibroByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the libros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @return the range of libros
	 */
	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro> getLibros(
			int start, int end) {

		return getService().getLibros(start, end);
	}

	/**
	 * Returns all the libros matching the UUID and company.
	 *
	 * @param uuid the UUID of the libros
	 * @param companyId the primary key of the company
	 * @return the matching libros, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro>
			getLibrosByUuidAndCompanyId(String uuid, long companyId) {

		return getService().getLibrosByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of libros matching the UUID and company.
	 *
	 * @param uuid the UUID of the libros
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching libros, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.vass.liferay.servicebuilder.libro.model.Libro>
			getLibrosByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.vass.liferay.servicebuilder.libro.model.Libro>
						orderByComparator) {

		return getService().getLibrosByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of libros.
	 *
	 * @return the number of libros
	 */
	public static int getLibrosCount() {
		return getService().getLibrosCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static boolean hasEscritorLibro(long escritorId, long libroId) {
		return getService().hasEscritorLibro(escritorId, libroId);
	}

	public static boolean hasEscritorLibros(long escritorId) {
		return getService().hasEscritorLibros(escritorId);
	}

	public static void setEscritorLibros(long escritorId, long[] libroIds) {
		getService().setEscritorLibros(escritorId, libroIds);
	}

	/**
	 * Updates the libro in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was updated
	 */
	public static com.vass.liferay.servicebuilder.libro.model.Libro updateLibro(
		com.vass.liferay.servicebuilder.libro.model.Libro libro) {

		return getService().updateLibro(libro);
	}

	public static LibroLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LibroLocalService, LibroLocalService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LibroLocalService.class);

		ServiceTracker<LibroLocalService, LibroLocalService> serviceTracker =
			new ServiceTracker<LibroLocalService, LibroLocalService>(
				bundle.getBundleContext(), LibroLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}