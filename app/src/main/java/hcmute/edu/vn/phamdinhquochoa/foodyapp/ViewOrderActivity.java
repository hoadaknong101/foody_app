package hcmute.edu.vn.phamdinhquochoa.foodyapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Notify;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.NotifyToUser;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Order;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.OrderDetail;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.User;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.CartCard;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.dao.DAO;
import hcmute.edu.vn.phamdinhquochoa.foodyapp.fragments.ChatFragment;

public class ViewOrderActivity extends AppCompatActivity {

    private LinearLayout layout_container;
    private TextView tvDate, tvPrice, tvAddress, tvStatus;
    private DAO dao;
    private Order order;
    public static User user;

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
        if(order.getStatus().equals("Delivered")){
            btnDeleteOrder.setEnabled(false);
        }
        btnDeleteOrder.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Bạn có muốn xóa món đơn hàng này không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                order.setStatus("Canceled");
                dao.updateOrder(order);
                Toast.makeText(this, "Đơn hàng đã bị hủy!", Toast.LENGTH_SHORT).show();

                // User Notify
                String content = "Đơn hàng của bạn đã bị hủy!\nTổng giá trị đơn hàng là " + order.getTotalValue()
                        + " VNĐ \nBạn có thể góp ý hỗ trợ qua số điện thoại 0947679570!";
                dao.addNotify(new Notify(1, "Thông báo về đơn hàng!",
                        content, dao.getDate()));
                dao.addNotifyToUser(new NotifyToUser(dao.getNewestNotifyId(), user.getId()));
                finish();
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        Button btnCancel = findViewById(R.id.btnCancelOrderView);
        btnCancel.setOnClickListener(view -> {
            finish();
        });
    }

    private void LoadData(){
        tvDate.setText(String.format("Ngày đặt hàng: %s", order.getDateOfOrder()));
        tvAddress.setText(String.format("Địa chỉ: %s", order.getAddress()));
        tvPrice.setText(String.format("Tổng giá trị: %s", getRoundPrice(order.getTotalValue())));
        tvStatus.setText(String.format("Trạng thái giao hàng: %s",order.getStatus()));

        ArrayList<OrderDetail> orderDetailArrayList = dao.getCartDetailList(order.getId());
        if(orderDetailArrayList.size() > 0){
            Food food;
            Restaurant restaurant;
            for(OrderDetail orderDetail : orderDetailArrayList){
                food = dao.getFoodById(orderDetail.getFoodId());
                restaurant = dao.getRestaurantInformation(food.getRestaurantId());
                CartCard card = new CartCard(this, food, restaurant.getAddress(), orderDetail, false);
                layout_container.addView(card);
            }
        }

    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}