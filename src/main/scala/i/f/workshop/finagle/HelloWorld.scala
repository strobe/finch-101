package i.f.workshop.finagle

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{ListeningServer, Http, Service}
import com.twitter.io.Buf
import com.twitter.util.{Await, Future}

object HelloWorld extends App {

  val service: Service[Request, Response] = new Service[Request, Response] {
    def apply(req: Request): Future[Response] = {
      val rep: Response = Response()
      rep.content = Buf.Utf8("Hello, World!")

      Future.value(rep)
    }
  }

  val server: ListeningServer = Http.server.serve(":8081", service)
  Await.ready(server)
}
