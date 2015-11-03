// $Id: eclipse-argo-codetemplates.xml 11347 2006-10-26 22:37:44Z linus $
// Copyright (c) 2015 The Regents of the University of California. All
// Rights Reserved. Permission to use, copy, modify, and distribute this
// software and its documentation without fee, and without a written
// agreement is hereby granted, provided that the above copyright notice
// and this paragraph appear in all copies. This software program and
// documentation are copyrighted by The Regents of the University of
// California. The software program and documentation are supplied "AS
// IS", without any accompanying services from The Regents. The Regents
// does not warrant that the operation of the program will be
// uninterrupted or error-free. The end-user understands that the program
// was developed for research purposes and is advised not to rely
// exclusively on the program for any reason. IN NO EVENT SHALL THE
// UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
// SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
// ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
// THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE. THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY
// WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
// PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
// CALIFORNIA HAS NO OBLIGATIONS TO PROVIDE MAINTENANCE, SUPPORT,
// UPDATES, ENHANCEMENTS, OR MODIFICATIONS.

package org.uwl2owl;

import bean.SystemUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import restclient.AuthenticationClient;
import soapclient.Concept;
import util.Constants;


public class WorkspaceDriver {
    
    private soapclient.DesignWorkspaceOntology port = null;
    public WorkspaceDriver() {
        initial();
    }
    
    public void initial() {
        
        Constants.ontologyServerURI = "http://aufeef.itu.dk:8080/ArchitectureDesignWorkspace/webresources";
        
        AuthenticationClient authenticationClient = new AuthenticationClient();
        SystemUser tempSystemUser = (SystemUser)authenticationClient.isSystemUserPresent(SystemUser.class, "test");
        if( !(tempSystemUser.getUserid() != null && tempSystemUser.getUserid().length() > 0) )
        {
            SystemUser systemUser = new SystemUser();
            systemUser.setUserid("test");
            systemUser.setName("testname");
            systemUser.setPassword("pwd");
            tempSystemUser = (SystemUser) authenticationClient.createSystemUser_XML(SystemUser.class, systemUser);
        }
        SystemUser systemUser = new SystemUser();
        systemUser.setUserid("test");
        systemUser.setPassword("pwd");
//        tempSystemUser = (SystemUser) authenticationClient.getAccessKey_XML(SystemUser.class, systemUser);

        String userId = "test";
        String authKey = tempSystemUser.getCurrentAuthKey();

        soapclient.DesignOntology service = new soapclient.DesignOntology();
        port = service.getDesignWorkspaceOntologyPort();

        Map<String, Object> req_ctx = ((BindingProvider)port).getRequestContext();
        Map<String, List<String>> headers = new HashMap<String, List<String>>();
        headers.put(Constants.userId, Collections.singletonList(userId));
        headers.put(Constants.authKey, Collections.singletonList(authKey));
        req_ctx.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
    }
    
    public ArrayList<String> getRelationlist(){
        
        ArrayList<String> relationList = (ArrayList) port.getRelationList(Constants.workspaceIdentifier);
        
        return relationList;
    }
}
