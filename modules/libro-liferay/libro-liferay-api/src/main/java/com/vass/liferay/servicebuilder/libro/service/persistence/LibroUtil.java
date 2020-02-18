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

package com.vass.liferay.servicebuilder.libro.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.vass.liferay.servicebuilder.libro.model.Libro;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the libro service. This utility wraps <code>com.vass.liferay.servicebuilder.libro.service.persistence.impl.LibroPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LibroPersistence
 * @generated
 */
@ProviderType
public class LibroUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(Libro libro) {
		getPersistence().clearCache(libro);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, Libro> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Libro> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Libro> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Libro> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Libro update(Libro libro) {
		return getPersistence().update(libro);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Libro update(Libro libro, ServiceContext serviceContext) {
		return getPersistence().update(libro, serviceContext);
	}

	/**
	 * Returns all the libros where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching libros
	 */
	public static List<Libro> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the libros where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @return the range of matching libros
	 */
	public static List<Libro> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the libros where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByUuid(String, int, int, OrderByComparator)}
	 * @param uuid the uuid
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching libros
	 */
	@Deprecated
	public static List<Libro> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the libros where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching libros
	 */
	public static List<Libro> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByUuid_First(
			String uuid, OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByUuid_First(
		String uuid, OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByUuid_Last(
			String uuid, OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByUuid_Last(
		String uuid, OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the libros before and after the current libro in the ordered set where uuid = &#63;.
	 *
	 * @param libroId the primary key of the current libro
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	public static Libro[] findByUuid_PrevAndNext(
			long libroId, String uuid,
			OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_PrevAndNext(
			libroId, uuid, orderByComparator);
	}

	/**
	 * Removes all the libros where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of libros where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching libros
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the libro where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLibroException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByUUID_G(String uuid, long groupId)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the libro where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #fetchByUUID_G(String,long)}
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Deprecated
	public static Libro fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Returns the libro where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Removes the libro where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the libro that was removed
	 */
	public static Libro removeByUUID_G(String uuid, long groupId)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of libros where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching libros
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the libros where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching libros
	 */
	public static List<Libro> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the libros where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @return the range of matching libros
	 */
	public static List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the libros where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByUuid_C(String,long, int, int, OrderByComparator)}
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching libros
	 */
	@Deprecated
	public static List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the libros where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching libros
	 */
	public static List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the libros before and after the current libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param libroId the primary key of the current libro
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	public static Libro[] findByUuid_C_PrevAndNext(
			long libroId, String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByUuid_C_PrevAndNext(
			libroId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the libros where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of libros where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching libros
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the libros where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @return the matching libros
	 */
	public static List<Libro> findByTitulo(String titulo) {
		return getPersistence().findByTitulo(titulo);
	}

	/**
	 * Returns a range of all the libros where titulo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param titulo the titulo
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @return the range of matching libros
	 */
	public static List<Libro> findByTitulo(String titulo, int start, int end) {
		return getPersistence().findByTitulo(titulo, start, end);
	}

	/**
	 * Returns an ordered range of all the libros where titulo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findByTitulo(String, int, int, OrderByComparator)}
	 * @param titulo the titulo
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching libros
	 */
	@Deprecated
	public static List<Libro> findByTitulo(
		String titulo, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return getPersistence().findByTitulo(
			titulo, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the libros where titulo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param titulo the titulo
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching libros
	 */
	public static List<Libro> findByTitulo(
		String titulo, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().findByTitulo(
			titulo, start, end, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByTitulo_First(
			String titulo, OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByTitulo_First(titulo, orderByComparator);
	}

	/**
	 * Returns the first libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByTitulo_First(
		String titulo, OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByTitulo_First(titulo, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	public static Libro findByTitulo_Last(
			String titulo, OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByTitulo_Last(titulo, orderByComparator);
	}

	/**
	 * Returns the last libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	public static Libro fetchByTitulo_Last(
		String titulo, OrderByComparator<Libro> orderByComparator) {

		return getPersistence().fetchByTitulo_Last(titulo, orderByComparator);
	}

	/**
	 * Returns the libros before and after the current libro in the ordered set where titulo = &#63;.
	 *
	 * @param libroId the primary key of the current libro
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	public static Libro[] findByTitulo_PrevAndNext(
			long libroId, String titulo,
			OrderByComparator<Libro> orderByComparator)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByTitulo_PrevAndNext(
			libroId, titulo, orderByComparator);
	}

	/**
	 * Removes all the libros where titulo = &#63; from the database.
	 *
	 * @param titulo the titulo
	 */
	public static void removeByTitulo(String titulo) {
		getPersistence().removeByTitulo(titulo);
	}

	/**
	 * Returns the number of libros where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @return the number of matching libros
	 */
	public static int countByTitulo(String titulo) {
		return getPersistence().countByTitulo(titulo);
	}

	/**
	 * Caches the libro in the entity cache if it is enabled.
	 *
	 * @param libro the libro
	 */
	public static void cacheResult(Libro libro) {
		getPersistence().cacheResult(libro);
	}

	/**
	 * Caches the libros in the entity cache if it is enabled.
	 *
	 * @param libros the libros
	 */
	public static void cacheResult(List<Libro> libros) {
		getPersistence().cacheResult(libros);
	}

	/**
	 * Creates a new libro with the primary key. Does not add the libro to the database.
	 *
	 * @param libroId the primary key for the new libro
	 * @return the new libro
	 */
	public static Libro create(long libroId) {
		return getPersistence().create(libroId);
	}

	/**
	 * Removes the libro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro that was removed
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	public static Libro remove(long libroId)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().remove(libroId);
	}

	public static Libro updateImpl(Libro libro) {
		return getPersistence().updateImpl(libro);
	}

	/**
	 * Returns the libro with the primary key or throws a <code>NoSuchLibroException</code> if it could not be found.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	public static Libro findByPrimaryKey(long libroId)
		throws com.vass.liferay.servicebuilder.libro.exception.
			NoSuchLibroException {

		return getPersistence().findByPrimaryKey(libroId);
	}

	/**
	 * Returns the libro with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro, or <code>null</code> if a libro with the primary key could not be found
	 */
	public static Libro fetchByPrimaryKey(long libroId) {
		return getPersistence().fetchByPrimaryKey(libroId);
	}

	/**
	 * Returns all the libros.
	 *
	 * @return the libros
	 */
	public static List<Libro> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the libros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @return the range of libros
	 */
	public static List<Libro> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the libros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @deprecated As of Mueller (7.2.x), replaced by {@link #findAll(int, int, OrderByComparator)}
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of libros
	 */
	@Deprecated
	public static List<Libro> findAll(
		int start, int end, OrderByComparator<Libro> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns an ordered range of all the libros.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of libros
	 * @param end the upper bound of the range of libros (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of libros
	 */
	public static List<Libro> findAll(
		int start, int end, OrderByComparator<Libro> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Removes all the libros from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of libros.
	 *
	 * @return the number of libros
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of escritors associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @return long[] of the primaryKeys of escritors associated with the libro
	 */
	public static long[] getEscritorPrimaryKeys(long pk) {
		return getPersistence().getEscritorPrimaryKeys(pk);
	}

	/**
	 * Returns all the libro associated with the escritor.
	 *
	 * @param pk the primary key of the escritor
	 * @return the libros associated with the escritor
	 */
	public static List<Libro> getEscritorLibros(long pk) {
		return getPersistence().getEscritorLibros(pk);
	}

	/**
	 * Returns all the libro associated with the escritor.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the escritor
	 * @param start the lower bound of the range of escritors
	 * @param end the upper bound of the range of escritors (not inclusive)
	 * @return the range of libros associated with the escritor
	 */
	public static List<Libro> getEscritorLibros(long pk, int start, int end) {
		return getPersistence().getEscritorLibros(pk, start, end);
	}

	/**
	 * Returns all the libro associated with the escritor.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>LibroModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param pk the primary key of the escritor
	 * @param start the lower bound of the range of escritors
	 * @param end the upper bound of the range of escritors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of libros associated with the escritor
	 */
	public static List<Libro> getEscritorLibros(
		long pk, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return getPersistence().getEscritorLibros(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of escritors associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @return the number of escritors associated with the libro
	 */
	public static int getEscritorsSize(long pk) {
		return getPersistence().getEscritorsSize(pk);
	}

	/**
	 * Returns <code>true</code> if the escritor is associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 * @return <code>true</code> if the escritor is associated with the libro; <code>false</code> otherwise
	 */
	public static boolean containsEscritor(long pk, long escritorPK) {
		return getPersistence().containsEscritor(pk, escritorPK);
	}

	/**
	 * Returns <code>true</code> if the libro has any escritors associated with it.
	 *
	 * @param pk the primary key of the libro to check for associations with escritors
	 * @return <code>true</code> if the libro has any escritors associated with it; <code>false</code> otherwise
	 */
	public static boolean containsEscritors(long pk) {
		return getPersistence().containsEscritors(pk);
	}

	/**
	 * Adds an association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 */
	public static void addEscritor(long pk, long escritorPK) {
		getPersistence().addEscritor(pk, escritorPK);
	}

	/**
	 * Adds an association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritor the escritor
	 */
	public static void addEscritor(
		long pk,
		com.vass.liferay.servicebuilder.libro.model.Escritor escritor) {

		getPersistence().addEscritor(pk, escritor);
	}

	/**
	 * Adds an association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors
	 */
	public static void addEscritors(long pk, long[] escritorPKs) {
		getPersistence().addEscritors(pk, escritorPKs);
	}

	/**
	 * Adds an association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors
	 */
	public static void addEscritors(
		long pk,
		List<com.vass.liferay.servicebuilder.libro.model.Escritor> escritors) {

		getPersistence().addEscritors(pk, escritors);
	}

	/**
	 * Clears all associations between the libro and its escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro to clear the associated escritors from
	 */
	public static void clearEscritors(long pk) {
		getPersistence().clearEscritors(pk);
	}

	/**
	 * Removes the association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 */
	public static void removeEscritor(long pk, long escritorPK) {
		getPersistence().removeEscritor(pk, escritorPK);
	}

	/**
	 * Removes the association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritor the escritor
	 */
	public static void removeEscritor(
		long pk,
		com.vass.liferay.servicebuilder.libro.model.Escritor escritor) {

		getPersistence().removeEscritor(pk, escritor);
	}

	/**
	 * Removes the association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors
	 */
	public static void removeEscritors(long pk, long[] escritorPKs) {
		getPersistence().removeEscritors(pk, escritorPKs);
	}

	/**
	 * Removes the association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors
	 */
	public static void removeEscritors(
		long pk,
		List<com.vass.liferay.servicebuilder.libro.model.Escritor> escritors) {

		getPersistence().removeEscritors(pk, escritors);
	}

	/**
	 * Sets the escritors associated with the libro, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors to be associated with the libro
	 */
	public static void setEscritors(long pk, long[] escritorPKs) {
		getPersistence().setEscritors(pk, escritorPKs);
	}

	/**
	 * Sets the escritors associated with the libro, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors to be associated with the libro
	 */
	public static void setEscritors(
		long pk,
		List<com.vass.liferay.servicebuilder.libro.model.Escritor> escritors) {

		getPersistence().setEscritors(pk, escritors);
	}

	public static LibroPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LibroPersistence, LibroPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LibroPersistence.class);

		ServiceTracker<LibroPersistence, LibroPersistence> serviceTracker =
			new ServiceTracker<LibroPersistence, LibroPersistence>(
				bundle.getBundleContext(), LibroPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}