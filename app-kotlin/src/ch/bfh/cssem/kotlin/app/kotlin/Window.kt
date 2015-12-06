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

private val addressBook = ServiceLoader.load(AddressBook::class.java).single()

class FXWindow : Initializable {

	@FXML protected lateinit var rootPane: BorderPane
	@FXML protected lateinit var titleLabel: Label
	@FXML protected lateinit var tabPane: TabPane
	@FXML protected lateinit var peopleTab: Tab
	@FXML protected lateinit var peopleTable: TableView<FXPerson>

	override fun initialize(location: URL?, resources: ResourceBundle?) {

		peopleTable.items = addressBook.fetchAllPeople().map { FXPerson(it) }.toCollection(FXCollections.observableArrayList())
	}

	fun initialize(stage: Stage, scene: Scene) {

		stage.title = titleLabel.text
		stage.minWidth = rootPane.minWidth
		stage.minHeight = rootPane.minHeight
		stage.width = rootPane.prefWidth
		stage.height = rootPane.prefHeight
	}
}
