package com.example.android.justjava;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int coffeePrice = 5;


    CharSequence tooMuchCoffee = "Can't order more than 100 cups.";
    CharSequence tooLittleCoffee = "Can't order less than 1 cup.";
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the ORDER button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String customerName = nameField.getText().toString();

        int addOnPrice = 0;

        //Checks to see if whipped cream box has been selected. If true, add $1 to addOnPrice
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        if(hasWhippedCream){
            addOnPrice = addOnPrice + 1;
        }

        //Checks to see if chocolate box has been selected. If true, add $2 to addOnPrice
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        if(hasChocolate){
            addOnPrice = addOnPrice + 2;
        }

        //Calculates totalPrice by calling calculatePrice and providing coffeePrice & addOnPrice
        int totalPrice = calculatePrice(coffeePrice, addOnPrice);

        String orderMessage = createOrderSummary(customerName, totalPrice, hasWhippedCream, hasChocolate);
        displayMessage(orderMessage);
    }

    /**
     * Calculates the total price of the order.
     */
    private int calculatePrice(int coffeePrice, int addOnPrice) {
        return quantity * (coffeePrice + addOnPrice);
    }

    /**
     * This method is called when the + (plus) button is clicked.
     */
    public void increment(View view){
        if(quantity > 99){
            Context context = getApplicationContext();
            //Shows a toast error message
            Toast.makeText(context, tooMuchCoffee, duration).show();
            //This return exits the method without executing any remaining code.
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - (minus) button is clicked.
     */
    public void decrement(View view){
        if(quantity < 1){
            Context context = getApplicationContext();
            //Shows a toast error message
            Toast.makeText(context, tooLittleCoffee, duration).show();
            //This return exits the method without executing any remaining code.
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message){
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Creates a summary of the order.
     *
     * @param customerName of the customer
     * @param totalPrice is the price of the order
     * @param hasWhippedCream whether or not customer has selected whipped cream topping
     * @param hasChocolate whether or not customer has selected chocolate
     * @return the order summary
     *
     */
    private String createOrderSummary(String customerName, int totalPrice, boolean hasWhippedCream, boolean hasChocolate){

        StringBuilder sb = new StringBuilder();

        sb.append("Name: " + customerName);
        sb.append("\nQuantity: " + quantity);
        sb.append("\nTotal: $" + totalPrice);
        sb.append("\nAdd whipped cream? " + hasWhippedCream);
        sb.append("\nAdd chocolate? " + hasChocolate);
        sb.append("\nThank You!");

        return sb.toString();
    }

}
