package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     *
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
     *
     * @return 1 si succès, 0 sinon
     *
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
     *
     * @return le nombre d'enregistrements supprimés (1 ou 0)
     *
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
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
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
     * Trouver un Customer à partir de sa clé
     *
     * @param customerID la clé du CUSTOMER à rechercher
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public ProductEntity findProduct(int productID) throws DAOException {
        ProductEntity result = new ProductEntity();

        String sql = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) { // On a trouvé

                    int productId = rs.getInt("PRODUCT_ID");
                    int manufacturerId = rs.getInt("MANUFACTURER_ID");
                    String productCode = rs.getString("PRODUCT_CODE");
                    float purchaseCost = rs.getInt("PURCHASE_COST");
                    int quantity = rs.getInt("QUANTITY_ON_HAND");
                    float markup = rs.getFloat("MARKUP");
                    String available = rs.getString("AVAILABLE");
                    String description = rs.getString("DESCRIPTION");

                    // On crée l'objet "entity"
                    result.setAvailable(available);
                    result.setDescription(description);
                    result.setManufacturerId(manufacturerId);
                    result.setMarkup(markup);
                    result.setProductCode(productCode);
                    result.setProductId(productId);
                    result.setPurchaseCost(purchaseCost);
                    result.setQuantity(quantity);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }
