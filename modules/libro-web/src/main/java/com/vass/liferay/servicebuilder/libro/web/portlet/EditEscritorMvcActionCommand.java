package com.vass.liferay.servicebuilder.libro.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=com_vass_liferay_servicebuilder_libro_web_MyMvcPortlet",
                "mvc.command.name=editEscritor"
        },
        service = MVCActionCommand.class
)

public class EditEscritorMvcActionCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
	    final String id = ParamUtil.getString(request, "idEscritor");
	    final String nombre = ParamUtil.getString(request, "nombreEscritor");

	    EscritorLocalServiceUtil.updateEscritor(Long.valueOf(id), nombre);

	    response.getRenderParameters();
		
	}

}
