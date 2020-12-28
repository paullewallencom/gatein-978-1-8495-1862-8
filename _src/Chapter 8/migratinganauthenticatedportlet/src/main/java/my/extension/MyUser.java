package my.extension;

import org.exoplatform.services.organization.OrganizationConfig.User;

public class MyUser extends User {

    private String organizationId;

    public MyUser()
    {
    }

    public String getOrganizationId()
    {
       return organizationId;
    }

    public void setOrganizationId(String organizationId)
    {
       this.organizationId = organizationId;
    }
    
}
