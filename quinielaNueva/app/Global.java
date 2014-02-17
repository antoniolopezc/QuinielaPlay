/**
 * 
 */

import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

/**
 * @author alopez1
 *
 */
public class Global extends GlobalSettings {
	
	 @Override
	    public void onStart(Application app) {
	        // Check if the database is empty
	        if (Torneo.find.findRowCount() == 0) {
//	        	Ebean.save((List) Yaml.load("equipos.yml"));
	            Ebean.save((List) Yaml.load("torneo.yml"));
	        }
	    }

}
