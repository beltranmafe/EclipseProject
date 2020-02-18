package com.vass.liferay.servicebuilder.libro.web.portlet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vass.liferay.servicebuilder.libro.model.Escritor;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
 
@Component(
        immediate = true,
        property = {
                "javax.portlet.name=com_vass_liferay_servicebuilder_libro_web_MyMvcPortlet",
                "mvc.command.name=displayEscritorEdition"
        },
        service = MVCRenderCommand.class
)
public class EditEscritorMvcRenderCommand implements MVCRenderCommand {
 
    @Override
    public String render(RenderRequest request, RenderResponse response) throws PortletException {
        final String id = ParamUtil.getString(request, "idEscritor");
 
        try {
            final Escritor escritor = EscritorLocalServiceUtil.getEscritor(Long.valueOf(id));
            request.setAttribute("escritor", escritor);
 
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }
 
        return "/escritorEdit.jsp";
    }
 
}
