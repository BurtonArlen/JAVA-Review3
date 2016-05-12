import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Welcome to MultiVersal Cuts");
  }
  @Test
  public void rootAdminTest() {
    goTo("http://localhost:4567/admin");
    assertThat(pageSource()).contains("Stylists");
  }

  @Test
  public void appointmnetTest() {
    goTo("http://localhost:4567/");
    click("#appoint", withText("make an appointment"));
    click(".Rick", withText("Request Appointment"));
    fill("#userName").with("Squanchy");
    submit("#appConf");
    assertThat(pageSource()).contains("Thank you for scheduling an appointment with Rick, Squanchy. We'll see you then.");
  }

  @Test
  public void stylistsDisplayedTest() {
    goTo("http://localhost:4567/admin");
    assertThat(pageSource()).contains("Rick");
    assertThat(pageSource()).contains("Jan Michael Vincent");
  }

  @Test
  public void clientsDisplayedTest() {
    goTo("http://localhost:4567/admin");
    click("a#Rick", withText("Rick"));
    assertThat(pageSource()).contains("Squanchy");
  }

}
