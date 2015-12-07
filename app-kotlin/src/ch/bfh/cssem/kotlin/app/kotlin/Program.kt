@file:JvmName("Program")
@file:JvmMultifileClass

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
 *
 * @author strut1 & touwm1
 */
fun main(vararg args: String) {

	if (args.size > 0)
		throw IllegalArgumentException("No command-line arguments supported!")

	Application.launch(FXApplication::class.java, *args)
}

/**
 * Contains the logic to start the JavaFX [Application].
 *
 * @author strut1 & touwm1
 */
class FXApplication() : Application() {

	override fun start(primaryStage: Stage) {

		val loader = FXMLLoader()
		val root = loader.load<Parent>(FXResources.fxml.openStream())
		val controller = loader.getController<FXWindow>()

		val scene = Scene(root)
		primaryStage.scene = scene

		primaryStage.icons.add(Image(FXResources.logo.toExternalForm()))
		scene.stylesheets.add(FXResources.stylesheet.toExternalForm())

		controller.initialize(primaryStage, scene)
		primaryStage.show()
	}
}
