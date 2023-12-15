import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class User {
    private String id;
    private String username;
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Product {
    private String productNumber;
    private String productName;
    private String description;
    private int quantity;
    private double price;

    public Product(String productNumber, String productName, String description, int quantity, double price) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product Number: " + productNumber + "\n" +
                "Product Name: " + productName + "\n" +
                "Description: " + description + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: $" + price + "\n";
    }
}

class AddProductFrame extends JFrame implements ActionListener {
    private JTextField productNumberField;
    private JTextField productNameField;
    private JTextField descriptionField;
    private JTextField quantityField;
    private JTextField priceField;
    private JButton addButton;
    private JButton confirmButton;  // New button for confirmation
    private JTextArea productListArea;
    private JScrollPane scrollPane;
    private List<Product> productList;

    public AddProductFrame() {
        super("Add Product");

        productList = new ArrayList<>();

        // Create components
        productNumberField = new JTextField();
        productNameField = new JTextField();
        descriptionField = new JTextField();
        quantityField = new JTextField();
        priceField = new JTextField();
        addButton = new JButton("Add Product");
        confirmButton = new JButton("Confirm");  // New button for confirmation
        productListArea = new JTextArea(20, 40);
        productListArea.setEditable(false);  // Make the text area read-only
        scrollPane = new JScrollPane(productListArea);

        // Set layouts
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to frame
        add(new JLabel("Product Number:"));
        add(productNumberField);
        add(new JLabel("Product Name:"));
        add(productNameField);
        add(new JLabel("Description:"));
        add(descriptionField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Price:"));
        add(priceField);
        add(addButton);
        add(confirmButton);  // Add the confirm button
        add(scrollPane);

        // Add action listeners
        addButton.addActionListener(this);
        confirmButton.addActionListener(this);  // Add listener for the confirm button

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                String productNumber = productNumberField.getText();
                String productName = productNameField.getText();
                String description = descriptionField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                Product newProduct = new Product(productNumber, productName, description, quantity, price);
                productList.add(newProduct);

                updateProductListArea();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for quantity and price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == confirmButton) {  // Handle confirm button click
          //  openConfirmationFrame();
        }
    }

    private void clearFields() {
        productNumberField.setText("");
        productNameField.setText("");
        descriptionField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    private void updateProductListArea() {
        productListArea.setText("");
        for (Product product : productList) {
            productListArea.append(product.toString() + "---------------\n");
        }
    }
    
    class ConfirmationFrame extends JFrame {
    public ConfirmationFrame(List<Product> productList) {
        super("Confirmation");

        JTextArea confirmationArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(confirmationArea);

        StringBuilder confirmationText = new StringBuilder("Products to be confirmed:\n\n");
        for (Product product : productList) {
            confirmationText.append(product.toString()).append("---------------\n");
        }
        confirmationArea.setText(confirmationText.toString());

        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

    private void openConfirmationFrame() {
        ConfirmationFrame confirmationFrame = new ConfirmationFrame(getProductList());
        confirmationFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Handle window closing event if needed
            }
        });
    }

    public List<Product> getProductList() {
        return productList;
    }
}

class RegisterFrame extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registerButton;
    private JButton signInButton; // Updated label to "Sign-In"
    private LoginFrame loginFrame;
    private List<User> registeredUsers;

    public RegisterFrame(LoginFrame loginFrame) {
        super("Register");

        this.loginFrame = loginFrame;

        // Create components
        idField = new JTextField();
        nameField = new JTextField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        registerButton = new JButton("Register");
        signInButton = new JButton("Sign-In"); // Updated label to "Sign-In"

        // Set layouts
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to frame
        add(new JLabel("ID:"));
        add(idField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Confirm Password:"));
        add(confirmPasswordField);
        add(registerButton);
        add(signInButton); // Updated button label to "Sign-In"

        // Add action listeners
        registerButton.addActionListener(this);
        signInButton.addActionListener(this); // Add listener for the sign-in button

        // Set frame properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        registeredUsers = new ArrayList<>(); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String name = nameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (e.getSource() == registerButton) {
            if (validateInput(id, name, username, password, confirmPassword)) {
                if (registerUser(id, username, password)) {
                    // Add the new user to the list of registered users in LoginFrame
                    loginFrame.addRegisteredUser(new User(id, username, password));
                    JOptionPane.showMessageDialog(this, "Registration successful. You can now log in.");
                    dispose();
                    loginFrame.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input. Please fill in all the fields.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == signInButton) {
            dispose();
            loginFrame.setVisible(true);
        }
    }
    
    private boolean validateInput(String id, String name, String username, String password, String confirmPassword) {
        return !id.isEmpty() && !name.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confirmPassword.isEmpty();
    }

    private boolean registerUser(String id, String username, String password) {
        if (!password.equals(confirmPasswordField.getText())) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        User newUser = new User(id, username, password);
        registeredUsers.add(newUser);
        return true;
    }
}

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private User[] users;
    private int userCount;
    private List<User> registeredUsers;
    
    public LoginFrame() {
        super("Login System");

        users = new User[100];  // Adjust the array size as needed
        userCount = 0;

        // Create components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set layouts
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add components to frame
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Add action listeners
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Initially hide the login frame
        setVisible(false);

        // Automatically open the RegisterFrame
        registeredUsers = new ArrayList<>();
        
    }

     @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (e.getSource() == loginButton) {
            if (loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + username + "!");
                openAddProductFrame();
                setLoginFrameVisibility(false); // Hide the login frame
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Incorrect username or password.", "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == registerButton) {
            openRegisterFrame();
        }
    }    
    
    public void setLoginFrameVisibility(boolean visible) {
        setVisible(visible);
    }
    
    private void openAddProductFrame() {
        AddProductFrame addProductFrame = new AddProductFrame();
        addProductFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Handle window closing event
                List<Product> productList = addProductFrame.getProductList();
                // Process the productList as needed
                for (Product product : productList) {
                    System.out.println(product.toString() + "---------------");
                }
                System.exit(0);
            }
        });
    }

    public boolean registerUser(String id, String name, String username, String password, String confirmPassword) {
        // Implement registration logic
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists.", "Registration Unsuccessful", JOptionPane.ERROR_MESSAGE);
                return false; // Username already exists
            }
        }

        User newUser = new User(id, username, password);
        users[userCount++] = newUser;
        return true;
    }

    private boolean loginUser(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    private void openRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame(this);
        registerFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Handle window closing event if needed
            }
        });
    }
    
    public void addRegisteredUser(User newUser) {
        registeredUsers.add(newUser);
    }
    
    
    class BuyProductsFrame extends JFrame {
    public BuyProductsFrame(List<Product> productList) {
        super("Buy Products");

        JTextArea productListArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(productListArea);

        StringBuilder productsText = new StringBuilder("Products Available for Purchase:\n\n");
        for (Product product : productList) {
            productsText.append(product.toString()).append("---------------\n");
        }
        productListArea.setText(productsText.toString());

        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}



    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    });
}
}
