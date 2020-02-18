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

package com.vass.liferay.servicebuilder.libro.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.service.persistence.impl.TableMapper;
import com.liferay.portal.kernel.service.persistence.impl.TableMapperFactory;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import com.vass.liferay.servicebuilder.libro.exception.NoSuchLibroException;
import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.model.Libro;
import com.vass.liferay.servicebuilder.libro.model.impl.LibroImpl;
import com.vass.liferay.servicebuilder.libro.model.impl.LibroModelImpl;
import com.vass.liferay.servicebuilder.libro.service.persistence.LibroPersistence;
import com.vass.liferay.servicebuilder.libro.service.persistence.impl.constants.LIBROPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the libro service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = LibroPersistence.class)
@ProviderType
public class LibroPersistenceImpl
	extends BasePersistenceImpl<Libro> implements LibroPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LibroUtil</code> to access the libro persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LibroImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the libros where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching libros
	 */
	@Override
	public List<Libro> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Libro> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<Libro> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return findByUuid(uuid, start, end, orderByComparator);
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
	@Override
	public List<Libro> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid;
			finderArgs = new Object[] {uuid};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<Libro> list = (List<Libro>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Libro libro : list) {
				if (!uuid.equals(libro.getUuid())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LibroModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	@Override
	public Libro findByUuid_First(
			String uuid, OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByUuid_First(uuid, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByUuid_First(
		String uuid, OrderByComparator<Libro> orderByComparator) {

		List<Libro> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	@Override
	public Libro findByUuid_Last(
			String uuid, OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByUuid_Last(uuid, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByUuid_Last(
		String uuid, OrderByComparator<Libro> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<Libro> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Libro[] findByUuid_PrevAndNext(
			long libroId, String uuid,
			OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		uuid = Objects.toString(uuid, "");

		Libro libro = findByPrimaryKey(libroId);

		Session session = null;

		try {
			session = openSession();

			Libro[] array = new LibroImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, libro, uuid, orderByComparator, true);

			array[1] = libro;

			array[2] = getByUuid_PrevAndNext(
				session, libro, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Libro getByUuid_PrevAndNext(
		Session session, Libro libro, String uuid,
		OrderByComparator<Libro> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LIBRO_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LibroModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(libro)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Libro> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the libros where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (Libro libro :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(libro);
		}
	}

	/**
	 * Returns the number of libros where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching libros
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 = "libro.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(libro.uuid IS NULL OR libro.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the libro where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchLibroException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	@Override
	public Libro findByUUID_G(String uuid, long groupId)
		throws NoSuchLibroException {

		Libro libro = fetchByUUID_G(uuid, groupId);

		if (libro == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchLibroException(msg.toString());
		}

		return libro;
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
	@Override
	public Libro fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the libro where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = new Object[] {uuid, groupId};

		Object result = finderCache.getResult(
			_finderPathFetchByUUID_G, finderArgs, this);

		if (result instanceof Libro) {
			Libro libro = (Libro)result;

			if (!Objects.equals(uuid, libro.getUuid()) ||
				(groupId != libro.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<Libro> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(
						_finderPathFetchByUUID_G, finderArgs, list);
				}
				else {
					Libro libro = list.get(0);

					result = libro;

					cacheResult(libro);
				}
			}
			catch (Exception e) {
				finderCache.removeResult(_finderPathFetchByUUID_G, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (Libro)result;
		}
	}

	/**
	 * Removes the libro where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the libro that was removed
	 */
	@Override
	public Libro removeByUUID_G(String uuid, long groupId)
		throws NoSuchLibroException {

		Libro libro = findByUUID_G(uuid, groupId);

		return remove(libro);
	}

	/**
	 * Returns the number of libros where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching libros
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"libro.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(libro.uuid IS NULL OR libro.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"libro.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the libros where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching libros
	 */
	@Override
	public List<Libro> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return findByUuid_C(uuid, companyId, start, end, orderByComparator);
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
	@Override
	public List<Libro> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		uuid = Objects.toString(uuid, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByUuid_C;
			finderArgs = new Object[] {uuid, companyId};
		}
		else {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<Libro> list = (List<Libro>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Libro libro : list) {
				if (!uuid.equals(libro.getUuid()) ||
					(companyId != libro.getCompanyId())) {

					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LibroModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public Libro findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByUuid_C_First(uuid, companyId, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the first libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<Libro> orderByComparator) {

		List<Libro> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Libro findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByUuid_C_Last(uuid, companyId, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the last libro in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<Libro> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<Libro> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Libro[] findByUuid_C_PrevAndNext(
			long libroId, String uuid, long companyId,
			OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		uuid = Objects.toString(uuid, "");

		Libro libro = findByPrimaryKey(libroId);

		Session session = null;

		try {
			session = openSession();

			Libro[] array = new LibroImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, libro, uuid, companyId, orderByComparator, true);

			array[1] = libro;

			array[2] = getByUuid_C_PrevAndNext(
				session, libro, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Libro getByUuid_C_PrevAndNext(
		Session session, Libro libro, String uuid, long companyId,
		OrderByComparator<Libro> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_LIBRO_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LibroModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(libro)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Libro> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the libros where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (Libro libro :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(libro);
		}
	}

	/**
	 * Returns the number of libros where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching libros
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LIBRO_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"libro.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(libro.uuid IS NULL OR libro.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"libro.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByTitulo;
	private FinderPath _finderPathWithoutPaginationFindByTitulo;
	private FinderPath _finderPathCountByTitulo;

	/**
	 * Returns all the libros where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @return the matching libros
	 */
	@Override
	public List<Libro> findByTitulo(String titulo) {
		return findByTitulo(titulo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Libro> findByTitulo(String titulo, int start, int end) {
		return findByTitulo(titulo, start, end, null);
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
	@Override
	public List<Libro> findByTitulo(
		String titulo, int start, int end,
		OrderByComparator<Libro> orderByComparator, boolean useFinderCache) {

		return findByTitulo(titulo, start, end, orderByComparator);
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
	@Override
	public List<Libro> findByTitulo(
		String titulo, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		titulo = Objects.toString(titulo, "");

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindByTitulo;
			finderArgs = new Object[] {titulo};
		}
		else {
			finderPath = _finderPathWithPaginationFindByTitulo;
			finderArgs = new Object[] {titulo, start, end, orderByComparator};
		}

		List<Libro> list = (List<Libro>)finderCache.getResult(
			finderPath, finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Libro libro : list) {
				if (!titulo.equals(libro.getTitulo())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LIBRO_WHERE);

			boolean bindTitulo = false;

			if (titulo.isEmpty()) {
				query.append(_FINDER_COLUMN_TITULO_TITULO_3);
			}
			else {
				bindTitulo = true;

				query.append(_FINDER_COLUMN_TITULO_TITULO_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else if (pagination) {
				query.append(LibroModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitulo) {
					qPos.add(titulo);
				}

				if (!pagination) {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	@Override
	public Libro findByTitulo_First(
			String titulo, OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByTitulo_First(titulo, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("titulo=");
		msg.append(titulo);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the first libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByTitulo_First(
		String titulo, OrderByComparator<Libro> orderByComparator) {

		List<Libro> list = findByTitulo(titulo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro
	 * @throws NoSuchLibroException if a matching libro could not be found
	 */
	@Override
	public Libro findByTitulo_Last(
			String titulo, OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		Libro libro = fetchByTitulo_Last(titulo, orderByComparator);

		if (libro != null) {
			return libro;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("titulo=");
		msg.append(titulo);

		msg.append("}");

		throw new NoSuchLibroException(msg.toString());
	}

	/**
	 * Returns the last libro in the ordered set where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchByTitulo_Last(
		String titulo, OrderByComparator<Libro> orderByComparator) {

		int count = countByTitulo(titulo);

		if (count == 0) {
			return null;
		}

		List<Libro> list = findByTitulo(
			titulo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public Libro[] findByTitulo_PrevAndNext(
			long libroId, String titulo,
			OrderByComparator<Libro> orderByComparator)
		throws NoSuchLibroException {

		titulo = Objects.toString(titulo, "");

		Libro libro = findByPrimaryKey(libroId);

		Session session = null;

		try {
			session = openSession();

			Libro[] array = new LibroImpl[3];

			array[0] = getByTitulo_PrevAndNext(
				session, libro, titulo, orderByComparator, true);

			array[1] = libro;

			array[2] = getByTitulo_PrevAndNext(
				session, libro, titulo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Libro getByTitulo_PrevAndNext(
		Session session, Libro libro, String titulo,
		OrderByComparator<Libro> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LIBRO_WHERE);

		boolean bindTitulo = false;

		if (titulo.isEmpty()) {
			query.append(_FINDER_COLUMN_TITULO_TITULO_3);
		}
		else {
			bindTitulo = true;

			query.append(_FINDER_COLUMN_TITULO_TITULO_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LibroModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindTitulo) {
			qPos.add(titulo);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(libro)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<Libro> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the libros where titulo = &#63; from the database.
	 *
	 * @param titulo the titulo
	 */
	@Override
	public void removeByTitulo(String titulo) {
		for (Libro libro :
				findByTitulo(
					titulo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(libro);
		}
	}

	/**
	 * Returns the number of libros where titulo = &#63;.
	 *
	 * @param titulo the titulo
	 * @return the number of matching libros
	 */
	@Override
	public int countByTitulo(String titulo) {
		titulo = Objects.toString(titulo, "");

		FinderPath finderPath = _finderPathCountByTitulo;

		Object[] finderArgs = new Object[] {titulo};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LIBRO_WHERE);

			boolean bindTitulo = false;

			if (titulo.isEmpty()) {
				query.append(_FINDER_COLUMN_TITULO_TITULO_3);
			}
			else {
				bindTitulo = true;

				query.append(_FINDER_COLUMN_TITULO_TITULO_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindTitulo) {
					qPos.add(titulo);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TITULO_TITULO_2 =
		"libro.titulo = ?";

	private static final String _FINDER_COLUMN_TITULO_TITULO_3 =
		"(libro.titulo IS NULL OR libro.titulo = '')";

	public LibroPersistenceImpl() {
		setModelClass(Libro.class);

		setModelImplClass(LibroImpl.class);
		setModelPKClass(long.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);
	}

	/**
	 * Caches the libro in the entity cache if it is enabled.
	 *
	 * @param libro the libro
	 */
	@Override
	public void cacheResult(Libro libro) {
		entityCache.putResult(
			entityCacheEnabled, LibroImpl.class, libro.getPrimaryKey(), libro);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {libro.getUuid(), libro.getGroupId()}, libro);

		libro.resetOriginalValues();
	}

	/**
	 * Caches the libros in the entity cache if it is enabled.
	 *
	 * @param libros the libros
	 */
	@Override
	public void cacheResult(List<Libro> libros) {
		for (Libro libro : libros) {
			if (entityCache.getResult(
					entityCacheEnabled, LibroImpl.class,
					libro.getPrimaryKey()) == null) {

				cacheResult(libro);
			}
			else {
				libro.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all libros.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LibroImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the libro.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Libro libro) {
		entityCache.removeResult(
			entityCacheEnabled, LibroImpl.class, libro.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((LibroModelImpl)libro, true);
	}

	@Override
	public void clearCache(List<Libro> libros) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Libro libro : libros) {
			entityCache.removeResult(
				entityCacheEnabled, LibroImpl.class, libro.getPrimaryKey());

			clearUniqueFindersCache((LibroModelImpl)libro, true);
		}
	}

	protected void cacheUniqueFindersCache(LibroModelImpl libroModelImpl) {
		Object[] args = new Object[] {
			libroModelImpl.getUuid(), libroModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, libroModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		LibroModelImpl libroModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				libroModelImpl.getUuid(), libroModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((libroModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				libroModelImpl.getOriginalUuid(),
				libroModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new libro with the primary key. Does not add the libro to the database.
	 *
	 * @param libroId the primary key for the new libro
	 * @return the new libro
	 */
	@Override
	public Libro create(long libroId) {
		Libro libro = new LibroImpl();

		libro.setNew(true);
		libro.setPrimaryKey(libroId);

		String uuid = PortalUUIDUtil.generate();

		libro.setUuid(uuid);

		libro.setCompanyId(CompanyThreadLocal.getCompanyId());

		return libro;
	}

	/**
	 * Removes the libro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro that was removed
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	@Override
	public Libro remove(long libroId) throws NoSuchLibroException {
		return remove((Serializable)libroId);
	}

	/**
	 * Removes the libro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the libro
	 * @return the libro that was removed
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	@Override
	public Libro remove(Serializable primaryKey) throws NoSuchLibroException {
		Session session = null;

		try {
			session = openSession();

			Libro libro = (Libro)session.get(LibroImpl.class, primaryKey);

			if (libro == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLibroException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(libro);
		}
		catch (NoSuchLibroException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Libro removeImpl(Libro libro) {
		libroToEscritorTableMapper.deleteLeftPrimaryKeyTableMappings(
			libro.getPrimaryKey());

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(libro)) {
				libro = (Libro)session.get(
					LibroImpl.class, libro.getPrimaryKeyObj());
			}

			if (libro != null) {
				session.delete(libro);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (libro != null) {
			clearCache(libro);
		}

		return libro;
	}

	@Override
	public Libro updateImpl(Libro libro) {
		boolean isNew = libro.isNew();

		if (!(libro instanceof LibroModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(libro.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(libro);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in libro proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom Libro implementation " +
					libro.getClass());
		}

		LibroModelImpl libroModelImpl = (LibroModelImpl)libro;

		if (Validator.isNull(libro.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			libro.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (libro.getCreateDate() == null)) {
			if (serviceContext == null) {
				libro.setCreateDate(now);
			}
			else {
				libro.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!libroModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				libro.setModifiedDate(now);
			}
			else {
				libro.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (libro.isNew()) {
				session.save(libro);

				libro.setNew(false);
			}
			else {
				libro = (Libro)session.merge(libro);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {libroModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				libroModelImpl.getUuid(), libroModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {libroModelImpl.getTitulo()};

			finderCache.removeResult(_finderPathCountByTitulo, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByTitulo, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((libroModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {libroModelImpl.getOriginalUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {libroModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((libroModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					libroModelImpl.getOriginalUuid(),
					libroModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					libroModelImpl.getUuid(), libroModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((libroModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByTitulo.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					libroModelImpl.getOriginalTitulo()
				};

				finderCache.removeResult(_finderPathCountByTitulo, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTitulo, args);

				args = new Object[] {libroModelImpl.getTitulo()};

				finderCache.removeResult(_finderPathCountByTitulo, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByTitulo, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, LibroImpl.class, libro.getPrimaryKey(), libro,
			false);

		clearUniqueFindersCache(libroModelImpl, false);
		cacheUniqueFindersCache(libroModelImpl);

		libro.resetOriginalValues();

		return libro;
	}

	/**
	 * Returns the libro with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the libro
	 * @return the libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	@Override
	public Libro findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLibroException {

		Libro libro = fetchByPrimaryKey(primaryKey);

		if (libro == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLibroException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return libro;
	}

	/**
	 * Returns the libro with the primary key or throws a <code>NoSuchLibroException</code> if it could not be found.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro
	 * @throws NoSuchLibroException if a libro with the primary key could not be found
	 */
	@Override
	public Libro findByPrimaryKey(long libroId) throws NoSuchLibroException {
		return findByPrimaryKey((Serializable)libroId);
	}

	/**
	 * Returns the libro with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro, or <code>null</code> if a libro with the primary key could not be found
	 */
	@Override
	public Libro fetchByPrimaryKey(long libroId) {
		return fetchByPrimaryKey((Serializable)libroId);
	}

	/**
	 * Returns all the libros.
	 *
	 * @return the libros
	 */
	@Override
	public List<Libro> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<Libro> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<Libro> findAll(
		int start, int end, OrderByComparator<Libro> orderByComparator,
		boolean useFinderCache) {

		return findAll(start, end, orderByComparator);
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
	@Override
	public List<Libro> findAll(
		int start, int end, OrderByComparator<Libro> orderByComparator) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;
			finderPath = _finderPathWithoutPaginationFindAll;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<Libro> list = (List<Libro>)finderCache.getResult(
			finderPath, finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_LIBRO);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LIBRO;

				if (pagination) {
					sql = sql.concat(LibroModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Libro>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the libros from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Libro libro : findAll()) {
			remove(libro);
		}
	}

	/**
	 * Returns the number of libros.
	 *
	 * @return the number of libros
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LIBRO);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the primaryKeys of escritors associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @return long[] of the primaryKeys of escritors associated with the libro
	 */
	@Override
	public long[] getEscritorPrimaryKeys(long pk) {
		long[] pks = libroToEscritorTableMapper.getRightPrimaryKeys(pk);

		return pks.clone();
	}

	/**
	 * Returns all the libro associated with the escritor.
	 *
	 * @param pk the primary key of the escritor
	 * @return the libros associated with the escritor
	 */
	@Override
	public List<Libro> getEscritorLibros(long pk) {
		return getEscritorLibros(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
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
	@Override
	public List<Libro> getEscritorLibros(long pk, int start, int end) {
		return getEscritorLibros(pk, start, end, null);
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
	@Override
	public List<Libro> getEscritorLibros(
		long pk, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return libroToEscritorTableMapper.getLeftBaseModels(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of escritors associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @return the number of escritors associated with the libro
	 */
	@Override
	public int getEscritorsSize(long pk) {
		long[] pks = libroToEscritorTableMapper.getRightPrimaryKeys(pk);

		return pks.length;
	}

	/**
	 * Returns <code>true</code> if the escritor is associated with the libro.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 * @return <code>true</code> if the escritor is associated with the libro; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEscritor(long pk, long escritorPK) {
		return libroToEscritorTableMapper.containsTableMapping(pk, escritorPK);
	}

	/**
	 * Returns <code>true</code> if the libro has any escritors associated with it.
	 *
	 * @param pk the primary key of the libro to check for associations with escritors
	 * @return <code>true</code> if the libro has any escritors associated with it; <code>false</code> otherwise
	 */
	@Override
	public boolean containsEscritors(long pk) {
		if (getEscritorsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Adds an association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 */
	@Override
	public void addEscritor(long pk, long escritorPK) {
		Libro libro = fetchByPrimaryKey(pk);

		if (libro == null) {
			libroToEscritorTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk, escritorPK);
		}
		else {
			libroToEscritorTableMapper.addTableMapping(
				libro.getCompanyId(), pk, escritorPK);
		}
	}

	/**
	 * Adds an association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritor the escritor
	 */
	@Override
	public void addEscritor(long pk, Escritor escritor) {
		Libro libro = fetchByPrimaryKey(pk);

		if (libro == null) {
			libroToEscritorTableMapper.addTableMapping(
				CompanyThreadLocal.getCompanyId(), pk,
				escritor.getPrimaryKey());
		}
		else {
			libroToEscritorTableMapper.addTableMapping(
				libro.getCompanyId(), pk, escritor.getPrimaryKey());
		}
	}

	/**
	 * Adds an association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors
	 */
	@Override
	public void addEscritors(long pk, long[] escritorPKs) {
		long companyId = 0;

		Libro libro = fetchByPrimaryKey(pk);

		if (libro == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = libro.getCompanyId();
		}

		libroToEscritorTableMapper.addTableMappings(companyId, pk, escritorPKs);
	}

	/**
	 * Adds an association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors
	 */
	@Override
	public void addEscritors(long pk, List<Escritor> escritors) {
		addEscritors(
			pk, ListUtil.toLongArray(escritors, Escritor.ESCRITOR_ID_ACCESSOR));
	}

	/**
	 * Clears all associations between the libro and its escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro to clear the associated escritors from
	 */
	@Override
	public void clearEscritors(long pk) {
		libroToEscritorTableMapper.deleteLeftPrimaryKeyTableMappings(pk);
	}

	/**
	 * Removes the association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPK the primary key of the escritor
	 */
	@Override
	public void removeEscritor(long pk, long escritorPK) {
		libroToEscritorTableMapper.deleteTableMapping(pk, escritorPK);
	}

	/**
	 * Removes the association between the libro and the escritor. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritor the escritor
	 */
	@Override
	public void removeEscritor(long pk, Escritor escritor) {
		libroToEscritorTableMapper.deleteTableMapping(
			pk, escritor.getPrimaryKey());
	}

	/**
	 * Removes the association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors
	 */
	@Override
	public void removeEscritors(long pk, long[] escritorPKs) {
		libroToEscritorTableMapper.deleteTableMappings(pk, escritorPKs);
	}

	/**
	 * Removes the association between the libro and the escritors. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors
	 */
	@Override
	public void removeEscritors(long pk, List<Escritor> escritors) {
		removeEscritors(
			pk, ListUtil.toLongArray(escritors, Escritor.ESCRITOR_ID_ACCESSOR));
	}

	/**
	 * Sets the escritors associated with the libro, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritorPKs the primary keys of the escritors to be associated with the libro
	 */
	@Override
	public void setEscritors(long pk, long[] escritorPKs) {
		Set<Long> newEscritorPKsSet = SetUtil.fromArray(escritorPKs);
		Set<Long> oldEscritorPKsSet = SetUtil.fromArray(
			libroToEscritorTableMapper.getRightPrimaryKeys(pk));

		Set<Long> removeEscritorPKsSet = new HashSet<Long>(oldEscritorPKsSet);

		removeEscritorPKsSet.removeAll(newEscritorPKsSet);

		libroToEscritorTableMapper.deleteTableMappings(
			pk, ArrayUtil.toLongArray(removeEscritorPKsSet));

		newEscritorPKsSet.removeAll(oldEscritorPKsSet);

		long companyId = 0;

		Libro libro = fetchByPrimaryKey(pk);

		if (libro == null) {
			companyId = CompanyThreadLocal.getCompanyId();
		}
		else {
			companyId = libro.getCompanyId();
		}

		libroToEscritorTableMapper.addTableMappings(
			companyId, pk, ArrayUtil.toLongArray(newEscritorPKsSet));
	}

	/**
	 * Sets the escritors associated with the libro, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the libro
	 * @param escritors the escritors to be associated with the libro
	 */
	@Override
	public void setEscritors(long pk, List<Escritor> escritors) {
		try {
			long[] escritorPKs = new long[escritors.size()];

			for (int i = 0; i < escritors.size(); i++) {
				Escritor escritor = escritors.get(i);

				escritorPKs[i] = escritor.getPrimaryKey();
			}

			setEscritors(pk, escritorPKs);
		}
		catch (Exception e) {
			throw processException(e);
		}
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "libroId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LIBRO;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LibroModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the libro persistence.
	 */
	@Activate
	public void activate() {
		LibroModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		LibroModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		libroToEscritorTableMapper = TableMapperFactory.getTableMapper(
			"LIBRO_Libros_Escritores#libroId", "LIBRO_Libros_Escritores",
			"companyId", "libroId", "escritorId", this, Escritor.class);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			LibroModelImpl.UUID_COLUMN_BITMASK |
			LibroModelImpl.TITULO_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			LibroModelImpl.UUID_COLUMN_BITMASK |
			LibroModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			LibroModelImpl.UUID_COLUMN_BITMASK |
			LibroModelImpl.COMPANYID_COLUMN_BITMASK |
			LibroModelImpl.TITULO_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByTitulo = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByTitulo",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByTitulo = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, LibroImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByTitulo",
			new String[] {String.class.getName()},
			LibroModelImpl.TITULO_COLUMN_BITMASK);

		_finderPathCountByTitulo = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByTitulo",
			new String[] {String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(LibroImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		TableMapperFactory.removeTableMapper("LIBRO_Libros_Escritores#libroId");
	}

	@Override
	@Reference(
		target = LIBROPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.vass.liferay.servicebuilder.libro.model.Libro"),
			true);
	}

	@Override
	@Reference(
		target = LIBROPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = LIBROPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	protected TableMapper<Libro, Escritor> libroToEscritorTableMapper;

	private static final String _SQL_SELECT_LIBRO =
		"SELECT libro FROM Libro libro";

	private static final String _SQL_SELECT_LIBRO_WHERE =
		"SELECT libro FROM Libro libro WHERE ";

	private static final String _SQL_COUNT_LIBRO =
		"SELECT COUNT(libro) FROM Libro libro";

	private static final String _SQL_COUNT_LIBRO_WHERE =
		"SELECT COUNT(libro) FROM Libro libro WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "libro.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Libro exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No Libro exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		LibroPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	static {
		try {
			Class.forName(LIBROPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}