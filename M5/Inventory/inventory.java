
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {

        //Inventory, stores the different products
        Inventory myInventory = new Inventory(3);

        //Creation of the panel and setting it up
        JFrame frame = new JFrame("Inventory Manager");
        frame.setSize(1000, 750);
        frame.setLayout(new GridLayout(3, 1));

        //Fonts that are going to be used on the buttons and labels.
        Font buttonFontBig = new Font("Serif", Font.BOLD, 65);
        Font inputFieldFont = new Font("Serif", Font.BOLD, 45);

        Border border = BorderFactory.createLineBorder(Color.gray, 3);


        //The panel holding the top half of the screen. It includes the Input fields for the product along with a label telling
        //which one gives what
        JPanel InfoEnterPanel = new JPanel(new FlowLayout());

        JLabel productNameHeader = new JLabel("Product Name:");
        productNameHeader.setFont(inputFieldFont);
        productNameHeader.setBorder(border);

        JLabel productCostHeader = new JLabel("Product Cost:");
        productCostHeader.setFont(inputFieldFont);
        productCostHeader.setBorder(border);

        JLabel productQuantityHeader = new JLabel("Product Quantity:");
        productQuantityHeader.setFont(inputFieldFont);
        productQuantityHeader.setBorder(border);

        TextField productNameInput = new TextField();
        productNameInput.setFont(inputFieldFont);

        TextField productCostInput = new TextField();
        productCostInput.setFont(inputFieldFont);

        TextField productQuantityInput = new TextField();
        productQuantityInput.setFont(inputFieldFont);

        InfoEnterPanel.setLayout(new GridLayout(3, 2));
        InfoEnterPanel.add(productNameHeader);
        InfoEnterPanel.add(productNameInput);
        InfoEnterPanel.add(productCostHeader);
        InfoEnterPanel.add(productCostInput);
        InfoEnterPanel.add(productQuantityHeader);
        InfoEnterPanel.add(productQuantityInput);

        frame.add(InfoEnterPanel);
        //*****************************************************INFO ENTER SLOT***************************************//

        //Panel for the bottom half of the screen. It holds the console for the app (where feedback is going to be displayed),
        //along with the buttons to add or replace products
        JPanel outputAndButtons = new JPanel();

        JPanel output = new JPanel();
        output.setLayout(new GridLayout(1, 2));

        JTextArea outputMenu = new JTextArea("Here is displayed console");
        outputMenu.setFont(new Font("Comic Sans", Font.BOLD, 14));
        outputMenu.setBorder(border);
        outputMenu.setEditable(false);
        outputMenu.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(outputMenu);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        output.add(outputMenu);
        output.add(scrollPane);

        //The subpanel which will hold the buttons and input field which tells which product to change
        JPanel buttonPanel = new JPanel();

        JButton addProductButton = new JButton("Add Product");
        addProductButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        addProductButton.setBackground(Color.LIGHT_GRAY);

        JLabel explanationOfProductButton = new JLabel("Added in first empty slot");
        explanationOfProductButton.setBorder(border);
        explanationOfProductButton.setFont(new Font("Serif", Font.ITALIC, 20));

        JButton setProductButton = new JButton("Set Product");
        setProductButton.setFont(new Font("Comic Sans", Font.BOLD, 30));
        setProductButton.setBackground(Color.LIGHT_GRAY);


        TextField setProductSlotToChange = new TextField();
        setProductSlotToChange.setFont(buttonFontBig);
        setProductButton.setHorizontalAlignment(SwingConstants.CENTER);
        setProductButton.setVerticalAlignment(SwingConstants.CENTER);

        buttonPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.add(addProductButton);
        buttonPanel.add(explanationOfProductButton);
        buttonPanel.add(setProductButton);
        buttonPanel.add(setProductSlotToChange);

        outputAndButtons.setLayout(new GridLayout(1, 2));
        outputAndButtons.add(outputMenu);
        //outputAndButtons.add(scrollPane);
        outputAndButtons.add(buttonPanel);

        frame.add(outputAndButtons);

        //***********************************************************OUTPUT AND BUTTONS*******************************************************************************//

        //Display Inventory button. It is going to print all the inventory in the output zone.
        JButton displayInv = new JButton("Display Inventory");
        displayInv.setFont(new Font("Comic Sans", Font.BOLD, 40));
        displayInv.setBackground(Color.red);

        frame.add(displayInv);
        frame.setVisible(true);

        //The Add product logic
        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Check if there is added info everywhere
                if(productNameInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product NAME.");
                    return;
                }
                if(productCostInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product COST.");
                    return;
                }
                if(productQuantityInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product QUANTITY.");
                    return;
                }

                //Error handling for incorrect info type
                try{
                    String msg = myInventory.addProduct(new Product(productNameInput.getText(), Double.parseDouble(productCostInput.getText()), Integer.parseInt(productQuantityInput.getText())));
                    outputMenu.setText(msg);
                }catch(Exception exception){
                    outputMenu.setText("The added product is INVALID. Please check the quantity and price!");
                }

            }
        } );

        //Set product button logic
        setProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Checks if there is added info
                if(productNameInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product NAME.");
                    return;
                }
                if(productCostInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product COST.");
                    return;
                }
                if(productQuantityInput.getText().equals("")) {
                    outputMenu.setText("The added product is INVALID. Please add the Product QUANTITY.");
                    return;
                }

                //Error handling
                try{
                    myInventory.updateProduct(new Product(productNameInput.getText(), Double.parseDouble(productCostInput.getText()), Integer.parseInt(productQuantityInput.getText())), Integer.parseInt(setProductSlotToChange.getText()) - 1);
                    outputMenu.setText("Replaced the product successfully!");
                }catch(Exception exception){
                    outputMenu.setText("The added product is INVALID. Please check the quantity, the price and the current slot!");
                }
            }
        });

        //Display Inventory button logic
        displayInv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                outputMenu.setText(myInventory.displayAllProducts());
            }
        });
    }

}


class Product{
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String DisplayDetails(){
        String pr = "";
        pr += "Name: " + name + "\n";
        pr += "Price: " + price + "\n";
        pr += "Quantity: " + quantity + "\n";
        return pr;
    }
}

class Inventory {
    //All of the stored products
    private Product[] products;

    //Intiailiazes an inventory with a predetermined amount of products it can store
    public Inventory(int numProducts) {
        products = new Product[numProducts];
    }

    //Adds a product to the array in the first open slot. Gives an error if it is maxed out.
    public String addProduct(Product product) {
        String n = "";
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return "Added product successfully!";
            } else if (products[i] != null && i == products.length - 1) {
                return "Already have max number of items. Try replacing";
            }
        }
        return n;
    }

    //Replaces a specified product from the array
    public void updateProduct(Product product, int slot) {
        products[slot] = product;
    }

    //Displays inventory
    public String displayAllProducts() {
        String displayedProducts = "";

        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) {
                displayedProducts += products[i].DisplayDetails() + "\n";
            }
        }
        return displayedProducts;
    }
}


