package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import ordination.DagligFast;
import ordination.DagligSkaev;
import ordination.Ordination;
import ordination.PN;
import ordination.Patient;

import java.util.NoSuchElementException;

public class VisOrdinationPane extends GridPane {
	private ListView<Patient> lstPatient = new ListView<>();
	private ListView<Ordination> lstOrdination = new ListView<>();
	private OrdinationDetailsPane ordinationDetailsPane = new OrdinationDetailsPane();

	private Controller controller;

	public VisOrdinationPane() {
		controller = Controller.getController();

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		this.add(new Label("Vælg patient"), 0, 0);
		this.add(lstPatient, 0, 1);
		lstPatient.getItems().setAll(controller.getAllPatienter());
		lstPatient
				.getSelectionModel()
				.selectedIndexProperty()
				.addListener(
						observable -> lstOrdination.getItems().setAll(
									lstPatient.getSelectionModel()
											.getSelectedItem()
											.getOrdinationer()));
		lstPatient.getSelectionModel().selectFirst();

		this.add(new Label("Vælg ordination"), 1, 0);
		this.add(lstOrdination, 1, 1);
		lstOrdination.getSelectionModel().selectedItemProperty()
				.addListener(observable -> updateDetails());

		this.add(new Label("Ordinationsdetaljer"), 2, 0);
		this.add(ordinationDetailsPane, 2, 1);
	}

	public void updateDetails() {
		Ordination ordination = lstOrdination.getSelectionModel()
				.getSelectedItem();
		ordinationDetailsPane.clear();
		if (ordination != null) {
			ordinationDetailsPane.clear();
			try {
				ordinationDetailsPane.setOrdination(ordination);
			}
			catch (NoSuchElementException exception) {

			}
			if (ordination instanceof DagligFast) {
				DagligFast dagligFast = (DagligFast) ordination;
				ordinationDetailsPane.setFast(dagligFast.getDoser()[0],
						dagligFast.getDoser()[1], dagligFast.getDoser()[2],
						dagligFast.getDoser()[3]);
			} else if (ordination instanceof DagligSkaev) {
				ordinationDetailsPane.setSkaev((DagligSkaev) ordination);
			} else if (ordination instanceof PN) {
				ordinationDetailsPane.setPN((PN) ordination);
			}
		}
	}

	public void updateControls() {
		int selected = 0;
		if (lstOrdination.getSelectionModel().getSelectedItem() != null) {
			selected = lstOrdination.getSelectionModel().getSelectedIndex();
		}
		lstOrdination.getItems().setAll(
				lstPatient.getSelectionModel().getSelectedItem()
						.getOrdinationer());
		lstOrdination.getSelectionModel().select(selected);
	}
}
