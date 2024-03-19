package org.example.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OpenAPIComponent {
    @Value("${openAPI.serviceKey}")
    public String serviceKey;

    public String getServiceKey(){
        return serviceKey;
    }
}
