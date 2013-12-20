/**
 * Basado en el ejemplo de securesocial
 *
 */
package securesociallocal;

import play.Application;
import securesocial.core.Identity;
import securesocial.core.IdentityId;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.Token;

import models.Usuario;



public class ServicioUsuario extends BaseUserService {

    public ServicioUsuario(Application application) {
        super(application);
    }

    @Override
    public Identity doSave(Identity user) {
        int LU =  Usuario.find.where().eq("email",user.email().get()).findRowCount();
        
    	if(LU==0) {
    		// nuevo para guardar 
    		Usuario U= new Usuario();
    		U.email = user.email().get();
    		U.nombre = user.fullName();
    		U.Avatar = user.avatarUrl().get();
    		U.save();
    	}
       return user;
    }

    @Override
    public void doSave(Token token) {
    	
    }

    @Override
    public Identity doFind(IdentityId userId) {
    	int LU =  Usuario.find.where().eq("email",((Identity)userId).email().get()).findRowCount();
        
    	if(LU> 0 )
    		return  (Identity)userId; 
    	return null;
    }

    @Override
    public Token doFindToken(String tokenId) {
    	return null;
    }

    @Override
    public Identity doFindByEmailAndProvider(String email, String providerId) {
       return null;
    }

    @Override
    public void doDeleteToken(String uuid) {
    }

    @Override
    public void doDeleteExpiredTokens() {
    }
}