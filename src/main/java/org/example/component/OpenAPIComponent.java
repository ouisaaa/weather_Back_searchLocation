package org.example.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAPIComponent {
    @Value("${openAPI.link}")
    private String link;
    @Value("${openAPI.serviceKey}")
    private String serviceKey;
    @Value("${openAPI.dataType}")
    private String dataType;


    public String getLink(){ return link; }
    public String getServiceKey(){
        return serviceKey;
    }
    public String getDataType(){return dataType;}
}
