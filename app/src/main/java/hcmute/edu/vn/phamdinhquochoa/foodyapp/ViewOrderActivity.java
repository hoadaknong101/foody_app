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
        if(order.getStatus().equals("Delivered") || order.getStatus().equals("Canceled")){
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
        btnCancel.setOnClickListener(view -> finish());
    }

    private void LoadData(){
        tvDate.setText(String.format("Ngày đặt hàng: %s", order.getDateOfOrder()));
        tvAddress.setText(String.format("Địa chỉ: %s", order.getAddress()));
        tvPrice.setText(String.format("Tổng giá trị: %s", getRoundPrice(order.getTotalValue())));
        tvStatus.setText(String.format("Trạng thái giao hàng: %s",order.getStatus()));

        ArrayList<OrderDetail> orderDetailArrayList = dao.getCartDetailList(order.getId());
        if(orderDetailArrayList.size() > 0){;
            for(OrderDetail orderDetail : orderDetailArrayList){
                Food food = dao.getFoodById(orderDetail.getFoodId());
                Restaurant restaurant = dao.getRestaurantInformation(food.getRestaurantId());
                FoodSize foodSize = dao.getFoodSize(orderDetail.getFoodId(), orderDetail.getSize());

                CartCard card = new CartCard(this, food, restaurant.getAddress(), orderDetail, false);
                card.setOnClickListener(view -> {
                    Intent intent = new Intent(this, FoodDetailsActivity.class);
                    ArrayList<FoodSize> foodSizeArrayList = HomeActivity.dao.getAllFoodSize(food.getId());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("food", food);
                    bundle.putSerializable("restaurant", restaurant);
                    bundle.putSerializable("foodSizeS", foodSizeArrayList.get(0));

                    if(foodSizeArrayList.size() < 3){
                        bundle.putSerializable("foodSizeM", foodSizeArrayList.get(1));
                    }
                    if(foodSizeArrayList.size() == 3) {
                        bundle.putSerializable("foodSizeM", foodSizeArrayList.get(1));
                        bundle.putSerializable("foodSizeL", foodSizeArrayList.get(2));
                    }

                    bundle.putSerializable("defaultFoodSize", foodSize);

                    intent.putExtra("foodDetail", bundle);
                    try {
                        startActivity(intent);
                    } catch (Exception e){
                        Toast.makeText(this, "Không thể hiển thị thông tin!", Toast.LENGTH_SHORT).show();
                    }
                });

                layout_container.addView(card);
            }
        }

    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}