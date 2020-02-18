package com.vass.liferay.servicebuilder.libro.web.portlet;

import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;
import com.vass.liferay.servicebuilder.libro.web.constants.MyMvcPortletKeys;

import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author maria.velandia
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=MyMvc",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + MyMvcPortletKeys.MYMVC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class MyMvcPortlet extends MVCPortlet {
	
    @ProcessAction(name = "addEscritor")
    public void addEscritor(ActionRequest request, ActionResponse response) {
        final ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        final String nombre = ParamUtil.getString(request, "nombreEscritor");
 
        EscritorLocalServiceUtil.addEscritor(td.getSiteGroupId(), td.getCompanyId(), td.getUser().getUserId(), td.getUser().getFullName(), nombre);
    }
    

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
    	final List<Escritor> escritores = EscritorLocalServiceUtil.getEscritors(0, Integer.MAX_VALUE);
 
    	renderRequest.setAttribute("escritores", escritores);
 
    	super.render(renderRequest, renderResponse);
    }
    

	@ProcessAction(name = "displayEscritorEdition")
	public void displayEscritorEdition(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {
	    final String id = request.getParameter("idEscritor");
	    final Escritor escritor = EscritorLocalServiceUtil.getEscritor(Long.valueOf(id));
	 
	    request.setAttribute("escritor", escritor);
	    response.setRenderParameter("mvcPath", "/escritorEdit.jsp");
	}
	
	@ProcessAction(name = "editEscritor")
	public void editEscritor(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {
	    final String id = request.getParameter("idEscritor");
	    final String nombre = request.getParameter("nombreEscritor");

	    EscritorLocalServiceUtil.updateEscritor(Long.valueOf(id), nombre);

	    response.setRenderParameter("mvcPath", "/view.jsp");
	}
	

	@ProcessAction(name = "deleteEscritor")
	public void deleteEscritor(ActionRequest request, ActionResponse response) throws IOException, PortletException, PortalException {
	    final String id = request.getParameter("idEscritor");
	 
	    EscritorLocalServiceUtil.deleteEscritor(Long.valueOf(id));
	}
}