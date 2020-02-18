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

package com.vass.liferay.servicebuilder.libro.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.model.Libro;
import com.vass.liferay.servicebuilder.libro.model.impl.LibroImpl;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;
import com.vass.liferay.servicebuilder.libro.service.base.LibroLocalServiceBaseImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the libro local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.vass.liferay.servicebuilder.libro.service.LibroLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LibroLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.vass.liferay.servicebuilder.libro.model.Libro",
	service = AopService.class
)
public class LibroLocalServiceImpl extends LibroLocalServiceBaseImpl {

    public void addLibro(long groupId, long companyId, long userId, String userName, String titulo, LocalDate publicacion, String genero, Collection<Escritor> escritores) {
        final Libro libro = new LibroImpl();
 
        libro.setLibroId(counterLocalService.increment());
        libro.setGroupId(groupId);
        libro.setCompanyId(companyId);
        libro.setUserId(userId);
        libro.setUserName(userName);
 
        libro.setTitulo(titulo);
        libro.setPublicacion(localDateToDate(publicacion));
        libro.setGenero(genero);
 
        addLibro(libro);
 
        EscritorLocalServiceUtil.setLibroEscritors(libro.getLibroId(), getEscritorIds(escritores));
    }
 
    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
 
    public void updateLibro(long id, Collection<Escritor> escritores) throws PortalException {
        EscritorLocalServiceUtil.setLibroEscritors(id, getEscritorIds(escritores));
    }
 
    private long[] getEscritorIds(Collection<Escritor> escritores) {
        return escritores.stream().mapToLong(Escritor::getEscritorId).toArray();
    }
}