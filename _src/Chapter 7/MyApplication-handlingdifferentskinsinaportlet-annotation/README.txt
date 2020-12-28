to install the project:

mvn clean install

The project cannot be deployed as a war application. Because WebUI is developed only for the internal administration system, it needs to work inside a own context.
So, if you want execute the project inside Gatein you must:

1 - Download the Gatein sources from https://github.com/gatein/gatein-portal/zipball/3.2.0-GA

2 - merge the classes and resources in the exo.portal.portlet.web project. Go in the root folder of the exo.portal.portlet.web project 
    and execute mvn clean install. 

3 - Copy the new web.war package in the $PORTAL_ROOT and substitute the old package

     