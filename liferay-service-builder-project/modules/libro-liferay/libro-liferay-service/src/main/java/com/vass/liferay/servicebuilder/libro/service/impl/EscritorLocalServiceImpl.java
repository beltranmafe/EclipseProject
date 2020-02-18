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
import com.vass.liferay.servicebuilder.libro.model.impl.EscritorImpl;
import com.vass.liferay.servicebuilder.libro.service.base.EscritorLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the escritor local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.vass.liferay.servicebuilder.libro.service.EscritorLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EscritorLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.vass.liferay.servicebuilder.libro.model.Escritor",
	service = AopService.class
)
public class EscritorLocalServiceImpl extends EscritorLocalServiceBaseImpl {

    public void addEscritor(long groupId, long companyId, long userId, String userName, String nombre) {
        final Escritor escritor = new EscritorImpl();
        escritor.setEscritorId(counterLocalService.increment());
        escritor.setGroupId(groupId);
        escritor.setCompanyId(companyId);
        escritor.setUserId(userId);
        escritor.setUserName(userName);
        escritor.setNombre(nombre);
 
        addEscritor(escritor);
    }
    

	public void updateEscritor(long id, String nombre) throws PortalException {
	    final Escritor escritor = getEscritor(id);
	    escritor.setNombre(nombre);
	 
	    updateEscritor(escritor);
	}
}