package view;

import dao.*;
import model.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Super Tienda - Menú Principal (Gestión Total de Catálogos)
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    public MenuPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        configurarEventosTablas();
        recargarTodo();
    }

    private void recargarTodo() {
        cargarComboRoles();
        cargarComboProveedores();
        cargarComboCategorias();
        cargarComboBodegas();
        
        btnRecargarActionPerformed(null);
        btnRecargarProductoActionPerformed(null);
        btnMostrarTodoActionPerformed(null);
        btnMostrarTodoClientesActionPerformed(null);
        btnRecargarCateActionPerformed(null);
        btnRecargarBodegaActionPerformed(null);
        btnRecargarProvActionPerformed(null);
        btnRecargarRolActionPerformed(null);
    }

    private void configurarEventosTablas() {
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblUsuarios.getSelectedRow();
                if (r >= 0) {
                    txtNombre.setText(tblUsuarios.getValueAt(r, 1).toString());
                    txtCorreo.setText(tblUsuarios.getValueAt(r, 2).toString());
                    setSelectedItemByName(cmbRol, tblUsuarios.getValueAt(r, 3).toString());
                    cmbEstado.setSelectedItem(tblUsuarios.getValueAt(r, 4).toString());
                }
            }
        });
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblProductos.getSelectedRow();
                if (r >= 0) {
                    txtNombreProducto.setText(tblProductos.getValueAt(r, 1).toString());
                    txtPrecio.setText(tblProductos.getValueAt(r, 2).toString());
                    setSelectedItemByName(cmbProveedor, tblProductos.getValueAt(r, 3).toString());
                    setSelectedItemByName(cmbBodega, tblProductos.getValueAt(r, 4).toString());
                    setSelectedItemByName(cmbCategoria, tblProductos.getValueAt(r, 5).toString());
                    txtFechaVencimiento.setText(tblProductos.getValueAt(r, 6).toString());
                }
            }
        });
        tblCate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblCate.getSelectedRow();
                if (r >= 0) {
                    txtNomCate.setText(tblCate.getValueAt(r, 1).toString());
                    txtDescCate.setText(tblCate.getValueAt(r, 2).toString());
                }
            }
        });
        tblBod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblBod.getSelectedRow();
                if (r >= 0) {
                    txtNomBod.setText(tblBod.getValueAt(r, 1).toString());
                    txtDirBod.setText(tblBod.getValueAt(r, 2).toString());
                }
            }
        });
        tblProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblProv.getSelectedRow();
                if (r >= 0) {
                    txtNomProv.setText(tblProv.getValueAt(r, 1).toString());
                    txtTelProv.setText(tblProv.getValueAt(r, 2).toString());
                    txtDirProv.setText(tblProv.getValueAt(r, 3).toString());
                    txtRtnProv.setText(tblProv.getValueAt(r, 4).toString());
                }
            }
        });
        tblRol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int r = tblRol.getSelectedRow();
                if (r >= 0) {
                    txtNomRol.setText(tblRol.getValueAt(r, 1).toString());
                    txtDescRol.setText(tblRol.getValueAt(r, 2).toString());
                }
            }
        });
    }

    private void setSelectedItemByName(javax.swing.JComboBox<?> combo, String name) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).toString().equals(name)) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        
        // --- PESTAÑA USUARIOS ---
        pnlUsuarios = crearPanelTablas("USUARIOS", 
            new String[]{"Nombre", "Correo", "Rol", "Est"}, 
            new String[]{"ID", "Nombre", "Correo", "Rol", "Estado"});
        
        // --- PESTAÑA PRODUCTOS (Layout Mejorado de 3 Filas) ---
        pnlProductos = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel pnlCamposProdFull = new javax.swing.JPanel(new java.awt.GridLayout(3, 1, 5, 5));
        
        javax.swing.JPanel f1 = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        txtNombreProducto = new javax.swing.JTextField(10);
        txtPrecio = new javax.swing.JTextField(6);
        txtCantidad = new javax.swing.JTextField(6);
        txtFechaVencimiento = new javax.swing.JTextField(10);
        f1.add(new javax.swing.JLabel("Nombre:")); f1.add(txtNombreProducto);
        f1.add(new javax.swing.JLabel("Precio:")); f1.add(txtPrecio);
        f1.add(new javax.swing.JLabel("Stock:")); f1.add(txtCantidad);
        f1.add(new javax.swing.JLabel("Vencimiento (AAAA-MM-DD):")); f1.add(txtFechaVencimiento);

        javax.swing.JPanel f2 = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        cmbProveedor = new javax.swing.JComboBox<>();
        cmbCategoria = new javax.swing.JComboBox<>();
        cmbBodega = new javax.swing.JComboBox<>();
        f2.add(new javax.swing.JLabel("Prov:")); f2.add(cmbProveedor);
        f2.add(new javax.swing.JLabel("Cat:")); f2.add(cmbCategoria);
        f2.add(new javax.swing.JLabel("Bod:")); f2.add(cmbBodega);

        javax.swing.JPanel f3 = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        btnAniadirProducto = new javax.swing.JButton("Añadir");
        btnModificarProductos = new javax.swing.JButton("Modificar");
        btnEliminarProducto = new javax.swing.JButton("Eliminar");
        btnRecargarProducto = new javax.swing.JButton("Recargar");
        btnIrAVentas = new javax.swing.JButton("CAJA/VENTAS");
        btnIrAVentas.setBackground(new java.awt.Color(51, 153, 255));
        f3.add(btnAniadirProducto); f3.add(btnModificarProductos); 
        f3.add(btnEliminarProducto); f3.add(btnRecargarProducto); f3.add(btnIrAVentas);

        pnlCamposProdFull.add(f1); pnlCamposProdFull.add(f2); pnlCamposProdFull.add(f3);
        tblProductos = new javax.swing.JTable();
        pnlProductos.add(pnlCamposProdFull, java.awt.BorderLayout.NORTH);
        pnlProductos.add(new javax.swing.JScrollPane(tblProductos), java.awt.BorderLayout.CENTER);

        // --- PESTAÑA INVENTARIO ---
        pnlInventario = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel pnlBusqInv = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        txtBuscar = new javax.swing.JTextField(15);
        btnBuscarIDoNombre = new javax.swing.JButton("Buscar");
        btnMostrarTodo = new javax.swing.JButton("Todo");
        btnModificarInventario = new javax.swing.JButton("Modificar Stock");
        pnlBusqInv.add(new javax.swing.JLabel("ID/Nombre:")); pnlBusqInv.add(txtBuscar);
        pnlBusqInv.add(btnBuscarIDoNombre); pnlBusqInv.add(btnMostrarTodo); pnlBusqInv.add(btnModificarInventario);
        tblInventario = new javax.swing.JTable();
        pnlInventario.add(pnlBusqInv, java.awt.BorderLayout.NORTH);
        pnlInventario.add(new javax.swing.JScrollPane(tblInventario), java.awt.BorderLayout.CENTER);

        // --- PESTAÑA CLIENTES ---
        pnlClientes = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel pnlBusqCli = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        txtBuscarCliente = new javax.swing.JTextField(15);
        btnBuscarNombre = new javax.swing.JButton("Buscar");
        btnMostrarTodoClientes = new javax.swing.JButton("Todo");
        btnModificarClientes = new javax.swing.JButton("Modificar");
        btnNuevoCliente = new javax.swing.JButton("Registrar Nuevo");
        pnlBusqCli.add(new javax.swing.JLabel("Nombre:")); pnlBusqCli.add(txtBuscarCliente);
        pnlBusqCli.add(btnBuscarNombre); pnlBusqCli.add(btnMostrarTodoClientes); pnlBusqCli.add(btnModificarClientes); pnlBusqCli.add(btnNuevoCliente);
        tblClientes = new javax.swing.JTable();
        pnlClientes.add(pnlBusqCli, java.awt.BorderLayout.NORTH);
        pnlClientes.add(new javax.swing.JScrollPane(tblClientes), java.awt.BorderLayout.CENTER);

        // --- PESTAÑAS DE ADMINISTRACIÓN (CATEGORÍAS, BODEGAS, PROVEEDORES, ROLES) ---
        pnlCategorias = crearTabGestion("CATEGORÍAS", new String[]{"Nombre", "Desc"});
        pnlBodegas = crearTabGestion("BODEGAS", new String[]{"Nombre", "Dir"});
        pnlProveedores = crearTabGestion("PROVEEDORES", new String[]{"Nombre", "Tel", "Dir", "RTN"});
        pnlRoles = crearTabGestion("ROLES", new String[]{"Nombre", "Desc"});

        jTabbedPane1.addTab("USUARIOS", pnlUsuarios);
        jTabbedPane1.addTab("PRODUCTOS", pnlProductos);
        jTabbedPane1.addTab("INVENTARIO", pnlInventario);
        jTabbedPane1.addTab("CLIENTES", pnlClientes);
        jTabbedPane1.addTab("CATEGORÍAS", pnlCategorias);
        jTabbedPane1.addTab("BODEGAS", pnlBodegas);
        jTabbedPane1.addTab("PROVEEDORES", pnlProveedores);
        jTabbedPane1.addTab("ROLES", pnlRoles);

        asignarActionListeners();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.add(jTabbedPane1);
        this.pack();
    }

    private javax.swing.JPanel crearPanelTablas(String titulo, String[] labels, String[] cols) {
        javax.swing.JPanel p = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel pnlF = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        if (titulo.equals("USUARIOS")) {
            txtNombre = new javax.swing.JTextField(8); txtCorreo = new javax.swing.JTextField(8);
            txtContrasenia = new javax.swing.JPasswordField(6); cmbRol = new javax.swing.JComboBox<>();
            cmbEstado = new javax.swing.JComboBox<>(new String[]{"Activo", "Inactivo"});
            btnAniadir = new javax.swing.JButton("Añadir"); btnModificar = new javax.swing.JButton("Modificar");
            btnEliminar = new javax.swing.JButton("Eliminar"); btnRecargar = new javax.swing.JButton("Recargar");
            pnlF.add(new javax.swing.JLabel("Nom:")); pnlF.add(txtNombre); pnlF.add(new javax.swing.JLabel("Cor:")); pnlF.add(txtCorreo);
            pnlF.add(new javax.swing.JLabel("Rol:")); pnlF.add(cmbRol); pnlF.add(new javax.swing.JLabel("Est:")); pnlF.add(cmbEstado);
            pnlF.add(new javax.swing.JLabel("Pw:")); pnlF.add(txtContrasenia); pnlF.add(btnAniadir); pnlF.add(btnModificar); pnlF.add(btnEliminar); pnlF.add(btnRecargar);
            tblUsuarios = new javax.swing.JTable(); p.add(new javax.swing.JScrollPane(tblUsuarios), java.awt.BorderLayout.CENTER);
        }
        p.add(pnlF, java.awt.BorderLayout.NORTH);
        return p;
    }

    private javax.swing.JPanel crearTabGestion(String tipo, String[] fields) {
        javax.swing.JPanel p = new javax.swing.JPanel(new java.awt.BorderLayout());
        javax.swing.JPanel pnlF = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        javax.swing.JButton btnA = new javax.swing.JButton("Añadir"), btnM = new javax.swing.JButton("Modificar"), btnE = new javax.swing.JButton("Eliminar"), btnR = new javax.swing.JButton("Recargar");
        
        switch(tipo) {
            case "CATEGORÍAS":
                txtNomCate = new javax.swing.JTextField(10); txtDescCate = new javax.swing.JTextField(15);
                pnlF.add(new javax.swing.JLabel("Nom:")); pnlF.add(txtNomCate); pnlF.add(new javax.swing.JLabel("Des:")); pnlF.add(txtDescCate);
                btnACate = btnA; btnMCate = btnM; btnECate = btnE; btnRCate = btnR;
                tblCate = new javax.swing.JTable(); p.add(new javax.swing.JScrollPane(tblCate), java.awt.BorderLayout.CENTER);
                break;
            case "BODEGAS":
                txtNomBod = new javax.swing.JTextField(10); txtDirBod = new javax.swing.JTextField(15);
                pnlF.add(new javax.swing.JLabel("Nom:")); pnlF.add(txtNomBod); pnlF.add(new javax.swing.JLabel("Dir:")); pnlF.add(txtDirBod);
                btnABod = btnA; btnMBod = btnM; btnEBod = btnE; btnRBod = btnR;
                tblBod = new javax.swing.JTable(); p.add(new javax.swing.JScrollPane(tblBod), java.awt.BorderLayout.CENTER);
                break;
            case "PROVEEDORES":
                txtNomProv = new javax.swing.JTextField(8); txtTelProv = new javax.swing.JTextField(8); txtDirProv = new javax.swing.JTextField(10); txtRtnProv = new javax.swing.JTextField(10);
                pnlF.add(new javax.swing.JLabel("Nom:")); pnlF.add(txtNomProv); pnlF.add(new javax.swing.JLabel("Tel:")); pnlF.add(txtTelProv); pnlF.add(new javax.swing.JLabel("Dir:")); pnlF.add(txtDirProv); pnlF.add(new javax.swing.JLabel("RTN:")); pnlF.add(txtRtnProv);
                btnAProv = btnA; btnMProv = btnM; btnEProv = btnE; btnRProv = btnR;
                tblProv = new javax.swing.JTable(); p.add(new javax.swing.JScrollPane(tblProv), java.awt.BorderLayout.CENTER);
                break;
            case "ROLES":
                txtNomRol = new javax.swing.JTextField(10); txtDescRol = new javax.swing.JTextField(15);
                pnlF.add(new javax.swing.JLabel("Nom:")); pnlF.add(txtNomRol); pnlF.add(new javax.swing.JLabel("Des:")); pnlF.add(txtDescRol);
                btnARol = btnA; btnMRol = btnM; btnERol = btnE; btnRRol = btnR;
                tblRol = new javax.swing.JTable(); p.add(new javax.swing.JScrollPane(tblRol), java.awt.BorderLayout.CENTER);
                break;
        }
        pnlF.add(btnA); pnlF.add(btnM); pnlF.add(btnE); pnlF.add(btnR);
        p.add(pnlF, java.awt.BorderLayout.NORTH);
        return p;
    }

    private void asignarActionListeners() {
        btnRecargar.addActionListener(e -> btnRecargarActionPerformed(e));
        btnAniadir.addActionListener(e -> btnAniadirActionPerformed(e));
        btnModificar.addActionListener(e -> btnModificarActionPerformed(e));
        btnEliminar.addActionListener(e -> btnEliminarActionPerformed(e));
        btnAniadirProducto.addActionListener(e -> btnAniadirProductoActionPerformed(e));
        btnModificarProductos.addActionListener(e -> btnModificarProductosActionPerformed(e));
        btnEliminarProducto.addActionListener(e -> btnEliminarProductoActionPerformed(e));
        btnRecargarProducto.addActionListener(e -> btnRecargarProductoActionPerformed(e));
        btnBuscarIDoNombre.addActionListener(e -> btnBuscarIDoNombreActionPerformed(e));
        btnMostrarTodo.addActionListener(e -> btnMostrarTodoActionPerformed(e));
        btnModificarInventario.addActionListener(e -> btnMosificarInventarioActionPerformed(e));
        btnBuscarNombre.addActionListener(e -> btnBuscarNombreActionPerformed(e));
        btnMostrarTodoClientes.addActionListener(e -> btnMostrarTodoClientesActionPerformed(e));
        btnModificarClientes.addActionListener(e -> btnModificarClientesActionPerformed(e));
        btnNuevoCliente.addActionListener(e -> new RegistroCliente().setVisible(true));
        btnIrAVentas.addActionListener(e -> { this.dispose(); new VentanaVentas().setVisible(true); });
        
        btnACate.addActionListener(e -> { new CategoriaDAO().insert(new Categoria(0, txtNomCate.getText(), txtDescCate.getText())); btnRecargarCateActionPerformed(null); cargarComboCategorias(); });
        btnMCate.addActionListener(e -> { 
            int r = tblCate.getSelectedRow(); 
            if (r>=0) { 
                if (new CategoriaDAO().update(new Categoria((int)tblCate.getValueAt(r, 0), txtNomCate.getText(), txtDescCate.getText()))) {
                    JOptionPane.showMessageDialog(this, "Categoría actualizada");
                    btnRecargarCateActionPerformed(null); cargarComboCategorias(); 
                }
            } 
        });
        btnECate.addActionListener(e -> { int r = tblCate.getSelectedRow(); if (r>=0) { new CategoriaDAO().delete((int)tblCate.getValueAt(r, 0)); btnRecargarCateActionPerformed(null); cargarComboCategorias(); } });
        btnRCate.addActionListener(e -> btnRecargarCateActionPerformed(e));

        btnABod.addActionListener(e -> { new BodegaDAO().insert(new Bodega(0, txtNomBod.getText(), txtDirBod.getText())); btnRecargarBodegaActionPerformed(null); cargarComboBodegas(); });
        btnMBod.addActionListener(e -> { 
            int r = tblBod.getSelectedRow(); 
            if (r>=0) { 
                if (new BodegaDAO().update(new Bodega((int)tblBod.getValueAt(r, 0), txtNomBod.getText(), txtDirBod.getText()))) {
                    JOptionPane.showMessageDialog(this, "Bodega actualizada");
                    btnRecargarBodegaActionPerformed(null); cargarComboBodegas(); 
                }
            } 
        });
        btnEBod.addActionListener(e -> { int r = tblBod.getSelectedRow(); if (r>=0) { new BodegaDAO().delete((int)tblBod.getValueAt(r, 0)); btnRecargarBodegaActionPerformed(null); cargarComboBodegas(); } });
        btnRBod.addActionListener(e -> btnRecargarBodegaActionPerformed(e));

        btnAProv.addActionListener(e -> { new ProveedorDAO().insert(new Proveedor(0, txtNomProv.getText(), txtTelProv.getText(), txtDirProv.getText(), txtRtnProv.getText())); btnRecargarProvActionPerformed(null); cargarComboProveedores(); });
        btnMProv.addActionListener(e -> { 
            int r = tblProv.getSelectedRow(); 
            if (r>=0) { 
                if (new ProveedorDAO().update(new Proveedor((int)tblProv.getValueAt(r, 0), txtNomProv.getText(), txtTelProv.getText(), txtDirProv.getText(), txtRtnProv.getText()))) {
                    JOptionPane.showMessageDialog(this, "Proveedor actualizado");
                    btnRecargarProvActionPerformed(null); cargarComboProveedores(); 
                }
            } 
        });
        btnEProv.addActionListener(e -> { int r = tblProv.getSelectedRow(); if (r>=0) { new ProveedorDAO().delete((int)tblProv.getValueAt(r, 0)); btnRecargarProvActionPerformed(null); cargarComboProveedores(); } });
        btnRProv.addActionListener(e -> btnRecargarProvActionPerformed(e));

        btnARol.addActionListener(e -> { new RolDAO().insert(new Rol(0, txtNomRol.getText(), txtDescRol.getText())); btnRecargarRolActionPerformed(null); cargarComboRoles(); });
        btnMRol.addActionListener(e -> { 
            int r = tblRol.getSelectedRow(); 
            if (r>=0) { 
                if (new RolDAO().update(new Rol((int)tblRol.getValueAt(r, 0), txtNomRol.getText(), txtDescRol.getText()))) {
                    JOptionPane.showMessageDialog(this, "Rol actualizado");
                    btnRecargarRolActionPerformed(null); cargarComboRoles(); 
                }
            } 
        });
        btnERol.addActionListener(e -> { int r = tblRol.getSelectedRow(); if (r>=0) { new RolDAO().delete((int)tblRol.getValueAt(r, 0)); btnRecargarRolActionPerformed(null); cargarComboRoles(); } });
        btnRRol.addActionListener(e -> btnRecargarRolActionPerformed(e));
    }

    // --- ACCIONES RECARGA ---
    private void btnRecargarCateActionPerformed(java.awt.event.ActionEvent e) { DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Desc"}, 0); for (Categoria c : new CategoriaDAO().getAll()) m.addRow(new Object[]{c.getIdCategoria(), c.getNombre(), c.getDescripcion()}); tblCate.setModel(m); }
    private void btnRecargarBodegaActionPerformed(java.awt.event.ActionEvent e) { DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Dir"}, 0); for (Bodega b : new BodegaDAO().getAll()) m.addRow(new Object[]{b.getIdBodega(), b.getNombre(), b.getDireccionBodega()}); tblBod.setModel(m); }
    private void btnRecargarProvActionPerformed(java.awt.event.ActionEvent e) { DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Tel", "Dir", "RTN"}, 0); for (Proveedor p : new ProveedorDAO().getAll()) m.addRow(new Object[]{p.getIdProveedor(), p.getNombreProveedor(), p.getTelefonoProveedor(), p.getDireccionProveedor(), p.getRtn()}); tblProv.setModel(m); }
    private void btnRecargarRolActionPerformed(java.awt.event.ActionEvent e) { DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Desc"}, 0); for (Rol r : new RolDAO().getAll()) m.addRow(new Object[]{r.getIdRol(), r.getNombreRol(), r.getDescripcionRol()}); tblRol.setModel(m); }

    // --- LÓGICA USUARIOS ---
    private void btnAniadirActionPerformed(java.awt.event.ActionEvent evt) {
        Usuario u = new Usuario(); u.setNombre(txtNombre.getText()); u.setCorreo(txtCorreo.getText()); u.setContrasenia(new String(txtContrasenia.getPassword()));
        u.setRolesIdRol(((model.Rol)cmbRol.getSelectedItem()).getIdRol()); u.setEstadoCuenta(cmbEstado.getSelectedItem().toString());
        if (new UsuarioDAO().insert(u)) btnRecargarActionPerformed(evt);
    }
    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Correo", "Rol", "Estado"}, 0);
        for (Usuario u : new UsuarioDAO().getAll()) m.addRow(new Object[]{u.getIdUsuario(), u.getNombre(), u.getCorreo(), u.getNombreRol(), u.getEstadoCuenta()});
        tblUsuarios.setModel(m);
    }
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {
        int r = tblUsuarios.getSelectedRow();
        if (r >= 0) {
            Usuario u = new Usuario(); 
            u.setIdUsuario((int)tblUsuarios.getValueAt(r, 0)); 
            u.setNombre(txtNombre.getText()); 
            u.setCorreo(txtCorreo.getText());
            u.setContrasenia(new String(txtContrasenia.getPassword())); // <--- ESTO FALTABA
            u.setRolesIdRol(((model.Rol)cmbRol.getSelectedItem()).getIdRol()); 
            u.setEstadoCuenta(cmbEstado.getSelectedItem().toString());
            
            if (new UsuarioDAO().update(u)) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado");
                btnRecargarActionPerformed(evt);
            }
        }
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {
        int r = tblUsuarios.getSelectedRow(); if (r >= 0) { new UsuarioDAO().delete((int)tblUsuarios.getValueAt(r, 0)); btnRecargarActionPerformed(evt); }
    }

    // --- LÓGICA PRODUCTOS ---
    private void btnAniadirProductoActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int idInv = new InventarioDAO().insert(Integer.parseInt(txtCantidad.getText()));
            if (idInv > 0) {
                Producto p = new Producto(); p.setNombre(txtNombreProducto.getText()); p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setProveedoresIdProveedor(((model.Proveedor)cmbProveedor.getSelectedItem()).getIdProveedor());
                p.setCategoriasIdCategoria(((model.Categoria)cmbCategoria.getSelectedItem()).getIdCategoria());
                p.setBodegasIdBodega(((model.Bodega)cmbBodega.getSelectedItem()).getIdBodega());
                p.setInventariosIdInventario(idInv); 
                
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    p.setFechaVencimiento(sdf.parse(txtFechaVencimiento.getText()));
                } catch (Exception ex) {
                    p.setFechaVencimiento(new java.util.Date()); // Fallback
                }

                if (new ProductoDAO().insert(p)) btnRecargarProductoActionPerformed(evt);
            }
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Error: " + e.getMessage()); }
    }
    private void btnRecargarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Proveedor", "Bodega", "Categoria", "Vencimiento"}, 0);
        for (Producto p : new ProductoDAO().getAll()) m.addRow(new Object[]{p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getNombreProveedor(), p.getNombreBodega(), p.getNombreCategoria(), p.getFechaVencimiento()});
        tblProductos.setModel(m);
    }
    private void btnModificarProductosActionPerformed(java.awt.event.ActionEvent evt) {
        int r = tblProductos.getSelectedRow();
        if (r >= 0) {
            Producto p = new Producto(); p.setIdProducto((int)tblProductos.getValueAt(r, 0)); p.setNombre(txtNombreProducto.getText());
            p.setPrecio(Double.parseDouble(txtPrecio.getText()));
            p.setProveedoresIdProveedor(((model.Proveedor)cmbProveedor.getSelectedItem()).getIdProveedor());
            p.setCategoriasIdCategoria(((model.Categoria)cmbCategoria.getSelectedItem()).getIdCategoria());
            p.setBodegasIdBodega(((model.Bodega)cmbBodega.getSelectedItem()).getIdBodega());
            
            try {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                p.setFechaVencimiento(sdf.parse(txtFechaVencimiento.getText()));
            } catch (Exception ex) {
                p.setFechaVencimiento(new java.util.Date());
            }

            if (new ProductoDAO().update(p)) {
                JOptionPane.showMessageDialog(this, "Producto actualizado");
                btnRecargarProductoActionPerformed(evt);
            }
        }
    }
    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {
        int r = tblProductos.getSelectedRow(); if (r >= 0) { new ProductoDAO().delete((int)tblProductos.getValueAt(r, 0)); btnRecargarProductoActionPerformed(evt); }
    }

    // --- INVENTARIO ---
    private void btnMostrarTodoActionPerformed(java.awt.event.ActionEvent e) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Producto", "Stock Actual", "Registro", "Salida"}, 0);
        for (Inventario i : new InventarioDAO().getAllWithProductInfo()) m.addRow(new Object[]{i.getIdInventario(), i.getNombreProducto(), i.getStockActual(), i.getFechaRegistro(), i.getFechaSalida()});
        tblInventario.setModel(m);
    }
    private void btnBuscarIDoNombreActionPerformed(java.awt.event.ActionEvent e) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "Producto", "Stock Actual", "Registro", "Salida"}, 0);
        for (Inventario i : new InventarioDAO().searchByIdOrProductName(txtBuscar.getText())) m.addRow(new Object[]{i.getIdInventario(), i.getNombreProducto(), i.getStockActual(), i.getFechaRegistro(), i.getFechaSalida()});
        tblInventario.setModel(m);
    }
    private void btnMosificarInventarioActionPerformed(java.awt.event.ActionEvent e) {
        int r = tblInventario.getSelectedRow();
        if (r >= 0) {
            String val = JOptionPane.showInputDialog("Nuevo Stock:", tblInventario.getValueAt(r, 2));
            if (val != null) { new InventarioDAO().update((int)tblInventario.getValueAt(r, 0), Integer.parseInt(val)); btnMostrarTodoActionPerformed(e); }
        }
    }

    // --- CLIENTES ---
    private void btnMostrarTodoClientesActionPerformed(java.awt.event.ActionEvent e) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "DNI", "Nombre", "Tel", "Correo"}, 0);
        for (Cliente c : new ClienteDAO().getAll()) m.addRow(new Object[]{c.getIdCliente(), c.getDni(), c.getNombre(), c.getTelefono(), c.getCorreo()});
        tblClientes.setModel(m);
    }
    private void btnBuscarNombreActionPerformed(java.awt.event.ActionEvent e) {
        DefaultTableModel m = new DefaultTableModel(new String[]{"ID", "DNI", "Nombre", "Tel", "Correo"}, 0);
        for (Cliente c : new ClienteDAO().searchByName(txtBuscarCliente.getText())) m.addRow(new Object[]{c.getIdCliente(), c.getDni(), c.getNombre(), c.getTelefono(), c.getCorreo()});
        tblClientes.setModel(m);
    }
    private void btnModificarClientesActionPerformed(java.awt.event.ActionEvent e) {
        int r = tblClientes.getSelectedRow();
        if (r >= 0) {
            int id = (int)tblClientes.getValueAt(r, 0);
            String dni = JOptionPane.showInputDialog("DNI:", tblClientes.getValueAt(r, 1));
            String nom = JOptionPane.showInputDialog("Nombre:", tblClientes.getValueAt(r, 2));
            String tel = JOptionPane.showInputDialog("Tel:", tblClientes.getValueAt(r, 3));
            String cor = JOptionPane.showInputDialog("Correo:", tblClientes.getValueAt(r, 4));
            if (dni != null) { Cliente c = new Cliente(); c.setIdCliente(id); c.setDni(dni); c.setNombre(nom); c.setTelefono(tel); c.setCorreo(cor); new ClienteDAO().update(c); btnMostrarTodoClientesActionPerformed(e); }
        }
    }

    // --- COMBOS ---
    private void cargarComboRoles() { cmbRol.removeAllItems(); for (Rol r : new RolDAO().getAll()) cmbRol.addItem(r); }
    private void cargarComboProveedores() { cmbProveedor.removeAllItems(); for (Proveedor p : new ProveedorDAO().getAll()) cmbProveedor.addItem(p); }
    private void cargarComboCategorias() { cmbCategoria.removeAllItems(); for (Categoria c : new CategoriaDAO().getAll()) cmbCategoria.addItem(c); }
    private void cargarComboBodegas() { cmbBodega.removeAllItems(); for (Bodega b : new BodegaDAO().getAll()) cmbBodega.addItem(b); }

    public static void main(String args[]) { java.awt.EventQueue.invokeLater(() -> new MenuPrincipal().setVisible(true)); }

    // Variables
    private javax.swing.JButton btnAniadir, btnAniadirProducto, btnBuscarIDoNombre, btnBuscarNombre, btnEliminar, btnEliminarProducto, btnModificar, btnModificarClientes, btnModificarInventario, btnModificarProductos, btnMostrarTodo, btnMostrarTodoClientes, btnRecargar, btnRecargarProducto, btnIrAVentas, btnNuevoCliente;
    private javax.swing.JButton btnACate, btnMCate, btnECate, btnRCate, btnABod, btnMBod, btnEBod, btnRBod, btnAProv, btnMProv, btnEProv, btnRProv, btnARol, btnMRol, btnERol, btnRRol;
    private javax.swing.JComboBox<model.Rol> cmbRol;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<model.Proveedor> cmbProveedor;
    private javax.swing.JComboBox<model.Categoria> cmbCategoria;
    private javax.swing.JComboBox<model.Bodega> cmbBodega;
    private javax.swing.JPanel pnlUsuarios, pnlProductos, pnlInventario, pnlClientes, pnlCategorias, pnlBodegas, pnlProveedores, pnlRoles;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblUsuarios, tblProductos, tblInventario, tblClientes, tblCate, tblBod, tblProv, tblRol;
    private javax.swing.JTextField txtNombre, txtCorreo, txtNombreProducto, txtPrecio, txtCantidad, txtBuscar, txtBuscarCliente, txtNomCate, txtDescCate, txtNomBod, txtDirBod, txtNomProv, txtTelProv, txtDirProv, txtRtnProv, txtNomRol, txtDescRol, txtFechaVencimiento;
    private javax.swing.JPasswordField txtContrasenia;
}
