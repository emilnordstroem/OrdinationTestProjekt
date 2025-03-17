package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import ordination.Laegemiddel;

public class StatistikPane extends GridPane {
	private TextField ordinationerPerVaegtPerLaegemiddel = new TextField();
	private TextField txfVaegtFra = new TextField();
	private TextField txfVaegtTil = new TextField();
	private ComboBox<Laegemiddel> lstLaegemidler = new ComboBox<>();
	private Controller controller;

	public StatistikPane() {
		controller = Controller.getController();
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);
		initContent();
	}

	private void initContent() {
		GridPane pane1 = new GridPane();
		pane1.setHgap(20);
		pane1.setVgap(10);
		pane1.setPadding(new Insets(10));

		GridPane pane2 = new GridPane();
		pane2.setHgap(20);
		pane2.setVgap(10);
		pane2.setPadding(new Insets(10));

		pane1.setStyle("-fx-border-color: grey;");
		pane2.setStyle("-fx-border-color: grey;");

		Label label = new Label("Antal ordinationer");
		label.setFont(new Font(25));
		this.add(label, 0, 0, 2, 1);

		txfVaegtFra.setMaxWidth(40);
		txfVaegtTil.setMaxWidth(40);
		pane1.add(new Label("Vægt fra: "), 0, 0);
		pane1.add(txfVaegtFra, 1, 0);

		pane1.add(new Label("Vægt til: "), 0, 1);
		pane1.add(txfVaegtTil, 1, 1);

		lstLaegemidler.getItems().setAll(controller.getAllLaegemidler());
		pane1.add(new Label("Lægemiddel: "), 0, 2);
		pane1.add(lstLaegemidler, 1, 2);
		this.add(pane1, 0, 1);

		pane2.add(new Label("Antal: "), 0, 0);
		ordinationerPerVaegtPerLaegemiddel.setEditable(false);
		pane2.add(ordinationerPerVaegtPerLaegemiddel, 1, 0);
		this.add(pane2, 0, 2);

		// Adding listeners
		txfVaegtFra.setOnKeyReleased(event -> updateDetails());
		txfVaegtTil.setOnKeyReleased(event -> updateDetails());
		lstLaegemidler.setOnAction(event -> updateDetails());

		updateDetails();
	}

	public void updateDetails() {
		try {
			int vFra = Integer.parseInt(txfVaegtFra.getText());
			int vTil = Integer.parseInt(txfVaegtTil.getText());
			Laegemiddel laegemiddel = lstLaegemidler.getSelectionModel()
					.getSelectedItem();
			int antal = controller.antalOrdinationerPrVaegtPrLaegemiddel(vFra, vTil,
					laegemiddel);
			ordinationerPerVaegtPerLaegemiddel.setText(antal + "");
		} catch (NumberFormatException e) {
			ordinationerPerVaegtPerLaegemiddel.setText("");
		}
		catch (NullPointerException e) {
			ordinationerPerVaegtPerLaegemiddel.setText("");
		}
	}
	
	public void updateControls() {
		ordinationerPerVaegtPerLaegemiddel.clear();
		txfVaegtFra.clear();
		txfVaegtTil.clear();
		lstLaegemidler.getSelectionModel().clearSelection();
		
	}
}
