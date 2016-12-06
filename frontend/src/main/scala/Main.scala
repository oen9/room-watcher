import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js.JSApp

object Main extends JSApp {
  @scala.scalajs.js.annotation.JSExport
  override def main(): Unit = {
    println("Hello my friend")
  }

  @scala.scalajs.js.annotation.JSExport
  def runPage(): Unit = {
    appendPar(document.body, "Hello world!!")
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }
}
