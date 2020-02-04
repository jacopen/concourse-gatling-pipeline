package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CFSimulation extends Simulation {

  val httpProtocol = http // 4
    .baseUrl(System.getProperty("target_url"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("CFSimulation")
    .exec(http("concourse")
      .get("/"))
    .pause(5)

  val requests=Integer.getInteger("requests", 1)
  val duration=Integer.getInteger("duration", 1)

  setUp(
    scn.inject(rampUsers(requests) during (duration seconds)) 
  ).protocols(httpProtocol)
}
