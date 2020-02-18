package com.vass.liferay.servicebuilder.libro.web.portlet;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;
import com.vass.liferay.servicebuilder.libro.web.constants.MyMvcPortletKeys;
import java.io.IOException;
import java.util.List;
import javax.portlet.PortletException;
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
	  
    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
    	final List<Escritor> escritores = EscritorLocalServiceUtil.getEscritors(0, Integer.MAX_VALUE);
 
    	renderRequest.setAttribute("escritores", escritores);
 
    	super.render(renderRequest, renderResponse);
    }
    
}