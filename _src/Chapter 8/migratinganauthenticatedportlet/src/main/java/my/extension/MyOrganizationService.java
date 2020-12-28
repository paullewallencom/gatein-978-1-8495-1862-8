package my.extension;

import org.exoplatform.container.xml.InitParams;
import org.exoplatform.services.organization.idm.PicketLinkIDMOrganizationServiceImpl;
import org.exoplatform.services.organization.idm.PicketLinkIDMService;

public class MyOrganizationService extends PicketLinkIDMOrganizationServiceImpl {

  public MyOrganizationService(InitParams params,
        PicketLinkIDMService idmService) throws Exception {
        super(params, idmService);
        userDAO_ = new MyUserDAOImpl(this, idmService);

  }
}
