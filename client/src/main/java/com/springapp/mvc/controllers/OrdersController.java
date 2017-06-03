package com.springapp.mvc.controllers;

import com.springapp.mvc.entities.Good;
import com.springapp.mvc.entities.Order;
import com.springapp.mvc.entities.OrderStates;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class OrdersController implements Initializable {

    ObservableList<Order> orderList;
    @FXML
    private TableView<Order> orderTableView;
    @FXML
    private TableColumn<Order, Long> idCol;
    @FXML
    private TableColumn<Order, Long> userIdCol;
    @FXML
    private TableColumn<Order, String> goodNameCol;
    @FXML
    private TableColumn<Order, OrderStates> orderStateCol;
    @FXML
    private Button orderSentBtn;
    @FXML
    private Button orderDoneBtn;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        orderSentBtn.setDisable(true);
        orderDoneBtn.setDisable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<Order, Long>("id"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<Order, Long>("userId"));
        goodNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("good"));
        orderStateCol.setCellValueFactory(new PropertyValueFactory<Order, OrderStates>("state"));
        orderTableView.setOnMouseClicked(event -> {
            orderSentBtn.setDisable(false);
            orderDoneBtn.setDisable(false);
        });
    }

    public void loadOrders() {
        orderList = FXCollections.observableArrayList();
        Platform.runLater(() -> {
            try {
                HttpGet httpGet = new HttpGet("http://localhost:8080/rest/orders");
                HttpClient httpclient = HttpClientBuilder.create().build();
                HttpResponse response = httpclient.execute(httpGet);
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                result.append("{\"orders\":");
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                result.append("}");
                System.out.println(result);
                JSONObject ob = new JSONObject(result.toString());
                JSONArray arr = ob.getJSONArray("orders");
                List<Order> orders = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    Order order = new Order();
                    try {
                        OrderStates orderState = OrderStates.valueOf(arr.getJSONObject(i).getString("orderStates"));
                        order.setOrderStates(orderState);
                    }catch (IllegalArgumentException e){

                    }
                    order.setId(arr.getJSONObject(i).getLong("id"));
                    JSONObject userObj = arr.getJSONObject(i).getJSONObject("customer");
                    order.setUserId(userObj.getLong("id"));
                    JSONObject goodInOrder = arr.getJSONObject(i).getJSONObject("orderItem");
                    Good good = new Good();
                    good.setCount(goodInOrder.getInt("count"));
                    JSONObject jgood = goodInOrder.getJSONObject("good");
                    good.setId(jgood.getLong("id"));
                    good.setName(jgood.getString("name"));
                    good.setPrice(jgood.getInt("price"));
                    order.setGood(good.getName());
                    orders.add(order);
                }
                orderList.addAll(orders);
                orderTableView.setItems(orderList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void doOrderSENDING() {
        changeOrderState(OrderStates.SENDING);
    }

    public void doOrderDONE() {
        changeOrderState(OrderStates.DONE);
        System.out.println("DONE");
    }

    private void changeOrderState(OrderStates orderStates) {
        Order order = orderTableView.getSelectionModel().getSelectedItem();
        order.setOrderStates(orderStates);
        Platform.runLater(() -> {
            try {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String params = "id=" + order.getId() + "&state=" + orderStates;
                System.out.println(params);
                HttpGet httpGet = new HttpGet("http://localhost:8080/rest/changeState?" + params);
                CloseableHttpResponse response = httpclient.execute(httpGet);
                System.out.println(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
