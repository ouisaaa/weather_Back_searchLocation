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
    @Value("${openAPI.numOfRows}")
    private String numOfRows;


    public String getLink(){ return link; }
    public String getServiceKey(){
        return serviceKey;
    }
    public String getDataType(){return dataType;}
    public String getNumOfRows(){return numOfRows;}
}
