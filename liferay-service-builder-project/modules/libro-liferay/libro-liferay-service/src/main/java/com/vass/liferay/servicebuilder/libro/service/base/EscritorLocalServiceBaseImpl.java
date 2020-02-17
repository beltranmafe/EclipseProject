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

import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalService;
import com.vass.liferay.servicebuilder.libro.service.persistence.EscritorPersistence;
import com.vass.liferay.servicebuilder.libro.service.persistence.LibroPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the escritor local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.vass.liferay.servicebuilder.libro.service.impl.EscritorLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.vass.liferay.servicebuilder.libro.service.impl.EscritorLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class EscritorLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements EscritorLocalService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>EscritorLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil</code>.
	 */

	/**
	 * Adds the escritor to the database. Also notifies the appropriate model listeners.
	 *
	 * @param escritor the escritor
	 * @return the escritor that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Escritor addEscritor(Escritor escritor) {
		escritor.setNew(true);

		return escritorPersistence.update(escritor);
	}

	/**
	 * Creates a new escritor with the primary key. Does not add the escritor to the database.
	 *
	 * @param escritorId the primary key for the new escritor
	 * @return the new escritor
	 */
	@Override
	@Transactional(enabled = false)
	public Escritor createEscritor(long escritorId) {
		return escritorPersistence.create(escritorId);
	}

	/**
	 * Deletes the escritor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param escritorId the primary key of the escritor
	 * @return the escritor that was removed
	 * @throws PortalException if a escritor with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Escritor deleteEscritor(long escritorId) throws PortalException {
		return escritorPersistence.remove(escritorId);
	}

	/**
	 * Deletes the escritor from the database. Also notifies the appropriate model listeners.
	 *
	 * @param escritor the escritor
	 * @return the escritor that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Escritor deleteEscritor(Escritor escritor) {
		return escritorPersistence.remove(escritor);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Escritor.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return escritorPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.EscritorModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return escritorPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.EscritorModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return escritorPersistence.findWithDynamicQuery(
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
		return escritorPersistence.countWithDynamicQuery(dynamicQuery);
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

		return escritorPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Escritor fetchEscritor(long escritorId) {
		return escritorPersistence.fetchByPrimaryKey(escritorId);
	}

	/**
	 * Returns the escritor matching the UUID and group.
	 *
	 * @param uuid the escritor's UUID
	 * @param groupId the primary key of the group
	 * @return the matching escritor, or <code>null</code> if a matching escritor could not be found
	 */
	@Override
	public Escritor fetchEscritorByUuidAndGroupId(String uuid, long groupId) {
		return escritorPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the escritor with the primary key.
	 *
	 * @param escritorId the primary key of the escritor
	 * @return the escritor
	 * @throws PortalException if a escritor with the primary key could not be found
	 */
	@Override
	public Escritor getEscritor(long escritorId) throws PortalException {
		return escritorPersistence.findByPrimaryKey(escritorId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(escritorLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Escritor.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("escritorId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			escritorLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Escritor.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("escritorId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(escritorLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Escritor.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("escritorId");
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
			new ActionableDynamicQuery.PerformActionMethod<Escritor>() {

				@Override
				public void performAction(Escritor escritor)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, escritor);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Escritor.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return escritorLocalService.deleteEscritor((Escritor)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return escritorPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the escritors matching the UUID and company.
	 *
	 * @param uuid the UUID of the escritors
	 * @param companyId the primary key of the company
	 * @return the matching escritors, or an empty list if no matches were found
	 */
	@Override
	public List<Escritor> getEscritorsByUuidAndCompanyId(
		String uuid, long companyId) {

		return escritorPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of escritors matching the UUID and company.
	 *
	 * @param uuid the UUID of the escritors
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of escritors
	 * @param end the upper bound of the range of escritors (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching escritors, or an empty list if no matches were found
	 */
	@Override
	public List<Escritor> getEscritorsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Escritor> orderByComparator) {

		return escritorPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the escritor matching the UUID and group.
	 *
	 * @param uuid the escritor's UUID
	 * @param groupId the primary key of the group
	 * @return the matching escritor
	 * @throws PortalException if a matching escritor could not be found
	 */
	@Override
	public Escritor getEscritorByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return escritorPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the escritors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.vass.liferay.servicebuilder.libro.model.impl.EscritorModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of escritors
	 * @param end the upper bound of the range of escritors (not inclusive)
	 * @return the range of escritors
	 */
	@Override
	public List<Escritor> getEscritors(int start, int end) {
		return escritorPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of escritors.
	 *
	 * @return the number of escritors
	 */
	@Override
	public int getEscritorsCount() {
		return escritorPersistence.countAll();
	}

	/**
	 * Updates the escritor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param escritor the escritor
	 * @return the escritor that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Escritor updateEscritor(Escritor escritor) {
		return escritorPersistence.update(escritor);
	}

	/**
	 */
	@Override
	public void addLibroEscritor(long libroId, long escritorId) {
		libroPersistence.addEscritor(libroId, escritorId);
	}

	/**
	 */
	@Override
	public void addLibroEscritor(long libroId, Escritor escritor) {
		libroPersistence.addEscritor(libroId, escritor);
	}

	/**
	 */
	@Override
	public void addLibroEscritors(long libroId, long[] escritorIds) {
		libroPersistence.addEscritors(libroId, escritorIds);
	}

	/**
	 */
	@Override
	public void addLibroEscritors(long libroId, List<Escritor> escritors) {
		libroPersistence.addEscritors(libroId, escritors);
	}

	/**
	 */
	@Override
	public void clearLibroEscritors(long libroId) {
		libroPersistence.clearEscritors(libroId);
	}

	/**
	 */
	@Override
	public void deleteLibroEscritor(long libroId, long escritorId) {
		libroPersistence.removeEscritor(libroId, escritorId);
	}

	/**
	 */
	@Override
	public void deleteLibroEscritor(long libroId, Escritor escritor) {
		libroPersistence.removeEscritor(libroId, escritor);
	}

	/**
	 */
	@Override
	public void deleteLibroEscritors(long libroId, long[] escritorIds) {
		libroPersistence.removeEscritors(libroId, escritorIds);
	}

	/**
	 */
	@Override
	public void deleteLibroEscritors(long libroId, List<Escritor> escritors) {
		libroPersistence.removeEscritors(libroId, escritors);
	}

	/**
	 * Returns the libroIds of the libros associated with the escritor.
	 *
	 * @param escritorId the escritorId of the escritor
	 * @return long[] the libroIds of libros associated with the escritor
	 */
	@Override
	public long[] getLibroPrimaryKeys(long escritorId) {
		return escritorPersistence.getLibroPrimaryKeys(escritorId);
	}

	/**
	 */
	@Override
	public List<Escritor> getLibroEscritors(long libroId) {
		return escritorPersistence.getLibroEscritors(libroId);
	}

	/**
	 */
	@Override
	public List<Escritor> getLibroEscritors(long libroId, int start, int end) {
		return escritorPersistence.getLibroEscritors(libroId, start, end);
	}

	/**
	 */
	@Override
	public List<Escritor> getLibroEscritors(
		long libroId, int start, int end,
		OrderByComparator<Escritor> orderByComparator) {

		return escritorPersistence.getLibroEscritors(
			libroId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getLibroEscritorsCount(long libroId) {
		return libroPersistence.getEscritorsSize(libroId);
	}

	/**
	 */
	@Override
	public boolean hasLibroEscritor(long libroId, long escritorId) {
		return libroPersistence.containsEscritor(libroId, escritorId);
	}

	/**
	 */
	@Override
	public boolean hasLibroEscritors(long libroId) {
		return libroPersistence.containsEscritors(libroId);
	}

	/**
	 */
	@Override
	public void setLibroEscritors(long libroId, long[] escritorIds) {
		libroPersistence.setEscritors(libroId, escritorIds);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			EscritorLocalService.class, IdentifiableOSGiService.class,
			PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		escritorLocalService = (EscritorLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return EscritorLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Escritor.class;
	}

	protected String getModelClassName() {
		return Escritor.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = escritorPersistence.getDataSource();

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

	protected EscritorLocalService escritorLocalService;

	@Reference
	protected EscritorPersistence escritorPersistence;

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