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
class FXApplication : Application() {

	/**
	 * Starts the JavaFX [Application].
	 * Loads the resources and creates the [Window][javafx.stage.Window].
	 */
	override fun start(primaryStage: Stage) {

		val loader = FXMLLoader()

		primaryStage.icons.add(Image(FXResources.logo.toExternalForm()))

		primaryStage.scene = Scene(FXResources.fxml.openStream().use { loader.load<Parent>(it) })
		primaryStage.scene.stylesheets.add(FXResources.stylesheet.toExternalForm())

		loader.getController<FXWindow>().initialize(primaryStage, primaryStage.scene)
		primaryStage.show()
	}
}

/**
 * Contains the [URL]s to the different resources.
 *
 * @property fxml       [FXML] file containing the [Window][javafx.stage.Window] components
 * @property stylesheet CSS [Stylesheet][com.sun.javafx.css.Stylesheet]
 * @property logo       logo of the Bern University of Applied Sciences
 *
 * @author strut1 & touwm1
 */
object FXResources {

	val fxml = FXResources::class.java.getResource("AddressBook.fxml")!!
	val stylesheet = FXResources::class.java.getResource("AddressBook.css")!!
	val logo = FXResources::class.java.getResource("icons/bfh.png")!!
}
