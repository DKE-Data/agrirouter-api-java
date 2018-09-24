package com.dke.data.agrirouter.impl.common;

import com.dke.data.agrirouter.api.env.Environment;
import com.dke.data.agrirouter.api.exception.CouldNotFetchCookiesException;
import com.dke.data.agrirouter.impl.EnvironmentalService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.Cookie;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/** Resolve the cookies after login to use them for further requests. */
public class CookieResolverService extends EnvironmentalService {

  private static final HashMap<String, Set<Cookie>> cookieCache = new HashMap<>();
  private String username;
  private String password;

  public CookieResolverService(Environment environment) {
    super(environment);
  }

  public Set<Cookie> cookies(String username, String password) {
    this.logMethodBegin(username, password);

    if (StringUtils.isAnyBlank(username, password)) {
      throw new IllegalArgumentException(
          "Please provide valid - means no blank values - values for username and password.");
    }

    this.username = username;
    this.password = password;

    Set<Cookie> cookies;
    Optional<Set<Cookie>> cookiesFromCache = this.fetchCookiesFromCache();

    if (cookiesFromCache.isPresent() && !this.isAnyCookieExpired(cookiesFromCache.get())) {
      this.getNativeLogger().trace("Deliver cookies from cache.");
      cookies = cookiesFromCache.get();
    } else {
      cookies = this.deliverCookiesFromAgrirouter();
    }

    this.logMethodEnd(cookies);
    return cookies;
  }

  private Set<Cookie> deliverCookiesFromAgrirouter() {
    this.getNativeLogger().trace("Deliver cookies from agrirouter.");
    return this.fetchCookiesFromAgrirouter();
  }

  private Set<Cookie> fetchCookiesFromAgrirouter() {
    this.logMethodBegin(username, password);

    this.getNativeLogger().trace("Creating web client.");
    try (final WebClient webClient = new WebClient()) {
      return this.getCookiesFromWebClient(webClient);
    } catch (IOException e) {
      throw new CouldNotFetchCookiesException(e);
    }
  }

  private Set<Cookie> getCookiesFromWebClient(WebClient webClient) throws IOException {
    webClient.getOptions().setThrowExceptionOnScriptError(false);
    webClient.getOptions().setUseInsecureSSL(true);

    final String url = this.environment.getAgrirouterLoginUrl();
    this.getNativeLogger()
            .trace("Define URL '{}' for cookie resolving.", url);

    this.fillHtmlForm(webClient.getPage(url));

    this.getNativeLogger().trace("Read cookies from cookie manager.");
    Set<Cookie> cookiesFromWebClient = webClient.getCookieManager().getCookies();

    this.getNativeLogger().trace("Cookies {} found.", cookiesFromWebClient);
    cookieCache.put(username, cookiesFromWebClient);

    this.logMethodEnd(cookiesFromWebClient);
    return cookiesFromWebClient;
  }

  private void fillHtmlForm(HtmlPage page) throws IOException {
    final HtmlTextInput usernameTextInput = page.getHtmlElementById("j_username");
    usernameTextInput.setText(username);

    final HtmlPasswordInput passwordTextInput = page.getHtmlElementById("j_password");
    passwordTextInput.setText(password);

    final HtmlButton submitInput = page.getHtmlElementById("logOnFormSubmit");
    submitInput.click();
  }

  private boolean isAnyCookieExpired(Set<Cookie> cookies) {
    return null == cookies
        || cookies.isEmpty()
        || cookies
            .stream()
            .anyMatch(
                cookie ->
                    cookie.getExpires() != null
                        && cookie.getExpires().before(Date.from(Instant.now())));
  }

  private Optional<Set<Cookie>> fetchCookiesFromCache() {
    return Optional.ofNullable(cookieCache.get(username));
  }
}
