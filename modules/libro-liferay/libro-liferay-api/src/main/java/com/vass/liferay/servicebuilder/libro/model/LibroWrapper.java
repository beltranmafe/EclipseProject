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

package com.vass.liferay.servicebuilder.libro.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * <p>
 * This class is a wrapper for {@link Libro}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Libro
 * @generated
 */
@ProviderType
public class LibroWrapper
	extends BaseModelWrapper<Libro> implements Libro, ModelWrapper<Libro> {

	public LibroWrapper(Libro libro) {
		super(libro);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("libroId", getLibroId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("titulo", getTitulo());
		attributes.put("publicacion", getPublicacion());
		attributes.put("genero", getGenero());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long libroId = (Long)attributes.get("libroId");

		if (libroId != null) {
			setLibroId(libroId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String titulo = (String)attributes.get("titulo");

		if (titulo != null) {
			setTitulo(titulo);
		}

		Date publicacion = (Date)attributes.get("publicacion");

		if (publicacion != null) {
			setPublicacion(publicacion);
		}

		String genero = (String)attributes.get("genero");

		if (genero != null) {
			setGenero(genero);
		}
	}

	/**
	 * Returns the company ID of this libro.
	 *
	 * @return the company ID of this libro
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this libro.
	 *
	 * @return the create date of this libro
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the genero of this libro.
	 *
	 * @return the genero of this libro
	 */
	@Override
	public String getGenero() {
		return model.getGenero();
	}

	/**
	 * Returns the group ID of this libro.
	 *
	 * @return the group ID of this libro
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the libro ID of this libro.
	 *
	 * @return the libro ID of this libro
	 */
	@Override
	public long getLibroId() {
		return model.getLibroId();
	}

	/**
	 * Returns the modified date of this libro.
	 *
	 * @return the modified date of this libro
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this libro.
	 *
	 * @return the primary key of this libro
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the publicacion of this libro.
	 *
	 * @return the publicacion of this libro
	 */
	@Override
	public Date getPublicacion() {
		return model.getPublicacion();
	}

	/**
	 * Returns the titulo of this libro.
	 *
	 * @return the titulo of this libro
	 */
	@Override
	public String getTitulo() {
		return model.getTitulo();
	}

	/**
	 * Returns the user ID of this libro.
	 *
	 * @return the user ID of this libro
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this libro.
	 *
	 * @return the user name of this libro
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this libro.
	 *
	 * @return the user uuid of this libro
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this libro.
	 *
	 * @return the uuid of this libro
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this libro.
	 *
	 * @param companyId the company ID of this libro
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this libro.
	 *
	 * @param createDate the create date of this libro
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the genero of this libro.
	 *
	 * @param genero the genero of this libro
	 */
	@Override
	public void setGenero(String genero) {
		model.setGenero(genero);
	}

	/**
	 * Sets the group ID of this libro.
	 *
	 * @param groupId the group ID of this libro
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the libro ID of this libro.
	 *
	 * @param libroId the libro ID of this libro
	 */
	@Override
	public void setLibroId(long libroId) {
		model.setLibroId(libroId);
	}

	/**
	 * Sets the modified date of this libro.
	 *
	 * @param modifiedDate the modified date of this libro
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this libro.
	 *
	 * @param primaryKey the primary key of this libro
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the publicacion of this libro.
	 *
	 * @param publicacion the publicacion of this libro
	 */
	@Override
	public void setPublicacion(Date publicacion) {
		model.setPublicacion(publicacion);
	}

	/**
	 * Sets the titulo of this libro.
	 *
	 * @param titulo the titulo of this libro
	 */
	@Override
	public void setTitulo(String titulo) {
		model.setTitulo(titulo);
	}

	/**
	 * Sets the user ID of this libro.
	 *
	 * @param userId the user ID of this libro
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this libro.
	 *
	 * @param userName the user name of this libro
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this libro.
	 *
	 * @param userUuid the user uuid of this libro
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this libro.
	 *
	 * @param uuid the uuid of this libro
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected LibroWrapper wrap(Libro libro) {
		return new LibroWrapper(libro);
	}

}