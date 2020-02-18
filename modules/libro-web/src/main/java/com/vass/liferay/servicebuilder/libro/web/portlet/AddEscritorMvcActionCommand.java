package com.vass.liferay.servicebuilder.libro.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;


@Component(
        immediate = true,
        property = {
                "javax.portlet.name=com_vass_liferay_servicebuilder_libro_web_MyMvcPortlet",
                "mvc.command.name=addEscritor"
        },
        service = MVCActionCommand.class
)



public class AddEscritorMvcActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest request, ActionResponse response) throws Exception {
		final ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        final String nombre = ParamUtil.getString(request, "nombreEscritor");
 
        EscritorLocalServiceUtil.addEscritor(td.getSiteGroupId(), td.getCompanyId(), 
        		td.getUser().getUserId(), td.getUser().getFullName(), nombre);
		
	}


}
