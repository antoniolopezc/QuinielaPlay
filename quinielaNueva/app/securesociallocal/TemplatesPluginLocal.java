/**
 * 
 */
package securesociallocal;

import play.Application;
import play.api.data.Form;
import play.api.mvc.Request;
import play.api.mvc.RequestHeader;
import play.api.templates.Html;
import play.api.templates.Txt;
import scala.Option;
import scala.Tuple2;
import securesocial.controllers.PasswordChange.ChangeInfo;
import securesocial.controllers.Registration.RegistrationInfo;
import securesocial.controllers.TemplatesPlugin;
import securesocial.core.Identity;
import securesocial.core.SecuredRequest;

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
	public <A> Html getResetPasswordPage(Request<A> arg0,
			Form<Tuple2<String, String>> arg1, String arg2) {
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
	public void onStart() {
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getAlreadyRegisteredEmail(
			Identity arg0, RequestHeader arg1) {
		return null;
	}
	@Override
	public <A> Html getNotAuthorizedPage(Request<A> arg0) {
		return null;
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getPasswordChangedNoticeEmail(
			Identity arg0, RequestHeader arg1) {
		return null;
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getSendPasswordResetEmail(
			Identity arg0, String arg1, RequestHeader arg2) {
		return null;
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getWelcomeEmail(Identity arg0,
			RequestHeader arg1) {
		return null;
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getSignUpEmail(String arg0,
			RequestHeader arg1) {
		return null;
	}
	@Override
	public Tuple2<Option<Txt>, Option<Html>> getUnknownEmailNotice(
			RequestHeader arg0) {
		return null;
	}

}
