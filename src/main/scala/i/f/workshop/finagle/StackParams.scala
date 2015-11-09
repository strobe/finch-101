package i.f.workshop.finagle

import com.twitter.finagle.param
import com.twitter.finagle.http.{Response, Request}
import com.twitter.finagle.service.TimeoutFilter
import com.twitter.finagle.transport.Transport
import com.twitter.finagle.{Http, Service}
import com.twitter.util._
import com.twitter.conversions.time._

object StackParams extends App {

  implicit val timer: Timer = new JavaTimer()
  val service: Service[Request, Response] = new Service[Request, Response] {
    def apply(req: Request): Future[Response] =
      Future.sleep(5.seconds).map(_ => Response())
  }

  val monitor: Monitor = new Monitor {
    def handle(exc: Throwable): Boolean = {
      println(exc)

      false
    }
  }

  Await.ready(Http.server
    .configured(TimeoutFilter.Param(1.seconds))
    .configured(Transport.Verbose(true))
    .configured(param.Monitor(monitor))
    .serve(":8081", service)
  )
}