/**
 * Retourne l'Id du produit par description
 * @param Description description du produit
 * @return id du produit
 * @throws SQLException 
 */
    public int findProductId(String Description) throws SQLException {

        int result = 0;
        String sql = "SELECT PRODUCT_ID FROM PRODUCT WHERE DESCRIPTION LIKE ? ";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, Description);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("PRODUCT_ID");
            }
        }
        return result;
    }
    /**
     * retourne le prix d'un produit par description
     * @param Description description du produit
     * @return le prix du produit
     * @throws SQLException 
     */
    public int findProductCost(String Description) throws SQLException {

        int result = 0;
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE DESCRIPTION LIKE ? ";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, Description);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("PURCHASE_COST");
            }
        }
        return result;
    }
    
    
    /**
     * retourne le prix d'un produit en fonction de son ID
     * @param productID id du produit
     * @return prix du produit
     * @throws SQLException 
     */
    public int findProductCostById(int productID) throws SQLException {

        int result = 0;
        String sql = "SELECT PURCHASE_COST FROM PRODUCT WHERE PRODUCT_ID = ? ";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, productID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("PURCHASE_COST");
            }
        }
        return result;
    }
    /**
     * Vérifie si le client a assez de sous pour passer la commande
     * @param customerMoney limite de crédit du client
     * @param productCost prix du produit
     * @param quantity nombre de produits
     * @return 
     */
    public boolean CheckEnoughMoney(int customerMoney, int productCost, int quantity) {
        if(customerMoney >= (quantity*productCost))
            return true;
        else
            return false;
    }

    /**
     * requête qui récupère les commandes d'un client
     *
     * @param customerID la clé du CUSTOMER dont on veut les commandes
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public List<PurchaseEntity> viewPurshases(int customerID) throws DAOException {
        ArrayList<PurchaseEntity> purchases = new ArrayList();

        float totalCost = 0;

        String sql = "select * from purchase_order inner join product using (product_id) inner join product_code on (product_code = prod_code) inner join discount_code using (discount_code) inner join customer c using(customer_id) where c.customer_id = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, customerID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé

                    PurchaseEntity result = new PurchaseEntity();
                    
                    float rate = rs.getFloat("RATE");
                    int OrderNum = rs.getInt("ORDER_NUM");
                    int CustomerId = rs.getInt("CUSTOMER_ID");
                    int ProductId = rs.getInt("PRODUCT_ID");
                    int Quantity = rs.getInt("QUANTITY");
                    float ShippingCost = rs.getFloat("SHIPPING_COST");
                    String SalesDate = rs.getString("SALES_DATE");
                    String ShippingDate = rs.getString("SHIPPING_DATE");
                    String FreightCompany = rs.getString("FREIGHT_COMPANY");
                    String Description = rs.getString("DESCRIPTION");
                    float ProductCost = rs.getFloat("PURCHASE_COST");
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
                    totalCost = (ProductCost- ProductCost*rate/100)* Quantity + ShippingCost;
                    result.setTotalCost(totalCost);
                    purchases.add(result);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return purchases;
    }

    /**
     * requête qui récupère les commandes d'un client
     *
     * @param customerID la clé du CUSTOMER dont on veut les commandes
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public List<ProductEntity> allProduct() throws DAOException {
        ArrayList<ProductEntity> products = new ArrayList();

        String sql = "SELECT * FROM PRODUCT ";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé

                    ProductEntity result = new ProductEntity();

                    int productId = rs.getInt("PRODUCT_ID");
                    int manufacturerId = rs.getInt("MANUFACTURER_ID");
                    String productCode = rs.getString("PRODUCT_CODE");
                    float purchaseCost = rs.getInt("PURCHASE_COST");
                    int quantity = rs.getInt("QUANTITY_ON_HAND");
                    float markup = rs.getFloat("MARKUP");
                    String available = rs.getString("AVAILABLE");
                    String description = rs.getString("DESCRIPTION");

                    // On crée l'objet "entity"
                    result.setAvailable(available);
                    result.setDescription(description);
                    result.setManufacturerId(manufacturerId);
                    result.setMarkup(markup);
                    result.setProductCode(productCode);
                    result.setProductId(productId);
                    result.setPurchaseCost(purchaseCost);
                    result.setQuantity(quantity);

                    products.add(result);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return products;
    }

    /**
     * requête qui récupère les commandes d'un client
     *
     * @param customerID la clé du CUSTOMER dont on veut les commandes
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public int createPurshase(int customerId, int productId, int quantity, float shippingCost, String freightCompany) throws SQLException {

        Date date = new Date();
        int result = 0;
        String sql = "INSERT INTO PURCHASE_ORDER VALUES(?,?,?,?,?,?,?,?)";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, (int) (Math.random() * 50000));
            stmt.setInt(2, customerId);
            stmt.setInt(3, productId);
            stmt.setInt(4, quantity);
            stmt.setFloat(5, shippingCost);
            stmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now()));
            stmt.setDate(7, java.sql.Date.valueOf(java.time.LocalDate.now()));
            stmt.setString(8, freightCompany);
            result = stmt.executeUpdate();

        }
        return result;
    }
/**
 * Modifie la commande
 * @param orderNum numero de commande
 * @param customerId Id du client
 * @param productId id du produit
 * @param quantity quantité de produit
 * @param shippingCost frais de port
 * @param salesDate date de vente
 * @param shippingDate date de livraison
 * @param freightCompany compagnie de livraison
 * @return
 * @throws SQLException 
 */
    public int updatePurchase(int orderNum, int customerId, int productId, int quantity, float shippingCost, String salesDate, String shippingDate, String freightCompany) throws SQLException {
        int result = 0;
        String sql = "UPDATE PURCHASE_ORDER SET PRODUCT_ID = ?, QUANTITY=?, SHIPPING_COST=?, SALES_DATE=?, SHIPPING_DATE=?, FREIGHT_COMPANY=? where ORDER_NUM= ? and CUSTOMER_ID=?";
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

    /**
     * Supprime un enregistrement dans la table PURCHASE_ORDER
     *
     * @param code la clé de l'enregistrement à supprimer
     *
     * @return le nombre d'enregistrements supprimés (1 ou 0)
     *
     * @throws java.sql.SQLException renvoyées par JDBC
     *
     */
    public int deletePurchase(int code) throws SQLException {
        int result = 0;
        String sql = "DELETE FROM PURCHASE_ORDER WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, code);
            result = stmt.executeUpdate();
        }
        return result;
    }

    /**
     * Trouver un chiffre affaire par etat par date
     *
     * @param customerID la clé du CUSTOMER à rechercher
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public float chiffreAffaireEtat(String cate, String dateDeb, String dateFin) throws DAOException {
        System.out.println("date Début: " + dateDeb + " Date Fin: " + dateFin);
        float prix = 0;


        String sql = "select * from purchase_order inner join product using (product_id) inner join product_code on (product_code = prod_code) inner join discount_code using (discount_code) inner join customer c using(customer_id) where c.state= ? and  sales_date between ? and ?";

        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cate);
            stmt.setString(2, dateDeb);
            stmt.setString(3, dateFin);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé
                    prix += (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return prix;
    }

    /**
     * Trouver un chiffre affaire par article par date
     *
     * @param customerID la clé du CUSTOMER à rechercher
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public float chiffreAffaireProduit(String categorie, String dateDeb, String dateFin) throws DAOException {
        float prix = 0;

        String sql = "select * from purchase_order inner join product using (product_id) inner join product_code on (product_code = prod_code) inner join discount_code using (discount_code) where product_code = ? and sales_date between ? and ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, categorie);
            stmt.setString(2, dateDeb);
            stmt.setString(3, dateFin);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé
                    prix += (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return prix;
    }

        /**
         * Renvoie le chiffre d'affaire pour un client entre une date de début et une date de fin
         * @param iD id du client
         * @param dateDeb date du debut 
         * @param dateFin date de fin
         * @return chiffre d'affaire par client entre deux date
         * @throws DAOException 
         */
    public float chiffreAffaireClientDate(int iD, String dateDeb, String dateFin) throws DAOException {
        CustomerEntity result = new CustomerEntity();
        float prix = 0;

        String sql = "select * from purchase_order inner join product using (product_id) inner join product_code on (product_code = prod_code) inner join discount_code using (discount_code) inner join customer c using(customer_id) where c.customer_id = ? and sales_date between ? and ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, iD);
            stmt.setString(2, dateDeb);
            stmt.setString(3, dateFin);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé
                    prix += (rs.getFloat("purchase_cost") - (rs.getFloat("purchase_cost") * rs.getFloat("rate") / 100)) * rs.getInt("quantity") + rs.getFloat("shipping_cost");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return prix;
    }
