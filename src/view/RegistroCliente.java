package view;

import dao.ClienteDAO;
import model.Cliente;
import javax.swing.JOptionPane;

/**
 * Registro de Clientes - Sincronizado con esquema SQL oficial
 */
public class RegistroCliente extends javax.swing.JFrame {
    
    public RegistroCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel(new java.awt.GridLayout(5, 2, 10, 10));
        
        jLabel1 = new javax.swing.JLabel("DNI:");
        txtDni = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel("Nombre:");
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel("Teléfono:");
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel("Correo:");
        txtCorreo = new javax.swing.JTextField();
        
        btnGuardar = new javax.swing.JButton("Guardar");
        btnCancelar = new javax.swing.JButton("Cancelar");

        jPanel1.add(jLabel1); jPanel1.add(txtDni);
        jPanel1.add(jLabel2); jPanel1.add(txtNombre);
        jPanel1.add(jLabel3); jPanel1.add(txtTelefono);
        jPanel1.add(jLabel4); jPanel1.add(txtCorreo);
        jPanel1.add(btnGuardar); jPanel1.add(btnCancelar);

        btnGuardar.addActionListener(e -> btnGuardarActionPerformed(e));
        btnCancelar.addActionListener(e -> this.dispose());

        this.add(jPanel1);
        this.setTitle("Nuevo Cliente");
        this.pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        String dni = txtDni.getText();
        String nombre = txtNombre.getText();
        String tel = txtTelefono.getText();
        String correo = txtCorreo.getText();

        if (dni.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y DNI son obligatorios");
            return;
        }

        Cliente c = new Cliente();
        c.setDni(dni);
        c.setNombre(nombre);
        c.setTelefono(tel);
        c.setCorreo(correo);

        if (new ClienteDAO().insert(c)) {
            JOptionPane.showMessageDialog(this, "Cliente guardado!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar cliente");
        }
    }

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(() -> new RegistroCliente().setVisible(true)); }

    private javax.swing.JButton btnCancelar, btnGuardar;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDni, txtNombre, txtTelefono, txtCorreo;
}
