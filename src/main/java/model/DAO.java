package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {

    private final DataSource myDataSource;

    /**
     * Construit le AO avec sa source de données
     *
     * @param dataSource la source de données à utiliser
     */
    public DAO() {
        this.myDataSource = DataSourceFactory.getDataSource();
    }

    /**
     * Contenu de la table DISCOUNT_CODE
     *
     * @return Liste des discount codes
     * @throws SQLException renvoyées par JDBC
     */
    public List<DiscountCode> allCodes() throws SQLException {

        List<DiscountCode> result = new LinkedList<>();

        String sql = "SELECT * FROM DISCOUNT_CODE ORDER BY DISCOUNT_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("DISCOUNT_CODE");
                float rate = rs.getFloat("RATE");
                DiscountCode c = new DiscountCode(id, rate);
                result.add(c);
            }
        }
        return result;
    }

    /**
     * Ajout d'un enregistrement dans la table DISCOUNT_CODE
     *
     * @param code le code (non null)
     * @param rate le taux (positive or 0)
     * @return 1 si succès, 0 sinon
     * @throws SQLException renvoyées par JDBC
     */
    public int addDiscountCode(String code, float rate) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO DISCOUNT_CODE VALUES (?, ?)";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.setFloat(2, rate);
            result = stmt.executeUpdate();
        }
        return result;
    }

    /**
     * Supprime un enregistrement dans la table DISCOUNT_CODE
     *
     * @param code la clé de l'enregistrement à supprimer
     * @return le nombre d'enregistrements supprimés (1 ou 0)
     * @throws java.sql.SQLException renvoyées par JDBC
	 *
     */
    public int deleteDiscountCode(String code) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, code);
            result = stmt.executeUpdate();
        }
        return result;
    }

    /**
     * Trouver un Customer à partir de sa clé
     *
     * @param customerID la clé du CUSTOMER à rechercher
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     * @throws DAOException
     */
    public CustomerEntity findCustomer(int customerID) throws DAOException {
        CustomerEntity result = new CustomerEntity();

        String sql = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // On a trouvé
                    String name = rs.getString("NAME");
                    String address = rs.getString("EMAIL");
                    // On crée l'objet "entity"
                    result.setCustomerId(customerID);
                    result.setEmail(address);
                    result.setName(name);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }

    /**
     * requête qui récupère les commandes d'un client
     *
     * @param customerID la clé du CUSTOMER dont on veut les commandes
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     * @throws DAOException
     */
    public List<PurchaseEntity> viewPurshases(int customerID) throws DAOException {
        ArrayList<PurchaseEntity> purchases = new ArrayList();

        String sql = "SELECT * FROM PURCHASE_ORDER INNER JOIN PRODUCT USING(PRODUCT_ID) WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // On a trouvé

                    PurchaseEntity result = new PurchaseEntity();

                    int OrderNum = rs.getInt("ORDER_NUM");
                    int CustomerId = rs.getInt("CUSTOMER_ID");
                    int ProductId = rs.getInt("PRODUCT_ID");
                    int Quantity = rs.getInt("QUANTITY");
                    float ShippingCost = rs.getFloat("SHIPPING_COST");
                    String SalesDate = rs.getString("SALES_DATE");
                    String ShippingDate = rs.getString("SHIPPING_DATE");
                    String FreightCompany = rs.getString("FREIGHT_COMPANY");
                    String Description = rs.getString("DESCRIPTION");
                    // On crée l'objet "entity"
                    result.setOrderNum(OrderNum);
                    result.setCustomerId(CustomerId);
                    result.setProductId(ProductId);
                    result.setQuantity(Quantity);
                    result.setShippingCost(ShippingCost);
                    result.setSalesDate(SalesDate);
                    result.setShippingDate(ShippingDate);
                    result.setFreightCompany(FreightCompany);
                    result.setDescription(Description);

                    purchases.add(result);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return purchases;
    }

    public int createPurshase(int orderNum, int customerId, int productId, int quantity, float shippingCost, String salesDate, String shippingDate, String freightCompany) throws SQLException {
        int result = 0;
        String sql = "INSERT INTO PURCHASE_ORDER VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, orderNum);
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setFloat(5, shippingCost);
            stmt.setString(6, salesDate);
            stmt.setString(7, shippingDate);
            stmt.setString(8, freightCompany);
            result = stmt.executeUpdate();

        }
        return result;
    }

}
