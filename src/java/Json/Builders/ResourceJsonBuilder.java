/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json.Builders;

import entity.Resource;
import java.math.BigDecimal;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author pupil
 */
public class ResourceJsonBuilder {
    public JsonObject createJsonResource(Resource resource){
        JsonObjectBuilder job = Json.createObjectBuilder();
         job.add("id", resource.getId())
                .add("name", resource.getName())
                .add("url", resource.getUrl())
                .add("login", resource.getLogin())
                .add("password", resource.getPassword());
         return job.build();
    }
}
