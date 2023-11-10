package businessLogic;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import dataAccess.DataAccess;

public class Factory {
    public BLFacade create(ConfigXML c) throws MalformedURLException{
        if (c.isBusinessLogicLocal()) {
            DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
            return new BLFacadeImplementation(da);
        } else{
            String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
            URL url;
        
            url = new URL(serviceName);
            QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
            Service service = Service.create(url, qname);
            return service.getPort(BLFacade.class);

        }
    }

    public BLFacade create(String type, ConfigXML c) throws MalformedURLException{
        switch (type) {
            case "local":
                DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
                return new BLFacadeImplementation(da);
            case "remote":
                
                String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
                URL url = new URL(serviceName);
                QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
                Service service = Service.create(url, qname);
                return service.getPort(BLFacade.class);
                
                
                

            default: //local
                return create("local",c);    
        }
    }

}