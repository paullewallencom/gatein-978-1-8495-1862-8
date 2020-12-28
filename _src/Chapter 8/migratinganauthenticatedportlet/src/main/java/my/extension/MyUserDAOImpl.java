package my.extension;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.services.organization.Query;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.idm.IDMUserListAccess;
import org.exoplatform.services.organization.idm.PicketLinkIDMOrganizationServiceImpl;
import org.exoplatform.services.organization.idm.PicketLinkIDMService;
import org.exoplatform.services.organization.idm.Tools;
import org.exoplatform.services.organization.idm.UserDAOImpl;
import org.gatein.common.logging.LogLevel;
import org.gatein.common.logging.Logger;
import org.gatein.common.logging.LoggerFactory;
import org.picketlink.idm.api.query.UserQueryBuilder;

public class MyUserDAOImpl extends UserDAOImpl {

   private static Logger log =  
     LoggerFactory.getLogger(MyUserDAOImpl.class);

   private final PicketLinkIDMService service_;

   public MyUserDAOImpl(
     PicketLinkIDMOrganizationServiceImpl orgService,
     PicketLinkIDMService idmService) throws Exception {
        super(orgService, idmService);
        this.service_ = idmService;
   }

   @Override
   public ListAccess<User> findUsersByQuery(Query q) 
     throws Exception {
     if (log.isTraceEnabled()) {
        Tools.logMethodIn(log, LogLevel.TRACE, 
           "findUsersByQuery",
           new Object[] { "q", q });
     }

     IDMUserListAccess list;

     getOrgService().flush();

     UserQueryBuilder qb = service_.getIdentitySession()
        .createUserQueryBuilder();

     MyQuery myq = (MyQuery)q;
     if (myq.getOrganization() != null) {
         qb.attributeValuesFilter(
            MyUserDAOImpl.USER_ORGANIZATION_ID,
            new String[] { myq.getOrganization() }
        );
     }
     list = new IDMUserListAccess(qb, 20, false);
     return list;
  }

}