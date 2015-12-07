@file:JvmName("Program")
@file:JvmMultifileClass

package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXPerson
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TableView
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import java.net.URL
import java.util.ResourceBundle
import java.util.ServiceLoader

/**
 * [AddressBook] instance to use throughout the project.
 */
private val addressBook = ServiceLoader.load(AddressBook::class.java).single()

/**
 * Contains the logic to control the JavaFX [Window][javafx.stage.Window].
 *
 * @property rootPane    [FXML] node
 * @property titleLabel  [FXML] node
 * @property tabPane     [FXML] node
 * @property peopleTab   [FXML] node
 * @property peopleTable [FXML] node
 *
 * @author strut1 & touwm1
 */
class FXWindow : Initializable {

	@FXML protected lateinit var rootPane: BorderPane
	@FXML protected lateinit var titleLabel: Label
	@FXML protected lateinit var tabPane: TabPane
	@FXML protected lateinit var peopleTab: Tab
	@FXML protected lateinit var peopleTable: TableView<FXPerson>

	override fun initialize(location: URL?, resources: ResourceBundle?) {

		peopleTable.items = addressBook.fetchAllPeople().map { FXPerson(it) }.toCollection(FXCollections.observableArrayList())
	}

	/**
	 * Initialise the [Stage] and [Scene] with data from the [FXML] file.
	 */
	fun initialize(stage: Stage, scene: Scene) {

		stage.title = titleLabel.text
		stage.minWidth = rootPane.minWidth
		stage.minHeight = rootPane.minHeight
		stage.width = rootPane.prefWidth
		stage.height = rootPane.prefHeight
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

	val fxml = FXResources::class.java.getResource("AddressBook.fxml")
	val stylesheet = FXResources::class.java.getResource("AddressBook.css")
	val logo = FXResources::class.java.getResource("icons/bfh.png")
}
