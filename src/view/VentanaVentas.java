package view;

import dao.ProductoDAO;
import dao.VentaDAO;
import dao.DetalleVentaDAO;
import dao.InventarioDAO;
import model.Producto;
import model.Venta;
import model.DetalleVenta;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

/**
 * Ventana de Ventas - Procesa facturación y actualización de stock
 */
public class VentanaVentas extends javax.swing.JFrame {
    
    public VentanaVentas() {
        initComponents();
        this.setLocationRelativeTo(null);
        ((DefaultTableModel) tblVentas.getModel()).setRowCount(0);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        // --- COPIADO Y LIMPIADO DE LA ESTRUCTURA ORIGINAL ---
        jPanel3 = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jLabel1 = new javax.swing.JLabel("ID Producto:");
        txtBuscar = new javax.swing.JTextField(10);
        jLabel2 = new javax.swing.JLabel("Cant:");
        txtCantidad = new javax.swing.JTextField(5);
        btnIngresaraVenta = new javax.swing.JButton("Añadir");
        btnAniadirAClliente = new javax.swing.JButton("Nuevo Cliente");
        
        jPanel3.add(jLabel1); jPanel3.add(txtBuscar);
        jPanel3.add(jLabel2); jPanel3.add(txtCantidad);
        jPanel3.add(btnIngresaraVenta); jPanel3.add(btnAniadirAClliente);

        tblVentas = new javax.swing.JTable();
        tblVentas.setModel(new DefaultTableModel(new String[]{"ID", "Producto", "Cant", "Precio", "Subtotal"}, 0));
        jScrollPane1 = new javax.swing.JScrollPane(tblVentas);

        javax.swing.JPanel pnlPie = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        jLabel3 = new javax.swing.JLabel("TOTAL: ");
        txtTotal = new javax.swing.JTextField(8);
        txtTotal.setEditable(false);
        txtConfirmarPago = new javax.swing.JButton("Confirmar Pago");
        btnCerrarSesion = new javax.swing.JButton("Salir");
        
        pnlPie.add(btnCerrarSesion); pnlPie.add(jLabel3); pnlPie.add(txtTotal); pnlPie.add(txtConfirmarPago);

        btnIngresaraVenta.addActionListener(e -> btnIngresaraVentaActionPerformed(e));
        btnAniadirAClliente.addActionListener(e -> btnAniadirACllienteActionPerformed(e));
        txtConfirmarPago.addActionListener(e -> txtConfirmarPagoActionPerformed(e));
        btnCerrarSesion.addActionListener(e -> { this.dispose(); new MenuPrincipal().setVisible(true); });

        this.setLayout(new java.awt.BorderLayout());
        this.add(jPanel3, java.awt.BorderLayout.NORTH);
        this.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        this.add(pnlPie, java.awt.BorderLayout.SOUTH);
        this.pack();
    }

    private void btnIngresaraVentaActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int productId = Integer.parseInt(txtBuscar.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            Producto p = new ProductoDAO().getById(productId);

            if (p != null) {
                DefaultTableModel model = (DefaultTableModel) tblVentas.getModel();
                double subtotal = p.getPrecio() * cantidad;
                model.addRow(new Object[]{p.getIdProducto(), p.getNombre(), cantidad, p.getPrecio(), subtotal});
                actualizarTotal();
                txtBuscar.setText(""); txtCantidad.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en datos: " + e.getMessage());
        }
    }

    private void actualizarTotal() {
        double total = 0;
        DefaultTableModel model = (DefaultTableModel) tblVentas.getModel();
        for (int i = 0; i < model.getRowCount(); i++) total += (double) model.getValueAt(i, 4);
        txtTotal.setText(String.valueOf(total));
    }

    private void txtConfirmarPagoActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tblVentas.getModel();
        if (model.getRowCount() == 0) return;

        // 1. Crear Venta (Cabecera) - Simplificado con Cliente 1 y Usuario 1 para este ejemplo
        Venta v = new Venta();
        v.setTotal(Double.parseDouble(txtTotal.getText()));
        v.setClientesIdCliente(1); 
        v.setUsuariosIdUsuario(1);
        
        int idVenta = new VentaDAO().insert(v);
        if (idVenta > 0) {
            boolean exito = true;
            DetalleVentaDAO dvDao = new DetalleVentaDAO();
            InventarioDAO invDao = new InventarioDAO();

            for (int i = 0; i < model.getRowCount(); i++) {
                int idProd = (int) model.getValueAt(i, 0);
                int cant = (int) model.getValueAt(i, 2);
                double prec = (double) model.getValueAt(i, 3);

                // 2. Insertar Detalle
                DetalleVenta dv = new DetalleVenta(idVenta, idProd, cant, prec, 0);
                if (!dvDao.insert(dv)) exito = false;

                // 3. Descontar Stock
                if (!invDao.updateStock(idProd, cant)) exito = false;
            }

            if (exito) {
                JOptionPane.showMessageDialog(this, "Venta #"+idVenta+" procesada con éxito.");
                model.setRowCount(0);
                txtTotal.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Venta procesada con algunas advertencias en stock/detalles.");
            }
        }
    }

    private void btnAniadirACllienteActionPerformed(java.awt.event.ActionEvent evt) {
        new RegistroCliente().setVisible(true);
    }

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(() -> new VentanaVentas().setVisible(true)); }

    private javax.swing.JButton btnAniadirAClliente, btnCerrarSesion, btnIngresaraVenta, txtConfirmarPago;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBuscar, txtCantidad, txtTotal;
}
