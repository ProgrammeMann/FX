package com.springapp.mvc.controllers;


import com.springapp.mvc.entities.Store;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {

    ObservableList<Store> storeObservableList = FXCollections.observableArrayList();
    @FXML
    private ListView<Store> storeListView;
    @FXML
    private Parent root;

    @FXML
    public void initialize() {
        storeListView.setItems(storeObservableList);
                    Platform.runLater(new Runnable() {
                        public void run() {
                            try {
                                CloseableHttpClient httpClient = HttpClients.createDefault();
                                HttpGet httpGet = new HttpGet("http://localhost:8080/rest/stores");
                                CloseableHttpResponse response = httpClient.execute(httpGet);
                                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                                StringBuffer result = new StringBuffer();
                                result.append("{\"stores\":");
                                String s = "";
                                while ((s = rd.readLine()) != null) {
                                    result.append(s);
                                }
                                result.append("}");
                                JSONObject ob = new JSONObject(result.toString());
                                JSONArray arr = ob.getJSONArray("stores");
                                for (int i = 0; i < arr.length(); i++) {
                                    Store store = new Store();
                                    store.setCity(arr.getJSONObject(i).getString("city"));
                                    store.setAddress(arr.getJSONObject(i).getString("address"));
                                    store.setId(arr.getJSONObject(i).getLong("id"));
                                    storeObservableList.add(store);
                                }
                    storeListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getClickCount() == 2) {
                                Store store1 = storeListView.getSelectionModel().getSelectedItem();
                                Stage st = new Stage();
                                FXMLLoader loader = new FXMLLoader();
                                try {
                                    String xmlFile = "/fxml/store.fxml";
                                    Pane root = loader.load(getClass().getResourceAsStream(xmlFile));
                                    StoreController storeController = loader.getController();
                                    storeController.setThisStore(store1);
                                    storeController.loadGoods();
                                    storeController.setLabel(store1.toString());
                                    storeController.setStoreObservableList(storeObservableList);
                                    Scene scene = new Scene(root, 500, 500);
                                    st.setTitle("Current store");
                                    st.setScene(scene);
                                    st.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getOrdersWindow() {
        Stage st = new Stage();
        FXMLLoader loader = new FXMLLoader();
        try {
            Pane root = loader.load(getClass().getResource("/fxml/orders.fxml").openStream());
            OrdersController ordersController = loader.getController();
            ordersController.loadOrders();
            Scene scene = new Scene(root, 500, 500);
            st.setTitle("Orders");
            st.setScene(scene);
            st.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
