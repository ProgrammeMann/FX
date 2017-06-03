package com.springapp.mvc.controllers;

import com.springapp.mvc.entities.Good;
import com.springapp.mvc.entities.Store;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by programmemann on 03.06.17.
 */
public class StoreController implements Initializable {
    @FXML
    Button button;
    @FXML
    private Label store;
    @FXML
    private FlowPane flowPane;
    @FXML
    private ListView<Good> goodListView;
    @FXML
    private TextField count;
    @FXML
    private Label messages;
    @FXML
    private TextField movedCount;
    @FXML
    private ListView<Store> storeListView;
    @FXML
    private Button moveButton;

    private ObservableList<Store> storeObservableList;
    private Store thisStore;
    private ObservableList<Good> goodObservableList;

    public void setStoreObservableList(ObservableList<Store> storeObservableList) {
        this.storeObservableList = storeObservableList;
    }

    public Store getThisStore() {
        return thisStore;
    }

    public void setThisStore(Store thisStore) {
        this.thisStore = thisStore;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moveButton.setDisable(true);
        flowPane.setMaxWidth(400);
        button.setDisable(true);
        goodObservableList = FXCollections.observableArrayList();
        storeObservableList = FXCollections.observableArrayList();
        storeListView.setItems(storeObservableList);
        goodListView.setItems(goodObservableList);
        goodListView.setMaxHeight(250);
        goodListView.setMaxWidth(200);
        goodListView.setOnMouseClicked(event -> {
            button.setDisable(false);
            if (storeListView.getSelectionModel().getSelectedItem() != null)
                moveButton.setDisable(false);
            if (goodListView.getSelectionModel().getSelectedItem() != null) {
                count.setText(Integer.toString(goodListView.getSelectionModel().getSelectedItem().getCount()));
            }
            messages.setText("");
        });
        storeListView.setOnMouseClicked(event -> {
            if (goodListView.getSelectionModel().getSelectedItem() != null)
                moveButton.setDisable(false);
        });
        storeListView.setMaxSize(200, 250);
        count.setMaxWidth(180);
        store.setMaxWidth(400);
    }

    public void migrate() {
        Platform.runLater(() -> {
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String params = "from=" + thisStore.getId()
                        + "&to=" + storeListView.getSelectionModel().getSelectedItem().getId() + "&goodId=" + goodListView.getSelectionModel().getSelectedItem().getId();
                HttpGet httpGet = new HttpGet("http://localhost:8080/rest/moveGood?" + params);
                movedCount.setText("");
            } catch (Exception e) {
            }
        });
    }

    public void update() {
        try {
            int value = Integer.valueOf(count.getText());
            if (value < 0)
                throw new Exception();
            Platform.runLater(() -> {
                try {
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    String params = "goodId=" + goodListView.getSelectionModel().getSelectedItem().getId() +
                            "&count=" + value;
                    HttpGet httpGet = new HttpGet("http://localhost:8080/rest/update?" + params);
                    CloseableHttpResponse response = httpclient.execute(httpGet);
                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    StringBuffer result = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                    if (result.toString().contains("success")) {
                        messages.setText("Update success");
                        goodListView.getSelectionModel().getSelectedItem().setCount(value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadGoods() {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8080/rest/store/" + thisStore.getId());
            CloseableHttpResponse response = httpclient.execute(httpGet);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder result = new StringBuilder();
            result.append("{\"goods\":");
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            result.append("}");
            JSONObject ob = new JSONObject(result.toString());
            JSONArray goods = ob.getJSONArray("goods");
            for (int i = 0; i < goods.length(); i++) {
                Good g = new Good();
                g.setId(goods.getJSONObject(i).getLong("id"));
                g.setName(goods.getJSONObject(i).getJSONObject("goodInfo").getString("name"));
                g.setPrice(goods.getJSONObject(i).getJSONObject("goodInfo").getInt("price"));
                g.setCount(goods.getJSONObject(i).getInt("count"));
                goodObservableList.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setLabel(String text) {
        store.setText(text);
    }

}
