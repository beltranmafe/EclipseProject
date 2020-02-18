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

package com.vass.liferay.servicebuilder.libro.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.vass.liferay.servicebuilder.libro.model.Libro;
import com.vass.liferay.servicebuilder.libro.service.LibroLocalService;
import com.vass.liferay.servicebuilder.libro.service.persistence.EscritorPersistence;
import com.vass.liferay.servicebuilder.libro.service.persistence.LibroPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the libro local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.vass.liferay.servicebuilder.libro.service.impl.LibroLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.vass.liferay.servicebuilder.libro.service.impl.LibroLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class LibroLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements LibroLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LibroLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.vass.liferay.servicebuilder.libro.service.LibroLocalServiceUtil</code>.
	 */

	/**
	 * Adds the libro to the database. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Libro addLibro(Libro libro) {
		libro.setNew(true);

		return libroPersistence.update(libro);
	}

	/**
	 * Creates a new libro with the primary key. Does not add the libro to the database.
	 *
	 * @param libroId the primary key for the new libro
	 * @return the new libro
	 */
	@Override
	@Transactional(enabled = false)
	public Libro createLibro(long libroId) {
		return libroPersistence.create(libroId);
	}

	/**
	 * Deletes the libro with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro that was removed
	 * @throws PortalException if a libro with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Libro deleteLibro(long libroId) throws PortalException {
		return libroPersistence.remove(libroId);
	}

	/**
	 * Deletes the libro from the database. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Libro deleteLibro(Libro libro) {
		return libroPersistence.remove(libro);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Libro.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return libroPersistence.findWithDynamicQuery(dynamicQuery);
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
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return libroPersistence.findWithDynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return libroPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return libroPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return libroPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Libro fetchLibro(long libroId) {
		return libroPersistence.fetchByPrimaryKey(libroId);
	}

	/**
	 * Returns the libro matching the UUID and group.
	 *
	 * @param uuid the libro's UUID
	 * @param groupId the primary key of the group
	 * @return the matching libro, or <code>null</code> if a matching libro could not be found
	 */
	@Override
	public Libro fetchLibroByUuidAndGroupId(String uuid, long groupId) {
		return libroPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the libro with the primary key.
	 *
	 * @param libroId the primary key of the libro
	 * @return the libro
	 * @throws PortalException if a libro with the primary key could not be found
	 */
	@Override
	public Libro getLibro(long libroId) throws PortalException {
		return libroPersistence.findByPrimaryKey(libroId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(libroLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Libro.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("libroId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(libroLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Libro.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("libroId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(libroLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Libro.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("libroId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Libro>() {

				@Override
				public void performAction(Libro libro) throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, libro);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Libro.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return libroLocalService.deleteLibro((Libro)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return libroPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the libros matching the UUID and company.
	 *
	 * @param uuid the UUID of the libros
	 * @param companyId the primary key of the company
	 * @return the matching libros, or an empty list if no matches were found
	 */
	@Override
	public List<Libro> getLibrosByUuidAndCompanyId(
		String uuid, long companyId) {

		return libroPersistence.findByUuid_C(uuid, companyId);
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
	@Override
	public List<Libro> getLibrosByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return libroPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the libro matching the UUID and group.
	 *
	 * @param uuid the libro's UUID
	 * @param groupId the primary key of the group
	 * @return the matching libro
	 * @throws PortalException if a matching libro could not be found
	 */
	@Override
	public Libro getLibroByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return libroPersistence.findByUUID_G(uuid, groupId);
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
	@Override
	public List<Libro> getLibros(int start, int end) {
		return libroPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of libros.
	 *
	 * @return the number of libros
	 */
	@Override
	public int getLibrosCount() {
		return libroPersistence.countAll();
	}

	/**
	 * Updates the libro in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param libro the libro
	 * @return the libro that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Libro updateLibro(Libro libro) {
		return libroPersistence.update(libro);
	}

	/**
	 */
	@Override
	public void addEscritorLibro(long escritorId, long libroId) {
		escritorPersistence.addLibro(escritorId, libroId);
	}

	/**
	 */
	@Override
	public void addEscritorLibro(long escritorId, Libro libro) {
		escritorPersistence.addLibro(escritorId, libro);
	}

	/**
	 */
	@Override
	public void addEscritorLibros(long escritorId, long[] libroIds) {
		escritorPersistence.addLibros(escritorId, libroIds);
	}

	/**
	 */
	@Override
	public void addEscritorLibros(long escritorId, List<Libro> libros) {
		escritorPersistence.addLibros(escritorId, libros);
	}

	/**
	 */
	@Override
	public void clearEscritorLibros(long escritorId) {
		escritorPersistence.clearLibros(escritorId);
	}

	/**
	 */
	@Override
	public void deleteEscritorLibro(long escritorId, long libroId) {
		escritorPersistence.removeLibro(escritorId, libroId);
	}

	/**
	 */
	@Override
	public void deleteEscritorLibro(long escritorId, Libro libro) {
		escritorPersistence.removeLibro(escritorId, libro);
	}

	/**
	 */
	@Override
	public void deleteEscritorLibros(long escritorId, long[] libroIds) {
		escritorPersistence.removeLibros(escritorId, libroIds);
	}

	/**
	 */
	@Override
	public void deleteEscritorLibros(long escritorId, List<Libro> libros) {
		escritorPersistence.removeLibros(escritorId, libros);
	}

	/**
	 * Returns the escritorIds of the escritors associated with the libro.
	 *
	 * @param libroId the libroId of the libro
	 * @return long[] the escritorIds of escritors associated with the libro
	 */
	@Override
	public long[] getEscritorPrimaryKeys(long libroId) {
		return libroPersistence.getEscritorPrimaryKeys(libroId);
	}

	/**
	 */
	@Override
	public List<Libro> getEscritorLibros(long escritorId) {
		return libroPersistence.getEscritorLibros(escritorId);
	}

	/**
	 */
	@Override
	public List<Libro> getEscritorLibros(long escritorId, int start, int end) {
		return libroPersistence.getEscritorLibros(escritorId, start, end);
	}

	/**
	 */
	@Override
	public List<Libro> getEscritorLibros(
		long escritorId, int start, int end,
		OrderByComparator<Libro> orderByComparator) {

		return libroPersistence.getEscritorLibros(
			escritorId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getEscritorLibrosCount(long escritorId) {
		return escritorPersistence.getLibrosSize(escritorId);
	}

	/**
	 */
	@Override
	public boolean hasEscritorLibro(long escritorId, long libroId) {
		return escritorPersistence.containsLibro(escritorId, libroId);
	}

	/**
	 */
	@Override
	public boolean hasEscritorLibros(long escritorId) {
		return escritorPersistence.containsLibros(escritorId);
	}

	/**
	 */
	@Override
	public void setEscritorLibros(long escritorId, long[] libroIds) {
		escritorPersistence.setLibros(escritorId, libroIds);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			LibroLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		libroLocalService = (LibroLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LibroLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Libro.class;
	}

	protected String getModelClassName() {
		return Libro.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = libroPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Reference
	protected EscritorPersistence escritorPersistence;

	protected LibroLocalService libroLocalService;

	@Reference
	protected LibroPersistence libroPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

}