package dk.frankbille.scoreboard;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import dk.frankbille.scoreboard.daily.DailyGamePage;
import dk.frankbille.scoreboard.player.PlayerEditPage;
import dk.frankbille.scoreboard.player.PlayerListPage;
import dk.frankbille.scoreboard.player.PlayerPage;
import dk.frankbille.scoreboard.security.LoginPage;
import dk.frankbille.scoreboard.security.ScoreBoardAuthorizationStrategy;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see dk.frankbille.scoreboard.Start#main(String[])
 */
public class ScoreBoardApplication extends WebApplication {
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<DailyGamePage> getHomePage() {
		return DailyGamePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));

		getSecuritySettings().setAuthorizationStrategy(new ScoreBoardAuthorizationStrategy());

		mountPage("/daily", DailyGamePage.class);
		mountPage("/player/edit", PlayerEditPage.class);
		mountPage("/player", PlayerPage.class);
		mountPage("/players", PlayerListPage.class);
		mountPage("/login", LoginPage.class);
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new ScoreBoardSession(request);
	}

}