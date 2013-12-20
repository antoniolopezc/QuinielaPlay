/**
 * 
 */
package securesociallocal;

import play.Application;
import play.api.data.Form;
import play.api.mvc.Request;
import play.api.mvc.RequestHeader;
import play.api.templates.Html;
import scala.Option;
import scala.Tuple2;
import securesocial.controllers.PasswordChange.ChangeInfo;
import securesocial.controllers.Registration.RegistrationInfo;
import securesocial.controllers.TemplatesPlugin;
import securesocial.core.SecuredRequest;
import securesocial.core.SocialUser;


import securesociallocal.html.login;

/**
 * @author alopez1
 *
 */
public class TemplatesPluginLocal implements TemplatesPlugin {
	
	
	public TemplatesPluginLocal(Application a) {
	}
	/*
	 * (non-Javadoc)
	 * @see play.api.Plugin#enabled()
	 */
	@Override
	public boolean enabled() {
		return true;
	}

	@Override
	public void onStop() {
	}

	@Override
	public String getAlreadyRegisteredEmail(SocialUser arg0, RequestHeader arg1) {
		return null;
	}

	@Override
	public <A> Html getLoginPage(Request<A> R,
			Form<Tuple2<String, String>> F, Option<String> Option) {
			return login.render( F,Option);
	}

	@Override
	public <A> Option<String> getLoginPage$default$3() {
			return null;
	}

	@Override
	public <A> Html getPasswordChangePage(SecuredRequest<A> arg0,
			Form<ChangeInfo> arg1) {
			return null;
	}

	@Override
	public String getPasswordChangedNoticeEmail(SocialUser arg0,
			RequestHeader arg1) {
		return null;
	}

	@Override
	public <A> Html getResetPasswordPage(Request<A> arg0,
			Form<Tuple2<String, String>> arg1, String arg2) {
		return null;
	}

	@Override
	public String getSendPasswordResetEmail(SocialUser arg0, String arg1,
			RequestHeader arg2) {
		return null;
	}

	@Override
	public String getSignUpEmail(String arg0, RequestHeader arg1) {
		return null;
	}

	@Override
	public <A> Html getSignUpPage(Request<A> arg0, Form<RegistrationInfo> arg1,
			String arg2) {
		return null;
	}

	@Override
	public <A> Html getStartResetPasswordPage(Request<A> arg0, Form<String> arg1) {
		return null;
	}

	@Override
	public <A> Html getStartSignUpPage(Request<A> arg0, Form<String> arg1) {
		return null;
	}

	@Override
	public String getUnknownEmailNotice(RequestHeader arg0) {
		return null;
	}

	@Override
	public String getWelcomeEmail(SocialUser arg0, RequestHeader arg1) {
		return null;
	}

	@Override
	public void onStart() {
	}

}
