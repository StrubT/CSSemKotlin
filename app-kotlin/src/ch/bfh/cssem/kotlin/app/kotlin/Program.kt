package ch.bfh.cssem.kotlin.app.kotlin

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

/**
 * Entry point to the application.
 *
 * @param args command-line arguments (none supported)
 */
fun main(vararg args: String) {

	if (args.size > 0)
		throw IllegalArgumentException("No command-line arguments supported!")

	Application.launch(FXApplication::class.java, *args)
}

class FXApplication() : Application() {

	override fun start(primaryStage: Stage) {

		val loader = FXMLLoader()
		val root = loader.load<Parent>(FXWindow::class.java.getResourceAsStream("AddressBook.fxml"))
		val controller = loader.getController<FXWindow>()

		val scene = Scene(root)
		primaryStage.scene = scene

		primaryStage.icons.add(Image(FXWindow::class.java.getResource("icons/bfh.png").toExternalForm()))
		scene.stylesheets.add(FXWindow::class.java.getResource("AddressBook.css").toExternalForm())

		controller.initialize(primaryStage, scene)
		primaryStage.show()
	}
}
