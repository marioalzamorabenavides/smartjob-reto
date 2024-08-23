package com.smartjob.reto.business.connector;

import com.smartjob.reto.resource.structure.UserAuthStructure;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;

public interface AuthBusinessConnector {
    UserRespStructure registerUser(UserReqStructure user);
    UserRespStructure login(UserAuthStructure user);
}
