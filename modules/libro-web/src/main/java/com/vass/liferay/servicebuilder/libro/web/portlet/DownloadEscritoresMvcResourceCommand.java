package com.vass.liferay.servicebuilder.libro.web.portlet;

import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ContentTypes;
import com.vass.liferay.servicebuilder.libro.service.EscritorLocalServiceUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "javax.portlet.name=com_vass_liferay_servicebuilder_libro_web_MyMvcPortlet",
                "mvc.command.name=downloadEscritores"
        },
        service = MVCResourceCommand.class
)

public class DownloadEscritoresMvcResourceCommand extends BaseMVCResourceCommand {
		 
	    @Override
	    protected void doServeResource(ResourceRequest request, ResourceResponse response) throws Exception {
	        final String escritores = EscritorLocalServiceUtil.getEscritors(0, Integer.MAX_VALUE).toString();
	        final InputStream stream = new ByteArrayInputStream(escritores.getBytes(StandardCharsets.UTF_8));
	        PortletResponseUtil.sendFile(request, response, "escritores.txt", stream, ContentTypes.APPLICATION_TEXT);
	    }
	    
}

