/**
 * 
 */
package securesociallocal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import play.Application;
import securesocial.core.java.BaseUserService;
import securesocial.core.java.SocialUser;
import securesocial.core.java.Token;
import securesocial.core.java.UserId;
/**
 * @author alopez1
 *
 */
public class ServicioUsuario extends BaseUserService {
	private HashMap<String, Token> tokens = new HashMap<String, Token>();
	private HashMap<String, SocialUser> usuarios = new HashMap<String, SocialUser>();
	
	public ServicioUsuario(Application application) {
		super(application);
		// TODO Auto-generated constructor stub
	}

	    @Override
	    public void doSave(Token token) {
	        tokens.put(token.uuid, token);
	    }

	    @Override
	    public Token doFindToken(String tokenId) {
	        return tokens.get(tokenId);
	    }

	    @Override
	    public void doDeleteToken(String uuid) {
	        tokens.remove(uuid);
	    }

	    @Override
	    public void doDeleteExpiredTokens() {
	        Iterator<Map.Entry<String,Token>> iterator = tokens.entrySet().iterator();
	        while ( iterator.hasNext() ) {
	            Map.Entry<String, Token> entry = iterator.next();
	            if ( entry.getValue().isExpired() ) {
	                iterator.remove();
	            }
	        }
	    }

		@Override
		public void doSave(SocialUser SU) {
			usuarios.put( SU.id.id, SU);
		}

		@Override
		public SocialUser doFind(UserId UI) {
			SocialUser ru=usuarios.get(UI.id);
			return ru;
		}

		@Override
		public SocialUser doFindByEmailAndProvider(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return null;
		}
}
