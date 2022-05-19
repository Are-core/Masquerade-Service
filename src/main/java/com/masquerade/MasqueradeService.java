package com.masquerade;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "MasqueradeService")
public class MasqueradeService {

    @WebMethod
    public String archetypeList(int value) {
        return "List here : " + value;
    }
}
