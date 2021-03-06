package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.FoodSize;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Notify;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.NotifyToUser;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Order;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.OrderDetail;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.CartCard;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;

public class ViewOrderActivity extends AppCompatActivity {

    private LinearLayout layout_container;
    private TextView tvDate, tvPrice, tvAddress, tvStatus;
    private DAO dao;
    private Order order;
    public static Integer userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        dao = new DAO(this);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");

        referencesComponent();
        LoadData();
    }

    public void referencesComponent(){
        layout_container = findViewById(R.id.layout_container_order_detail);

        tvDate = findViewById(R.id.tvDateMakeOrderView);
        tvPrice = findViewById(R.id.tvOrderPriceView);
        tvAddress = findViewById(R.id.tvOrderAddressView);
        tvStatus = findViewById(R.id.tvOrderStatusView);

        Button btnDeleteOrder = findViewById(R.id.btnDeleteOrder);
        if(order.getStatus().equals("Delivered") || order.getStatus().equals("Canceled")){
            btnDeleteOrder.setEnabled(false);
            btnDeleteOrder.setBackgroundColor(Color.GRAY);
        }
        btnDeleteOrder.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("B???n c?? mu???n x??a m??n ????n h??ng n??y kh??ng?");
            dialog.setPositiveButton("C??", (dialogInterface, i) -> {
                order.setStatus("Canceled");
                dao.updateOrder(order);
                Toast.makeText(this, "????n h??ng ???? b??? h???y!", Toast.LENGTH_SHORT).show();

                // User Notify
                String content = "????n h??ng c???a b???n ???? b??? h???y!\nT???ng gi?? tr??? ????n h??ng l?? " + order.getTotalValue()
                        + " VN?? \nB???n c?? th??? g??p ?? h??? tr??? qua s??? ??i???n tho???i 0947679570!";
                dao.addNotify(new Notify(1, "Th??ng b??o v??? ????n h??ng!",
                        content, dao.getDate()));
                dao.addNotifyToUser(new NotifyToUser(dao.getNewestNotifyId(), userID));
                finish();
            });
            dialog.setNegativeButton("Kh??ng", (dialogInterface, i) -> {});
            dialog.show();
        });

        Button btnCancel = findViewById(R.id.btnCancelOrderView);
        btnCancel.setOnClickListener(view -> finish());
    }

    private void LoadData(){
        tvDate.setText(String.format("Ng??y ?????t h??ng: %s", order.getDateOfOrder()));
        tvAddress.setText(String.format("?????a ch???: %s", order.getAddress()));
        tvPrice.setText(String.format("T???ng gi?? tr???: %s", getRoundPrice(order.getTotalValue())));
        tvStatus.setText(String.format("Tr???ng th??i giao h??ng: %s",order.getStatus()));

        ArrayList<OrderDetail> orderDetailArrayList = dao.getCartDetailList(order.getId());
        if(orderDetailArrayList.size() > 0){;
            for(OrderDetail orderDetail : orderDetailArrayList){
                Food food = dao.getFoodById(orderDetail.getFoodId());
                Restaurant restaurant = dao.getRestaurantInformation(food.getRestaurantId());
                FoodSize foodSize = dao.getFoodSize(orderDetail.getFoodId(), orderDetail.getSize());

                CartCard card = new CartCard(this, food, restaurant.getName(), orderDetail, false);
                card.setOnClickListener(view -> {
                    FoodDetailsActivity.foodSize = foodSize;
                    Intent intent = new Intent(this, FoodDetailsActivity.class);
                    intent.putExtra("food", food);
                    try {
                        startActivity(intent);
                    } catch (Exception e){
                        Toast.makeText(this, "Kh??ng th??? hi???n th??? th??ng tin!", Toast.LENGTH_SHORT).show();
                    }
                });

                layout_container.addView(card);
            }
        }

    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VN??";
    }
}