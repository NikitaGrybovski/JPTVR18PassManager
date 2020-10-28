/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json.Builders;

import entity.Users;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author pupil
 */
public class UserJsonBuilder {
    public JsonObject createJsonUser(Users users,String JSESSIONID,String roleUsers){
        JsonObjectBuilder job = Json.createObjectBuilder();
         job.add("id", users.getId())
                .add("login", users.getUserlogin())
                .add("JSESSIONID",JSESSIONID)
                .add("role",roleUsers);
                
         return job.build();
    }
    public JsonObject createJsonUser(Users users){
        return createJsonUser(users,null,null);
    }
    
}
