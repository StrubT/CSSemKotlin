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
 * @property rootPane    [FXML] [Node][javafx.scene.Node]
 * @property titleLabel  [FXML] [Node][javafx.scene.Node]
 * @property tabPane     [FXML] [Node][javafx.scene.Node]
 * @property peopleTab   [FXML] [Node][javafx.scene.Node]
 * @property peopleTable [FXML] [Node][javafx.scene.Node]
 *
 * @author strut1 & touwm1
 */
class FXWindow : Initializable {

	@FXML protected lateinit var rootPane: BorderPane
	@FXML protected lateinit var titleLabel: Label
	@FXML protected lateinit var tabPane: TabPane
	@FXML protected lateinit var peopleTab: Tab
	@FXML protected lateinit var peopleTable: TableView<FXPerson>

	/**
	 * Initialises the [Window][javafx.stage.Window] controller after the root [Node][javafx.scene.Node] has been processed completely.
	 */
	override fun initialize(location: URL?, resources: ResourceBundle?) {

		peopleTable.items = addressBook.fetchAllPeople().mapTo(FXCollections.observableArrayList()) { FXPerson(it) }
	}

	/**
	 * Initialises the [Stage] and [Scene] with data from the [FXML] file.
	 */
	fun initialize(stage: Stage, scene: Scene) {

		stage.minWidth = rootPane.minWidth
		stage.minHeight = rootPane.minHeight
		stage.width = rootPane.prefWidth
		stage.height = rootPane.prefHeight

		stage.title = titleLabel.text
	}
}