/**
 * renvoie la liste de tout les codes produits
 * @return
 * @throws SQLException 
 */
    public List<String> productCodes() throws SQLException {

        List<String> result = new ArrayList<>();

        String sql = "SELECT * FROM PRODUCT_CODE";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String res = rs.getString("PROD_CODE");
                result.add(res);
            }
        }
        return result;
    }
/**
 * Renvoie la liste de tout les clients
 * @return
 * @throws DAOException 
 */
    public List<CustomerEntity> AllCustomers() throws DAOException {
        List<CustomerEntity> result = new ArrayList();
        String sql = "SELECT * FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé
                    CustomerEntity custo = new CustomerEntity();
                    String name = rs.getString("NAME");
                    String address = rs.getString("EMAIL");
                    int customerID = rs.getInt("CUSTOMER_ID");
                    // On crée l'objet "entity"
                    custo.setCustomerId(customerID);
                    custo.setEmail(address);
                    custo.setName(name);
                    result.add(custo);
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
     *
     * @return l'enregistrement correspondant dans la table CUSTOMER, ou null si
     * pas trouvé
     *
     * @throws DAOException
     */
    public List<String> allEtats() throws DAOException {
        List<String> result = new ArrayList();
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) { // On a trouvé
                    String etat = rs.getString("STATE");
                    result.add(etat);
                } // else on n'a pas trouvé, on renverra null
            }
        } catch (SQLException ex) {
            Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
            throw new DAOException(ex.getMessage());
        }

        return result;
    }
    
/**
 * Retourne la liste des descriptions des produits
 * @return
 * @throws DAOException 
 */

    public ArrayList<String> GetProductsDescriptions() throws DAOException {

        ArrayList<String> DescriptionList = new ArrayList();

        String sql = "SELECT DESCRIPTION FROM PRODUCT";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String Descritpion = rs.getString("DESCRIPTION");
                DescriptionList.add(Descritpion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DescriptionList;
    }
    /**
     * Retourne la liste des compagnie de livraison
     * @return
     * @throws DAOException 
     */

    public ArrayList<String> GetCompanies() throws DAOException {

        ArrayList<String> companies = new ArrayList();

        String sql = "SELECT DISTINCT FREIGHT_COMPANY FROM PURCHASE_ORDER";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String companie = rs.getString("FREIGHT_COMPANY");
                companies.add(companie);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }
/**
 * Modifie la commande
 * @param orderNum numero de commande
 * @param quantity quantité de produit
 * @param shippingCost frais de port
 * @param freightCompany compagnie de livraison
 * @return
 * @throws SQLException 
 */
    public int EditPurshase(int orderNum, int quantity, float shippingCost, String freightCompany) throws SQLException {
        int result = 0;
        String sql = "UPDATE PURCHASE_ORDER SET QUANTITY = ?, FREIGHT_COMPANY = ?, SHIPPING_COST = ? WHERE ORDER_NUM = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, freightCompany);
            stmt.setFloat(3, shippingCost);
            stmt.setInt(4, orderNum);
            result = stmt.executeUpdate();
        }
        return result;
    }
/**
 * Renvoie la limite de crédit du client
 * @param customerID id du client
 * @return limite de crédit
 * @throws SQLException 
 */
    public int GetCreditLimit(int customerID) throws SQLException {
        int result = 0;
        String sql = "SELECT CREDIT_LIMIT FROM CUSTOMER WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = rs.getInt("CREDIT_LIMIT");
            }
        }
        return result;
    }
    /**
     * Donne 5000 crédits supplémentaires au client
     * @param customerID id du client
     * @return 
     * @throws SQLException 
     */
    public int GiveCredit(int customerID) throws SQLException {
        int result = 0;
        String sql = "UPDATE CUSTOMER SET CREDIT_LIMIT = ? WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, GetCreditLimit(customerID) + 5000);     
            stmt.setInt(2, customerID);         
            result = stmt.executeUpdate();
        }
        return result;
    }
    /**
     * modifie la limite de crédit du client
     * @param customerID id du client
     * @param amount montant
     * @return
     * @throws SQLException 
     */
    public int SetCreditLimit(int customerID, int amount) throws SQLException {
        int result = 0;
        String sql = "UPDATE CUSTOMER SET CREDIT_LIMIT = ? WHERE CUSTOMER_ID = ?";
        try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, GetCreditLimit(customerID) - amount);     
            stmt.setInt(2, customerID);         
            result = stmt.executeUpdate();
        }
        return result;
    }
}
