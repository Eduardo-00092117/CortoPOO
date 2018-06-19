package vista;

import dao.FiltroDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author UCA
 */
public class Consulta extends JFrame {
    
    public JLabel lblCarnet, lblNombre, lblEdad, lblUniversidad, lblApellidos, lblEstado;

    public JTextField carnet, nombre, edad, apellido;
    
    public JComboBox universidad;

    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Alumno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCarnet);
        container.add(lblNombre);
        container.add(lblApellidos);
        container.add(lblEdad);
        container.add(lblUniversidad);
        container.add(lblEstado);
        container.add(carnet);
        container.add(nombre);
        container.add(apellido);
        container.add(edad);
        container.add(si);
        container.add(no);
        container.add(universidad);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(650, 650);
        eventos();
    }

    public final void agregarLabels() {
        lblCarnet = new JLabel("Carnet:");
        lblNombre = new JLabel("Nombre:");
        lblEdad = new JLabel("Edad:");
        lblUniversidad = new JLabel("Universidad:");
        lblEstado = new JLabel("Estado:");
        lblApellidos   = new JLabel("Apellidos:");
        lblCarnet.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, ANCHOC, ALTOC);
        lblUniversidad.setBounds(10, 140, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 180, ANCHOC, ALTOC);
        lblApellidos.setBounds(300, 50, ANCHOC, ALTOC);
    }

    public final void formulario() {
        carnet = new JTextField();
        universidad = new JComboBox();
        edad = new JTextField();
        apellido = new JTextField();
        nombre = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        
        resultados = new JTable();
        
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();
        universidad.addItem("UCA");
        universidad.addItem("UDB");
        universidad.addItem("UFG");
        universidad.addItem("UGB");
        
        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        carnet.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 60, ANCHOC, ALTOC);
        edad.setBounds(140, 100, ANCHOC, ALTOC);
        universidad.setBounds(140, 140, 100, ALTOC);
        apellido.setBounds(400, 50, 100, ALTOC);
        si.setBounds(140, 180, 50, ALTOC);
        no.setBounds(210, 180, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        
        resultados = new JTable() {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 350, 600, 300);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    case 4:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Carnet");
        tm.addColumn("Nombres");
        tm.addColumn("Apellidos");
        tm.addColumn("Edad");
        tm.addColumn("Universidad");
        tm.addColumn("Estado");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCarnet(), fi.getNombres(), fi.getApellidos(), fi.getEdad(), fi.getUniversidad(), fi.getEstado()});
        }

        resultados.setModel(tm);
    }

    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                //String nombre, String apellido, String universidad, int edad, boolean estado, int carnet
                Filtro f = new Filtro(nombre.getText(), apellido.getText(), universidad.getSelectedItem().toString(), Integer.parseInt(edad.getText()), true, Integer.parseInt(carnet.getText()));

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado con existo");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(nombre.getText(), apellido.getText(), universidad.getSelectedItem().toString(), Integer.parseInt(edad.getText()), true, Integer.parseInt(carnet.getText()));

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificacion con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro");

                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(Integer.parseInt(carnet.getText()))) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(Integer.parseInt(carnet.getText()));
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                } else {
                    carnet.setText(String.valueOf(f.getCarnet()));
                    edad.setText(String.valueOf(f.getEdad()));
                    nombre.setText(f.getNombres());
                    apellido.setText(f.getApellidos());
                    universidad.setSelectedItem(f.getUniversidad());

                    if (f.getEstado()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        
        /*resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    carnet.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    marca.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    stock.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
                    if (resultados.getValueAt(resultados.getSelectedRow(), 3).toString() == "false") {
                        sino.setSelected(false);
                    } else {
                        sino.setSelected(true);
                    }
                }
            }
        });*/
    }

    public void limpiarCampos() {
        carnet.setText("");
        universidad.setSelectedItem("UCA");
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        si.setSelected(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
}
